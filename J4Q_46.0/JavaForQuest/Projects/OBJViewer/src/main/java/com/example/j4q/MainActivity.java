package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.activities.QuestActivity;
import edu.ufl.digitalworlds.j4q.formats.OBJFile;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.shaders.PhongShader;
import edu.ufl.digitalworlds.j4q.shaders.ShadedTextureShader;
import edu.ufl.digitalworlds.j4q.shaders.Texture;

public class MainActivity extends QuestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    Model m;

    public void Start(){
       
        OBJFile obj=new OBJFile("objects/leonardo.obj");
        m=obj.getModel();
        m.setShader(new PhongShader());

        scene.appendChild(m);
        m.transform.translate(-0.5f,0f,-2);
        scene.setLightDir(0,0,-1);
        scene.background(0,0,0);
    }

    @Override
    public void Update() {
        m.transform.rotateY(20* J4Q.perSec());
        m.transform.rotateX(20* J4Q.perSec());
    }
}
