package j4q.shaders;

import android.content.Context;
import android.opengl.GLES30;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import j4q.J4Q;
import j4q.models.Component;
import j4q.models.Mesh;

/**
 * Base class for OpenGL ES shader management and uniform handling.
 * <p>
 * Provides methods for compiling shaders, setting uniforms, binding buffers, and rendering meshes.
 * </p>
 */
public class Shader extends Component {


    /**
     * The OpenGL ES shader program handle.
     */
    public int shaderProgram = 0;
    // public int sceneMatricesBuffer;

    //Attribute Locations compatible with Mesh class: 0:Positions, 1:Normals, 2:UV, 3:Tangents, 4:Colors
    /**
     * Constructs a Shader from asset files and attribute locations.
     * @param filename The base filename for vertex and fragment shader assets.
     * @param attribLocations Attribute locations for shader inputs.
     */
    public Shader(String filename, String[] attribLocations){
        this(readAsset(J4Q.activity,filename+".vert"),readAsset(J4Q.activity,filename+".frag"),attribLocations);
    }

    /**
     * Constructs a Shader from raw vertex and fragment shader code.
     * @param vertexShaderCode Vertex shader source code.
     * @param fragmentShaderCode Fragment shader source code.
     */
    public Shader(String vertexShaderCode, String fragmentShaderCode){
        shaderProgram=Shader.compileShader(vertexShaderCode,fragmentShaderCode,new String[]{"vertexPosition"});
        GLES30.glUseProgram(shaderProgram);
    }

    /**
     * Constructs a Shader from raw vertex and fragment shader code with custom attribute locations.
     * @param vertexShaderCode Vertex shader source code.
     * @param fragmentShaderCode Fragment shader source code.
     * @param attribLocations Attribute locations for shader inputs.
     */
    public Shader(String vertexShaderCode, String fragmentShaderCode, String[] attribLocations){
        shaderProgram=Shader.compileShader(vertexShaderCode,fragmentShaderCode,attribLocations);
        GLES30.glUseProgram(shaderProgram);
    }

   /* public void setSceneMatricesBuffer(int buffer){
        sceneMatricesBuffer=buffer;
        //GLES30.glUseProgram(shaderProgram);
        GLES30.glBindBufferBase( GLES30.GL_UNIFORM_BUFFER, 0, sceneMatricesBuffer  );
        //GLES30.glUseProgram(0);
    }*/

    /**
     * Sets a uniform buffer at the specified slot.
     * @param buffer The buffer handle.
     * @param slot The binding slot.
     */
    public void setUniformBuffer(int buffer,int slot){
        // Implementation placeholder
    }

    /**
     * Activates the shader program for use in OpenGL ES environment.
     */
    public void use(){
        GLES30.glUseProgram(shaderProgram);
    }


    /**
     * Sets a float uniform value in the shader.
     * @param name The uniform name.
     * @param value The float value.
     */
    public void setUniformFloat(String name, float value){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniform1f(mHandle,value);
    }

    /**
     * Sets an integer uniform value in the shader.
     * @param name The uniform name.
     * @param value The integer value.
     */
    public void setUniformInteger(String name, int value){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniform1i(mHandle,value);
    }

    /**
     * Sets a vec2 uniform value in the shader.
     * @param name The uniform name.
     * @param value The float array (length 2).
     */
    public void setUniformVec2(String name, float[] value){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniform2fv(mHandle, 1,value , 0);
    }

    /**
     * Sets a vec3 uniform value in the shader.
     * @param name The uniform name.
     * @param value The float array (length 3).
     */
    public void setUniformVec3(String name, float[] value){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniform3fv(mHandle, 1,value , 0);
    }

    /**
     * Sets a vec4 uniform value in the shader.
     * @param name The uniform name.
     * @param value The float array (length 4).
     */
    public void setUniformVec4(String name, float[] value){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniform4fv(mHandle, 1,value , 0);
    }

    /**
     * Sets a mat3 uniform value in the shader.
     * @param name The uniform name.
     * @param value The float array (length 9).
     */
    public void setUniformMat3(String name, float[] value){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniformMatrix3fv(mHandle, 1,false,value , 0);
    }

    /**
     * Sets a mat4 uniform value in the shader.
     * @param name The uniform name.
     * @param value The float array (length 16).
     */
    public void setUniformMat4(String name, float[] value){
        int mHandle = GLES30.glGetUniformLocation(shaderProgram, name);
        GLES30.glUniformMatrix4fv(mHandle, 1,false,value , 0);
    }

    /**
     * Gets the location of a uniform variable in the shader program.
     * @param name The uniform name.
     * @return The location handle.
     */
    public int getUniformLocation(String name){
        return GLES30.glGetUniformLocation(shaderProgram, name);
    }



    /**
     * Compiles a shader of the given type from source code.
     * @param type The shader type (GLES30.GL_VERTEX_SHADER or GLES30.GL_FRAGMENT_SHADER).
     * @param shaderCode The shader source code.
     * @return The compiled shader handle.
     */
    public static int compileShader(int type, String shaderCode){
        int shader = GLES30.glCreateShader(type);
        GLES30.glShaderSource(shader, shaderCode);
        GLES30.glCompileShader(shader);
        return shader;
    }

    /**
     * Compiles and links a shader program from vertex and fragment shader code with attribute locations.
     * @param vertexShaderCode Vertex shader source code.
     * @param fragmentShaderCode Fragment shader source code.
     * @param attribLocations Attribute locations for shader inputs.
     * @return The linked shader program handle.
     */
    public static int compileShader(String vertexShaderCode, String fragmentShaderCode, String[] attribLocations){
        int vertexShader = Shader.compileShader(GLES30.GL_VERTEX_SHADER, vertexShaderCode);
        int[] compileStatus = new int[1];
        GLES30.glGetShaderiv(vertexShader, GLES30.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            String error = GLES30.glGetShaderInfoLog(vertexShader);
            GLES30.glDeleteShader(vertexShader);
            throw new RuntimeException("VertexShader compile failed:\n" + error);
        }
        int fragmentShader = Shader.compileShader(GLES30.GL_FRAGMENT_SHADER, fragmentShaderCode);
        GLES30.glGetShaderiv(fragmentShader, GLES30.GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == 0) {
            String error = GLES30.glGetShaderInfoLog(fragmentShader);
            GLES30.glDeleteShader(fragmentShader);
            throw new RuntimeException("FragmentShader compile failed:\n" + error);
        }
        int mProgram = GLES30.glCreateProgram();
        GLES30.glAttachShader(mProgram, vertexShader);
        GLES30.glAttachShader(mProgram, fragmentShader);
        for(int i=0;i<attribLocations.length;i++)
            if(attribLocations[i]!=null)
                GLES30.glBindAttribLocation( mProgram, i, attribLocations[i] );
        GLES30.glLinkProgram(mProgram);
        int[] linkStatus = new int[1];
        GLES30.glGetProgramiv(mProgram, GLES30.GL_LINK_STATUS,linkStatus,0);
        if (linkStatus[0] != GLES30.GL_TRUE) {
            String error = GLES30.glGetProgramInfoLog(mProgram);
            throw new RuntimeException("GLES Error: "+error);
        }
        int l= GLES30.glGetUniformBlockIndex( mProgram, "SceneMatrices" );
        GLES30.glUniformBlockBinding( mProgram, l, 0 );
        return mProgram;
    }

    /**
     * Renders the mesh using this shader program.
     * @param mesh The mesh to render.
     */
    public void render(Mesh mesh) {
        GLES30.glUseProgram(shaderProgram);
        GLES30.glBindVertexArray(mesh.vertexArrayObject);
        GLES30.glDrawElements(GLES30.GL_TRIANGLES, mesh.triangleLength, GLES30.GL_UNSIGNED_SHORT, 0);
        GLES30.glBindVertexArray(0);
        GLES30.glUseProgram(0);
    }

    /**
     * Reads a shader asset file as a UTF-8 string.
     * @param ctx The Android context.
     * @param path The asset file path.
     * @return The file contents as a string.
     */
    private static String readAsset(Context ctx, String path) {
        try (InputStream is = ctx.getAssets().open(path);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[4096];
            int r;
            while ((r = is.read(buf)) != -1) baos.write(buf, 0, r);
            return baos.toString("UTF-8");
        } catch (IOException e) { throw new RuntimeException(e); }
    }
}
