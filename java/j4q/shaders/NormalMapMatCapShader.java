package j4q.shaders;

import android.opengl.GLES30;

import j4q.models.Mesh;

/**
 * Shader for rendering objects with MatCap and normal map textures.
 * <p>
 * Supports animated effects, normal mapping, and stylized MatCap shading.
 * </p>
 */
public class NormalMapMatCapShader extends Shader {

    /**
     * Constructs a NormalMapMatCapShader for stylized rendering with normal mapping.
     */
    public NormalMapMatCapShader() {
        super("shaders/normalMapMatCap",new String[]{"aPosition","aNormal","aUV","aTangent"});
    }

    /**
     * The MatCap texture used for stylized shading.
     */
    protected Texture matcap = null;

    /**
     * The normal map texture used for surface detail.
     */
    protected Texture normalmap = null;

    /**
     * Sets the time uniform for animated shader effects.
     * @param time The time value to set.
     * @return This shader instance for chaining.
     */
    public NormalMapMatCapShader setTime(float time){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uTime");
        GLES30.glUniform1f(mHandle, time);
        return this;
    }

    /**
     * Sets the normal map texture for the shader and binds it.
     * @param normalMap The normal map texture to use.
     * @return This shader instance for chaining.
     */
    public NormalMapMatCapShader setNormalMap(Texture normalMap){
        this.normalmap=normalMap;
        this.normalmap.setActive(1);
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uNormalMap");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 1.
        GLES30.glUniform1i(mTextureHandle, normalmap.slot);
        return this;
    }
    /**
     * Sets the MatCap texture for the shader and binds it.
     * @param matCap The MatCap texture to use.
     * @return This shader instance for chaining.
     */
    public NormalMapMatCapShader setMatCap(Texture matCap){
        this.matcap=matCap;
        this.matcap.setActive(0);
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uMatCap");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 1.
        GLES30.glUniform1i(mTextureHandle, matcap.slot);
        return this;
    }

    /**
     * Renders the mesh using the normal map MatCap shader and bound textures.
     * @param mesh The mesh to render.
     */
    @Override
    public void render(Mesh mesh) {
        GLES30.glUseProgram(shaderProgram);
        GLES30.glBindVertexArray( mesh.vertexArrayObject ) ;

        if(matcap!=null) {
            //shader.setUniformInteger("uTexture", texture.slot);
            GLES30.glActiveTexture(GLES30.GL_TEXTURE0 + matcap.slot);
            // Bind the texture to this unit.
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, matcap.gles_handle);
        }
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, mesh.triangleLength, GLES30.GL_UNSIGNED_SHORT,0);
        GLES30.glBindVertexArray( 0 );
        GLES30.glUseProgram( 0 );
    }
}

