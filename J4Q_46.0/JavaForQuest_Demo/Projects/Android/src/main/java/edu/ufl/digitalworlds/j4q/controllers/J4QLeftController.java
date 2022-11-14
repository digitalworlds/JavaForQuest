package edu.ufl.digitalworlds.j4q.controllers;

import edu.ufl.digitalworlds.j4q.J4Q;

public class J4QLeftController extends J4QController{
    public J4QToggleButton buttonX=new J4QToggleButton();
    public J4QToggleButton buttonY=new J4QToggleButton();
    public J4QToggleButton buttonMenu=new J4QToggleButton();
    public void vibrate(float amplitude, float seconds, int frequency){
        J4Q.applyHapticFeedbackLeft(amplitude,seconds,frequency);
    }
    public void stopVibration(){
        J4Q.stopHapticFeedbackLeft();
    }
}
