package j4q.shaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

import j4q.J4Q;

/**
 * Class for managing OpenGL ES textures, including loading from assets and resources, uploading to GPU, and binding to texture units.
 */
public class Texture {

    /**
     * The OpenGL ES texture handle.
     */
    public int gles_handle = 0;
    /**
     * The texture unit slot.
     */
    public int slot;

    /**
     * Constructs an empty OpenGL ES texture and generates a handle.
     */
    public Texture(){
        final int[] textureHandle = new int[1];
        GLES30.glGenTextures(1, textureHandle, 0);
        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error loading texture.");
        }
        gles_handle =textureHandle[0];
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle[0]);
    }

    /**
     * Constructs a texture and loads it from an asset file.
     * @param filename The asset filename.
     */
    public Texture(final String filename){
        this();
        load(J4Q.activity,filename);
    }

    /**
     * Constructs a texture and loads it from a resource ID.
     * @param context The Android context.
     * @param resourceId The resource ID.
     */
    public Texture(final Context context, final int resourceId){
        this();
        load(context,resourceId);
    }

    /**
     * Constructs a texture and loads it from an asset file with a given context.
     * @param context The Android context.
     * @param filename The asset filename.
     */
    public Texture(final Context context, final String filename){
        this();
        load(context,filename);
    }

    /**
     * Loads a texture from an asset file.
     * @param context The Android context.
     * @param filename The asset filename.
     */
    public void load(final Context context, final String filename)
    {
        if (gles_handle == 0)return;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        try {
            InputStream is = context.getAssets().open(filename);
            final Bitmap bitmap = BitmapFactory.decodeStream(is);
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, gles_handle);
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR_MIPMAP_LINEAR);
            loadToGPU(bitmap);
            bitmap.recycle();
        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading texture.");
        }
    }

    /**
     * Uploads a bitmap to the GPU, flipping vertically before upload.
     * @param bitmap The bitmap to upload.
     */
    protected void loadToGPU(Bitmap bitmap){
        Matrix flip = new Matrix();
        flip.postScale(1f, -1f);
        Bitmap flipped = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), flip, true);
        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0,GLES30.GL_RGBA, flipped, 0);
        GLES30.glGenerateMipmap(GLES30.GL_TEXTURE_2D);
        flipped.recycle();
    }

    /**
     * Loads a texture from a resource ID.
     * @param context The Android context.
     * @param resourceId The resource ID.
     */
    public void load(final Context context, final int resourceId)
    {
        if (gles_handle == 0)return;
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, gles_handle);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR_MIPMAP_LINEAR);
        loadToGPU(bitmap);
        bitmap.recycle();
    }

    /**
     * Sets this texture as active in the specified texture unit slot.
     * @param slot The texture unit slot.
     */
    public void setActive(int slot){
        this.slot=slot;
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0+slot);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, gles_handle);
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
    }

}

