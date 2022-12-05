package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.activities.QuestActivity;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.ObjectMaker;

public class MainActivity extends QuestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void Start(){

        //create an instance of ObjectMaker
        //ObjectMaker is a utility class that composes 3D models our of primitive shapes
        ObjectMaker om=new ObjectMaker();

        //First we will make a green square for the grass
        om.color(0,0.7f,0);//pick a green color
        om.translate(0,-0.25f,0);//move a little down
        om.rotateX(-90);//rotate 90 degrees
        om.rectangle(6,6);//put a 6 meters x 6 meters square

        //Then we will make a house
        om.identity();//reset the coordinate system (i.e. go back to the beginning)
        om.color(1,1,1);//pick white color
        om.box(0.5f,0.5f,0.5f);//put a 0.5meter cube

        //red pyramid for roof
        om.translate(0,0.25f,0);//go a little up
        om.color(1,0,0);//pick red color
        om.pyramid(0.55f,0.45f,0.55f); //put a pyramid

        //gray chimney
        om.translate(-0.15f,0.25f,0);//go a little to the side and higher
        om.color(0.5f,0.5f,0.5f);//pick gray color
        om.cylinderY(0.1f,0.4f,0.1f,8);//put a cylinder



        //add several trees around the house
        for(int i=0;i<30;i++) {
            //pick a random spot around the house
            float x=(float)Math.random()*2-1; x=Math.signum(x)+x*1.5f;
            float z=(float)Math.random()*2-1; z=Math.signum(z)+z*1.5f;

            om.identity();//reset the coordinate system
            om.translate(x, 0, z);//go to the randomly selected spot

            om.color(101 / 255f, 67 / 255f, 33 / 255f);//pick brown color
            om.cylinderY(0.15f, 0.5f, 0.15f, 8);//put a cylinder for the trunk
            om.translate(0, 0.5f, 0);//go a little higher
            om.color(0, 0.5f, 0);//pick a dark green color
            om.sphere(0.5f, 1, 0.5f,16);//put an ellipsoid
        }

        //at the end flush the model that we made
        Model house=om.flushShadedColoredModel();
        //and append it to the scene
        scene.appendChild(house);

        //move the model 5 meters deep into the scene
        house.transform.translate(0,0,-5);
        //and rotate it by 10 degrees so that we can see a little the side of the house
        house.transform.rotateY(10);

        //Let's pick a blue shade for background
        scene.background(153/255f,	204/255f,	255/255f);

        //set light direction forward and downwards
        scene.setLightDir(0,-1,-1);

    }

    @Override
    public void Update() {}
}
