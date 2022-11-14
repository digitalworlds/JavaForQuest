package edu.ufl.digitalworlds.j4q.geometry;

public class Position {
    public float x=0;
    public float y=0;
    public float z=0;

    public Position(){}
    public Position(float x,float y,float z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public float distance(Position p){
        float dx=x-p.x;
        float dy=y-p.y;
        float dz=z-p.z;
        return (float)Math.sqrt(dx*dx+dy*dy+dz*dz);
    }
}
