package j4q.models;

/**
 * Abstract base class for components attached to GameObjects in the scene graph.
 * <p>
 * The {@code Component} class enables modular behavior and properties for GameObjects.
 * </p>
 */
public abstract class Component {

    /**
     * The GameObject to which this component is attached.
     */
    public GameObject gameObject;
}