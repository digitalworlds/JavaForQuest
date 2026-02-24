package gl.controllers;


import gl.J4Q;

public class J4QRightController extends J4QController {
    public J4QToggleButton buttonA=new J4QToggleButton();
    public J4QToggleButton buttonB=new J4QToggleButton();
    public void vibrate(float amplitude, float seconds, int frequency){
        J4Q.applyHapticFeedbackRight(amplitude,seconds,frequency);
    }
    public void stopVibration(){
        J4Q.stopHapticFeedbackRight();
    }
}
