package j4q.controllers;

import android.opengl.Matrix;

import j4q.J4Q;
import j4q.activities.GameEngineScene;
import j4q.geometry.Transform;
import j4q.models.GameObject;

public class J4QController {

    public int id=0;

    private ObjectPicker objectPicker;

    public void setup(){
        if(objectPicker==null)objectPicker=new ObjectPicker();
        objectPicker.setSize(4,4);
    }

    public void capture(GameEngineScene scene){
        objectPicker.begin();

        /*GLES31.glViewport( 0, 0, frameBufferWidth, frameBufferHeight );
        GLES31.glScissor( 0, 0, frameBufferWidth, frameBufferHeight );

        float m[]=new float[16];
        System.arraycopy(sceneMatrices,16,m,0,16);
        scene.setupProjection(m);*/

        float[] ortho = new float[16];

        Matrix.orthoM(
                ortho, 0,
                -0.024f, 0.024f,   // left, right
                -0.024f, 0.024f,   // bottom, top
                0.1f, 1024.0f      // near, far
        );
        scene.setupProjection(ortho);


        Transform t=new Transform();
        t.translate(aim.position);
        t.rotate(aim.orientation);
        scene.setupView(t.getInverseMatrix());
        scene.root.draw(objectPicker.shader);
        id=objectPicker.pick(2,2);
        objectPicker.end();
    }

    public int pick(){
        return id;
    }

    public GameObject pickObject(){
        return J4Q.getObject(id);
    }

    public J4QJoystick joystick=new J4QJoystick();
    public J4QPressureButton trigger=new J4QPressureButton();
    public J4QPressureButton squeeze=new J4QPressureButton();
    public J4QPose aim=new J4QPose();
    public J4QPose grip=new J4QPose();
    public boolean active=false;
}
