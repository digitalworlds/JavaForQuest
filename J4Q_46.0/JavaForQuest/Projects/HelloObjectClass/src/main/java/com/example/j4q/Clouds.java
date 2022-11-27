package com.example.j4q;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.ObjectMaker;

public class Clouds extends Model {

    public Clouds(){

        ObjectMaker om=new ObjectMaker();

        //Here we make a cloud out of 4 ellipsoids
        om.color(1,1,1);
        om.translate(0,2,0);
        om.sphere(0.5f,0.2f,0.2f,16);
        om.translate(0.3f,0.1f,0);
        om.sphere(0.5f,0.4f,0.2f,16);
        om.translate(0.3f,-0.2f,0);
        om.sphere(0.8f,0.2f,0.2f,16);
        om.translate(-0.1f,0.1f,0);
        om.sphere(0.3f,0.3f,0.2f,16);

        //we flush the result into this model
        om.flushShadedColoredModel(this);

    }

    float x=0;//this will be the location x of the cloud

    @Override
    public void Update(){

        //we move the cloud along x for 0.5 meter per second
        x+=0.5* J4Q.perSec();

        //if the cloud goes too far to the right we restart the animation at -3 meters
        if(x>3)x=-3;

        //we reset the transformation
        transform.identity();
        //and we move the cloud to the new position
        transform.translate(x,0,0);

    }

}
