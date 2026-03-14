package j4q.physics;

import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;

import javax.vecmath.Vector3f;

/**
 * Represents a sphere-shaped rigid body for physics simulation.
 * <p>
 * Uses Bullet Physics SphereShape and supports mass, inertia, and damping configuration.
 * </p>
 */
public class RigidSphere extends RigidBody{

    /**
     * Constructs a sphere-shaped rigid body with the specified radius and mass.
     * @param radius The radius of the sphere.
     * @param mass The mass of the sphere.
     */
    public RigidSphere(float radius, float mass){
        SphereShape sphereShape = new SphereShape(radius); // 1x1x1 cube

        Vector3f inertia = new Vector3f(0, 0, 0);
        if (mass != 0f) {
            sphereShape.calculateLocalInertia(mass, inertia);
        }

        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, null, sphereShape, inertia);
        body = new com.bulletphysics.dynamics.RigidBody(rbInfo);
        body.setSleepingThresholds(0.8f, 0.8f);
        body.setDamping(0.15f, 0.9f);
    }

}
