package edu.ufl.digitalworlds.j4q.activities;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Date;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.geometry.Transform;
import edu.ufl.digitalworlds.j4q.models.Model;

public class GameEngineScene {

    public Model root=new Model();
    public void appendChild(Model model){root.appendChild(model);}
    public void prependChild(Model model){root.prependChild(model);}
    public void removeChild(Model model){root.removeChild(model);}

    public float[] mNormalMatrix=new float[16];
    public float[] lightDir=new float[3];
    public FloatBuffer mProjectionMatrixBuffer;
    public FloatBuffer mViewMatrixBuffer;
    public FloatBuffer mNormalMatrixBuffer;
    public FloatBuffer mLightDirBuffer;
    public Transform view;

    public int sceneMatricesBuffer;
    private long start_time;
    double lastTime=0;
    float elapsedDisplayTime=0;
    public float getElapsedDisplayTime(){return elapsedDisplayTime;}
    float perSec=0;

    public float perSec(){return perSec;}

    private GameEngineActivity activity;

    public GameEngineScene(GameEngineActivity activity){
        this.activity=activity;
        J4Q.scene=this;
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                16 * 4);
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

    public void background(float r,float g,float b){
        GLES30.glClearColor(r, g, b, 1.0f);
    }

    public void setupProjection(float[] mProjectionMatrix){

        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mProjectionMatrixBuffer.put(mProjectionMatrix);
        mProjectionMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 16*4,16*4,mProjectionMatrixBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);
    }

    public void update(){
        elapsedDisplayTime=(new Date().getTime()-start_time)/1000f;
        perSec=(float)(elapsedDisplayTime-lastTime);
        lastTime=elapsedDisplayTime;

        root.updateAnimation();
        activity.Update();
        root.updateGlobalPositions(false);
    }

    public void draw(){
        //update view matrix
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mViewMatrixBuffer.put(view.matrix);
        mViewMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 0,16*4,mViewMatrixBuffer);

        Transform t=new Transform(view.matrix);
        mNormalMatrixBuffer.put(t.getNormalMatrix());
        mNormalMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 16*2*4,16*4,mNormalMatrixBuffer);

        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);

        GLES30.glClear( GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT );
        root.draw();
    }


    public void start(){
        GLES30.glEnable( GLES30.GL_SCISSOR_TEST );
        GLES30.glDepthMask( true );
        GLES30.glEnable( GLES30.GL_DEPTH_TEST );
        GLES30.glDepthFunc( GLES30.GL_LEQUAL );
        GLES30.glEnable( GLES30.GL_CULL_FACE );
        GLES30.glCullFace( GLES30.GL_BACK);

        GLES30.glEnable(GLES30.GL_BLEND);
        GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);

        int[] i=new int[1];
        GLES30.glGenBuffers(1,i,0);
        sceneMatricesBuffer=i[0];
        GLES30.glBindBuffer(GLES30.GL_UNIFORM_BUFFER, i[0]);
        GLES30.glBufferData(GLES30.GL_UNIFORM_BUFFER, (16*3+4)*4, null, GLES30.GL_STATIC_DRAW);
        GLES30.glBindBuffer(GLES30.GL_UNIFORM_BUFFER, 0);

        GLES30.glBindBufferRange(GLES30.GL_UNIFORM_BUFFER, 0,//map to index 0
                sceneMatricesBuffer, 0, (16*3+4)*4);


        setLightDir(0,0,-1);

        activity.Start();
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
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, (16*3)*4,3*4,mLightDirBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);
    }

}
