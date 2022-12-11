package edu.ufl.digitalworlds.j4q.shaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

import edu.ufl.digitalworlds.j4q.J4Q;

public class Texture {

    public int gles_handle =0;
    public int slot;

    public Texture(){
        final int[] textureHandle = new int[1];
        GLES30.glGenTextures(1, textureHandle, 0);
        if (textureHandle[0] == 0)
        {
            throw new RuntimeException("Error loading texture.");
        }
        gles_handle =textureHandle[0];

        // Bind to the texture in OpenGL
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, textureHandle[0]);
    }

    public Texture(final String filename){
        this();
        load(J4Q.activity,filename);
    }

    public Texture(final Context context, final int resourceId){
        this();
        load(context,resourceId);
    }

    public Texture(final Context context, final String filename){
        this();
        load(context,filename);
    }

    public void load(final Context context, final String filename)
    {
        if (gles_handle == 0)return;

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;   // No pre-scaling

        try {
            InputStream is = context.getAssets().open(filename);
            // Read in the resource
            final Bitmap bitmap = BitmapFactory.decodeStream(is);

            // Bind to the texture in OpenGL
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, gles_handle);

            // Set filtering
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
            GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR_MIPMAP_LINEAR);

            loadToGPU(bitmap);

            // Recycle the bitmap, since its data has been loaded into OpenGL.
            bitmap.recycle();

        }catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading texture.");
        }

    }

    protected void loadToGPU(Bitmap bitmap){
        // Flip the texture before uploading it to GPU.
        Matrix flip = new Matrix();
        flip.postScale(1f, -1f);
        Bitmap flipped = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), flip, true);

        // Load the bitmap into the bound texture.
        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D, 0,GLES30.GL_RGBA, flipped, 0);
        GLES30.glGenerateMipmap(GLES30.GL_TEXTURE_2D);
        flipped.recycle();
    }

    public void load(final Context context, final int resourceId)
    {
        if (gles_handle == 0)return;

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;   // No pre-scaling

        // Read in the resource
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);

        // Bind to the texture in OpenGL
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, gles_handle);

        // Set filtering
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
        GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR_MIPMAP_LINEAR);


        loadToGPU(bitmap);

        // Recycle the bitmap, since its data has been loaded into OpenGL.
        bitmap.recycle();
    }

    public void setActive(int slot){
        this.slot=slot;
        // Set the active texture unit to texture unit #slot.
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0+slot);
        // Bind the texture to this unit.
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, gles_handle);

        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
    }

}

