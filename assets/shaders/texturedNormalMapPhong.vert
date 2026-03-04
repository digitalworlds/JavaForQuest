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
in vec3 aTangent;
in vec2 aUV;

out vec4 vPosition;
out vec3 vNormal;
out vec3 vTangent;
out vec3 vBitangent;
out vec2 vUV;
out vec3 vEye; //We will calculate the direction from the camera to each vertex
out vec3 lightDir; //We will calculate the rotated light direction (using the camera normal matrix)


void main() {
    vec4 p = sm.ViewMatrix * modelMatrix * vec4(aPosition, 1.0);
    vPosition = p;
    //We will use the position p to calculate the Eye direction (i.e. from the camera to that point)
    vEye=normalize(vec3(p));
    //We calculate the new (rotated) light direction
    lightDir=mat3(sm.NormalMatrix)*vec3(sm.uLightDir);

    // transform normal, tangent, bitangent to view space
    mat3 normalMat = mat3(sm.NormalMatrix * normalMatrix);
    vNormal = normalize(normalMat * aNormal);
    vTangent = normalize(normalMat * aTangent);
    vBitangent = normalize(cross(vNormal, vTangent));

    vUV = aUV;

    gl_Position = sm.ProjectionMatrix * p;
}