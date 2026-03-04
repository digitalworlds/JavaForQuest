#version 300 es
uniform SceneMatrices
{
	mat4 ViewMatrix;
	mat4 ProjectionMatrix;
	mat4 NormalMatrix;
	vec4 uLightDir;
} sm;
uniform mat4 modelMatrix;
uniform mat4 normalMatrix;

in vec3 aPosition;
in vec2 aUV;
out vec2 vUV;

out vec4 fragmentColor;
mat4 trans;
void main()
{
	trans=sm.ViewMatrix * modelMatrix;
	trans[3][0]=0.0;
	trans[3][1]=0.0;
	trans[3][2]=0.0;
	trans[3][3]=1.0;
	gl_Position = sm.ProjectionMatrix * ( trans *  vec4( aPosition , 1.0 )  );
	vUV=aUV;
}