package com.example.j4q;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.Spaceship;

public class LeftSpaceship extends Model {

    Model spaceship;

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
