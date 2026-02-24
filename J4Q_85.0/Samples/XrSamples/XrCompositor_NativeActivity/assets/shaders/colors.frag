#version 300 es
precision mediump float;
in lowp vec4 vColor;
out lowp vec4 outColor;

void main()
{
    outColor = vColor;
}