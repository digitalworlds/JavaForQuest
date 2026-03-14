package j4q.shaders;

import android.opengl.GLES30;

import j4q.models.Mesh;

/**
 * Shader for rendering objects with MatCap texture lighting.
 * <p>
 * Handles MatCap texture binding and mesh rendering for stylized shading.
 * </p>
 */
public class MatCapShader extends Shader {

    /**
     * Constructs a MatCapShader for stylized MatCap rendering.
     */
    public MatCapShader() {
        super("shaders/matCap",new String[]{"aPosition","aNormal"});
    }

    /**
     * The MatCap texture used for stylized shading.
     */
    protected Texture matcap = null;

    /**
     * Sets the MatCap texture for the shader and binds it.
     * @param matCap The MatCap texture to use.
     * @return This shader instance for chaining.
     */
    public MatCapShader setMatCap(Texture matCap){
        this.matcap=matCap;
        this.matcap.setActive(0);
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uMatCap");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 1.
        GLES30.glUniform1i(mTextureHandle, matcap.slot);
        return this;
    }

    /**
     * Renders the mesh using the MatCap shader and bound MatCap texture.
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

