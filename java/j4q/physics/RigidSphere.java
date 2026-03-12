package j4q.physics;

import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;

import javax.vecmath.Vector3f;

public class RigidSphere extends RigidBody{

    public RigidSphere(float radius, float mass){
        SphereShape sphereShape = new SphereShape(radius); // 1x1x1 cube

        Vector3f inertia = new Vector3f(0, 0, 0);
        if (mass != 0f) {
            sphereShape.calculateLocalInertia(mass, inertia);
        }

        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, null, sphereShape, inertia);
        body = new com.bulletphysics.dynamics.RigidBody(rbInfo);
    }

}
