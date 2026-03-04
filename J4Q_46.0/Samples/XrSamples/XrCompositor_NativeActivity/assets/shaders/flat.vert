#version 300 es
precision mediump float;
uniform SceneMatrices {
    mat4 ViewMatrix;
    mat4 ProjectionMatrix;
    mat4 NormalMatrix;
    vec4 uLightDir;
    } sm;
uniform mat4 modelMatrix;

in vec3 aPosition;

void main() {
    vec4 p = sm.ViewMatrix * modelMatrix * vec4(aPosition, 1.0);
    gl_Position = sm.ProjectionMatrix * p;
}