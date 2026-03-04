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

in vec3 aPosition;
in vec3 aNormal;

out vec3 vNormal;

void main() {
    vec4 p = sm.ViewMatrix * modelMatrix * vec4(aPosition, 1.0);

    // Transform normal to view space
    mat3 normalMat = mat3(sm.NormalMatrix * normalMatrix);
    vNormal = normalize(normalMat * aNormal);

    gl_Position = sm.ProjectionMatrix * p;
}