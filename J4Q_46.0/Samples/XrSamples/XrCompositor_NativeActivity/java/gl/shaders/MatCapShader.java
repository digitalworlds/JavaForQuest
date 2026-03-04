package gl.shaders;

import android.opengl.GLES30;

import gl.models.Mesh;

public class MatCapShader extends Shader {

    public MatCapShader() {
        super("shaders/matCap",new String[]{"aPosition","aNormal"});

    }

    protected Texture matcap=null;

    public MatCapShader setMatCap(Texture matCap){
        this.matcap=matCap;
        this.matcap.setActive(0);
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uMatCap");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 1.
        GLES30.glUniform1i(mTextureHandle, matcap.slot);
        return this;
    }

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

