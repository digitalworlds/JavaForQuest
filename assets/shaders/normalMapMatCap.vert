#version 300 es
precision mediump float;
uniform SceneMatrices { 
    mat4 ViewMatrix; 
    mat4 ProjectionMatrix;
    mat4 NormalMatrix;
    vec4 uLightDir; 
    } sm;

uniform mat4 modelMatrix;
uniform mat4 normalMatrix;

uniform float uTime;

in vec3 aPosition;
in vec3 aNormal;
in vec3 aTangent;
in vec2 aUV;

out vec4 vPosition;
out vec3 vNormal;
out vec3 vTangent;
out vec3 vBitangent;
out vec2 vUV;

void main() {

    vec4 p = sm.ViewMatrix * modelMatrix * vec4(aPosition, 1.0);
    vPosition = p;

    // transform normal, tangent, bitangent to view space
    mat3 normalMat = mat3(sm.NormalMatrix * normalMatrix);
    vNormal = normalize(normalMat * aNormal);
    vTangent = normalize(normalMat * aTangent);
    vBitangent = normalize(cross(vNormal, vTangent));

    vUV = aUV+vec2(0,uTime*2.0);

    gl_Position = sm.ProjectionMatrix * p;
}