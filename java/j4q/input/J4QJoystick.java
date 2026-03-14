package j4q.input;

/**
 * Represents a joystick input device, extending toggle button functionality.
 * <p>
 * The {@code J4QJoystick} class provides access to the current X and Y values of the joystick.
 * </p>
 */

public class J4QJoystick extends J4QToggleButton {

    /**
     * The current X and Y values of the joystick.
     */
    public float[] currentValue = new float[2];

    /**
     * Returns the current X value of the joystick.
     * @return The X axis value.
     */
    public float getX() { return currentValue[0]; }

    /**
     * Returns the current Y value of the joystick.
     * @return The Y axis value.
     */
    public float getY() { return currentValue[1]; }
}
