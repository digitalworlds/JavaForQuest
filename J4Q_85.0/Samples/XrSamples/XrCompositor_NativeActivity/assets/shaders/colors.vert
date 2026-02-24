#version 300 es
uniform SceneMatrices
{
    mat4 ViewMatrix;
    mat4 ProjectionMatrix;
    mat4 NormalMatrix;
    vec4 uLightDir;
} sm;
uniform mat4 modelMatrix;
uniform mat4 normalMatrix;

in vec3 aPosition;
in vec3 aColor;//we use an input color for each vertex
out vec4 vColor;
void main()
{
    gl_Position = sm.ProjectionMatrix * ( sm.ViewMatrix *modelMatrix*  vec4( aPosition , 1.0 )  );
    vColor = vec4(aColor,1.0);//we export the vertex color to the fragment shader
}
