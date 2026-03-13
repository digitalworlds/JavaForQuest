package edu.ufl.j4q;


import j4q.J4Q;
import j4q.input.J4QLeftController;
import j4q.models.GameObject;
import j4q.models.Spaceship;

public class LeftSpaceship extends GameObject {

    GameObject spaceship;
    J4QLeftController leftController;

    public LeftSpaceship(){
        spaceship=new Spaceship(3);
        appendChild(spaceship);
        leftController=J4Q.getInputDevice(J4QLeftController.class);
    }

    @Override
    public void Update(){

        if(leftController.squeeze.changedSinceLastSync && leftController.squeeze.currentState){
            new Spaceship((int)Math.floor(Math.random()*Spaceship.TYPES),spaceship);
        }

        transform.reset();
        transform.translate(leftController.aim.position);
        transform.rotate(leftController.aim.orientation);
        transform.scale(0.2f);
    }
}
