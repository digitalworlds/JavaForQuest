package gl.shaders;

public class ColorShader extends Shader {

    public ColorShader() {
        super("shaders/colors", new String[]{"aPosition",null,null,null,"aColor"});

    }
}
