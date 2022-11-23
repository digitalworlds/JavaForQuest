package edu.ufl.digitalworlds.j4q.models;

import edu.ufl.digitalworlds.j4q.J4Q;

public class LeftController extends Model{
    Model aim;
    Model trigger;
    Model grip;
    Model squeeze;
    Model joystick;

    public LeftController(){
        ObjectMaker om=new ObjectMaker();
        om.color(1,0,0);
        om.box(0.1f,0.1f,0.1f);
        aim=om.flushShadedColoredModel();
        appendChild(aim);

        om.box(0.08f,0.08f,0.1f);
        trigger=om.flushShadedColoredModel();
        aim.appendChild(trigger);

        om.box(0.1f,0.1f,0.1f);;
        grip=om.flushShadedColoredModel();
        appendChild(grip);

        om.box(0.1f,0.08f,0.08f);
        squeeze=om.flushShadedColoredModel();
        grip.appendChild(squeeze);


        om.box(0.08f,0.1f,0.08f);
        joystick=om.flushShadedColoredModel();
        grip.appendChild(joystick);
    }

    @Override
    public void Update(){
        aim.transform.reset();
        aim.transform.translate(J4Q.leftController.aim.position);
        aim.transform.rotate(J4Q.leftController.aim.orientation);

        trigger.transform.reset();
        trigger.transform.translate(0,0,-0.1f+J4Q.leftController.trigger.currentValue*0.1f);

        grip.transform.reset();
        grip.transform.translate(J4Q.leftController.grip.position);
        grip.transform.rotate(J4Q.leftController.grip.orientation);

        squeeze.transform.reset();
        squeeze.transform.translate(0.1f-J4Q.leftController.squeeze.currentValue*0.1f,0,0);

        joystick.transform.reset();
        joystick.transform.translate(J4Q.leftController.joystick.getX()*0.1f,0.1f,-J4Q.leftController.joystick.getY()*0.1f);

    }
}
