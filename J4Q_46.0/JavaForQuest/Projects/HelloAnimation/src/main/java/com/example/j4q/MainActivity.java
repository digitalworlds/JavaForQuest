package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.J4Q;
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

    Model cube; //we define the cube variable here so that we can reference it inside update()

    public void Start(){
        //create a 3D model object
        cube=new Model();

        //add a 3D mesh to the model
        cube.mesh=new Mesh();

        //add vertices to the model to make a cube.
        //a cube has only 8 vertices, but here we will define them seperately for each face
        //in order to set different orientations (normals) for shading purposes.
        //so, in total we will define 24 vertices.
        cube.mesh.setXYZ(new float[]{
                -0.5f,0.5f,0.5f, //vertices of the front face
                0.5f,0.5f,0.5f,
                -0.5f,-0.5f,0.5f,
                0.5f,-0.5f,0.5f,

                -0.5f,0.5f,-0.5f, //vertices of the back face
                0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,

                0.5f,0.5f,0.5f, //vertices of the right face
                0.5f,-0.5f,0.5f,
                0.5f,0.5f,-0.5f,
                0.5f,-0.5f,-0.5f,

                -0.5f,0.5f,0.5f, //vertices of the left face
                -0.5f,-0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                -0.5f,-0.5f,-0.5f,

                -0.5f,0.5f,0.5f,//vertices of the top face
                0.5f,0.5f,0.5f,
                -0.5f,0.5f,-0.5f,
                0.5f,0.5f,-0.5f,

                -0.5f,-0.5f,0.5f,//vertices of the bottom face
                0.5f,-0.5f,0.5f,
                -0.5f,-0.5f,-0.5f,
                0.5f,-0.5f,-0.5f
        });

        //for each vertex we define here its orientation (i.e. the normal vector)
        cube.mesh.setNormals(new float[]{
                0,0,1,//normals of the front face (orientation towards us)
                0,0,1,
                0,0,1,
                0,0,1,

                0,0,-1,//normals of the back face (orientation towards deep inside the scene)
                0,0,-1,
                0,0,-1,
                0,0,-1,

                1,0,0,//normals of the right face (orientation towards the right)
                1,0,0,
                1,0,0,
                1,0,0,

                -1,0,0,//normals of the left face (orientation towards the left)
                -1,0,0,
                -1,0,0,
                -1,0,0,

                0,1,0,//normals of the top face (orientation towards the top)
                0,1,0,
                0,1,0,
                0,1,0,

                0,-1,0,//normals of the bottom face (orientation towards the bottom)
                0,-1,0,
                0,-1,0,
                0,-1,0
        });

        //from the texture add pixel locations to the vertices.
        //here we map the whole texture on each face of the cube.
        cube.mesh.setUV(new float[]{
                0,1,//the pixels of the front face
                1,1,
                0,0,
                1,0,

                1,1,//the pixels of the back face
                0,1,
                1,0,
                0,0,

                0,1,//the pixels of the right face
                0,0,
                1,1,
                1,0,

                1,1,//the pixels of the left face
                1,0,
                0,1,
                0,0,

                0,0,//the pixels of the top face
                1,0,
                0,1,
                1,1,

                1,0,//the pixels of the bottom face
                0,0,
                1,1,
                0,1
        });

        //make a triangular mesh
        cube.mesh.setTriangles(new short[]{
                0,2,1,//two triangles for the front face
                1,2,3,

                4,5,6,//two triangles for the back face
                6,5,7,

                9,11,8,//two triangles for the right face
                8,11,10,

                13,12,15,//two triangles for the left face
                15,12,14,

                16,17,18,//two triangles for the top face
                18,17,19,

                21,20,22,//two triangles for the bottom face
                21,22,23
        });


        //add a shader to the model
        cube.shader=new TextureShader();

        //add a texture to the shader
        ((TextureShader)cube.shader).setTexture(new Texture("textures/box.png"));

        //add the 3D model to the scene
        appendChild(cube);

        //move the cube 1 meter high and 1.5 meters along the z-axis (deep into the scene)
        cube.transform.translate(0,1,-1.5f);

        //Let's pick a blue shade for background
        background(153/255f,	204/255f,	255/255f);
    }

    @Override
    public void Update() {

        //we rotate the cube 2 degrees per second around the Y axis
        cube.transform.rotateY(2* J4Q.perSec());

        //we rotate the cube 2 degrees per second around the X axis
        cube.transform.rotateX(2* J4Q.perSec());
    }
}
