package j4q.input;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import j4q.shaders.ColorPickerShader;

/**
 * Implements object picking using an offscreen framebuffer and color encoding.
 * <p>
 * The {@code ObjectPicker} class manages OpenGL resources for picking objects in a rendered scene
 * by encoding object IDs into colors and reading pixels from the framebuffer.
 * </p>
 */
public class ObjectPicker {

    /**
     * The shader used for color-based object picking.
     */
    public ColorPickerShader shader;

    /**
     * The width of the picking framebuffer.
     */
    private int width = 2;

    /**
     * The height of the picking framebuffer.
     */
    private int height = 2;

    /**
     * The OpenGL framebuffer object ID.
     */
    private int fboId;

    /**
     * The OpenGL color texture ID.
     */
    private int colorTexId;

    /**
     * The OpenGL depth renderbuffer ID.
     */
    private int depthRbId;

    /**
     * The pixel buffer used for reading object IDs.
     */
    private ByteBuffer pixelBuffer;

    /**
     * Constructs an ObjectPicker and initializes OpenGL resources.
     */
    public ObjectPicker() {
        shader=new ColorPickerShader();
        setupFBO();
        pixelBuffer = ByteBuffer.allocateDirect(4);
        pixelBuffer.order(ByteOrder.nativeOrder());
    }

    private void setupFBO() {

        int[] ids = new int[1];

        // -------- Create Color Texture --------
        GLES30.glGenTextures(1, ids, 0);
        colorTexId = ids[0];

        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, colorTexId);
        GLES30.glTexImage2D(
                GLES30.GL_TEXTURE_2D,
                0,
                GLES30.GL_RGBA,
                width,
                height,
                0,
                GLES30.GL_RGBA,
                GLES30.GL_UNSIGNED_BYTE,
                null
        );

        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,
                GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,
                GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);

        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,
                GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_CLAMP_TO_EDGE);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,
                GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_CLAMP_TO_EDGE);

        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, 0);

        // -------- Create Depth Renderbuffer --------
        GLES30.glGenRenderbuffers(1, ids, 0);
        depthRbId = ids[0];

        GLES30.glBindRenderbuffer(GLES30.GL_RENDERBUFFER, depthRbId);
        GLES30.glRenderbufferStorage(
                GLES30.GL_RENDERBUFFER,
                GLES30.GL_DEPTH_COMPONENT16,
                width,
                height
        );
        GLES30.glBindRenderbuffer(GLES30.GL_RENDERBUFFER, 0);

        // -------- Create FBO --------
        GLES30.glGenFramebuffers(1, ids, 0);
        fboId = ids[0];

        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, fboId);

        GLES30.glFramebufferTexture2D(
                GLES30.GL_FRAMEBUFFER,
                GLES30.GL_COLOR_ATTACHMENT0,
                GLES30.GL_TEXTURE_2D,
                colorTexId,
                0
        );

        GLES30.glFramebufferRenderbuffer(
                GLES30.GL_FRAMEBUFFER,
                GLES30.GL_DEPTH_ATTACHMENT,
                GLES30.GL_RENDERBUFFER,
                depthRbId
        );

        int status = GLES30.glCheckFramebufferStatus(GLES30.GL_FRAMEBUFFER);
        if (status != GLES30.GL_FRAMEBUFFER_COMPLETE) {
            throw new RuntimeException("ColorPicker FBO not complete: " + status);
        }

        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0);
    }

    // Call before rendering picking pass
    /**
     * Begins the picking pass by binding the framebuffer and clearing buffers.
     */
    public void begin() {
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, fboId);
        GLES30.glViewport(0, 0, width, height);

        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT);
    }

    // Call after picking
    /**
     * Ends the picking pass by unbinding the framebuffer.
     */
    public void end() {
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0);
    }

    // Reads pixel and returns object ID
    /**
     * Reads the pixel at the specified coordinates and returns the decoded object ID.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return The decoded object ID.
     */
    public int pick(int x, int y) {

        // Flip Y (Android touch origin is top-left)
        int realY = height - y;

        pixelBuffer.clear();

        GLES30.glReadPixels(
                x,
                realY,
                1,
                1,
                GLES30.GL_RGBA,
                GLES30.GL_UNSIGNED_BYTE,
                pixelBuffer
        );

        int r = pixelBuffer.get(0) & 0xFF;
        int g = pixelBuffer.get(1) & 0xFF;
        int b = pixelBuffer.get(2) & 0xFF;

        return decodeId(r, g, b);
    }



    private int decodeId(int r, int g, int b) {
        return r | (g << 8) | (b << 16);
    }

    /**
     * Releases OpenGL resources used by the picker.
     */
    public void release() {
        int[] ids = new int[1];

        ids[0] = colorTexId;
        GLES30.glDeleteTextures(1, ids, 0);

        ids[0] = depthRbId;
        GLES30.glDeleteRenderbuffers(1, ids, 0);

        ids[0] = fboId;
        GLES30.glDeleteFramebuffers(1, ids, 0);
    }

    /**
     * Sets the size of the picking framebuffer and reallocates resources as needed.
     * @param newWidth The new width.
     * @param newHeight The new height.
     */
    public void setSize(int newWidth, int newHeight) {

        if (newWidth == width && newHeight == height) {
            return; // nothing to change
        }


        width = newWidth;
        height = newHeight;


        // ----- Resize Color Texture -----
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, colorTexId);

        GLES30.glTexImage2D(
                GLES30.GL_TEXTURE_2D,
                0,
                GLES30.GL_RGBA,
                width,
                height,
                0,
                GLES30.GL_RGBA,
                GLES30.GL_UNSIGNED_BYTE,
                null
        );

        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, 0);

        // ----- Resize Depth Renderbuffer -----
        GLES30.glBindRenderbuffer(GLES30.GL_RENDERBUFFER, depthRbId);

        GLES30.glRenderbufferStorage(
                GLES30.GL_RENDERBUFFER,
                GLES30.GL_DEPTH_COMPONENT16,
                width,
                height
        );

        GLES30.glBindRenderbuffer(GLES30.GL_RENDERBUFFER, 0);

        // ----- Reallocate Pixel Buffer -----
        pixelBuffer = ByteBuffer.allocateDirect(4);
        pixelBuffer.order(ByteOrder.nativeOrder());

        // Optional: Verify framebuffer completeness again
        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, fboId);

        int status = GLES30.glCheckFramebufferStatus(GLES30.GL_FRAMEBUFFER);
        if (status != GLES30.GL_FRAMEBUFFER_COMPLETE) {
            throw new RuntimeException("ColorPicker FBO incomplete after resize: " + status);
        }

        GLES30.glBindFramebuffer(GLES30.GL_FRAMEBUFFER, 0);
    }
}


