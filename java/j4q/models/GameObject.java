package j4q.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import j4q.geometry.Transform;
import j4q.physics.RigidBody;
import j4q.shaders.Shader;

/**
 * Represents an object in the scene graph, supporting components, hierarchy, and rendering.
 * <p>
 * The {@code GameObject} class manages child objects, components, transforms, and drawing logic.
 * </p>
 */
public class GameObject {

    /**
     * Map of component types to component instances.
     */
    private Map<Class<?>, Component> components = new HashMap<>();

    /**
     * Adds a component to this GameObject. Handles Mesh, Shader, and RigidBody specially.
     * @param component The component to add.
     * @param <T> The type of the component.
     */
    public <T extends Component> void addComponent(T component) {
        if(component instanceof Mesh){
            if(mesh!=null) components.remove(mesh);
            mesh=(Mesh)component;
        }else if(component instanceof Shader){
            if(shader!=null) components.remove(shader);
            shader=(Shader)component;
        }else if(component instanceof RigidBody){
            if(rigidBody!=null) components.remove(rigidBody);
            rigidBody=(RigidBody) component;
        }
        component.gameObject = this;
        components.put(component.getClass(), component);
    }

    /**
     * Retrieves a component of the specified type from this GameObject.
     * @param type The class type of the component.
     * @param <T> The type of the component.
     * @return The component instance, or null if not found.
     */
    public <T extends Component> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }

    /**
     * Removes a component of the specified type from this GameObject.
     * Handles Mesh, Shader, and RigidBody specially.
     * @param type The class type of the component.
     * @param <T> The type of the component.
     * @return The removed component instance, or null if not found.
     */
    public <T extends Component> T removeComponent(Class<T> type) {

        Component removed = components.remove(type);
        if (removed == null) return null;

        if (removed instanceof Mesh && mesh == removed) {
            mesh = null;
        }
        else if (removed instanceof Shader && shader == removed) {
            shader = null;
        }
        else if (removed instanceof RigidBody && rigidBody == removed) {
            rigidBody = null;
        }
        removed.gameObject = null;
        return type.cast(removed);
    }

    /**
     * The list of child GameObjects.
     */
    ArrayList<GameObject> children = new ArrayList<>();

    /**
     * The parent GameObject in the hierarchy.
     */
    GameObject parent = null;
    /**
     * The mesh component of this GameObject.
     */
    public Mesh mesh = null;
    /**
     * The shader component of this GameObject.
     */
    public Shader shader = null;
    /**
     * The rigid body component of this GameObject.
     */
    public RigidBody rigidBody = null;

    /**
     * Indicates whether the GameObject is visible.
     */
    private boolean visible = true;

    /**
     * Makes the GameObject visible.
     */
    public void show() { visible = true; }

    /**
     * Makes the GameObject invisible.
     */
    public void hide() { visible = false; }

    /**
     * Returns whether the GameObject is visible.
     * @return True if visible, false otherwise.
     */
    public boolean isShown() { return visible; }

    /**
     * Returns the parent GameObject.
     * @return The parent GameObject, or null if root.
     */
    public GameObject getParent() { return parent; }

    /**
     * The local transform of this GameObject.
     */
    public Transform transform = new Transform();

    /**
     * The global transform of this GameObject.
     */
    public Transform globalTransform = new Transform();

    /**
     * Appends a GameObject as a child to this GameObject.
     * @param model The GameObject to append.
     * @return The appended GameObject.
     */
    public GameObject appendChild(GameObject model) {
        if(children.indexOf(model)==-1) {
            children.add(model);
            model.parent = this;
        }
        return model;
    }

    /**
     * Prepends a GameObject as a child to this GameObject.
     * @param model The GameObject to prepend.
     * @return The prepended GameObject.
     */
    public GameObject prependChild(GameObject model) {
        if(children.indexOf(model)==-1) {
            children.add(0,model);
            model.parent = this;
        }
        return model;
    }

    /**
     * Removes a child GameObject from this GameObject.
     * @param model The GameObject to remove.
     * @return The removed GameObject.
     */
    public GameObject removeChild(GameObject model) {
        if(children.remove(model)){
            model.parent=null;
        }
        return model;
    }

    /**
     * Removes this GameObject from its parent.
     */
    public void remove() {
        if(parent!=null){
            parent.removeChild(this);
        }
    }

    /**
     * Called every frame to update the GameObject's state.
     * Override in subclasses for custom behavior.
     */
    public void Update() {};

    /**
     * Updates animation for this GameObject and its children.
     */
    public void updateAnimation() {
        for (Component comp : components.values()) {
            if (comp instanceof MonoBehaviour) {
                ((MonoBehaviour) comp).Update();
            }
        }
        Update();
        for (GameObject model : children) {
            model.updateAnimation();
        }
    }

    /**
     * Updates global transforms and shader uniforms for this GameObject and its children.
     * @param parentModified Whether the parent transform was modified.
     */
    public void updateGlobalPositions(boolean parentModified) {

        boolean modified=false;
        if(parentModified||transform.isModified())modified=true;

        globalTransform.reset(parent);
        globalTransform.multiply(transform.matrix);

        for (GameObject model : children) {
            model.updateGlobalPositions(modified);
        }

        if(modified) {
            transform.resetModifiedFlag();
            if(shader!=null) {
                shader.use();
                Transform t=new Transform(globalTransform.matrix);
                shader.setUniformMat4("modelMatrix", t.matrix);
                shader.setUniformMat4("normalMatrix", t.getNormalMatrix());
            }
        }
    }

    /**
     * Sets the shader for this GameObject and all its children.
     * @param s The shader to set.
     */
    public void setShader(Shader s) {
        addComponent(s);
        for (GameObject model : children) {
            model.setShader(s);
        }
    }

    /**
     * Simulates the GameObject for the given time step.
     * Override in subclasses for custom simulation.
     * @param elapsedDisplayTime The elapsed display time.
     * @param perSec The time per frame.
     */
    public void simulate(double elapsedDisplayTime, double perSec) {};

    /**
     * Draws the GameObject using its shader.
     */
    public void draw() {
        draw(null);
    }

    /**
     * Draws the GameObject using the specified shader, or its own shader if null.
     * @param otherShader The shader to use, or null for default.
     */
    public void draw(Shader otherShader) {

        if(!visible)return;

        if(otherShader!=null){
            if(mesh!=null){
                otherShader.use();
                Transform t=new Transform(globalTransform.matrix);
                otherShader.setUniformMat4("modelMatrix", t.matrix);
                otherShader.render(mesh);
            }
            for (GameObject model : children) {
                model.draw(otherShader);
            }
        }
        else{
            if(mesh!=null){
                if(shader!=null) shader.render(mesh);
            }
            for (GameObject model : children) {
                model.draw();
            }
        }

    }
}
