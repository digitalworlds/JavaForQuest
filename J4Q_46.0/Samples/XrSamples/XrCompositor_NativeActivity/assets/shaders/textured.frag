#version 300 es
precision mediump float;

uniform sampler2D uTexture;

in lowp vec2 vUV;

out lowp vec4 outColor;


void main() {
    outColor = texture(uTexture, vUV);
}