package edu.ufl.digitalworlds.j4q.shaders;

import android.opengl.GLES30;

import edu.ufl.digitalworlds.j4q.models.Mesh;

public class ShadedTextureShader extends Shader {

    public ShadedTextureShader() {
        super(

                        "uniform SceneMatrices\n"+
                        "{\n"+
                        "	uniform mat4 ViewMatrix;\n"+
                        "	uniform mat4 ProjectionMatrix;\n"+
                                "uniform mat3 NormalMatrix;\n"+
                        "   uniform vec3 uLightDir;\n"+
                        "} sm;\n"+
                        "uniform mat4 localTransform;\n"+

                        "in vec3 aPosition;\n"+
                        "in vec2 aUV;" +
                        "out vec2 vUV;"+
                        "in vec3 aNormal;"+
                        "out vec3 vNormal;"+
                                "out vec3 vE;"+
                        "out vec3 lightDir;"+

                        "void main()\n"+
                        "{\n"+
                                "lightDir=sm.NormalMatrix*sm.uLightDir;"+
                        "vUV=aUV;"+
                        " vec4 tN=normalize(localTransform *vec4(aNormal,0.0));" +
                        "vNormal=normalize(sm.NormalMatrix*vec3(tN.x,tN.y,tN.z));"+
                                "vec4 p=sm.ViewMatrix *localTransform*  vec4( aPosition , 1.0 );"+
                        "	gl_Position = sm.ProjectionMatrix * p;\n"+
                                "vE=normalize(vec3(p));"+
                                "}\n",


                        "uniform SceneMatrices\n"+
                        "{\n"+
                        "	uniform mat4 ViewMatrix;\n"+
                        "	uniform mat4 ProjectionMatrix;\n"+
                                "uniform mat3 NormalMatrix;\n"+
                        "   uniform vec3 uLightDir;\n"+
                        "} sm;\n"+
                        "in lowp vec2 vUV;\n"+
                        "in vec3 vNormal;"+
                        "in vec3 vE;"+
                                "in vec3 lightDir;"+
                        "out lowp vec4 outColor;\n"+
                        "uniform sampler2D uTexture;\n"+
                        "uniform vec3 uAmbientColor;"+
                        "uniform vec3 uDiffuseColor;"+
                        "uniform vec3 uSpecularColor;"+
                        "uniform float uSpecularExponent;"+

                        "void main()\n"+
                        "{\n"+
                        "vec3 E=normalize(vE);"+
                        "vec4 shade=vec4(0.0,0.0,0.0,1.0);"+
                        "shade+=vec4(uAmbientColor,1.0);"+
                        "shade+=vec4(uDiffuseColor,1.0)*max(dot(vNormal,lightDir),0.0);"+
                        "vec3 reflectionDirection = reflect(lightDir, vNormal);"+
                        "shade+=vec4(uSpecularColor,1.0)*pow(max(dot(reflectionDirection, E), 0.0), uSpecularExponent);"+

                        "vec4 color=texture(uTexture, vUV);"+
                                "shade=color*shade;"+
                        "  outColor = shade;" +
                        "}\n",new String[]{"aPosition","aUV","aNormal"});
        setAmbientColor(new float[]{0.3f,0.3f,0.3f});
        setDiffuseColor(new float[]{0.7f,0.7f,0.7f});
        setSpecularColor(new float[]{0.5f,0.5f,0.5f});
        setSpecularExponent(10);
    }


    public ShadedTextureShader setAmbientColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uAmbientColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
        return this;
    }

    public ShadedTextureShader setDiffuseColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uDiffuseColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
        return this;
    }

    public ShadedTextureShader setSpecularColor(float[] color){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularColor");
        GLES30.glUniform3fv(mHandle,1, color,0);
        return this;
    }

    public ShadedTextureShader setSpecularExponent(float exponent){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, "uSpecularExponent");
        GLES30.glUniform1f(mHandle, exponent);
        return this;
    }

    protected Texture texture=null;

    public ShadedTextureShader setTexture(Texture texture){
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
            GLES30.glBindTexture(GLES30.GL_TEXTURE_2D, texture.data);
        }
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, mesh.triangleLength, GLES30.GL_UNSIGNED_SHORT,0);
        GLES30.glBindVertexArray( 0 );
        GLES30.glUseProgram( 0 );
    }
}

