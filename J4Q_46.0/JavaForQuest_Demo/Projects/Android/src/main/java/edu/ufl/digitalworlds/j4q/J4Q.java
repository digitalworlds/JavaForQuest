package edu.ufl.digitalworlds.j4q;

import edu.ufl.digitalworlds.j4q.activities.QuestActivity;
import edu.ufl.digitalworlds.j4q.controllers.J4QLeftController;
import edu.ufl.digitalworlds.j4q.controllers.J4QRightController;

public class J4Q {
    public static QuestActivity activity;
    public static J4QLeftController leftController=new J4QLeftController();
    public static J4QRightController rightController=new J4QRightController();
    public static float perSec(){return activity.perSec();}
    public static native long stopHapticFeedbackLeft();
    public static native long stopHapticFeedbackRight();
    public static native long applyHapticFeedbackLeft(float amplitude, float seconds, int frequency);
    public static native long applyHapticFeedbackRight(float amplitude, float seconds, int frequency);
}
