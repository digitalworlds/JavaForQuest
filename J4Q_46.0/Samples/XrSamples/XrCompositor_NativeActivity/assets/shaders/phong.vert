#version 300 es
uniform SceneMatrices
{
    mat4 ViewMatrix;
    mat4 ProjectionMatrix;
    mat4 NormalMatrix;
    vec4 uLightDir;
} sm;
uniform mat4 modelMatrix;
uniform mat4 normalMatrix; //This is the inverse transpose of the modelMatrix

in vec3 aPosition; //We will use the position of each vertex
in vec3 aNormal; //We will use the normal of each vertex


out vec3 vEye; //We will calculate the direction from the camera to each vertex
out vec3 vNormal; //We will calculate the rotated normal of each vertex
out vec3 lightDir; //We will calculate the rotated light direction (using the camera normal matrix)

void main()
{
    //We calculate the new vertex position
    vec4 p=sm.ViewMatrix *modelMatrix*  vec4( aPosition , 1.0 );
    gl_Position = sm.ProjectionMatrix * p;

    //We will use the position p to calculate the Eye direction (i.e. from the camera to that point)
    vEye=normalize(vec3(p));

    //We calculate the new (rotated) normals
    vNormal=normalize(mat3(sm.NormalMatrix*normalMatrix)*aNormal);

    //We calculate the new (rotated) light direction
    lightDir=mat3(sm.NormalMatrix)*vec3(sm.uLightDir);
}
