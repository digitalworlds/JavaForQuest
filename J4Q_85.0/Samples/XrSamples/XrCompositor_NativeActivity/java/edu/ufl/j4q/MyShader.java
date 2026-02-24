package edu.ufl.j4q;

import gl.shaders.Shader;

public class MyShader extends Shader {

    public MyShader() {
        //It uses the GLSL shader located in: assets/shaders/flat.vert and .frag
        super("shaders/normals",new String[] { "aPosition","aNormal","aUV" });
    }

}

