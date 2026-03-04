package gl.shaders;

import android.opengl.GLES30;

import gl.models.Mesh;

public class NormalMapMatCapShader extends Shader {

    public NormalMapMatCapShader() {
        super("shaders/normalMapMatCap",new String[]{"aPosition","aNormal","aUV","aTangent"});

    }

    protected Texture matcap=null;
    protected Texture normalmap=null;

    public NormalMapMatCapShader setTime(float time){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uTime");
        GLES30.glUniform1f(mHandle, time);
        return this;
    }

    public NormalMapMatCapShader setNormalMap(Texture normalMap){
        this.normalmap=normalMap;
        this.normalmap.setActive(1);
        int mTextureHandle = GLES30.glGetUniformLocation(shaderProgram, "uNormalMap");
        // Tell the texture uniform sampler to use this texture in the shader by binding to texture unit 1.
        GLES30.glUniform1i(mTextureHandle, normalmap.slot);
        return this;
    }
    public NormalMapMatCapShader setMatCap(Texture matCap){
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

