package j4q;

import android.content.Context;

import java.util.HashMap;

import j4q.activities.GameEngineScene;
import j4q.controllers.J4QLeftController;
import j4q.controllers.J4QRightController;
import j4q.models.GameObject;
import j4q.models.Mesh;
import j4q.physics.PhysicsEngine;

public class J4Q {
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
