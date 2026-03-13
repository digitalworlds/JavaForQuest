package edu.ufl.j4q;

import android.os.Bundle;

import java.util.Random;

import j4q.activities.QuestActivity;
import j4q.geometry.Transform;
import j4q.models.Background360;
import j4q.models.GameObject;
import j4q.models.ObjectMaker;
import j4q.physics.RigidBody;
import j4q.physics.RigidBox;
import j4q.physics.RigidCylinder;
import j4q.physics.RigidPlane;
import j4q.physics.RigidSphere;
import j4q.shaders.NormalMapPhongShader;
import j4q.shaders.ShadedTextureShader;
import j4q.shaders.Texture;


public class MainActivity extends QuestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    RightController rs;
    LeftController ls;


    public void makeBox(Texture texture){
        //Define the appearance
        ObjectMaker om=new ObjectMaker();
        om.box(0.5f,0.5f,0.5f);
        GameObject box=om.flushModel(true,true);//we export a Mesh with Vertices, Normals, and UVs
        box.addComponent(new ShadedTextureShader().setTexture(texture).setSpecularColor(0,0,0));
        scene.appendChild(box);
        box.transform.translate(new Random().nextFloat()*2-1,new Random().nextFloat()*10,-new Random().nextFloat()*10);

        //Define the physics
        RigidBody rb=new RigidBox(0.5f,0.5f,0.5f,1f);
        box.addComponent(rb);
        rb.setWorldTransform(box);
        rb.addToEngine();
    }

    public void makeWalls(){
        //Front Wall
        RigidBody rb=new RigidPlane(0,0,1);
        rb.setWorldTransform(new Transform().translate(0,0,-15));
        rb.addToEngine();

        //Left Wall
        rb=new RigidPlane(0,0,1);
        rb.setWorldTransform(new Transform().translate(-17,0,0).rotateY(90));
        rb.addToEngine();

        //Back Wall
        rb=new RigidPlane(0,0,1);
        rb.setWorldTransform(new Transform().translate(0,0,10).rotateY(180));
        rb.addToEngine();

        //Right Wall
        rb=new RigidPlane(0,0,1);
        rb.setWorldTransform(new Transform().translate(17,0,0).rotateY(-90));
        rb.addToEngine();
    }

    public void makeSphere(Texture texture){
        //Define the appearance
        ObjectMaker om=new ObjectMaker();
        om.sphere(0.24f,0.24f,0.24f);
        GameObject sphere=om.flushModel(true,true);//we export a Mesh with Vertices, Normals, and UVs
        sphere.addComponent(new ShadedTextureShader().setTexture(texture).setSpecularColor(0,0,0));
        scene.appendChild(sphere);
        sphere.transform.translate(new Random().nextFloat()*2-1,new Random().nextFloat()*10,-new Random().nextFloat()*10);

        //Define the physics
        RigidBody rb=new RigidSphere(0.12f,0.145f);
        rb.getBody().setRestitution(0.9f);//Make it bouncy
        rb.getBody().setFriction(0.6f);//Make it roll/slide
        sphere.addComponent(rb);
        rb.setWorldTransform(sphere);
        rb.addToEngine();
    }

    public void makeCylinder(Texture texture,Texture normalmap){
        //Define the appearance
        ObjectMaker om=new ObjectMaker();
        om.cylinder(1,1,1);
        GameObject cylinder=om.flushModel(true,true,false,true);//we export a Mesh with Vertices, Normals, and UVs
        cylinder.addComponent(new NormalMapPhongShader()
                .setNormalMap(normalmap)
                .setAmbientColor(0.0f,0.0f,0.1f)
                .setDiffuseColor(0.8f,0.0f,0.0f)
                .setSpecularColor(0.5f,0.5f,0.0f)
                .setSpecularExponent(50));
        scene.appendChild(cylinder);
        cylinder.transform.translate(new Random().nextFloat()*2-1,new Random().nextFloat()*10,-new Random().nextFloat()*10);

        //Define the physics
        RigidBody rb=new RigidCylinder(1f,1f,1f,10);
        cylinder.addComponent(rb);
        rb.setWorldTransform(cylinder);
        rb.addToEngine();
    }

    public void Start(){

        scene.background(153/255f,	204/255f,	255/255f);
        scene.setLightDir(-0.5f,0.5f,-0.5f);

        //We load three textures to the GPU that will be shared by many objects
        Texture wood=new Texture("textures/box.png");
        Texture basketball=new Texture("textures/earth_1024.jpg");
        Texture metallic=new Texture("matcaps/gold.jpg");
        Texture machine=new Texture("normalmaps/machine.png");

        Background360 background=new Background360();
        background.setTexture(new Texture("backgrounds/eso0932a.jpg"));
        scene.appendChild(background);

        rs=new RightController();
        scene.appendChild(rs);

        ls=new LeftController();
        scene.appendChild(ls);

        ObjectMaker om=new ObjectMaker();
        om.rectangle(200,200,200,100);
        GameObject floor=om.flushModel(true,true);//we export a Mesh with Vertices, Normals, and UVs
        floor.addComponent(new ShadedTextureShader().setTexture(new Texture("textures/rock.jpg")));
        scene.appendChild(floor);
        floor.transform.translate(0,0,0).rotateX(-90);

        RigidPlane rb=new RigidPlane(0,1,0);
        rb.getBody().setRestitution(0.8f);//Make the elastic objects bounce on it
        rb.getBody().setFriction(0.8f);//Make the objects slide on it
        rb.setWorldTransform(new Transform().translate(0,0,0));
        rb.addToEngine();

        //makeWalls();




        //We randomly generate three types of objects: boxes, spheres, and cylinders
        for(int i=0;i<100;i++)
        {
            float r=new Random().nextFloat();
            if(r<0.33f)
                makeBox(wood);
            else if(r>0.66f)
                makeSphere(basketball);
            else
                makeCylinder(metallic,machine);
        }

    }

    int frame=0;

    public  void Update(){




    }

}
