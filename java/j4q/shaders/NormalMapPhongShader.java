package j4q.shaders;

import android.opengl.GLES30;

import j4q.models.Mesh;

/**
 * Shader for rendering objects with Phong lighting and normal mapping.
 * <p>
 * Supports ambient, diffuse, and specular color configuration, exponent control, and normal map binding.
 * </p>
 */
public class NormalMapPhongShader extends Shader {

    /**
     * Constructs a NormalMapPhongShader with default lighting parameters.
     */
    public NormalMapPhongShader() {
        super("shaders/normalMapPhong",new String[]{"aPosition","aNormal","aUV","aTangent"});
        setAmbientColor(0.3f,0.3f,0.3f);
        setDiffuseColor(0.7f,0.7f,0.7f);
        setSpecularColor(0.5f,0.5f,0.5f);
        setSpecularExponent(50);
    }

    /**
     * The normal map texture used for surface detail.
     */
    protected Texture normalmap = null;

    /**
     * Sets the normal map texture for the shader and binds it.
     * @param normalMap The normal map texture to use.
     * @return This shader instance for chaining.
     */
    public NormalMapPhongShader setNormalMap(Texture normalMap){
        this.normalmap=normalMap;
        this.normalmap.setActive(1);
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uNormalMap");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 1.
        GLES30.glUniform1i(mTextureHandle, normalmap.slot);
        return this;
    }

    /**
     * Renders the mesh using the normal map Phong shader and bound normal map texture.
     * @param mesh The mesh to render.
     */
    @Override
    public void render(Mesh mesh) {
        GLES30.glUseProgram(shaderProgram);
        GLES30.glBindVertexArray( mesh.vertexArrayObject ) ;

        if(normalmap!=null) {
            //shader.setUniformInteger("uTexture", texture.slot);
            GLES30.glActiveTexture(GLES30.GL_TEXTURE0 + normalmap.slot);
            // Bind the texture to this unit.
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, normalmap.gles_handle);
        }
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, mesh.triangleLength, GLES30.GL_UNSIGNED_SHORT,0);
        GLES30.glBindVertexArray( 0 );
        GLES30.glUseProgram( 0 );
    }

    /**
     * Sets the ambient color for the shader.
     * @param red Red component (0-1).
     * @param green Green component (0-1).
     * @param blue Blue component (0-1).
     * @return This shader instance for chaining.
     */
    public NormalMapPhongShader setAmbientColor(float red,float green, float blue){
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
    public NormalMapPhongShader setDiffuseColor(float red,float green, float blue){
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
    public NormalMapPhongShader setSpecularColor(float red,float green, float blue){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularColor");
        GLES30.glUniform3f(mHandle,red,green,blue);
        return this;
    }

    /**
     * Sets the specular exponent for the shader (shininess).
     * @param exponent The specular exponent value.
     * @return This shader instance for chaining.
     */
    public NormalMapPhongShader setSpecularExponent(float exponent){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularExponent");
        GLES30.glUniform1f(mHandle, exponent);
        return this;
    }
}

