package j4q.input;

import j4q.J4Q;


/**
 * Represents the left VR controller, providing access to buttons and haptic feedback.
 * <p>
 * The {@code J4QLeftController} class manages button states and vibration for the left controller.
 * </p>
 */
public class J4QLeftController extends J4QController {

    /**
     * The X button on the left controller.
     */
    public J4QToggleButton buttonX = new J4QToggleButton();

    /**
     * The Y button on the left controller.
     */
    public J4QToggleButton buttonY = new J4QToggleButton();

    /**
     * The Menu button on the left controller.
     */
    public J4QToggleButton buttonMenu = new J4QToggleButton();

    /**
     * Triggers haptic feedback (vibration) on the left controller.
     * @param amplitude The vibration amplitude.
     * @param seconds The duration in seconds.
     * @param frequency The vibration frequency.
     */
    public void vibrate(float amplitude, float seconds, int frequency) {
        J4Q.applyHapticFeedbackLeft(amplitude, seconds, frequency);
    }

    /**
     * Stops haptic feedback (vibration) on the left controller.
     */
    public void stopVibration() {
        J4Q.stopHapticFeedbackLeft();
    }
}
