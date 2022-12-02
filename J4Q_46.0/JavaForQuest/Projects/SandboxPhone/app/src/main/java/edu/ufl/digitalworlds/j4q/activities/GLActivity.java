package edu.ufl.digitalworlds.j4q.activities;

import android.graphics.PixelFormat;
import android.opengl.GLES30;
import android.opengl.GLES31;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Date;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.ufl.digitalworlds.j4q.geometry.Transform;
import edu.ufl.digitalworlds.j4q.models.Model;

public abstract class GLActivity extends AppCompatActivity implements GLSurfaceView.Renderer{

    public Model root=new Model();
    public void appendChild(Model model){root.appendChild(model);}
    public void removeChild(Model model){root.removeChild(model);}

    public float[] mProjectionMatrix = new float[16];
    public float[] mNormalMatrix=new float[9];
    public float[] lightDir=new float[3];
    public FloatBuffer mProjectionMatrixBuffer;
    public FloatBuffer mViewMatrixBuffer;
    public FloatBuffer mNormalMatrixBuffer;
    public FloatBuffer mLightDirBuffer;
    public Transform view;

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

        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                mProjectionMatrix.length * 4);
        bb.order(ByteOrder.nativeOrder());
        mProjectionMatrixBuffer = bb.asFloatBuffer();

        ByteBuffer bb4 = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                mNormalMatrix.length * 4);
        bb.order(ByteOrder.nativeOrder());
        mNormalMatrixBuffer = bb.asFloatBuffer();

        view=new Transform();

        ByteBuffer bb2 = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                view.matrix.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        mViewMatrixBuffer = bb2.asFloatBuffer();

        ByteBuffer bb3 = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                lightDir.length * 4);
        bb3.order(ByteOrder.nativeOrder());
        mLightDirBuffer = bb3.asFloatBuffer();

    }





    @Override
    public void onDestroy(){
        super.onDestroy();
        destroy();
    }

    public void destroy(){

    }


    public void background(float r,float g,float b){
        GLES30.glClearColor(r, g, b, 1.0f);
    }

    public void setupProjection(int width, int height){
        float ratio = (float) width / height;
        Matrix.frustumM(mProjectionMatrix, 0, -ratio*0.1f, ratio*0.1f, -0.1f, 0.1f, 0.1f, 1024);
    }

    public void setupView(){
        view.identity();
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);

        setupProjection(width,height);

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method

        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mProjectionMatrixBuffer.put(mProjectionMatrix);
        mProjectionMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 16*4,16*4,mProjectionMatrixBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);
    }


    abstract public void Start();
    abstract public void Update();

    public int sceneMatricesBuffer;
    private long start_time;
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);
        int[] i=new int[1];
        GLES30.glGenBuffers(1,i,0);
        sceneMatricesBuffer=i[0];
        GLES30.glBindBuffer(GLES30.GL_UNIFORM_BUFFER, i[0]);
        GLES30.glBufferData(GLES30.GL_UNIFORM_BUFFER, (16*2+9+3)*4, null, GLES30.GL_STATIC_DRAW);
        GLES30.glBindBuffer(GLES30.GL_UNIFORM_BUFFER, 0);

        GLES30.glBindBufferRange(GLES30.GL_UNIFORM_BUFFER, 0,//map to index 0
                sceneMatricesBuffer, 0, (16*2+9+3)*4);


        lightDir[0]=0;
        lightDir[1]=0;
        lightDir[2]=1;
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mLightDirBuffer.put(lightDir);
        mLightDirBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, (16*2+9)*4,3*4,mLightDirBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);

        Start();
        start_time=new Date().getTime();
    }


    public void setLightDir(float x, float y, float z){
        float mag=(float)Math.sqrt(x*x+y*y+z*z);

        lightDir[0]=-x;
        lightDir[1]=-y;
        lightDir[2]=-z;
        if(mag>0){
            lightDir[0]/=mag;
            lightDir[1]/=mag;
            lightDir[2]/=mag;
        }
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mLightDirBuffer.put(lightDir);
        mLightDirBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, (16*2+9)*4,3*4,mLightDirBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);
    }

    double lastTime=0;
    float elapsedDisplayTime=0;
    public float getElapsedDisplayTime(){return elapsedDisplayTime;}
    float perSec=0;
    public float perSec(){return perSec;}
    public void onDrawFrame(GL10 unused) {

        setupView();

        elapsedDisplayTime=(new Date().getTime()-start_time)/1000f;
        perSec=(float)(elapsedDisplayTime-lastTime);
        lastTime=elapsedDisplayTime;

        root.updateAnimation();
        Update();
        root.updateGlobalPositions(false);

        //update view matrix
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mViewMatrixBuffer.put(view.matrix);
        mViewMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 0,16*4,mViewMatrixBuffer);


        Transform t=new Transform(view.matrix);
        mNormalMatrixBuffer.put(t.getNormalMatrix());
        mNormalMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 16*2*4,9*4,mNormalMatrixBuffer);

        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);

        GLES31.glClear( GLES31.GL_COLOR_BUFFER_BIT | GLES31.GL_DEPTH_BUFFER_BIT );
        root.draw();


    }



}
