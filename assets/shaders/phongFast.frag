#version 300 es
precision mediump float;

//This is the input from the vertex shader
in vec4 shade;

//This is the output color
out vec4 outColor;

void main()
{
    //This is the final color
    outColor = shade;
}