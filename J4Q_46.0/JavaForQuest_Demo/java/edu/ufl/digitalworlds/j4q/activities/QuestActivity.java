package edu.ufl.digitalworlds.j4q.activities;

import android.app.NativeActivity;
import android.opengl.GLES30;
import android.opengl.GLES31;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.geometry.Transform;
import edu.ufl.digitalworlds.j4q.models.Model;

public abstract class QuestActivity extends NativeActivity {

    static{
        System.loadLibrary("xrcompositor");
    }

    public abstract void Start();

    public Model scene=new Model();
    public void appendChild(Model model){scene.appendChild(model);}
    public void removeChild(Model model){scene.removeChild(model);}

    public void _simulate(double elapsedDisplayTime,
                          boolean toggleStateLeftTrigger_changedSinceLastSync,
                          boolean toggleStateLeftTrigger_currentState,
                          boolean toggleStateRightTrigger_changedSinceLastSync,
                          boolean toggleStateRightTrigger_currentState,
                          boolean toggleStateLeftSqueeze_changedSinceLastSync,
                          boolean toggleStateLeftSqueeze_currentState,
                          boolean toggleStateRightSqueeze_changedSinceLastSync,
                          boolean toggleStateRightSqueeze_currentState,
                          boolean toggleStateLeftX_changedSinceLastSync,
                          boolean toggleStateLeftX_currentState,
                          boolean toggleStateLeftY_changedSinceLastSync,
                          boolean toggleStateLeftY_currentState,
                          boolean toggleStateMenu_changedSinceLastSync,
                          boolean toggleStateMenu_currentState,
                          boolean toggleStateRightA_changedSinceLastSync,
                          boolean toggleStateRightA_currentState,
                          boolean toggleStateRightB_changedSinceLastSync,
                          boolean toggleStateRightB_currentState,
                          boolean thumbstickClickStateLeft_changedSinceLastSync,
                          boolean thumbstickClickStateLeft_currentState,
                          boolean thumbstickClickStateRight_changedSinceLastSync,
                          boolean thumbstickClickStateRight_currentState,
                          boolean moveXStateLeftSqueeze_changedSinceLastSync,
                          float moveXStateLeftSqueeze_currentState,
                          boolean moveXStateRightSqueeze_changedSinceLastSync,
                          float moveXStateRightSqueeze_currentState,
                          boolean moveYStateLeftTriggerValue_changedSinceLastSync,
                          float moveYStateLeftTriggerValue_currentState,
                          boolean moveYStateRightTriggerValue_changedSinceLastSync,
                          float moveYStateRightTriggerValue_currentState,
                          boolean moveJoystickStateLeft_changedSinceLastSync,
                          float moveJoystickStateLeft_currentState_x,
                          float moveJoystickStateLeft_currentState_y,
                          boolean moveJoystickStateRight_changedSinceLastSync,
                          float moveJoystickStateRight_currentState_x,
                          float moveJoystickStateRight_currentState_y,
                          boolean controller1_active,
                          float controller1_pose_x,
                          float controller1_pose_y,
                          float controller1_pose_z,
                          float controller1_orientation_w,
                          float controller1_orientation_x,
                          float controller1_orientation_y,
                          float controller1_orientation_z,
                          boolean controller2_active,
                          float controller2_pose_x,
                          float controller2_pose_y,
                          float controller2_pose_z,
                          float controller2_orientation_w,
                          float controller2_orientation_x,
                          float controller2_orientation_y,
                          float controller2_orientation_z,
                          boolean controller3_active,
                          float controller3_pose_x,
                          float controller3_pose_y,
                          float controller3_pose_z,
                          float controller3_orientation_w,
                          float controller3_orientation_x,
                          float controller3_orientation_y,
                          float controller3_orientation_z,
                          boolean controller4_active,
                          float controller4_pose_x,
                          float controller4_pose_y,
                          float controller4_pose_z,
                          float controller4_orientation_w,
                          float controller4_orientation_x,
                          float controller4_orientation_y,
                          float controller4_orientation_z){

        J4Q.leftController.trigger.changedSinceLastSync=toggleStateLeftTrigger_changedSinceLastSync;
        J4Q.leftController.trigger.currentState=toggleStateLeftTrigger_currentState;
        J4Q.leftController.trigger.currentValue=moveYStateLeftTriggerValue_currentState;
        J4Q.leftController.squeeze.changedSinceLastSync= toggleStateLeftSqueeze_changedSinceLastSync;
        J4Q.leftController.squeeze.currentState=toggleStateLeftSqueeze_currentState;
        J4Q.leftController.squeeze.currentValue= moveXStateLeftSqueeze_currentState;
        J4Q.leftController.buttonX.changedSinceLastSync=toggleStateLeftX_changedSinceLastSync;
        J4Q.leftController.buttonX.currentState=toggleStateLeftX_currentState;
        J4Q.leftController.buttonY.changedSinceLastSync=toggleStateLeftY_changedSinceLastSync;
        J4Q.leftController.buttonY.currentState=toggleStateLeftY_currentState;
        J4Q.leftController.buttonMenu.changedSinceLastSync=toggleStateMenu_changedSinceLastSync;
        J4Q.leftController.buttonMenu.currentState=toggleStateMenu_currentState;
        J4Q.leftController.joystick.changedSinceLastSync= thumbstickClickStateLeft_changedSinceLastSync;
        J4Q.leftController.joystick.currentValue[0]=moveJoystickStateLeft_currentState_x;
        J4Q.leftController.joystick.currentValue[1]=moveJoystickStateLeft_currentState_y;

        J4Q.leftController.active=controller1_active;
        J4Q.leftController.aim.position.x=controller1_pose_x;
        J4Q.leftController.aim.position.y=controller1_pose_y;
        J4Q.leftController.aim.position.z=controller1_pose_z;
        J4Q.leftController.aim.orientation.w=controller1_orientation_w;
        J4Q.leftController.aim.orientation.x=controller1_orientation_x;
        J4Q.leftController.aim.orientation.y=controller1_orientation_y;
        J4Q.leftController.aim.orientation.z=controller1_orientation_z;
        J4Q.leftController.grip.position.x=controller2_pose_x;
        J4Q.leftController.grip.position.y=controller2_pose_y;
        J4Q.leftController.grip.position.z=controller2_pose_z;
        J4Q.leftController.grip.orientation.w=controller2_orientation_w;
        J4Q.leftController.grip.orientation.x=controller2_orientation_x;
        J4Q.leftController.grip.orientation.y=controller2_orientation_y;
        J4Q.leftController.grip.orientation.z=controller2_orientation_z;

        J4Q.rightController.trigger.changedSinceLastSync=toggleStateRightTrigger_changedSinceLastSync;
        J4Q.rightController.trigger.currentState=toggleStateRightTrigger_currentState;
        J4Q.rightController.trigger.currentValue=moveYStateRightTriggerValue_currentState;
        J4Q.rightController.squeeze.changedSinceLastSync= toggleStateRightSqueeze_changedSinceLastSync;
        J4Q.rightController.squeeze.currentState=toggleStateRightSqueeze_currentState;
        J4Q.rightController.squeeze.currentValue= moveXStateRightSqueeze_currentState;
        J4Q.rightController.buttonA.changedSinceLastSync=toggleStateRightA_changedSinceLastSync;
        J4Q.rightController.buttonA.currentState=toggleStateRightA_currentState;
        J4Q.rightController.buttonB.changedSinceLastSync=toggleStateRightB_changedSinceLastSync;
        J4Q.rightController.buttonB.currentState=toggleStateRightB_currentState;
        J4Q.rightController.joystick.changedSinceLastSync= thumbstickClickStateRight_changedSinceLastSync;
        J4Q.rightController.joystick.currentValue[0]=moveJoystickStateRight_currentState_x;
        J4Q.rightController.joystick.currentValue[1]=moveJoystickStateRight_currentState_y;

        J4Q.rightController.active=controller3_active;
        J4Q.rightController.aim.position.x=controller3_pose_x;
        J4Q.rightController.aim.position.y=controller3_pose_y;
        J4Q.rightController.aim.position.z=controller3_pose_z;
        J4Q.rightController.aim.orientation.w=controller3_orientation_w;
        J4Q.rightController.aim.orientation.x=controller3_orientation_x;
        J4Q.rightController.aim.orientation.y=controller3_orientation_y;
        J4Q.rightController.aim.orientation.z=controller3_orientation_z;
        J4Q.rightController.grip.position.x=controller4_pose_x;
        J4Q.rightController.grip.position.y=controller4_pose_y;
        J4Q.rightController.grip.position.z=controller4_pose_z;
        J4Q.rightController.grip.orientation.w=controller4_orientation_w;
        J4Q.rightController.grip.orientation.x=controller4_orientation_x;
        J4Q.rightController.grip.orientation.y=controller4_orientation_y;
        J4Q.rightController.grip.orientation.z=controller4_orientation_z;


        _perSec=(float)(elapsedDisplayTime-_lastTime);
        _lastTime=elapsedDisplayTime;

        scene.updateAnimation();
        Update();
        scene.updateGlobalPositions(false);
    }

    double _lastTime=0;
    float _perSec=0;
    public float perSec(){return _perSec;}

    public abstract void Update();

    public float[] lightDir=new float[3];
    public FloatBuffer mLightDirBuffer;

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

    public int sceneMatricesBuffer;
    public FloatBuffer mSceneMatricesBuffer;

    public int _setup(){
        J4Q.activity=this;
        int[] i=new int[1];
        GLES30.glGenBuffers(1,i,0);
        sceneMatricesBuffer=i[0];
        GLES30.glBindBuffer(GLES30.GL_UNIFORM_BUFFER, i[0]);
        GLES30.glBufferData(GLES30.GL_UNIFORM_BUFFER, (16*2+9+3)*4, null, GLES30.GL_STATIC_DRAW);
        GLES30.glBindBuffer(GLES30.GL_UNIFORM_BUFFER, 0);

        GLES30.glBindBufferRange(GLES30.GL_UNIFORM_BUFFER, 0,//map to index 0
                sceneMatricesBuffer, 0, (16*2+9+3)*4);

        ByteBuffer bb2 = ByteBuffer.allocateDirect(
                // (# of matrix values * 4 bytes per float)
                (16*2+9) * 4);
        bb2.order(ByteOrder.nativeOrder());
        mSceneMatricesBuffer = bb2.asFloatBuffer();

        ByteBuffer bb3 = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                lightDir.length * 4);
        bb3.order(ByteOrder.nativeOrder());
        mLightDirBuffer = bb3.asFloatBuffer();

        setLightDir(0,0,-1);

        GLES31.glEnable( GLES31.GL_SCISSOR_TEST );
        GLES31.glDepthMask( true );
        GLES31.glEnable( GLES31.GL_DEPTH_TEST );
        GLES31.glDepthFunc( GLES31.GL_LEQUAL );
        GLES31.glEnable( GLES31.GL_CULL_FACE );
        GLES31.glCullFace( GLES31.GL_BACK);

        Start();
        return sceneMatricesBuffer;
    }

    public void _draw(int frameBufferWidth,int frameBufferHeight,float[] sceneMatrices,int eye){

        //Log.d("Example",""+eye);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mSceneMatricesBuffer.put(sceneMatrices);

        Transform t=new Transform(sceneMatrices);
        mSceneMatricesBuffer.put(t.getNormalMatrix());

        mSceneMatricesBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 0,(16*2+9)*4,mSceneMatricesBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);


        GLES31.glViewport( 0, 0, frameBufferWidth, frameBufferHeight );
        GLES31.glScissor( 0, 0, frameBufferWidth, frameBufferHeight );

        GLES31.glClear( GLES31.GL_COLOR_BUFFER_BIT | GLES31.GL_DEPTH_BUFFER_BIT );
        scene.draw();
    }

    public void background(float r,float g,float b){
        GLES30.glClearColor(r, g, b, 1.0f);
    }
}
