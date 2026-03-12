package j4q.physics;

import com.bulletphysics.collision.dispatch.CollisionFlags;
import com.bulletphysics.collision.dispatch.CollisionObject;

import javax.vecmath.Vector3f;

import j4q.J4Q;
import j4q.geometry.Transform;
import j4q.models.Component;
import j4q.models.GameObject;

public class RigidBody extends Component {

    protected com.bulletphysics.dynamics.RigidBody body;

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

    public void setWorldTransform(GameObject object){
        setWorldTransform(object.transform);
    }

    public void addToEngine(){
        J4Q.physicsEngine.bodies.add(this);
        J4Q.physicsEngine.getDynamicsWorld().addRigidBody(body);
    }

    public void removeFromEngine(){
        J4Q.physicsEngine.bodies.remove(this);
        J4Q.physicsEngine.getDynamicsWorld().removeRigidBody(body);
    }


    private float[] getModelMatrix(){
        com.bulletphysics.linearmath.Transform trans = new com.bulletphysics.linearmath.Transform();
        body.getWorldTransform(trans);
        float[] result = new float[16];
        trans.getOpenGLMatrix(result);
       return result;
    }

    public void update(){
        if(gameObject!=null)
             gameObject.transform.reset(getModelMatrix());
    }

    public com.bulletphysics.dynamics.RigidBody getBody(){
        return body;
    }

    public void makeKinematic(){
        body.setCollisionFlags(body.getCollisionFlags() | CollisionFlags.KINEMATIC_OBJECT );
        body.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
        body.activate();
    }

    public void makeDynamic(){
        body.setCollisionFlags(body.getCollisionFlags() & ~CollisionFlags.KINEMATIC_OBJECT);
        body.setActivationState(CollisionObject.ACTIVE_TAG);
        body.setLinearVelocity(new Vector3f(0,0,0));
        body.setAngularVelocity(new Vector3f(0,0,0));
        body.activate();

    }

}
