package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.activities.QuestActivity;
import edu.ufl.digitalworlds.j4q.models.Mesh;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.shaders.Texture;
import edu.ufl.digitalworlds.j4q.shaders.TextureShader;

public class MainActivity extends QuestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void Start(){
        //create a 3D model object
        Model square=new Model();

        //add a 3D mesh to the model
        square.mesh=new Mesh();

        //add vertices to the model
        square.mesh.setXYZ(new float[]{
                -0.5f,-0.5f,0, //vertex #0: lower left corner
                0.5f,-0.5f,0,  //vertex #1: lower right corner
                0.5f,0.5f,0,   //vertex #2: upper right corner
                -0.5f,0.5f,0   //vertex #3: upper left corner
        });

        //from the texture add pixel locations to the vertices
        square.mesh.setUV(new float[]{
                0,0,  //vertex #0: lower left pixel
                1,0,  //vertex #1: lower right pixel
                1,1,  //vertex #2: upper right pixel
                0,1,  //vertex #3: upper left pixel
        });

        //make a triangular mesh
        square.mesh.setTriangles(new short[]{
                0,1,2, //Make one triangle by connecting vertices #0,#1,#2
                0,2,3  //Make another triangle by connecting vertices #0,#2,#3
        });


        //add a shader to the model
        square.shader=new TextureShader();

        //add a texture to the shader
        ((TextureShader)square.shader).setTexture(new Texture("textures/box.png"));

        //add the 3D model to the scene
        scene.appendChild(square);

        //move the square 1 meter high and 1 meter along the z-axis (deep into the scene)
        square.transform.translate(0,1,-1);

        //Let's pick a blue shade for background
        scene.background(153/255f,	204/255f,	255/255f);
    }

    @Override
    public void Update() {}
}
