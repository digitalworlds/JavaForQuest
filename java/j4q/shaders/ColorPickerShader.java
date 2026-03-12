package j4q.shaders;

import android.opengl.GLES30;

import j4q.models.Mesh;

public class ColorPickerShader extends Shader {

    private int uObjectId;

    public ColorPickerShader() {
        super("shaders/colorpicker", new String[]{"aPosition"});
        uObjectId=GLES30.glGetUniformLocation(shaderProgram, "uObjectId");
    }

    public ColorPickerShader setObjectID(int id){
        float red = (id & 0xFF) / 255.0f;
        float green = ((id >> 8) & 0xFF) / 255.0f;
        float blue = ((id >> 16) & 0xFF) / 255.0f;
        GLES30.glUniform3f(uObjectId,red,green,blue);
        return this;
    }

    @Override
    public void render(Mesh mesh) {
        GLES30.glUseProgram(shaderProgram);

        setObjectID(mesh.getObjectID());

        GLES30.glBindVertexArray(mesh.vertexArrayObject);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, mesh.triangleLength, GLES30.GL_UNSIGNED_SHORT, 0);
        GLES30.glBindVertexArray(0);
        GLES30.glUseProgram(0);
    }
}
