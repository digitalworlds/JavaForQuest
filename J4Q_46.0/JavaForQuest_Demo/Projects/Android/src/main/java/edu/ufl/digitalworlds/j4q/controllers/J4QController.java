package edu.ufl.digitalworlds.j4q.controllers;

public class J4QController {
    public J4QJoystick joystick=new J4QJoystick();
    public J4QPressureButton trigger=new J4QPressureButton();
    public J4QPressureButton squeeze=new J4QPressureButton();
    public J4QPose aim=new J4QPose();
    public J4QPose grip=new J4QPose();
    public boolean active=false;
}
