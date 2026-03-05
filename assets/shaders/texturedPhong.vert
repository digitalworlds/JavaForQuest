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
in vec2 aUV;
in vec3 aNormal;

out vec2 vUV;
out vec3 vNormal;
out vec3 vE;
out vec3 lightDir;

void main() {
    lightDir=mat3(sm.NormalMatrix)*vec3(sm.uLightDir);
    vUV=aUV;
    vNormal=normalize(mat3(sm.NormalMatrix*normalMatrix)*aNormal);
    vec4 p = sm.ViewMatrix * modelMatrix * vec4(aPosition, 1.0);
    gl_Position = sm.ProjectionMatrix * p;

    vUV=aUV;
    vE=normalize(vec3(p));
}
