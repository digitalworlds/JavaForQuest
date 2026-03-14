package j4q.activities;

/**
 * Interface for activities in the game engine lifecycle.
 * <p>
 * Implementations of {@code GameEngineActivity} define startup and update behavior
 * for game engine components or scenes.
 * </p>
 */

public interface GameEngineActivity {

    /**
     * Called when the activity starts. Used for initialization logic.
     */
    void Start();

    /**
     * Called every frame to update the activity's state.
     */
    void Update();
}
