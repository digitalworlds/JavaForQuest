package edu.ufl.digitalworlds.j4q.controllers;

public class J4QToggleButton {
    public boolean changedSinceLastSync=false;
    public boolean currentState=false;
    public boolean isPressed(){return currentState;}
    public boolean justPressed(){return changedSinceLastSync && currentState;}
    public boolean justReleased(){return changedSinceLastSync && !currentState;}
}
