package j4q.input;

/**
 * Represents a toggle button input, tracking state changes and press/release events.
 * <p>
 * The {@code J4QToggleButton} class provides methods to query button state and transitions.
 * </p>
 */
public class J4QToggleButton {

    /**
     * Indicates if the button state has changed since the last sync.
     */
    public boolean changedSinceLastSync = false;

    /**
     * The current state of the button (pressed or not).
     */
    public boolean currentState = false;

    /**
     * Returns whether the button is currently pressed.
     * @return True if pressed, false otherwise.
     */
    public boolean isPressed() { return currentState; }

    /**
     * Returns whether the button was just pressed since the last sync.
     * @return True if just pressed, false otherwise.
     */
    public boolean justPressed() { return changedSinceLastSync && currentState; }

    /**
     * Returns whether the button was just released since the last sync.
     * @return True if just released, false otherwise.
     */
    public boolean justReleased() { return changedSinceLastSync && !currentState; }
}
