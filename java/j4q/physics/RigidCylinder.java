package j4q.physics;

import com.bulletphysics.collision.shapes.CylinderShape;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;

import javax.vecmath.Vector3f;

/**
 * Represents a cylinder-shaped rigid body for physics simulation.
 * <p>
 * Uses Bullet Physics CylinderShape and supports mass, inertia, and damping configuration.
 * </p>
 */
public class RigidCylinder extends RigidBody{

    /**
     * Constructs a cylinder-shaped rigid body with the specified dimensions and mass.
     * @param width The width of the cylinder.
     * @param height The height of the cylinder.
     * @param depth The depth of the cylinder.
     * @param mass The mass of the cylinder.
     */
    public RigidCylinder(float width,float height,float depth, float mass){
        CylinderShape cylinderShape = new CylinderShape(new Vector3f(width/2f,height/2f,depth/2f)); // 1x1x1 cube

        Vector3f inertia = new Vector3f(0, 0, 0);
        if (mass != 0f) {
            cylinderShape.calculateLocalInertia(mass, inertia);
        }

        RigidBodyConstructionInfo rbInfo = new RigidBodyConstructionInfo(mass, null, cylinderShape, inertia);
        body = new com.bulletphysics.dynamics.RigidBody(rbInfo);
        body.setSleepingThresholds(0.8f, 0.8f);
        body.setDamping(0.05f, 0.85f);
    }

}
