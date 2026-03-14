package j4q.shaders;


import android.opengl.GLES30;

import j4q.models.Mesh;

/**
 * Shader for rendering 360-degree background textures.
 * <p>
 * Handles texture binding and mesh rendering for panoramic backgrounds.
 * </p>
 */
public class Background360Shader extends Shader {

    /**
     * Constructs a Background360Shader for 360-degree backgrounds.
     */
    public Background360Shader() {
        super("shaders/360background", new String[]{"aPosition",null,"aUV"});
    }

    /**
     * The texture used for the 360-degree background.
     */
    protected Texture texture = null;

    /**
     * Sets the texture for the background and binds it to the shader.
     * @param texture The texture to use.
     * @return This shader instance for chaining.
     */
    public Background360Shader setTexture(Texture texture){
        this.texture=texture;
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uTexture");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(mTextureHandle, texture.slot);
        return this;
    }

    /**
     * Renders the mesh using the 360-degree background shader and bound texture.
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
