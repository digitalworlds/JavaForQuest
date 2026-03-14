package j4q.physics;

import com.bulletphysics.collision.dispatch.CollisionFlags;
import com.bulletphysics.collision.dispatch.CollisionObject;

import javax.vecmath.Vector3f;

import j4q.J4Q;
import j4q.geometry.Transform;
import j4q.models.Component;
import j4q.models.GameObject;

/**
 * Represents a physical rigid body component for a GameObject.
 * <p>
 * Integrates with Bullet Physics for simulation, supporting kinematic and dynamic modes.
 * </p>
 */
public class RigidBody extends Component {

    /**
     * The underlying Bullet physics rigid body instance.
     */
    protected com.bulletphysics.dynamics.RigidBody body;

    /**
     * Sets the world transform of the rigid body using a Transform.
     * @param t The transform to apply.
     */
    public void setWorldTransform(Transform t){
        com.bulletphysics.linearmath.Transform startTransform = new com.bulletphysics.linearmath.Transform();
        startTransform.setFromOpenGLMatrix(t.matrix);
        if (body.getInvMass() != 0f) {
            body.setLinearVelocity(new Vector3f(0, 0, 0));
            body.setAngularVelocity(new Vector3f(0, 0, 0));
        }
        body.clearForces();
        body.setWorldTransform(startTransform);
        body.activate();
    }

    /**
     * Sets the world transform of the rigid body using a GameObject's transform.
     * @param object The GameObject whose transform to apply.
     */
    public void setWorldTransform(GameObject object){
        setWorldTransform(object.transform);
    }

    /**
     * Adds this rigid body to the physics engine and simulation world.
     */
    public void addToEngine(){
        J4Q.physicsEngine.bodies.add(this);
        J4Q.physicsEngine.getDynamicsWorld().addRigidBody(body);
    }

    /**
     * Removes this rigid body from the physics engine and simulation world.
     */
    public void removeFromEngine(){
        J4Q.physicsEngine.bodies.remove(this);
        J4Q.physicsEngine.getDynamicsWorld().removeRigidBody(body);
    }


    /**
     * Retrieves the model matrix representing the rigid body's world transform.
     * @return The 4x4 model matrix as a float array.
     */
    private float[] getModelMatrix(){
        com.bulletphysics.linearmath.Transform trans = new com.bulletphysics.linearmath.Transform();
        body.getWorldTransform(trans);
        float[] result = new float[16];
        trans.getOpenGLMatrix(result);
       return result;
    }

    /**
     * Updates the GameObject's transform to match the rigid body's world transform.
     */
    public void update(){
        if(gameObject!=null)
             gameObject.transform.reset(getModelMatrix());
    }

    /**
     * Returns the underlying Bullet physics rigid body instance.
     * @return The Bullet RigidBody object.
     */
    public com.bulletphysics.dynamics.RigidBody getBody(){
        return body;
    }

    /**
     * Sets the rigid body to kinematic mode (not affected by physics, but can be moved).
     */
    public void makeKinematic(){
        body.setCollisionFlags(body.getCollisionFlags() | CollisionFlags.KINEMATIC_OBJECT );
        body.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
        body.activate();
    }

    /**
     * Sets the rigid body to dynamic mode (affected by physics simulation).
     */
    public void makeDynamic(){
        body.setCollisionFlags(body.getCollisionFlags() & ~CollisionFlags.KINEMATIC_OBJECT);
        body.setActivationState(CollisionObject.ACTIVE_TAG);
        body.setLinearVelocity(new Vector3f(0,0,0));
        body.setAngularVelocity(new Vector3f(0,0,0));
        body.activate();

    }

}
