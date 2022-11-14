package edu.ufl.digitalworlds.j4q.models;

import java.util.ArrayList;

import edu.ufl.digitalworlds.j4q.geometry.Transform;
import edu.ufl.digitalworlds.j4q.shaders.Shader;

public class Model {

    ArrayList<Model> children=new ArrayList<>();
    Model parent=null;
    public Mesh mesh=null;
    public Shader shader=null;
    private boolean visible=true;

    public void show(){visible=true;}
    public void hide(){visible=false;}
    public boolean isShown(){return visible;}

    public Model getParent(){return parent;}

    public Transform transform=new Transform();
    public Transform globalTransform=new Transform();

    public void appendChild(Model model){
        if(children.indexOf(model)==-1) {
            children.add(model);
            model.parent = this;
        }
    }

    public void prependChild(Model model){
        if(children.indexOf(model)==-1) {
            children.add(0,model);
            model.parent = this;
        }
    }

    public void removeChild(Model model){
        if(children.remove(model)){
            model.parent=null;
        }
    }

    public void remove(){
        if(parent!=null){
            parent.removeChild(this);
        }
    }

    public void Update(){};

    public void updateAnimation(){
        Update();
        for (Model model : children) {
            model.updateAnimation();
        }
    }

    public void updateGlobalPositions(boolean parentModified){

        boolean modified=false;
        if(parentModified||transform.isModified())modified=true;

        globalTransform.reset(parent);
        globalTransform.multiply(transform.matrix);

        for (Model model : children) {
            model.updateGlobalPositions(modified);
        }

        if(modified) {
            transform.resetModifiedFlag();
            if(shader!=null) {
                shader.use();
                shader.setUniformMat4("localTransform", globalTransform.matrix);
            }
        }
    }

    public void setShader(Shader s){
        shader=s;
    }

    public void simulate(double elapsedDisplayTime, double perSec){};

    public void draw(){

        if(!visible)return;

        if(mesh!=null && shader!=null){
            shader.render(mesh);
        }
        for (Model model : children) {
            model.draw();
        }

    }
}
