package edu.ufl.digitalworlds.j4q.shaders;

public class ColorShader extends Shader {

    public ColorShader() {
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
                        "in vec3 aColor;\n"+
                        "out vec4 vColor;\n"+
                        "void main()\n"+
                        "{\n"+
                        "	gl_Position = sm.ProjectionMatrix * ( sm.ViewMatrix *modelMatrix*  vec4( aPosition , 1.0 )  );\n"+
                        "	vColor = vec4(aColor,1.0);\n"+
                        "}\n",


                "#version 300 es\n"+
                        "precision mediump float;\n" +
                                "in lowp vec4 vColor;\n"+
                        "out lowp vec4 outColor;\n"+

                        "void main()\n"+
                        "{\n"+
                        "	outColor = vColor;\n"+
                        "}\n",new String[]{"aPosition",null,"aColor"});

    }
}
