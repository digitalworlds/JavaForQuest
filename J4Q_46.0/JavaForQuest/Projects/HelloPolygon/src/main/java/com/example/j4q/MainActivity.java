package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.activities.QuestActivity;
import edu.ufl.digitalworlds.j4q.geometry.Position;
import edu.ufl.digitalworlds.j4q.models.LeftController;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.ObjectMaker;
import edu.ufl.digitalworlds.j4q.models.RightController;
import edu.ufl.digitalworlds.j4q.shaders.ShadedTextureShader;
import edu.ufl.digitalworlds.j4q.shaders.Texture;
import edu.ufl.digitalworlds.j4q.models.Background360;

public class MainActivity extends QuestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }


    RightController rc;
    LeftController lc;
    RightSpaceship rs;
    LeftSpaceship ls;

    int next_projectile=0;
    Model[] projectile;

    Background360 background;

    Level my_level;
    Model earth;
    Model moon;

    public void Start(){

        background(153/255f,	204/255f,	255/255f);
        setLightDir(-0.5f,0.5f,-0.5f);




        my_level=new Level();
        appendChild(my_level);

        //Make the earth
        ObjectMaker om=new ObjectMaker();
        om.sphere(320,320,320,32);
        earth=om.flushShadedTexturedModel();
        ((ShadedTextureShader)earth.shader).setTexture(new Texture(this,"textures/earth_1024.jpg"));
        ((ShadedTextureShader)earth.shader).setAmbientColor(new float[]{0.02f,0.02f,0.02f});
        appendChild(earth);
        earth.transform.translate(-320,0,-20);

        //Make the moon
        om.sphere(80,80,80,32);
        moon=om.flushShadedTexturedModel();
        ((ShadedTextureShader)moon.shader).setTexture(new Texture(this,"textures/moon_1024.jpg"));
        ((ShadedTextureShader)moon.shader).setAmbientColor(new float[]{0.02f,0.02f,0.02f});
        appendChild(moon);
        moon.transform.translate(320,0,-20);

        background=new Background360();
        background.setTexture(new Texture(this,"textures/eso0932a.jpg"));
        my_level.prependChild(background);

        rs=new RightSpaceship();
        appendChild(rs);

        ls=new LeftSpaceship();
        appendChild(ls);

        //rc=new RightController();
        //appendChild(rc);

        //lc=new LeftController();
        //appendChild(lc);

        projectile=new Model[10];
        for(int i=0;i<10;i++) {
            om.color(1,0,0);
            om.cylinderZ(0.02f, 0.02f, 0.2f,8);
            projectile[i] = om.flushShadedColoredModel();
            appendChild(projectile[i]);
        }

    }

    int frame=0;

    public  void Update(){

        frame+=1;

        earth.transform.rotateY(-2*J4Q.perSec());

        //Trigger projectile from the right controller
        if(J4Q.rightController.trigger.currentState && J4Q.rightController.trigger.changedSinceLastSync){
            J4Q.rightController.vibrate(0.5f,0.1f,10000);
            projectile[next_projectile].show();
            projectile[next_projectile].transform.reset();
            projectile[next_projectile].transform.translate(J4Q.rightController.aim.position);
            projectile[next_projectile].transform.rotate(J4Q.rightController.aim.orientation);
            projectile[next_projectile].transform.translate(0,0,-0.1f);
            next_projectile+=1;
            if(next_projectile>=projectile.length)next_projectile=0;
        }

        //Trigger projectile from the left controller
        if(J4Q.leftController.trigger.currentState && J4Q.leftController.trigger.changedSinceLastSync){
            J4Q.leftController.vibrate(0.5f,0.1f,10000);
            projectile[next_projectile].show();
            projectile[next_projectile].transform.reset();
            projectile[next_projectile].transform.translate(J4Q.leftController.aim.position);
            projectile[next_projectile].transform.rotate(J4Q.leftController.aim.orientation);
            projectile[next_projectile].transform.translate(0,0,-0.1f);
            next_projectile+=1;
            if(next_projectile>=projectile.length)next_projectile=0;
        }

        //Animate all projectiles
        for(int i=0;i<projectile.length;i++)
            projectile[i].transform.translate(0,0,-20f*perSec());


        //Check collision between projectiles and spaceships
        if(frame>1) {//FYI: In the first frame we do not have accurate globalTransform
            for (int i = 0; i < projectile.length; i++) {
                Position p = projectile[i].globalTransform.getPosition();
                for (int j = 0; j < my_level.segments.length; j++) {
                    Position p2 = my_level.segments[j].spaceship.globalTransform.getPosition();
                    float d = p2.distance(p);
                    if (p2.distance(p) < 0.2) {
                        my_level.segments[j].spaceship.remove();
                        projectile[i].hide();
                        J4Q.rightController.vibrate(0.5f,0.5f,3000);
                        J4Q.rightController.vibrate(0.5f,0.5f,3000);
                    }
                }
            }
        }



    }

}
