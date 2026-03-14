package j4q.input;

/**
 * Represents a pressure-sensitive button, extending toggle button functionality.
 * <p>
 * The {@code J4QPressureButton} class provides access to the current pressure value.
 * </p>
 */

public class J4QPressureButton extends J4QToggleButton {

    /**
     * The current pressure value of the button.
     */
    public float currentValue = 0;

    /**
     * Returns the current pressure value of the button.
     * @return The pressure value.
     */
    public float getPressure() { return currentValue; }
}
