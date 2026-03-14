package j4q.input;

import j4q.J4Q;


/**
 * Represents the right VR controller, providing access to buttons and haptic feedback.
 * <p>
 * The {@code J4QRightController} class manages button states and vibration for the right controller.
 * </p>
 */
public class J4QRightController extends J4QController {

    /**
     * The A button on the right controller.
     */
    public J4QToggleButton buttonA = new J4QToggleButton();

    /**
     * The B button on the right controller.
     */
    public J4QToggleButton buttonB = new J4QToggleButton();

    /**
     * Triggers haptic feedback (vibration) on the right controller.
     * @param amplitude The vibration amplitude.
     * @param seconds The duration in seconds.
     * @param frequency The vibration frequency.
     */
    public void vibrate(float amplitude, float seconds, int frequency) {
        J4Q.applyHapticFeedbackRight(amplitude, seconds, frequency);
    }

    /**
     * Stops haptic feedback (vibration) on the right controller.
     */
    public void stopVibration() {
        J4Q.stopHapticFeedbackRight();
    }
}
