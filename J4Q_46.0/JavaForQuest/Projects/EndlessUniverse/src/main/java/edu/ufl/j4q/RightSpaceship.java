package edu.ufl.j4q;

import j4q.J4Q;
import j4q.input.J4QRightController;
import j4q.models.GameObject;
import j4q.models.Spaceship;

public class RightSpaceship extends GameObject {

    GameObject spaceship;

    J4QRightController rightController;

    public RightSpaceship(){
        spaceship=new Spaceship(0);
        appendChild(spaceship);
        rightController=J4Q.getInputDevice(J4QRightController.class);
    }

    @Override
    public void Update(){

        if(rightController.squeeze.changedSinceLastSync && rightController.squeeze.currentState){
            new Spaceship((int)Math.floor(Math.random()*Spaceship.TYPES),spaceship);
        }

        transform.reset();
        transform.translate(rightController.aim.position);
        transform.rotate(rightController.aim.orientation);
        transform.scale(0.2f);
    }
}
