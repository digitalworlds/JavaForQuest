#version 300 es
precision mediump float;
in lowp vec2 vUV;
out lowp vec4 outColor;
uniform sampler2D uTexture;

void main()
{
	outColor = texture(uTexture, vUV);
}