package edu.ufl.digitalworlds.j4q.shaders;

public class ColorShader extends Shader {

    public ColorShader() {
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
                        "in vec3 aColor;\n"+
                        "out vec4 vColor;\n"+
                        "void main()\n"+
                        "{\n"+
                        "	gl_Position = sm.ProjectionMatrix * ( sm.ViewMatrix *localTransform*  vec4( aPosition , 1.0 )  );\n"+
                        "	vColor = vec4(aColor,1.0);\n"+
                        "}\n",


                        "in lowp vec4 vColor;\n"+
                        "out lowp vec4 outColor;\n"+

                        "void main()\n"+
                        "{\n"+
                        "	outColor = vColor;\n"+
                        "}\n",new String[]{"aPosition","aColor"});

    }
}
