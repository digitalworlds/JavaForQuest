package j4q.activities;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.TextureView;

import androidx.constraintlayout.widget.ConstraintLayout;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import j4q.J4Q;
import j4q.input.TouchScreen;
import j4q.physics.PhysicsEngine;

public abstract class GLActivity extends Activity implements GameEngineActivity,GLSurfaceView.Renderer{

    public GameEngineScene scene;
    private GLSurfaceView surfaceView;
    private TextureView textureView;
    public GLSurfaceView getSurfaceView(){return surfaceView;}
    public TextureView getTextureView(){return textureView;}

    private TouchScreen touchScreen;

    private float FOV=60;

    public void setFOV(float fov){
        this.FOV=fov;
        this.onSurfaceChanged(null,this.getScreenWidth(),this.getScreenHeight());
    }
    public float getFOV(){return this.FOV;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        touchScreen=new TouchScreen();
        J4Q.addInputDevice(touchScreen);

        ConstraintLayout layout=new ConstraintLayout(this);

        textureView=new TextureView(this);
        textureView.layout(0,0,0,0);
        layout.addView(textureView);

        surfaceView=new GLSurfaceView(this);
        surfaceView.layout(0,0,0,0);
        layout.addView(surfaceView);

        setContentView(layout);

        surfaceView.setEGLContextClientVersion(3);
        surfaceView.setZOrderOnTop(true);
        surfaceView.setEGLConfigChooser(8,8,8,8,16,0);
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);


        // my_renderer.showCamera(findViewById(R.id.textureView));
        J4Q.activity=this;
        J4Q.physicsEngine=new PhysicsEngine();
        scene=new GameEngineScene(this);
        surfaceView.setRenderer(this);
        surfaceView.setOnTouchListener(touchScreen);



    }



    @Override
    public void onDestroy(){
        super.onDestroy();
        destroy();
    }

    public void destroy(){

    }


    private int screen_width=0;
    private int screen_height=0;

    public int getScreenWidth(){return screen_width;}
    public int getScreenHeight(){return screen_height;}

    private static void perspectiveM(float[] matrix, float fov, float aspect, float near, float far) {

        float top = near * (float)Math.tan(Math.toRadians(fov / 2.0));

        float bottom = -top;
        float left = bottom * aspect;
        float right = top * aspect;

        Matrix.frustumM(
                matrix, 0,
                left, right,
                bottom, top,
                near, far
        );
    }
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        if(width==0 || height==0)return;
        screen_width=width;
        screen_height=height;
        GLES30.glViewport(0, 0, width, height);

        float[] mProjectionMatrix=new float[16];
        float ratio = (float) width / height;
        //Matrix.frustumM(mProjectionMatrix, 0, -ratio*0.1f, ratio*0.1f, -0.1f, 0.1f, 0.1f, 1024);
        Matrix.perspectiveM(mProjectionMatrix,0,this.FOV,ratio,0.1f,1024);

        scene.setupProjection(mProjectionMatrix);
        touchScreen.setup(width,height);
    }


    public void renderObjectPicker(){
        touchScreen.capture(scene);
    }

    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        scene.start();
    }

    public void onDrawFrame(GL10 unused) {
        scene.update();
        scene.view.identity();
        scene.draw();
    }



}
