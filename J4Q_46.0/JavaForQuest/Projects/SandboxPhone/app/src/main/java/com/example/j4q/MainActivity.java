package com.example.j4q;

import android.os.Bundle;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.activities.ThirdEyeActivity;
import edu.ufl.digitalworlds.j4q.formats.OBJFile;
import edu.ufl.digitalworlds.j4q.models.Background360;
import edu.ufl.digitalworlds.j4q.models.Mesh;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.ObjectMaker;
import edu.ufl.digitalworlds.j4q.shaders.ColorShader;
import edu.ufl.digitalworlds.j4q.shaders.ShadedColorShader;
import edu.ufl.digitalworlds.j4q.shaders.ShadedTextureShader;
import edu.ufl.digitalworlds.j4q.shaders.Texture;
import edu.ufl.digitalworlds.j4q.shaders.TextureShader;


public class MainActivity extends ThirdEyeActivity {

    Model cube;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void Start(){
        //Welcome to the J4Q (Java For Quest) Project!

        //This method will run once in the beginning of the program.
        //Write here code that loads your assets and composes your scene.
    }

    @Override
    public void Update() {
        //This method will run in every frame.
        //Write here code that modifies your variables and animates your scene.
    }

}
