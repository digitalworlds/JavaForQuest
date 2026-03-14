package j4q.shaders;

import android.opengl.GLES30;

import j4q.models.Mesh;

/**
 * Shader for rendering textured objects with Phong lighting.
 * <p>
 * Supports ambient, diffuse, and specular color configuration, specular exponent control, and texture binding.
 * </p>
 */
public class ShadedTextureShader extends Shader {

    /**
     * Constructs a ShadedTextureShader with default lighting parameters.
     */
    public ShadedTextureShader() {
        super("shaders/texturedPhong",new String[]{"aPosition","aNormal","aUV"});
        setAmbientColor(0.3f,0.3f,0.3f);
        setDiffuseColor(0.7f,0.7f,0.7f);
        setSpecularColor(0.5f,0.5f,0.5f);
        setSpecularExponent(10);
    }


    /**
     * Sets the ambient color for the shader.
     * @param red Red component (0-1).
     * @param green Green component (0-1).
     * @param blue Blue component (0-1).
     * @return This shader instance for chaining.
     */
    public ShadedTextureShader setAmbientColor(float red,float green, float blue){
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
    public ShadedTextureShader setDiffuseColor(float red,float green, float blue){
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
    public ShadedTextureShader setSpecularColor(float red,float green, float blue){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularColor");
        GLES30.glUniform3f(mHandle,red,green,blue);
        return this;
    }

    /**
     * Sets the specular exponent for the shader (shininess).
     * @param exponent The specular exponent value.
     * @return This shader instance for chaining.
     */
    public ShadedTextureShader setSpecularExponent(float exponent){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularExponent");
        GLES30.glUniform1f(mHandle, exponent);
        return this;
    }

    /**
     * The texture used for rendering.
     */
    protected Texture texture = null;

    /**
     * Sets the texture for the shader and binds it.
     * @param texture The texture to use.
     * @return This shader instance for chaining.
     */
    public ShadedTextureShader setTexture(Texture texture){
        this.texture=texture;
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uTexture");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(mTextureHandle, texture.slot);
        return this;
    }

    /**
     * Renders the mesh using the shaded texture shader and bound texture.
     * @param mesh The mesh to render.
     */
    @Override
    public void render(Mesh mesh) {
        GLES30.glUseProgram(shaderProgram);
        GLES30.glBindVertexArray( mesh.vertexArrayObject ) ;

        if(texture!=null) {
            //shader.setUniformInteger("uTexture", texture.slot);
            GLES30.glActiveTexture(GLES30.GL_TEXTURE0 + texture.slot);
            // Bind the texture to this unit.
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture.gles_handle);
        }
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, mesh.triangleLength, GLES30.GL_UNSIGNED_SHORT,0);
        GLES30.glBindVertexArray( 0 );
        GLES30.glUseProgram( 0 );
    }
}

