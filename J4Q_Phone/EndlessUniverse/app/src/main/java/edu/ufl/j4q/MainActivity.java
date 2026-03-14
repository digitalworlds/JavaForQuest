package edu.ufl.j4q;

import android.os.Bundle;

import j4q.J4Q;
import j4q.activities.GyroscopicActivity;
import j4q.geometry.Vector3;
import j4q.models.Background360;
import j4q.models.GameObject;
import j4q.models.ObjectMaker;
import j4q.shaders.ColorPhongShader;
import j4q.shaders.ShadedTextureShader;
import j4q.shaders.Texture;


public class MainActivity extends GyroscopicActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }


    int next_projectile=0;
    GameObject[] projectile;

    Background360 background;

    Level my_level;
    GameObject earth;
    GameObject moon;

    public void Start(){

        scene.background(153/255f,	204/255f,	255/255f);
        scene.setLightDir(-0.5f,0.5f,-0.5f);




        my_level=new Level();
        scene.appendChild(my_level);

        //Make the earth
        ObjectMaker om=new ObjectMaker();
        om.sphere(320,320,320,32);
        earth=om.flushModel(true,true);
        earth.setShader(new ShadedTextureShader().setTexture(new Texture(this,"textures/earth_1024.jpg")).setAmbientColor(0.02f,0.02f,0.02f));
        scene.appendChild(earth);
        earth.transform.translate(-320,0,-20);

        //Make the moon
        om.sphere(80,80,80,32);
        moon=om.flushModel(true,true);
        moon.setShader(new ShadedTextureShader().setTexture(new Texture(this,"textures/moon_1024.jpg")).setAmbientColor(0.02f,0.02f,0.02f));
        scene.appendChild(moon);
        moon.transform.translate(320,0,-20);

        background=new Background360();
        background.setTexture(new Texture(this,"textures/eso0932a.jpg"));
        my_level.prependChild(background);


        //rc=new RightController();
        //appendChild(rc);

        //lc=new LeftController();
        //appendChild(lc);

        projectile=new GameObject[10];
        for(int i=0;i<10;i++) {
            om.color(1,0,0);
            om.cylinderZ(0.02f, 0.02f, 0.2f,8);
            projectile[i] = om.flushModel(true,false,true);
            projectile[i].setShader(new ColorPhongShader());
            scene.appendChild(projectile[i]);
        }

    }

    public  void Update(){
        earth.transform.rotateY(-2* J4Q.perSec());
    }

}
