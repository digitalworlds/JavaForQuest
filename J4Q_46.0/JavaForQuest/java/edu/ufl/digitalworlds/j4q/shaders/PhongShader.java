package edu.ufl.digitalworlds.j4q.shaders;

import android.opengl.GLES30;

public class PhongShader extends Shader {

    public PhongShader() {
        super(
                "#version 300 es\n"+
                        "uniform SceneMatrices\n"+
                        "{\n"+
                        "	uniform mat4 ViewMatrix;\n"+
                        "	uniform mat4 ProjectionMatrix;\n"+
                        "uniform mat4 NormalMatrix;\n"+
                        "   uniform vec4 uLightDir;\n"+
                        "} sm;\n"+
                        "uniform mat4 modelMatrix;\n"+
                        "uniform mat4 normalMatrix;\n"+
                        "in vec3 aNormal;"+
                        "out vec3 vNormal;"+
                        "in vec3 aPosition;\n"+
                        "out vec3 vE;"+
                        "out vec3 lightDir;"+
                        "void main()\n"+
                        "{\n"+
                        "lightDir=mat3(sm.NormalMatrix)*vec3(sm.uLightDir);"+
                        "vNormal=normalize(mat3(sm.NormalMatrix*normalMatrix)*aNormal);"+
                        "vec4 p=sm.ViewMatrix *modelMatrix*  vec4( aPosition , 1.0 );"+
                        "	gl_Position = sm.ProjectionMatrix * p;\n"+
                        "vE=normalize(vec3(p));"+
                        "}\n",

                "#version 300 es\n"+
                        "precision mediump float;\n" +
                                "uniform SceneMatrices\n"+
                        "{\n"+
                        "	uniform mat4 ViewMatrix;\n"+
                        "	uniform mat4 ProjectionMatrix;\n"+
                                "uniform mat4 NormalMatrix;\n"+
                        "   uniform vec4 uLightDir;\n"+
                        "} sm;\n"+
                        "uniform vec3 uAmbientColor;"+
                        "uniform vec3 uDiffuseColor;"+
                        "uniform vec3 uSpecularColor;"+
                        "uniform float uSpecularExponent;"+

                        "out  vec4 outColor;\n"+
                        "in vec3 vNormal;"+
                        "in vec3 vE;"+
                                "in vec3 lightDir;"+

                        "void main()\n"+
                        "{\n"+
                                "vec3 N=normalize(vNormal);"+
                        "vec3 E=normalize(vE);"+
                        "vec4 shade=vec4(0.0,0.0,0.0,1.0);"+
                        "shade+=vec4(uAmbientColor,1.0);"+
                        "shade+=vec4(uDiffuseColor,1.0)*max(dot(N,lightDir),0.0);"+
                        "vec3 reflectionDirection = reflect(lightDir, N);"+
                                "shade+=vec4(uSpecularColor,1.0)*pow(max(dot(reflectionDirection, E), 0.0), uSpecularExponent);"+
                                " outColor = shade;" +
                                "}\n",new String[]{"aPosition","aNormal"});
        setAmbientColor(new float[]{0.3f,0.3f,0.3f});
        setDiffuseColor(new float[]{0.7f,0.7f,0.7f});
        setSpecularColor(new float[]{0.5f,0.5f,0.5f});
        setSpecularExponent(50);
    }

    public PhongShader setAmbientColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uAmbientColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
        return this;
    }

    public PhongShader setDiffuseColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uDiffuseColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
        return this;
    }

    public PhongShader setSpecularColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
        return this;
    }

    public PhongShader setSpecularExponent(float exponent){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularExponent");
        GLES30.glUniform1f(mHandle, exponent);
        return this;
    }
}
