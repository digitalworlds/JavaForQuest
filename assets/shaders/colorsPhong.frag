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
in vec3 vColor;
out vec4 outColor;
in vec3 vNormal;
in vec3 vE;
in vec3 lightDir;
void main()
{
    vec3 N=normalize(vNormal);
    vec3 E=normalize(vE);
    vec4 shade=vec4(0.0,0.0,0.0,1.0);
    shade+=vec4(uAmbientColor,1.0);
    shade+=vec4(uDiffuseColor,1.0)*max(dot(N,lightDir),0.0);
    vec3 reflectionDirection = reflect(lightDir, N);
    shade+=vec4(uSpecularColor,1.0)*pow(max(dot(reflectionDirection, E), 0.0), uSpecularExponent);
    outColor = vec4(vColor,1.0)*shade;
}