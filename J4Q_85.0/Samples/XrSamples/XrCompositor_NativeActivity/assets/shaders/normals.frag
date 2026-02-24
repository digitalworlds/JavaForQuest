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

void main() {
    // simple Lambertian shading with small ambient
    vec3 N = normalize(vNormal);
    vec3 L = normalize(sm.uLightDir.xyz);
    float diff = max(dot(N, L), 0.0);
    vec3 base = vec3(1.0,1.0,1.0);
    vec3 color = base * (0.12 + 0.88 * diff);
    outColor = vec4(color, 1.0);
}