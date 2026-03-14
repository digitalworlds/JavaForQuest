package j4q.shaders;

import android.opengl.GLES30;

import j4q.models.Mesh;

/**
 * Shader for object picking using unique color encoding.
 * <p>
 * Encodes object IDs as colors for selection in the rendering pipeline.
 * </p>
 */
public class ColorPickerShader extends Shader {

    /**
     * Uniform location for the object ID color in the shader.
     */
    private int uObjectId;

    /**
     * Constructs a ColorPickerShader for object selection.
     */
    public ColorPickerShader() {
        super("shaders/colorpicker", new String[]{"aPosition"});
        uObjectId=GLES30.glGetUniformLocation(shaderProgram, "uObjectId");
    }

    /**
     * Sets the object ID for picking, encoding it as a color.
     * @param id The object ID to encode.
     * @return This shader instance for chaining.
     */
    public ColorPickerShader setObjectID(int id){
        float red = (id & 0xFF) / 255.0f;
        float green = ((id >> 8) & 0xFF) / 255.0f;
        float blue = ((id >> 16) & 0xFF) / 255.0f;
        GLES30.glUniform3f(uObjectId,red,green,blue);
        return this;
    }

    /**
     * Renders the mesh using the color picker shader, encoding the object ID as a color.
     * @param mesh The mesh to render.
     */
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
