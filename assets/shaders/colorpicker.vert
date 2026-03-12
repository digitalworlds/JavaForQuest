#version 300 es
uniform SceneMatrices
{
    mat4 ViewMatrix;
    mat4 ProjectionMatrix;
    mat4 NormalMatrix;
    vec4 uLightDir;
} sm;
uniform mat4 modelMatrix;
in vec3 aPosition;
void main()
{
    gl_Position = sm.ProjectionMatrix * ( sm.ViewMatrix *modelMatrix*  vec4( aPosition , 1.0 )  );
}
