package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.activities.ThirdEyeActivity;
import edu.ufl.digitalworlds.j4q.formats.OBJFile;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.shaders.ShadedTextureShader;
import edu.ufl.digitalworlds.j4q.shaders.Texture;

public class MainActivity extends ThirdEyeActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    Model m;

    public void Start(){
        //Welcome to the J4Q (Java For Quest) Project!

        //This method will run once in the beginning of the program.
        //Write here code that loads your assets and composes your scene.

        Texture t=new Texture("textures/box.png");
        OBJFile obj=new OBJFile("objects/BEE.obj");
        m=obj.getModel();
        m.setShader(new ShadedTextureShader());

        scene.appendChild(m);
        m.transform.translate(-0.5f,-0.5f,-10);
        scene.setLightDir(0,0,-1);
        scene.background(1,1,1);
    }

    @Override
    public void Update() {
        //This method will run in every frame.
        //Write here code that modifies your variables and animates your scene.
        m. transform.rotateX(10*J4Q.perSec());
        m. transform.rotateY(20*J4Q.perSec());
    }

}
