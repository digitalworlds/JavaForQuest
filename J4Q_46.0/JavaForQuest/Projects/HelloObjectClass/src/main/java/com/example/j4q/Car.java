package com.example.j4q;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.ObjectMaker;

public class Car extends Model {

    public Car(){

        ObjectMaker om=new ObjectMaker();

        //here we make a car out of several trapezoids and cylinders

        om.color(1,0,0);
        om.rotateY(90);
        om.translate(0,-0.145f,0);

        //we make the main body of the car
        om.save();
        om.trapezoid(0.4f,0.1f,0.2f,0.35f,0.2f);
        om.translate(0,0.1f,0);
        om.trapezoid(0.25f,0.1f,0.18f,0.10f,0.15f);
        om.color(153/255f,	204/255f,	255/255f);
        om.trapezoid(0.255f,0.095f,0.17f,0.105f,0.14f);
        om.trapezoid(0.23f,0.085f,0.185f,0.09f,0.155f);
        om.restore();

        //we add 4 wheels
        om.save();
        om.color(0.1f,0.1f,0.1f);
        om.translate(-0.12f,-0.05f,0.08f);
        om.cylinderZ(0.1f,0.1f,0.05f,16);
        om.translate(0.24f,0,0);
        om.cylinderZ(0.1f,0.1f,0.05f,16);
        om.translate(0,0,-0.16f);
        om.cylinderZ(0.1f,0.1f,0.05f,16);
        om.translate(-0.24f,0,0);
        om.cylinderZ(0.1f,0.1f,0.05f,16);
        om.restore();

        //we add two cylinders for the lights
        om.color(1,1,0.2f);
        om.translate(0,0,-0.05f);
        om.cylinderX(0.4f,0.05f,0.05f,16);
        om.translate(0,0,0.1f);
        om.cylinderX(0.4f,0.05f,0.05f,16);

        //at the end we flush the result into this model
        om.flushShadedColoredModel(this);

    }

    float z=0;//this will be the location z of the car

    public void Update(){
        //we move the car by 0.5 meter ber second
        z+=0.5* J4Q.perSec();
        //if the car comes too close we reset the animation at -3
        if(z>3)z=-3;

        //we reset the transformation
        transform.identity();
        //and we move the car to the new position
        transform.translate(0.5f,0,z);
    }
}
