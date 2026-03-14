package j4q.physics;


import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;

import java.util.ArrayList;

import javax.vecmath.Vector3f;

/**
 * Manages the physics simulation using Bullet Physics.
 * <p>
 * Handles world creation, gravity, rigid body management, and simulation steps.
 * </p>
 */
public class PhysicsEngine {
    /**
     * The Bullet physics world instance.
     */
    private DiscreteDynamicsWorld dynamicsWorld;

    /**
     * List of rigid bodies managed by the physics engine.
     */
    ArrayList<RigidBody> bodies = new ArrayList<>();

    /**
     * Constructs a new PhysicsEngine and initializes the Bullet physics world.
     */
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
        dynamicsWorld.getSolverInfo().numIterations = 10;
    }

    /**
     * Advances the physics simulation by the given time step.
     * Updates all managed rigid bodies.
     * @param deltaTime The time step in seconds.
     */
    public void stepSimulation(float deltaTime) {
        dynamicsWorld.stepSimulation(deltaTime);
        for (RigidBody body : bodies) {
            body.update();
        }
    }

    /**
     * Returns the Bullet physics world instance.
     * @return The DiscreteDynamicsWorld object.
     */
    public DiscreteDynamicsWorld getDynamicsWorld() {
        return dynamicsWorld;
    }

}
