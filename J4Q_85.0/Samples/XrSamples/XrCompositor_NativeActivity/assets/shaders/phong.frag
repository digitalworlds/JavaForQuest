#version 300 es
precision mediump float;
uniform SceneMatrices
{
    mat4 ViewMatrix;
    mat4 ProjectionMatrix;
    mat4 NormalMatrix;
    vec4 uLightDir;
} sm;
uniform vec3 uAmbientColor;
uniform vec3 uDiffuseColor;
uniform vec3 uSpecularColor;
uniform float uSpecularExponent;

//This is the input from the vertex shader
in vec3 vNormal;
in vec3 vEye;
in vec3 lightDir;

//This is the output color
out vec4 outColor;

void main()
{
    //We just make sure that these are unit vectors
    vec3 N=normalize(vNormal);
    vec3 E=normalize(vEye);

    //We start with black
    vec4 shade=vec4(0.0,0.0,0.0,1.0);
    //We add ambient color (the darkest possible shade)
    shade+=vec4(uAmbientColor,1.0);

    //We add the diffuse color (how the light shades the faces)
    shade+=vec4(uDiffuseColor,1.0)*max(dot(N,lightDir),0.0);

    //We add the specular color (the shiny spot that appears when the light is reflected into our eyes)
    vec3 reflectionDirection = reflect(lightDir, N);
    shade+=vec4(uSpecularColor,1.0)*pow(max(dot(reflectionDirection, E), 0.0), uSpecularExponent);

    //This is the final color
    outColor = shade;
}