package edu.ufl.digitalworlds.j4q.controllers;

public class J4QJoystick extends J4QToggleButton{
    public float[] currentValue=new float[2];
    public float getX(){return currentValue[0];}
    public float getY(){return currentValue[1];}
}
