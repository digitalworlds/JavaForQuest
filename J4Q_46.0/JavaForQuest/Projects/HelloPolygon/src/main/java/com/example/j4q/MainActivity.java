package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.activities.QuestActivity;
import edu.ufl.digitalworlds.j4q.models.Mesh;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.shaders.ColorShader;

public class MainActivity extends QuestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void Start(){
        //create a 3D model object
        Model triangle=new Model();

        //add a 3D mesh to the model
        triangle.mesh=new Mesh();

        //add vertices to the model
        triangle.mesh.setXYZ(new float[]{
                -1,0,0, //vertex #0: lower left corner
                1,0,0,  //vertex #1: lower right corner
                0,1,0   //vertex #2: upper corner
        });

        //add colors to the vertices
        triangle.mesh.setColors(new float[]{
                1,0,0,  //vertex #0: red color
                0,1,0,  //vertex #1: green color
                0,0,1   //vertex #2: blue color
        });

        //make a triangular mesh
        triangle.mesh.setTriangles(new short[]{
                0,1,2  //Make one triangle by connecting vertices #0,#1,#2
        });

        //add a shader to the model
        triangle.shader=new ColorShader();

        //add the 3D model to the scene
        scene.appendChild(triangle);

        //move the triangle 1 meter along the z-axis (deep into the scene)
        triangle.transform.translate(0,0,-1);

        //Let's pick a blue shade for background
        scene.background(153/255f,	204/255f,	255/255f);
    }

    @Override
    public void Update() {}
}
