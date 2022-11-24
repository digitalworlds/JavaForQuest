package com.example.j4q;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.Spaceship;

public class RightSpaceship extends Model {

    Model spaceship;



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
