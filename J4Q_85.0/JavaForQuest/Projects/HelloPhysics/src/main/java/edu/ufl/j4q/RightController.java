package edu.ufl.j4q;

import javax.vecmath.Vector3f;

import j4q.J4Q;
import j4q.geometry.Transform;
import j4q.geometry.Vector3;
import j4q.input.J4QRightController;
import j4q.models.GameObject;
import j4q.models.ObjectMaker;
import j4q.shaders.ColorPhongShader;

public class RightController extends GameObject {


    J4QRightController rightController;

    public RightController(){
        ObjectMaker om=new ObjectMaker();
        om.color(0.5f,0.5f,0.5f);
        om.cylinder(0.1f,0.02f,0.1f);
        om.translate(0,-0.05f,0.05f);
        om.rotateX(-45);
        om.sphere(0.04f,0.15f,0.06f);
        om.identity();
        om.translate(0,0,-2.5f);
        om.color(1,0,0);
        om.cylinderZ(0.002f,0.002f,5,8);
        om.flushModel(this,true,false,true);
        addComponent(new ColorPhongShader());

        rightController=J4Q.getInputDevice(J4QRightController.class);
    }

    GameObject holding_object=null;
    Vector3 velocity;

    Transform initTransform;

    @Override
    public void Update(){

        if(!rightController.active){
            hide();
            return;
        }else{
            if(!isShown()) show();
        }

        transform.reset();
        transform.translate(rightController.aim.position);
        transform.rotate(rightController.aim.orientation);



        if(rightController.trigger.changedSinceLastSync){
            //pressed
            if(rightController.trigger.currentState) {
                GameObject o = rightController.pickObject();
                if (o != null && o.rigidBody != null) {
                    holding_object=o;

                    initTransform=new Transform(holding_object.transform.multiplyLeft(transform.getInverseMatrix()));
                    holding_object.rigidBody.setWorldTransform(holding_object);
                }
            }
            //released
            else{
                if(holding_object!=null) {
                    holding_object.rigidBody.getBody().setAngularVelocity(new Vector3f(0,0,0));
                    holding_object.rigidBody.getBody().setLinearVelocity(new Vector3f(velocity.x,velocity.y,velocity.z));
                    holding_object = null;
                }
            }


        }

        if(holding_object!=null){

            Vector3 p1=holding_object.transform.getPosition();

            holding_object.transform.reset(initTransform).multiplyLeft(transform.matrix);

            Vector3 p2=holding_object.transform.getPosition();
            velocity=p2.subtract(p1).scale(1f/J4Q.perSec());


            holding_object.rigidBody.setWorldTransform(holding_object);
        }



    }
}
