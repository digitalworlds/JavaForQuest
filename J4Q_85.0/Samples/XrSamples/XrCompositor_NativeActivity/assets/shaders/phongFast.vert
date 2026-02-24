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

uniform vec3 uAmbientColor;
uniform vec3 uDiffuseColor;
uniform vec3 uSpecularColor;
uniform float uSpecularExponent;

in vec3 aPosition; //We will use the position of each vertex
in vec3 aNormal; //We will use the normal of each vertex


out vec4 shade;

void main()
{
    //We calculate the new vertex position
    vec4 p=sm.ViewMatrix *modelMatrix*  vec4( aPosition , 1.0 );
    gl_Position = sm.ProjectionMatrix * p;

    //We will use the position p to calculate the Eye direction (i.e. from the camera to that point)
    vec3 Eye=normalize(vec3(p));

    //We calculate the new (rotated) normals
    vec3 Normal=normalize(mat3(sm.NormalMatrix*normalMatrix)*aNormal);

    //We calculate the new (rotated) light direction
    vec3 lightDir=normalize(mat3(sm.NormalMatrix)*vec3(sm.uLightDir));

    //We start with black
    shade=vec4(0.0,0.0,0.0,1.0);
    //We add ambient color (the darkest possible shade)
    shade+=vec4(uAmbientColor,1.0);

    //We add the diffuse color (how the light shades the faces)
    shade+=vec4(uDiffuseColor,1.0)*max(dot(Normal,lightDir),0.0);

    //We add the specular color (the shiny spot that appears when the light is reflected into our eyes)
    vec3 reflectionDirection = reflect(lightDir, Normal);
    shade+=vec4(uSpecularColor,1.0)*pow(max(dot(reflectionDirection, Eye), 0.0), uSpecularExponent);
}
