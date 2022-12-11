package edu.ufl.digitalworlds.j4q.shaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.opengl.GLES30;
import android.opengl.GLUtils;

public class Text extends Texture{

    Bitmap bitmap;
    Canvas canvas;
    Paint paint;

    public Text(int width, int height) {
        super();
        // Create a new bitmap with the specified width and height
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        // Create a new canvas to draw on the bitmap
        canvas = new Canvas(bitmap);
    }

    public void setText(String text){
        setText(text,Color.WHITE);
    }

    public void setText(String text, int red, int green, int blue){
        setText(text,Color.rgb(red,green,blue));
    }

    public void setText(String text, int color){
        if(paint==null) {
            // Set up the paint for drawing the text
            paint = new Paint();
            paint.setColor(color);
            paint.setTextSize(24);
        }

        setText(text,0,18,paint);
    }

    public void setText(String text, float x, float y, Paint paint){

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // Draw the text on the canvas
        canvas.drawText(text, x, y, paint);

        // Bind to the texture in OpenGL
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, gles_handle);
        loadToGPU(bitmap);
    }


}
