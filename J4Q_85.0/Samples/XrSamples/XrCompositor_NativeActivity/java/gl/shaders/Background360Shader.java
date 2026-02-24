package gl.shaders;


import android.opengl.GLES30;

import gl.models.Mesh;

public class Background360Shader extends Shader {

    public Background360Shader() {
        super("shaders/360background", new String[]{"aPosition",null,"aUV"});
    }

    protected Texture texture=null;

    public Background360Shader setTexture(Texture texture){
        this.texture=texture;
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uTexture");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 0.
        GLES30.glUniform1i(mTextureHandle, texture.slot);
        return this;
    }

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
