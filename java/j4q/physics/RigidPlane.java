package j4q.physics;

import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;

import javax.vecmath.Vector3f;

/**
 * Represents a static plane rigid body for physics simulation.
 * <p>
 * Uses Bullet Physics StaticPlaneShape for immovable surfaces like floors and walls.
 * </p>
 */
public class RigidPlane extends RigidBody{

    /**
     * Constructs a static plane rigid body with the specified normal vector.
     * @param nx X component of the normal.
     * @param ny Y component of the normal.
     * @param nz Z component of the normal.
     */
    public RigidPlane(float nx, float ny, float nz){
        StaticPlaneShape floorShape = new StaticPlaneShape(new Vector3f(nx,ny,nz),0f);

        // Static object → mass = 0
        float mass = 0f;
        // Inertia is zero for static objects
        Vector3f inertia = new Vector3f(0f, 0f, 0f);

        // Build the floor body
        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, null, floorShape, inertia);
        body = new com.bulletphysics.dynamics.RigidBody(rbInfo);
        body.setSleepingThresholds(0.8f, 0.8f);
        body.setDamping(0.05f, 0.85f);
    }

}
