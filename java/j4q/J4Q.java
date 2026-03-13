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

public class J4Q {


    private static Map<Class<?>, InputDevice> inputDevices = new HashMap<>();

    public static <T extends InputDevice> void addInputDevice(T inputDevice) {
        inputDevices.put(inputDevice.getClass(), inputDevice);
    }

    public static <T extends InputDevice> T getInputDevice(Class<T> type) {
        return type.cast(inputDevices.get(type));
    }

    public static <T extends InputDevice> T removeInputDevice(Class<T> type) {

        InputDevice removed = inputDevices.remove(type);
        if (removed == null) return null;

        return type.cast(removed);
    }


    private static int objectID=0;
    private static HashMap<Integer, Mesh> objects=new HashMap<>();
    public static GameObject getObject(int ID){Mesh m=objects.get(ID);
        // Log.d("Angelos","ID:="+ID+" Mesh="+m);
        if(m==null) return null; else{
            //   Log.d("Angelos","Object="+m.gameObject);
            return m.gameObject;
        }
    }

    public static HashMap<Integer,Mesh> getObjects(){return objects;}

    //public static TouchScreen touchScreen;
    public static int newObjectID(Mesh mesh){objectID+=1;objects.put(objectID,mesh);return objectID;}
    public static PhysicsEngine physicsEngine;

    public static Context activity;
    public static GameEngineScene scene;
    public static float perSec(){return scene.perSec();}

    public static J4QLeftController leftController=new J4QLeftController();
    public static J4QRightController rightController=new J4QRightController();
    public static native long stopHapticFeedbackLeft();
    public static native long stopHapticFeedbackRight();
    public static native long applyHapticFeedbackLeft(float amplitude, float seconds, int frequency);
    public static native long applyHapticFeedbackRight(float amplitude, float seconds, int frequency);
}
