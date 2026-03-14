package j4q;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import j4q.activities.GameEngineScene;
import j4q.input.InputDevice;
import j4q.input.J4QLeftController;
import j4q.input.J4QRightController;
import j4q.models.Component;
import j4q.models.GameObject;
import j4q.models.Mesh;
import j4q.physics.PhysicsEngine;
import j4q.physics.RigidBody;
import j4q.shaders.Shader;

/**
 * Central utility and state class for the J4Q engine.
 * <p>
 * Manages input devices, object registry, scene, physics engine, and haptic feedback controls.
 * </p>
 */
public class J4Q {


    /**
     * Registry of input devices by class type.
     */
    private static Map<Class<?>, InputDevice> inputDevices = new HashMap<>();

    /**
     * Adds an input device to the registry.
     * @param inputDevice The input device to add.
     * @param <T> InputDevice subtype.
     */
    public static <T extends InputDevice> void addInputDevice(T inputDevice) {
        inputDevices.put(inputDevice.getClass(), inputDevice);
    }

    /**
     * Retrieves an input device by class type.
     * @param type The class type of the input device.
     * @param <T> InputDevice subtype.
     * @return The input device instance, or null if not found.
     */
    public static <T extends InputDevice> T getInputDevice(Class<T> type) {
        return type.cast(inputDevices.get(type));
    }

    /**
     * Removes an input device by class type.
     * @param type The class type of the input device.
     * @param <T> InputDevice subtype.
     * @return The removed input device instance, or null if not found.
     */
    public static <T extends InputDevice> T removeInputDevice(Class<T> type) {
        InputDevice removed = inputDevices.remove(type);
        if (removed == null) return null;
        return type.cast(removed);
    }


    /**
     * The next available object ID.
     */
    private static int objectID = 0;
    /**
     * Registry of mesh objects by ID.
     */
    private static HashMap<Integer, Mesh> objects = new HashMap<>();

    /**
     * Retrieves the GameObject associated with a given ID.
     * @param ID The object ID.
     * @return The GameObject instance, or null if not found.
     */
    public static GameObject getObject(int ID) {
        Mesh m = objects.get(ID);
        if (m == null) return null;
        else return m.gameObject;
    }

    /**
     * Returns the registry of mesh objects.
     * @return HashMap of object IDs to Mesh instances.
     */
    public static HashMap<Integer, Mesh> getObjects() { return objects; }

    /**
     * Registers a mesh object and returns its new object ID.
     * @param mesh The mesh to register.
     * @return The new object ID.
     */
    public static int newObjectID(Mesh mesh) { objectID += 1; objects.put(objectID, mesh); return objectID; }

    /**
     * The global physics engine instance.
     */
    public static PhysicsEngine physicsEngine;

    /**
     * The global Android activity context.
     */
    public static Context activity;

    /**
     * The current game engine scene.
     */
    public static GameEngineScene scene;

    /**
     * Returns the per-second timing value from the scene.
     * @return The per-second value.
     */
    public static float perSec() { return scene.perSec(); }

    /**
     * Stops haptic feedback on the left controller.
     * @return Native result code.
     */
    public static native long stopHapticFeedbackLeft();

    /**
     * Stops haptic feedback on the right controller.
     * @return Native result code.
     */
    public static native long stopHapticFeedbackRight();

    /**
     * Applies haptic feedback to the left controller.
     * @param amplitude The vibration amplitude.
     * @param seconds The duration in seconds.
     * @param frequency The vibration frequency.
     * @return Native result code.
     */
    public static native long applyHapticFeedbackLeft(float amplitude, float seconds, int frequency);

    /**
     * Applies haptic feedback to the right controller.
     * @param amplitude The vibration amplitude.
     * @param seconds The duration in seconds.
     * @param frequency The vibration frequency.
     * @return Native result code.
     */
    public static native long applyHapticFeedbackRight(float amplitude, float seconds, int frequency);
}
