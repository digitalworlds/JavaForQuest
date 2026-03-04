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

in vec3 vNormal;
in vec3 vTangent;
in vec3 vBitangent;
in vec2 vUV;

uniform sampler2D uNormalMap;
uniform sampler2D uMatCap;

void main() {
    // Sample normal from normal map (in tangent space, range [0,1])
    vec3 normalSample = texture(uNormalMap, vUV).rgb;
    // Remap to [-1,1]
    vec3 n = normalize(normalSample * 2.0 - 1.0);

    // Construct TBN matrix
    mat3 TBN = mat3(normalize(vTangent), normalize(vBitangent), normalize(vNormal));
    // Transform normal from tangent space to view space
    vec3 N = normalize(TBN * n);


    // Map normal.xy to [0,1] range for texture lookup
    vec2 uv = N.xy * 0.5 + 0.5;
    // Clamp to avoid artifacts at the edge
    uv = clamp(uv, 0.0, 1.0);
    vec4 matcapColor = texture(uMatCap, uv);

    outColor = matcapColor;
}