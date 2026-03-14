package j4q.models;

/**
 * Base class for user-defined behaviors attached to GameObjects.
 * <p>
 * Extend {@code MonoBehaviour} to implement custom logic for initialization and per-frame updates.
 * </p>
 */
public abstract class MonoBehaviour extends Component {

    /**
     * Constructs a MonoBehaviour and calls the Start method.
     */
    public MonoBehaviour() {
        Start();
    }

    /**
     * Called when the MonoBehaviour is initialized. Override to implement setup logic.
     */
    public abstract void Start();

    /**
     * Called every frame to update the MonoBehaviour. Override to implement per-frame behavior.
     */
    public abstract void Update();

}
