#version 300 es
precision mediump float;
out lowp vec4 outColor;
uniform vec3 uObjectId;

void main()
{
    outColor = vec4(uObjectId,1.0);
}