package edu.ufl.j4q;

import android.os.Bundle;
import android.util.Log;

import gl.J4Q;
import gl.activities.QuestActivity;
import gl.models.Background360;
import gl.models.Model;
import gl.models.ObjectMaker;
import gl.shaders.NormalMapMatCapShader;
import gl.shaders.Texture;

public class MainActivity extends QuestActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Enable the following line for AR-type experience (without position tracking; orientation only)
        //showCamera(); //Make sure you do not paint over using scene.background()
    }

    Model m;

    NormalMapMatCapShader s;


    public void Start(){

        Background360 background=new Background360();
        background.setTexture(new Texture("backgrounds/eso0932a.jpg"));
        scene.root.appendChild(background);

        //Let's make a shape with a cube, pyramid, sphere, and cylinder
        ObjectMaker om=new ObjectMaker();
        //a white box
        om.translate(0.5f,-0.5f,0);
        om.color(1,1,1);
        om.box(1,1,1);
        //a red pyramid
        om.translate(0,0.5f,0);
        om.color(1,0,0);
        om.pyramid(1,1,1);

        //a green sphere
        om.translate(-1,0.5f,0);
        om.color(0,0.5f,0);
        om.sphere(1,1,1);

        //a brown cylinder
        om.translate(0,-1,0);
        om.color(0.58f,0.29f,0);
        om.cylinder(0.5f,1,0.5f);
        m=om.flushModel(true,true,true,true);//we export a Mesh with Vertices, Normals, and UVs

        //Let's attach to the model a shader
        //MyShader shader=new MyShader();

        //Below there are various examples of shaders. Uncomment to use them.

        //1. Phong shader with calculations inside the fragment shader
        //PhongShader shader=new PhongShader();
        //shader.setAmbientColor(0,0,0.3f);
        //shader.setDiffuseColor(1f,0.2f,0.2f);
        //shader.setSpecularColor(0.5f,0.5f,0);
        //shader.setSpecularExponent(50);

        //2. Phong shader with calculations inside the vertex shader
        //PhongShader shader=new PhongShader("shaders/phongFast");

        //3. We use an additional input attribute for each vertex for color
        //ColorShader shader=new ColorShader();

        //4. We use the colors with the Phong shader
        //ColorPhongShader shader=new ColorPhongShader();

        //5. We use the UVs for texture mapping
        //TextureShader shader=new TextureShader();
        //shader.setTexture(new Texture("textures/box.png"));

        //6. We use normals from a normal map
        //NormalMapShader shader=new NormalMapShader();
        //shader.setNormalMap(new Texture("normalmaps/textile.png"));

        //7. We use normals from a normal map in the Phong shading model
        //NormalMapPhongShader shader=new NormalMapPhongShader();
        //shader.setNormalMap(new Texture("normalmaps/machine.png"));
        //shader.setAmbientColor(0,0,0.3f);
        //shader.setDiffuseColor(1f,0.2f,0.2f);
        //shader.setSpecularColor(0.5f,0.5f,0);
        //shader.setSpecularExponent(10);

        //8. We use textures, normal maps and  the Phong shading model
        //TextureNormalMapPhongShader shader=new TextureNormalMapPhongShader();
        //shader.setNormalMap(new Texture("normalmaps/rock.jpg"));
        //shader.setTexture(new Texture("textures/rock.jpg"));
        //shader.setAmbientColor(0,0,0.3f);
        //shader.setDiffuseColor(1f,0.2f,0.2f);
        //shader.setSpecularColor(0.5f,0.5f,0);
        //shader.setSpecularExponent(10);

        //9. We use matcap
        //MatCapShader shader=new MatCapShader();
        //shader.setMatCap(new Texture("matcaps/gold.jpg"));

        //10. We use matcap and normalmap
        NormalMapMatCapShader shader=new NormalMapMatCapShader();
        shader.setMatCap(new Texture("matcaps/gold.jpg"));
        shader.setNormalMap(new Texture("normalmaps/machine.png"));
        s=shader;

        m.setShader(shader);
        
        //Add the model to our scene
        scene.appendChild(m);
        m.transform.translate(0,0,-2);


        //Other parameters to set
        scene.setLightDir(1f,-1f,-1);
        scene.background(0,0,0);
    }

    float time=0;
    @Override
    public void Update() {
        time+=0.5f* J4Q.perSec();
        if(time>1)time=0;

        s.use();
        s.setTime(time);



            m.transform.rotateY(20 * J4Q.perSec());
            m.transform.rotateX(20 * J4Q.perSec());
            m.transform.rotateZ(20 * J4Q.perSec());



    }

}
