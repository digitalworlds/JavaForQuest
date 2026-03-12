package j4q.physics;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;

import javax.vecmath.Vector3f;

public class RigidBox extends RigidBody{

    public RigidBox(float width, float height, float depth, float mass){
        BoxShape boxShape = new BoxShape(new Vector3f(width/2f, height/2f, depth/2f));

        Vector3f inertia = new Vector3f(0, 0, 0);
        if (mass != 0f) {
            boxShape.calculateLocalInertia(mass, inertia);
        }
        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, null, boxShape, inertia);
        body = new com.bulletphysics.dynamics.RigidBody(rbInfo);
    }

}
