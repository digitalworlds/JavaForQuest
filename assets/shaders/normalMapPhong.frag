#version 300 es
precision mediump float;
out lowp vec4 outColor;
in vec4 vPosition;
uniform SceneMatrices {
    mat4 ViewMatrix;
    mat4 ProjectionMatrix;
    mat4 NormalMatrix;
    vec4 uLightDir;
} sm;
uniform vec3 uAmbientColor;
uniform vec3 uDiffuseColor;
uniform vec3 uSpecularColor;
uniform float uSpecularExponent;

in vec3 vNormal;
in vec3 vTangent;
in vec3 vBitangent;
in vec2 vUV;
in vec3 vEye;
in vec3 lightDir;

uniform sampler2D uNormalMap;

void main() {
    // Sample normal from normal map (in tangent space, range [0,1])
    vec3 normalSample = texture(uNormalMap, vUV).rgb;
    // Remap to [-1,1]
    vec3 n = normalize(normalSample * 2.0 - 1.0);

    // Construct TBN matrix
    mat3 TBN = mat3(normalize(vTangent), normalize(vBitangent), normalize(vNormal));
    // Transform normal from tangent space to view space
    vec3 N = normalize(TBN * n);

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