package j4q.physics;

import com.bulletphysics.collision.shapes.CylinderShape;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;

import javax.vecmath.Vector3f;

public class RigidCylinder extends RigidBody{

    public RigidCylinder(float width,float height,float depth, float mass){
        CylinderShape cylinderShape = new CylinderShape(new Vector3f(width/2f,height/2f,depth/2f)); // 1x1x1 cube

        Vector3f inertia = new Vector3f(0, 0, 0);
        if (mass != 0f) {
            cylinderShape.calculateLocalInertia(mass, inertia);
        }

        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, null, cylinderShape, inertia);
        body = new com.bulletphysics.dynamics.RigidBody(rbInfo);
    }

}
