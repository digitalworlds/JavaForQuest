#version 300 es
precision mediump float;

uniform sampler2D uTexture;
uniform vec3 uAmbientColor;
uniform vec3 uDiffuseColor;
uniform vec3 uSpecularColor;
uniform float uSpecularExponent;

in lowp vec2 vUV;
in vec3 vNormal;
in vec3 vE;
in vec3 lightDir;
out lowp vec4 outColor;

void main() {
    vec3 E=normalize(vE);
    vec4 shade=vec4(0.0,0.0,0.0,1.0);
    shade+=vec4(uAmbientColor,1.0);
    shade+=vec4(uDiffuseColor,1.0)*max(dot(vNormal,lightDir),0.0);
    vec3 reflectionDirection = reflect(lightDir, vNormal);
    shade+=vec4(uSpecularColor,1.0)*pow(max(dot(reflectionDirection, E), 0.0), uSpecularExponent);

    vec4 color=texture(uTexture, vUV);
    shade=color*shade;
    outColor = shade;
}
