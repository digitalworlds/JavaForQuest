package j4q.physics;

import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;

import javax.vecmath.Vector3f;

public class RigidPlane extends RigidBody{

    public RigidPlane(float nx, float ny, float nz){
        StaticPlaneShape floorShape = new StaticPlaneShape(new Vector3f(nx,ny,nz),0f);

        // Static object → mass = 0
        float mass = 0f;
        // Inertia is zero for static objects
        Vector3f inertia = new Vector3f(0f, 0f, 0f);

        // Build the floor body
        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, null, floorShape, inertia);
        body = new com.bulletphysics.dynamics.RigidBody(rbInfo);
    }

}
