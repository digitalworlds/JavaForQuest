package edu.ufl.digitalworlds.j4q.activities;

import android.graphics.PixelFormat;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public abstract class GLActivity extends AppCompatActivity implements GameEngineActivity,GLSurfaceView.Renderer{

    public GameEngineScene scene;

    private GLSurfaceView surfaceView;
    public GLSurfaceView getSurfaceView(){return surfaceView;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout layout=new ConstraintLayout(this);

        surfaceView=new GLSurfaceView(this);
        surfaceView.layout(0,0,0,0);
        layout.addView(surfaceView);

        setContentView(layout);

        surfaceView.setEGLContextClientVersion(3);
        surfaceView.setZOrderOnTop(true);
        surfaceView.setEGLConfigChooser(8,8,8,8,16,0);
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);


        // my_renderer.showCamera(findViewById(R.id.textureView));

        surfaceView.setRenderer(this);

        scene=new GameEngineScene(this);
    }





    @Override
    public void onDestroy(){
        super.onDestroy();
        destroy();
    }

    public void destroy(){

    }




    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);

        float[] mProjectionMatrix=new float[16];
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio*0.1f, ratio*0.1f, -0.1f, 0.1f, 0.1f, 1024);

        scene.setupProjection(mProjectionMatrix);

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
