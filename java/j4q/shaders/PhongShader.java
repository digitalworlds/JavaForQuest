package j4q.shaders;

import android.opengl.GLES30;

/**
 * Shader for rendering objects with Phong lighting model.
 * <p>
 * Supports ambient, diffuse, and specular color configuration, as well as specular exponent control.
 * </p>
 */
public class PhongShader extends Shader {

    /**
     * Constructs a PhongShader with the default shader file.
     */
    public PhongShader(){
        this("shaders/phong");
    }
    /**
     * Constructs a PhongShader with a custom shader file and initializes lighting parameters.
     * @param filename The shader file to use.
     */
    public PhongShader(String filename) {
        super(filename, new String[]{"aPosition","aNormal"});
        setAmbientColor(0.3f,0.3f,0.3f);
        setDiffuseColor(0.7f,0.7f,0.7f);
        setSpecularColor(0.5f,0.5f,0.5f);
        setSpecularExponent(50);
    }

    /**
     * Sets the ambient color for the shader.
     * @param red Red component (0-1).
     * @param green Green component (0-1).
     * @param blue Blue component (0-1).
     * @return This shader instance for chaining.
     */
    public PhongShader setAmbientColor(float red,float green, float blue){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uAmbientColor");
        GLES30.glUniform3f(mHandle,red,green,blue);
        return this;
    }

    /**
     * Sets the diffuse color for the shader.
     * @param red Red component (0-1).
     * @param green Green component (0-1).
     * @param blue Blue component (0-1).
     * @return This shader instance for chaining.
     */
    public PhongShader setDiffuseColor(float red,float green, float blue){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uDiffuseColor");
        GLES30.glUniform3f(mHandle,red,green,blue);
        return this;
    }

    /**
     * Sets the specular color for the shader.
     * @param red Red component (0-1).
     * @param green Green component (0-1).
     * @param blue Blue component (0-1).
     * @return This shader instance for chaining.
     */
    public PhongShader setSpecularColor(float red,float green, float blue){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularColor");
        GLES30.glUniform3f(mHandle,red,green,blue);
        return this;
    }

    /**
     * Sets the specular exponent for the shader (shininess).
     * @param exponent The specular exponent value.
     * @return This shader instance for chaining.
     */
    public PhongShader setSpecularExponent(float exponent){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularExponent");
        GLES30.glUniform1f(mHandle, exponent);
        return this;
    }
}
