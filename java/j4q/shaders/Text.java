package j4q.shaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.opengl.GLES30;

/**
 * Texture subclass for rendering text onto a bitmap and uploading to OpenGL ES.
 * <p>
 * Provides methods for setting text, color, and paint properties, and handles drawing and GPU upload.
 * </p>
 */
public class Text extends Texture{

    /**
     * The bitmap used for text rendering.
     */
    Bitmap bitmap;
    /**
     * The canvas for drawing onto the bitmap.
     */
    Canvas canvas;
    /**
     * The paint used for text styling.
     */
    Paint paint;

    /**
     * Constructs a Text texture with the specified width and height.
     * @param width The width of the bitmap.
     * @param height The height of the bitmap.
     */
    public Text(int width, int height) {
        super();
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    /**
     * Sets the text to render using the default color (white).
     * @param text The text string to render.
     */
    public void setText(String text){
        setText(text,Color.WHITE);
    }

    /**
     * Sets the text to render with a custom RGB color.
     * @param text The text string to render.
     * @param red Red component (0-255).
     * @param green Green component (0-255).
     * @param blue Blue component (0-255).
     */
    public void setText(String text, int red, int green, int blue){
        setText(text,Color.rgb(red,green,blue));
    }

    /**
     * Sets the text to render with a custom color.
     * @param text The text string to render.
     * @param color The color value.
     */
    public void setText(String text, int color){
        if(paint==null) {
            paint = new Paint();
            paint.setColor(color);
            paint.setTextSize(64);
        }
        setText(text,0,46,paint);
    }

    /**
     * Sets the text to render at a specific position with custom paint.
     * @param text The text string to render.
     * @param x The x position.
     * @param y The y position.
     * @param paint The Paint object for styling.
     */
    public void setText(String text, float x, float y, Paint paint){
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        canvas.drawText(text, x, y, paint);
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, gles_handle);
        loadToGPU(bitmap);
    }


}
