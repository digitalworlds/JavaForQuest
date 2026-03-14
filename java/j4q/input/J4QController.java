package j4q.input;

import android.opengl.Matrix;

import j4q.J4Q;
import j4q.activities.GameEngineScene;
import j4q.geometry.Transform;
import j4q.models.GameObject;

/**
 * Represents a VR controller, providing object picking, input state, and pose information.
 * <p>
 * The {@code J4QController} class manages picking, joystick, trigger, squeeze, aim, and grip states.
 * </p>
 */
public class J4QController extends InputDevice {

    /**
     * The last picked object ID.
     */
    public int id = 0;

    /**
     * The object picker used for picking objects in the scene.
     */
    private ObjectPicker objectPicker;

    /**
     * Constructs a J4QController and initializes its ObjectPicker.
     */
    public J4QController() {
        objectPicker = new ObjectPicker();
        objectPicker.setSize(4, 4);
    }

    /**
     * Captures the current scene for object picking using the controller's aim and projection.
     * @param scene The GameEngineScene to capture.
     */
    public void capture(GameEngineScene scene) {
        objectPicker.begin();
        /*GLES31.glViewport( 0, 0, frameBufferWidth, frameBufferHeight );
        GLES31.glScissor( 0, 0, frameBufferWidth, frameBufferHeight );
        float m[]=new float[16];
        System.arraycopy(sceneMatrices,16,m,0,16);
        scene.setupProjection(m);*/
        float[] ortho = new float[16];
        Matrix.orthoM(
                ortho, 0,
                -0.024f, 0.024f,   // left, right
                -0.024f, 0.024f,   // bottom, top
                0.1f, 1024.0f      // near, far
        );
        scene.setupProjection(ortho);
        Transform t = new Transform();
        t.translate(aim.position);
        t.rotate(aim.orientation);
        scene.setupView(t.getInverseMatrix());
        scene.root.draw(objectPicker.shader);
        id = objectPicker.pick(2, 2);
        objectPicker.end();
    }

    /**
     * Returns the ID of the last picked object.
     * @return The picked object ID.
     */
    public int pick() {
        return id;
    }

    /**
     * Returns the GameObject corresponding to the last picked object ID.
     * @return The picked GameObject, or null if not found.
     */
    public GameObject pickObject() {
        return J4Q.getObject(id);
    }

    /**
     * The joystick input for this controller.
     */
    public J4QJoystick joystick = new J4QJoystick();

    /**
     * The trigger input for this controller.
     */
    public J4QPressureButton trigger = new J4QPressureButton();

    /**
     * The squeeze input for this controller.
     */
    public J4QPressureButton squeeze = new J4QPressureButton();

    /**
     * The aim pose for this controller.
     */
    public J4QPose aim = new J4QPose();

    /**
     * The grip pose for this controller.
     */
    public J4QPose grip = new J4QPose();

    /**
     * Indicates whether the controller is currently active.
     */
    public boolean active = false;
}
