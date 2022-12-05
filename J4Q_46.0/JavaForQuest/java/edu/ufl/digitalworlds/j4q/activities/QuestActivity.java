package edu.ufl.digitalworlds.j4q.activities;

import android.app.NativeActivity;
import android.opengl.GLES31;

import edu.ufl.digitalworlds.j4q.J4Q;

public abstract class QuestActivity extends NativeActivity implements GameEngineActivity {

    static{
        System.loadLibrary("xrcompositor");
    }

    public GameEngineScene scene;

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


        scene.update();
    }


    public int _setup(){
        J4Q.activity=this;

        scene=new GameEngineScene(this);

        scene.start();

        return 0;
    }

    public void _draw(int frameBufferWidth,int frameBufferHeight,float[] sceneMatrices,int eye){

        //Log.d("Example",""+eye);
        /*GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mSceneMatricesBuffer.put(sceneMatrices);

        Transform t=new Transform(sceneMatrices);
        mSceneMatricesBuffer.put(t.getNormalMatrix());

        mSceneMatricesBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 0,(16*2+9)*4,mSceneMatricesBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);*/

        GLES31.glViewport( 0, 0, frameBufferWidth, frameBufferHeight );
        GLES31.glScissor( 0, 0, frameBufferWidth, frameBufferHeight );

        float m[]=new float[16];
        System.arraycopy(sceneMatrices,16,m,0,16);
        scene.setupProjection(m);

        System.arraycopy(sceneMatrices,0,scene.view.matrix,0,16);

        scene.draw();
    }

}
