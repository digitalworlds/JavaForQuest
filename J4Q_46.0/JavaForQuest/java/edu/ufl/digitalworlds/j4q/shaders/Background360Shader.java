package edu.ufl.digitalworlds.j4q.shaders;


import android.opengl.GLES30;

import edu.ufl.digitalworlds.j4q.models.Mesh;

public class Background360Shader extends Shader {

    public Background360Shader() {
        super(
                "#version 300 es\n"+
                        "uniform SceneMatrices\n"+
                        "{\n"+
                        "	uniform mat4 ViewMatrix;\n"+
                        "	uniform mat4 ProjectionMatrix;\n"+
                        "uniform mat4 NormalMatrix;\n"+
                        "   uniform vec4 uLightDir;\n"+
                        "} sm;\n"+
                        "uniform mat4 modelMatrix;\n"+
                        "uniform mat4 normalMatrix;\n"+

                        "in vec3 aPosition;\n"+
                        "in vec2 aUV;" +
                        "out vec2 vUV;"+

                        "out vec4 fragmentColor;\n"+
                        "mat4 trans;"+
                        "void main()\n"+
                        "{\n"+
                        "trans=sm.ViewMatrix * modelMatrix;"+
                        "trans[3][0]=0.0;"+
                        "trans[3][1]=0.0;"+
                        "trans[3][2]=0.0;"+
                        "trans[3][3]=1.0;"+
                        "	gl_Position = sm.ProjectionMatrix * ( trans *  vec4( aPosition , 1.0 )  );\n"+
                        "	vUV=aUV;\n"+
                        "}\n",


                "#version 300 es\n"+
                        "precision mediump float;\n" +
                        "in lowp vec2 vUV;\n"+
                        "out lowp vec4 outColor;\n"+
                        "uniform sampler2D uTexture;\n"+

                        "void main()\n"+
                        "{\n"+
                        "	outColor = texture(uTexture, vUV);\n"+
                        "}\n",new String[]{"aPosition",null,"aUV"});

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
