package j4q.input;

import j4q.geometry.Orientation;
import j4q.geometry.Vector3;

/**
 * Represents the pose (position and orientation) of a VR controller or object.
 * <p>
 * The {@code J4QPose} class stores the spatial position and orientation using Vector3 and Orientation.
 * </p>
 */
public class J4QPose {

    /**
     * The spatial position of the pose.
     */
    public Vector3 position = new Vector3();

    /**
     * The orientation (quaternion) of the pose.
     */
    public Orientation orientation = new Orientation();
}
