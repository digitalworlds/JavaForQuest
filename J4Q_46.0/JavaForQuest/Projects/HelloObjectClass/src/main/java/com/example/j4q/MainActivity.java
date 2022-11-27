package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.activities.QuestActivity;

public class MainActivity extends QuestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    HouseScene house_scene;

    public void Start(){

        //create a house scene object
        house_scene=new HouseScene();
        //and append it to the scene
        appendChild(house_scene);
        //move the model 5 meters deep into the scene
        house_scene.transform.translate(0,0,-5);

        //create a clouds object
        Clouds clouds=new Clouds();
        //and append it to the house scene
        house_scene.appendChild(clouds);

        //create a car object
        Car car=new Car();
        //and append it to the house scene
        house_scene.appendChild(car);


        //Let's pick a blue shade for background
        background(153/255f,	204/255f,	255/255f);

        //set light direction forward and downwards
        setLightDir(0,-1,-1);

    }

    @Override
    public void Update() {

        //rotate the house scene 5 degrees per second
        house_scene.transform.rotateY(5* J4Q.perSec());

    }
}
