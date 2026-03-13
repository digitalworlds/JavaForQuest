#version 300 es
precision mediump float;
out lowp vec4 outColor;

in vec3 vNormal;
in vec3 vPos;

uniform sampler2D uMatCap;

void main() {

    vec3 n = normalize(vNormal);
    vec3 viewDir = normalize(-vPos);
    vec3 r = reflect(viewDir, n);


    float m = 2.0 * sqrt(
    r.x*r.x +
    r.y*r.y +
    (r.z+1.0)*(r.z+1.0)
    );

    vec2 uv = r.xy / m + 0.5;

    vec4 matcapColor = texture(uMatCap, uv);

    outColor = matcapColor;
}