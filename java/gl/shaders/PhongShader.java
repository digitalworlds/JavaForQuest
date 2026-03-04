package gl.shaders;

import android.opengl.GLES30;

public class PhongShader extends Shader {

    public PhongShader(){
        this("shaders/phong");
    }
    public PhongShader(String filename) {
        super(filename, new String[]{"aPosition","aNormal"});
        setAmbientColor(0.3f,0.3f,0.3f);
        setDiffuseColor(0.7f,0.7f,0.7f);
        setSpecularColor(0.5f,0.5f,0.5f);
        setSpecularExponent(50);
    }

    public PhongShader setAmbientColor(float red,float green, float blue){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uAmbientColor");
        GLES30.glUniform3f(mHandle,red,green,blue);
        return this;
    }

    public PhongShader setDiffuseColor(float red,float green, float blue){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uDiffuseColor");
        GLES30.glUniform3f(mHandle,red,green,blue);
        return this;
    }

    public PhongShader setSpecularColor(float red,float green, float blue){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularColor");
        GLES30.glUniform3f(mHandle,red,green,blue);
        return this;
    }

    public PhongShader setSpecularExponent(float exponent){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularExponent");
        GLES30.glUniform1f(mHandle, exponent);
        return this;
    }
}
