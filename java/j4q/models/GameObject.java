package j4q.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import j4q.geometry.Transform;
import j4q.physics.RigidBody;
import j4q.shaders.Shader;

public class GameObject {

    private Map<Class<?>, Component> components = new HashMap<>();

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

    public <T extends Component> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }

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

    ArrayList<GameObject> children=new ArrayList<>();

    GameObject parent=null;
    public Mesh mesh=null;
    public Shader shader=null;
    public RigidBody rigidBody=null;

    private boolean visible=true;

    public void show(){visible=true;}
    public void hide(){visible=false;}
    public boolean isShown(){return visible;}

    public GameObject getParent(){return parent;}

    public Transform transform=new Transform();
    public Transform globalTransform=new Transform();

    public GameObject appendChild(GameObject model){
        if(children.indexOf(model)==-1) {
            children.add(model);
            model.parent = this;
        }
        return model;
    }

    public GameObject prependChild(GameObject model){
        if(children.indexOf(model)==-1) {
            children.add(0,model);
            model.parent = this;
        }
        return model;
    }

    public GameObject removeChild(GameObject model){
        if(children.remove(model)){
            model.parent=null;
        }
        return model;
    }

    public void remove(){
        if(parent!=null){
            parent.removeChild(this);
        }
    }

    public void Update(){};

    public void updateAnimation(){
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

    public void updateGlobalPositions(boolean parentModified){

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

    public void setShader(Shader s){
        addComponent(s);
        for (GameObject model : children) {
            model.setShader(s);
        }
    }

    public void simulate(double elapsedDisplayTime, double perSec){};

    public void draw(){
        draw(null);
    }

    public void draw(Shader otherShader){

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
