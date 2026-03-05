package edu.ufl.j4q;

import j4q.J4Q;
import j4q.models.GameObject;
import j4q.models.Spaceship;

public class RightSpaceship extends GameObject {

    GameObject spaceship;



    public RightSpaceship(){
        spaceship=new Spaceship(0);
        appendChild(spaceship);
    }

    @Override
    public void Update(){

        if(J4Q.rightController.squeeze.changedSinceLastSync && J4Q.rightController.squeeze.currentState){
            new Spaceship((int)Math.floor(Math.random()*Spaceship.TYPES),spaceship);
        }

        transform.reset();
        transform.translate(J4Q.rightController.aim.position);
        transform.rotate(J4Q.rightController.aim.orientation);
        transform.scale(0.2f);
    }
}
