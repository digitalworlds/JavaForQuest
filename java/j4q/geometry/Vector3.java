package j4q.geometry;

/**
 * Represents a 3D vector and provides methods for vector arithmetic, scaling, normalization,
 * and geometric operations in three-dimensional space.
 * <p>
 * The {@code Vector3} class encapsulates the x, y, and z components of a vector and offers
 * a fluent API for common vector operations.
 * </p>
 */

public class Vector3 {

    /**
     * The x component of the vector.
     */
    public float x;

    /**
     * The y component of the vector.
     */
    public float y;

    /**
     * The z component of the vector.
     */
    public float z;

    /**
     * Default constructor. Initializes the vector to (0, 0, 0).
     */
    public Vector3() {
        this(0, 0, 0);
    }

    /**
     * Constructs a vector with the specified x, y, and z values.
     * @param x The x component.
     * @param y The y component.
     * @param z The z component.
     */
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Copy constructor. Initializes the vector with another vector's values.
     * @param v The vector to copy.
     */
    public Vector3(Vector3 v) {
        this(v.x, v.y, v.z);
    }

    /**
     * Sets the vector's components to the specified values.
     * @param x The x component.
     * @param y The y component.
     * @param z The z component.
     * @return This vector for chaining.
     */
    public Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    /**
     * Sets the vector's components to match another vector.
     * @param v The vector to copy values from.
     * @return This vector for chaining.
     */
    public Vector3 set(Vector3 v) {
        return set(v.x, v.y, v.z);
    }

    /**
     * Adds another vector to this vector.
     * @param v The vector to add.
     * @return This vector for chaining.
     */
    public Vector3 add(Vector3 v) {
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    /**
     * Adds the specified values to the vector's components.
     * @param x The x value to add.
     * @param y The y value to add.
     * @param z The z value to add.
     * @return This vector for chaining.
     */
    public Vector3 add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    /**
     * Subtracts another vector from this vector.
     * @param v The vector to subtract.
     * @return This vector for chaining.
     */
    public Vector3 subtract(Vector3 v) {
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    /**
     * Linearly interpolates between this vector and another vector.
     * @param p2 The target vector.
     * @param a The interpolation factor (clamped between 0 and 1).
     * @return This vector for chaining.
     */
    public Vector3 lerp(Vector3 p2, float a) {
        float b=Math.max(Math.min(a,1),0);
        x=x*(1-b)+p2.x*b;
        y=y*(1-b)+p2.y*b;
        x=z*(1-b)+p2.z*b;
        return this;
    }

    /**
     * Scales the vector by the specified factor.
     * @param s The scale factor.
     * @return This vector for chaining.
     */
    public Vector3 scale(float s) {
        x *= s;
        y *= s;
        z *= s;
        return this;
    }

    /**
     * Computes the dot product of this vector and another vector.
     * @param v The other vector.
     * @return The dot product value.
     */
    public float dot(Vector3 v) {
        return x*v.x + y*v.y + z*v.z;
    }

    /**
     * Computes the cross product of this vector and another vector.
     * @param v The other vector.
     * @return This vector set to the cross product result.
     */
    public Vector3 cross(Vector3 v) {
        float cx = y*v.z - z*v.y;
        float cy = z*v.x - x*v.z;
        float cz = x*v.y - y*v.x;
        return set(cx, cy, cz);
    }

    /**
     * Returns the length (magnitude) of the vector.
     * @return The vector length.
     */
    public float length() {
        return (float)Math.sqrt(x*x + y*y + z*z);
    }

    /**
     * Returns the squared length of the vector.
     * @return The squared vector length.
     */
    public float lengthSquared() {
        return x*x + y*y + z*z;
    }

    /**
     * Normalizes the vector to unit length.
     * @return This vector for chaining.
     */
    public Vector3 normalize() {
        float len = length();
        if(len != 0){
            scale(1f/len);
        }
        return this;
    }

    /**
     * Computes the Euclidean distance between this vector and another vector.
     * @param p The other vector.
     * @return The distance value.
     */
    public float distance(Vector3 p) {
        float dx = x - p.x;
        float dy = y - p.y;
        float dz = z - p.z;
        return (float)Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    /**
     * Returns a copy of this vector.
     * @return A new Vector3 instance with the same values.
     */
    public Vector3 copy() {
        return new Vector3(x,y,z);
    }

    /**
     * Sets all components of the vector to zero.
     * @return This vector for chaining.
     */
    public Vector3 zero() {
        x = y = z = 0;
        return this;
    }

    /**
     * Returns a string representation of the vector.
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
