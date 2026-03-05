package edu.ufl.j4q;


import j4q.J4Q;
import j4q.models.GameObject;
import j4q.models.Spaceship;

public class LeftSpaceship extends GameObject {

    GameObject spaceship;

    public LeftSpaceship(){
        spaceship=new Spaceship(3);
        appendChild(spaceship);
    }

    @Override
    public void Update(){

        if(J4Q.leftController.squeeze.changedSinceLastSync && J4Q.leftController.squeeze.currentState){
            new Spaceship((int)Math.floor(Math.random()*Spaceship.TYPES),spaceship);
        }

        transform.reset();
        transform.translate(J4Q.leftController.aim.position);
        transform.rotate(J4Q.leftController.aim.orientation);
        transform.scale(0.2f);
    }
}
