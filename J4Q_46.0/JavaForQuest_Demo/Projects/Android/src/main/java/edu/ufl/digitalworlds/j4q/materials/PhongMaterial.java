package edu.ufl.digitalworlds.j4q.materials;

import android.opengl.GLES30;

import edu.ufl.digitalworlds.j4q.shaders.Shader;

public class PhongMaterial extends Shader {

    public PhongMaterial(){
        super(
                "uniform SceneMatrices\n"+
                        "{\n"+
                        "	uniform mat4 ViewMatrix;\n"+
                        "	uniform mat4 ProjectionMatrix;\n"+
                        "   uniform vec3 uLightDir;\n"+
                        "} sm;\n"+
                        "uniform mat4 localTransform;\n"+
                        "in vec3 aNormal;"+
                        "out vec3 vNormal;"+
                        "in vec3 aPosition;\n"+
                        "in vec3 aColor;\n"+
                        "out vec3 vColor;\n"+
                        "out vec3 vE;"+
                        "void main()\n"+
                        "{\n"+
                        " vec4 tN=normalize(localTransform *vec4(aNormal,0.0));" +
                        "vNormal=vec3(tN.x,tN.y,tN.z);"+
                        "vec4 p=sm.ViewMatrix *localTransform*  vec4( aPosition , 1.0 );"+
                        "	gl_Position = sm.ProjectionMatrix * p;\n"+
                        "vE=normalize(vec3(p));"+
                        "	vColor = aColor;\n"+
                        "}\n",

                "uniform SceneMatrices\n"+
                        "{\n"+
                        "	uniform mat4 ViewMatrix;\n"+
                        "	uniform mat4 ProjectionMatrix;\n"+
                        "   uniform vec3 uLightDir;\n"+
                        "} sm;\n"+
                        "uniform vec3 uAmbientColor;"+
                        "uniform vec3 uDiffuseColor;"+
                        "uniform vec3 uSpecularColor;"+
                        "uniform float uSpecularExponent;"+

                        "in lowp vec3 vColor;\n"+
                        "out lowp vec4 outColor;\n"+
                        "in vec3 vNormal;"+
                        "in vec3 vE;"+

                        "void main()\n"+
                        "{\n"+
                        "vec3 E=normalize(vE);"+
                        "vec4 shade=vec4(0.0,0.0,0.0,1.0);"+
                        "shade+=vec4(uAmbientColor,1.0);"+
                        "shade+=vec4(uDiffuseColor,1.0)*max(dot(vNormal,sm.uLightDir),0.0);"+
                        "vec3 reflectionDirection = reflect(sm.uLightDir, vNormal);"+
                        "shade+=vec4(uSpecularColor,1.0)*pow(max(dot(reflectionDirection, E), 0.0), uSpecularExponent);"+
                        "  outColor = vec4(vColor,1.0)*shade;" +
                        "}\n",new String[]{"aPosition","aColor","aNormal"});

        setAmbientColor(new float[]{0.6f,0.6f,0.6f});
        setDiffuseColor(new float[]{0.4f,0.4f,0.4f});
        setSpecularColor(new float[]{0.4f,0.4f,0.4f});
        setSpecularExponent(5);
    }

    public void setAmbientColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uAmbientColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
    }

    public void setDiffuseColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uDiffuseColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
    }

    public void setSpecularColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
    }

    public void setSpecularExponent(float exponent){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularExponent");
        GLES30.glUniform1f(mHandle, exponent);
    }
}
