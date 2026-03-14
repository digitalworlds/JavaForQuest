package j4q.geometry;

/**
 * Represents a quaternion orientation in 3D space.
 * <p>
 * The {@code Orientation} class stores the four components of a quaternion (w, x, y, z)
 * used for representing rotations and orientations in three-dimensional space.
 * </p>
 * <ul>
 *   <li>{@code w} - The scalar component.</li>
 *   <li>{@code x} - The x component of the vector part.</li>
 *   <li>{@code y} - The y component of the vector part.</li>
 *   <li>{@code z} - The z component of the vector part.</li>
 * </ul>
 */
public class Orientation {

    /**
     * The scalar component of the quaternion.
     */
    public float w = 0;

    /**
     * The x component of the vector part of the quaternion.
     */
    public float x = 0;

    /**
     * The y component of the vector part of the quaternion.
     */
    public float y = 0;

    /**
     * The z component of the vector part of the quaternion.
     */
    public float z = 0;
}
