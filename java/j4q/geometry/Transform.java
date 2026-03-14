package j4q.geometry;

import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.List;

import j4q.models.GameObject;


/**
 * Represents a 3D transformation matrix and provides methods for manipulating
 * translation, rotation, scaling, and matrix operations in 3D space.
 * <p>
 * The {@code Transform} class encapsulates a 4x4 matrix and offers a fluent API
 * for common transformation operations, including chaining and left-multiplication.
 * </p>
 */
public class Transform {

    /**
     * The 4x4 transformation matrix representing this transform.
     */
    public float[] matrix = new float[16];

    /**
     * Stack for saving and restoring transformation matrices.
     */
    private final List<float[]> matrixList = new ArrayList<>();

    /**
     * Indicates whether the transform has been modified since last reset.
     */
    private boolean modified = true;

    /**
     * Checks if the transform has been modified since last reset.
     * @return True if modified, false otherwise.
     */
    public boolean isModified() { return modified; }

    /**
     * Resets the modified flag to false.
     */
    public void resetModifiedFlag() { modified = false; }

    /**
     * Constructs a Transform from a 4x4 matrix.
     * @param mat The 4x4 matrix array.
     */
    public Transform(float[] mat) {
        System.arraycopy(mat, 0, matrix, 0, 16);
    }

    /**
     * Default constructor. Initializes the transform to identity.
     */
    public Transform() { identity(); }

    /**
     * Copy constructor. Initializes the transform from another transform.
     * @param t The transform to copy.
     */
    public Transform(Transform t) { this(t.matrix); }

    /**
     * Computes the normal matrix (inverse transpose) for this transform.
     * @return The normal matrix as a float array.
     */
    public float[] getNormalMatrix() {
        float[] inv = new float[16];
        Matrix.invertM(inv, 0, matrix, 0);
        float[] trans = new float[16];
        Matrix.transposeM(trans, 0, inv, 0);
        return trans;
    }

    private static void rotateMLeft(float[] m, int offset,
                                   float angle, float x, float y, float z) {
        float[] r = new float[16];
        float[] tmp = new float[16];

        Matrix.setRotateM(r, 0, angle, x, y, z);
        Matrix.multiplyMM(tmp, 0, r, 0, m, offset);
        System.arraycopy(tmp, 0, m, offset, 16);
    }


    /**
     * Rotates the transform by the specified angle and axis.
     * @param degrees The angle in degrees.
     * @param x The x component of the axis.
     * @param y The y component of the axis.
     * @param z The z component of the axis.
     * @return This transform for chaining.
     */
    public Transform rotate(float degrees, float x, float y, float z) {
        Matrix.rotateM(matrix, 0, degrees, x, y, z);
        modified = true;
        return this;
    }

    /**
     * Rotates the transform by the specified angle and axis, applying rotation using left-side multiplication.
     * @param degrees The angle in degrees.
     * @param x The x component of the axis.
     * @param y The y component of the axis.
     * @param z The z component of the axis.
     * @return This transform for chaining.
     */
    public Transform rotateLeft(float degrees, float x, float y, float z) {
        rotateMLeft(matrix, 0, degrees, x, y, z);
        modified = true;
        return this;
    }

    /**
     * Rotates the transform around the X axis by the specified angle.
     * @param degrees The angle in degrees.
     * @return This transform for chaining.
     */
    public Transform rotateX(float degrees) {
        Matrix.rotateM(matrix, 0, degrees, 1, 0, 0.0f);
        modified = true;
        return this;
    }

    /**
     * Rotates the transform around the X axis by the specified angle, applying rotation using left-side multiplication.
     * @param degrees The angle in degrees.
     * @return This transform for chaining.
     */
    public Transform rotateXLeft(float degrees) {
        rotateMLeft(matrix, 0, degrees, 1, 0, 0.0f);
        modified = true;
        return this;
    }

    /**
     * Rotates the transform using quaternion parameters.
     * @param a The scalar component of the quaternion.
     * @param x The x component of the quaternion.
     * @param y The y component of the quaternion.
     * @param z The z component of the quaternion.
     * @return This transform for chaining.
     */
    public Transform rotateQ(float a, float x, float y, float z) {
        float[] m=new float[16];
        //Column 1
        m[0] = (a*a) + (x*x) - (y*y) - (z*z);
        m[1] = (2*x*y) + (2*a*z);
        m[2] = (2*x*z) - (2*a*y);
        m[3] = 0;
        //Column 2
        m[4] = (2*x*y) - (2*a*z);
        m[5] = (a*a) - (x*x) + (y*y) - (z*z);
        m[6] = (2*y*z) + (2*a*x);
        m[7] = 0;
        //Column 3
        m[8] = (2*x*z) + (2*a*y);
        m[9] = (2*y*z) - (2*a*x);
        m[10] = (a*a) - (x*x) - (y*y) + (z*z);
        m[11] = 0;
        //Column 4
        m[12] = 0;
        m[13] = 0;
        m[14] = 0;
        m[15] = 1;
        return multiply(m);
    }

    /**
     * Rotates the transform using quaternion parameters, applying rotation using left-side multiplication.
     * @param a The scalar component of the quaternion.
     * @param x The x component of the quaternion.
     * @param y The y component of the quaternion.
     * @param z The z component of the quaternion.
     * @return This transform for chaining.
     */
    public Transform rotateQLeft(float a, float x, float y, float z) {
        float[] m=new float[16];
        //Column 1
        m[0] = (a*a) + (x*x) - (y*y) - (z*z);
        m[1] = (2*x*y) + (2*a*z);
        m[2] = (2*x*z) - (2*a*y);
        m[3] = 0;
        //Column 2
        m[4] = (2*x*y) - (2*a*z);
        m[5] = (a*a) - (x*x) + (y*y) - (z*z);
        m[6] = (2*y*z) + (2*a*x);
        m[7] = 0;
        //Column 3
        m[8] = (2*x*z) + (2*a*y);
        m[9] = (2*y*z) - (2*a*x);
        m[10] = (a*a) - (x*x) - (y*y) + (z*z);
        m[11] = 0;
        //Column 4
        m[12] = 0;
        m[13] = 0;
        m[14] = 0;
        m[15] = 1;
        return multiplyLeft(m);
    }

    /**
     * Rotates the transform using an Orientation quaternion, applying rotation using left-side multiplication.
     * @param q The Orientation quaternion.
     * @return This transform for chaining.
     */
    public Transform rotateLeft(Orientation q) {
        return this.rotateQLeft(q.w, q.x, q.y, q.z);
    }

    /**
     * Rotates the transform using an Orientation quaternion.
     * @param q The Orientation quaternion.
     * @return This transform for chaining.
     */
    public Transform rotate(Orientation q) {
        return this.rotateQ(q.w, q.x, q.y, q.z);
    }

    /**
     * Rotates the transform around the Y axis by the specified angle.
     * @param degrees The angle in degrees.
     * @return This transform for chaining.
     */
    public Transform rotateY(float degrees) {
        Matrix.rotateM(matrix, 0, degrees, 0, 1, 0.0f);
        modified = true;
        return this;
    }

    /**
     * Rotates the transform around the Y axis by the specified angle, applying rotation using left-side multiplication.
     * @param degrees The angle in degrees.
     * @return This transform for chaining.
     */
    public Transform rotateYLeft(float degrees) {
        rotateMLeft(matrix, 0, degrees, 0, 1, 0.0f);
        modified = true;
        return this;
    }

    /**
     * Rotates the transform around the Z axis by the specified angle.
     * @param degrees The angle in degrees.
     * @return This transform for chaining.
     */
    public Transform rotateZ(float degrees) {
        Matrix.rotateM(matrix, 0, degrees, 0, 0, 1.0f);
        modified = true;
        return this;
    }

    /**
     * Rotates the transform around the Z axis by the specified angle, applying rotation using left-side multiplication.
     * @param degrees The angle in degrees.
     * @return This transform for chaining.
     */
    public Transform rotateZLeft(float degrees) {
        rotateMLeft(matrix, 0, degrees, 0, 0, 1.0f);
        modified = true;
        return this;
    }

    /**
     * Translates the transform by the specified vector.
     * @param p The vector to translate by.
     * @return This transform for chaining.
     */
    public Transform translate(Vector3 p) {
        return translate(p.x, p.y, p.z);
    }
    /**
     * Translates the transform by the specified vector, applying translation using left-side multiplication.
     * @param p The vector to translate by.
     * @return This transform for chaining.
     */
    public Transform translateLeft(Vector3 p) {
        return translateLeft(p.x, p.y, p.z);
    }

    /**
     * Computes the inverse of the transformation matrix.
     * @return The inverse matrix as a float array.
     */
    public float[] getInverseMatrix() {
        float[] inv = new float[16];
        Matrix.invertM(inv, 0, matrix, 0);
        return inv;
    }

    /**
     * Translates the transform by the specified values, applying translation using left-side multiplication.
     * @param x The x translation.
     * @param y The y translation.
     * @param z The z translation.
     * @return This transform for chaining.
     */
    public Transform translateLeft(float x, float y, float z) {
        float[] T = new float[16];
        float[] tmp = new float[16];
        Matrix.setIdentityM(T, 0);
        Matrix.translateM(T, 0, x, y, z);   // build T
        Matrix.multiplyMM(tmp, 0, T, 0, matrix, 0); // T * M
        System.arraycopy(tmp, 0, matrix, 0, 16);
        modified = true;
        return this;
    }

    /**
     * Translates the transform by the specified values.
     * @param x The x translation.
     * @param y The y translation.
     * @param z The z translation.
     * @return This transform for chaining.
     */
    public Transform translate(float x, float y, float z) {
        Matrix.translateM(matrix,0,x,y,z);
        modified=true;
        return this;
    }

    /**
     * Returns the position component of the transform as a Vector3.
     * @return The position vector.
     */
    public Vector3 getPosition() {
        return new Vector3(matrix[12], matrix[13], matrix[14]);
    }

    /**
     * Scales the transform by the specified values.
     * @param x The x scale factor.
     * @param y The y scale factor.
     * @param z The z scale factor.
     * @return This transform for chaining.
     */
    public Transform scale(float x, float y, float z) {
        Matrix.scaleM(matrix,0,x,y,z);
        modified=true;
        return this;
    }

    /**
     * Uniformly scales the transform by the specified factor.
     * @param scale The scale factor.
     * @return This transform for chaining.
     */
    public Transform scale(float scale) {
        return this.scale(scale, scale, scale);
    }
    /**
     * Uniformly scales the transform by the specified factor, applying scaling using left-side multiplication.
     * @param scale The scale factor.
     * @return This transform for chaining.
     */
    public Transform scaleLeft(float scale) {
        return this.scaleLeft(scale, scale, scale);
    }


    /**
     * Scales the transform by the specified values, applying scaling using left-side multiplication.
     * @param sx The x scale factor.
     * @param sy The y scale factor.
     * @param sz The z scale factor.
     * @return This transform for chaining.
     */
    public Transform scaleLeft(float sx, float sy, float sz) {
        float[] S = new float[16];
        float[] tmp = new float[16];
        Matrix.setIdentityM(S, 0);
        Matrix.scaleM(S, 0, sx, sy, sz);
        Matrix.multiplyMM(tmp, 0, S, 0, matrix, 0);
        System.arraycopy(tmp, 0, matrix, 0, 16);
        modified = true;
        return this;
    }

    /**
     * Sets the transform to the identity matrix.
     * @return This transform for chaining.
     */
    public Transform identity() {
        Matrix.setIdentityM(matrix,0);
        modified=true;
        return this;
    }

    /**
     * Resets the transform to the identity matrix.
     * @return This transform for chaining.
     */
    public Transform reset() {
        return identity();
    }

    /**
     * Resets the transform to match another transform.
     * @param t The transform to copy.
     * @return This transform for chaining.
     */
    public Transform reset(Transform t) {
        return reset(t.matrix);
    }

    /**
     * Resets the transform to the specified matrix.
     * @param m The matrix to copy.
     * @return This transform for chaining.
     */
    public Transform reset(float[] m) {
        System.arraycopy(m,0,matrix,0,16);
        modified=true;
       return this;
    }

    /**
     * Resets the transform to match the global transform of a GameObject.
     * @param m The GameObject whose global transform is used.
     * @return This transform for chaining.
     */
    public Transform reset(GameObject m) {
        if (m != null)
            return reset(m.globalTransform.matrix);
        else return reset();
    }

    /**
     * Multiplies the current matrix by the specified matrix (right multiplication).
     * @param mat The matrix to multiply by.
     * @return This transform for chaining.
     */
    public Transform multiply(float[] mat) {
        float[] copy=new float[16];
        System.arraycopy(matrix,0,copy,0,16);
        Matrix.multiplyMM(matrix,0,copy,0,mat,0);
        modified=true;
        return this;
    }

    /**
     * Multiplies the current matrix by the specified matrix (left multiplication).
     * @param mat The matrix to multiply by.
     * @return This transform for chaining.
     */
    public Transform multiplyLeft(float[] mat) {
        float[] copy = new float[16];
        System.arraycopy(matrix, 0, copy, 0, 16);
        Matrix.multiplyMM(matrix, 0, mat, 0, copy, 0);
        modified = true;
        return this;
    }

    /**
     * Saves the current matrix to the stack and creates a new matrix for further transformations.
     * @return This transform for chaining.
     */
    public Transform pushMatrix() {
        matrixList.add(matrix) ;
        float[] m=new float[16];
        for(int i=0;i<16;i++)m[i]=matrix[i];
        matrix=m;
        return this;
    }

    /**
     * Alias for pushMatrix(). Saves the current matrix to the stack.
     * @return This transform for chaining.
     */
    public Transform save() {
        return pushMatrix();
    }

    /**
     * Restores the last saved matrix from the stack.
     * @return This transform for chaining.
     */
    public Transform popMatrix() {
        if(matrixList.size()>0)
            matrix=matrixList.remove(matrixList.size()-1);
        return this;
    }

    /**
     * Alias for popMatrix(). Restores the last saved matrix from the stack.
     * @return This transform for chaining.
     */
    public Transform restore() {
        return popMatrix();
    }

}
