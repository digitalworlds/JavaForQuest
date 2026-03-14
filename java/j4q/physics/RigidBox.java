package j4q.physics;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;

import javax.vecmath.Vector3f;

/**
 * Represents a box-shaped rigid body for physics simulation.
 * <p>
 * Uses Bullet Physics BoxShape and supports mass, inertia, and damping configuration.
 * </p>
 */
public class RigidBox extends RigidBody{

    /**
     * Constructs a box-shaped rigid body with the specified dimensions and mass.
     * @param width The width of the box.
     * @param height The height of the box.
     * @param depth The depth of the box.
     * @param mass The mass of the box.
     */
    public RigidBox(float width, float height, float depth, float mass){
        BoxShape boxShape = new BoxShape(new Vector3f(width/2f, height/2f, depth/2f));

        Vector3f inertia = new Vector3f(0, 0, 0);
        if (mass != 0f) {
            boxShape.calculateLocalInertia(mass, inertia);
        }
        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, null, boxShape, inertia);
        body = new com.bulletphysics.dynamics.RigidBody(rbInfo);
        body.setSleepingThresholds(0.8f, 0.8f);
        body.setDamping(0.05f, 0.85f);
    }

}
