package j4q.shaders;

/**
 * Shader for rendering objects with vertex colors.
 * <p>
 * Uses a simple color shader for colored geometry.
 * </p>
 */
public class ColorShader extends Shader {

    /**
     * Constructs a ColorShader for rendering colored geometry.
     */
    public ColorShader() {
        super("shaders/colors", new String[]{"aPosition",null,null,null,"aColor"});
    }
}
