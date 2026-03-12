package j4q.physics;


import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;

import java.util.ArrayList;

import javax.vecmath.Vector3f;

public class PhysicsEngine {
    private DiscreteDynamicsWorld dynamicsWorld;

    ArrayList<RigidBody> bodies=new ArrayList<>();

    public PhysicsEngine() {
        // Collision configuration
        DefaultCollisionConfiguration collisionConfig = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfig);

        // Broadphase
        DbvtBroadphase broadphase = new DbvtBroadphase();

        // Solver
        SequentialImpulseConstraintSolver solver = new SequentialImpulseConstraintSolver();

        // Create world
        dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfig);
        dynamicsWorld.setGravity(new Vector3f(0f, -9.81f, 0f));
    }

    public void stepSimulation(float deltaTime) {
        dynamicsWorld.stepSimulation(deltaTime);
        for (RigidBody body : bodies) {
            body.update();
        }
    }

    public DiscreteDynamicsWorld getDynamicsWorld() {
        return dynamicsWorld;
    }

}
