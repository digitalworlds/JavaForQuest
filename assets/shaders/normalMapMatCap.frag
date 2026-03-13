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
in vec3 vPos;

uniform sampler2D uNormalMap;
uniform sampler2D uMatCap;

void main() {
    // Sample normal from normal map (in tangent space, range [0,1])
    vec3 normalSample = texture(uNormalMap, vUV).rgb;
    // Remap to [-1,1]
    vec3 n = normalize(normalSample * 2.0 - 1.0);
    vec3 viewDir = normalize(-vPos);

    // Construct TBN matrix
    mat3 TBN = mat3(normalize(vTangent), normalize(vBitangent), normalize(vNormal));
    // Transform normal from tangent space to view space
    vec3 N = normalize(TBN * n);

    vec3 r = reflect(viewDir, N);

    float m = 2.0 * sqrt(
    r.x*r.x +
    r.y*r.y +
    (r.z+1.0)*(r.z+1.0)
    );

    vec2 uv = r.xy / m + 0.5;

    vec4 matcapColor = texture(uMatCap, uv);

    outColor = matcapColor;
}