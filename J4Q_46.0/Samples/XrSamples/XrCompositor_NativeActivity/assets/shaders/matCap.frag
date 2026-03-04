#version 300 es
precision mediump float;
out lowp vec4 outColor;

in vec3 vNormal;

uniform sampler2D uMatCap;

void main() {
    // Project normal to screen (matcap) space
    vec3 n = normalize(vNormal);
    // Map normal.xy to [0,1] range for texture lookup
    vec2 uv = n.xy * 0.5 + 0.5;

    // Clamp to avoid artifacts at the edge
    //uv = clamp(uv, 0.0, 1.0);

    vec4 matcapColor = texture(uMatCap, uv);

    outColor = matcapColor;
}