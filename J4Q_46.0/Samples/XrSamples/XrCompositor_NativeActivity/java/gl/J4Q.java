package gl;

import android.content.Context;

import gl.activities.GameEngineScene;
import gl.activities.QuestActivity;
import gl.controllers.J4QLeftController;
import gl.controllers.J4QRightController;

public class J4Q {
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
