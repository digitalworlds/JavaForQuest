package j4q.input;

import android.util.Log;

import j4q.J4Q;
import j4q.geometry.Transform;
import j4q.models.Component;
import j4q.models.GameObject;

public abstract class DragListener extends Component implements TouchScreenEventListener,FrameListener{
    private int moved=20;
    private GameObject g;

    private float initial_x;
    private float initial_y;
    private Transform initial_tarsnform;
    boolean motion_started=false;

    TouchScreenEvent latest_event;

    public Transform getInitialTransform(){return initial_tarsnform;}

    @Override
    public void addedAsComponentOf(GameObject gameObject){
        super.addedAsComponentOf(gameObject);

        TouchScreen screen = J4Q.getInputDevice(TouchScreen.class);
        if(screen!=null) {
            screen.addTouchEventListener(this);
            screen.addFrameListener(this);
        }
    }

    public abstract void onDrag(TouchScreenEvent e,GameObject g);

    @Override
    public void onDraw(){
        if(motion_started) {
            onDrag(latest_event, g);
        }
    }

    @Override
    public void onPointerUp(TouchScreenEvent e) {
        latest_event=e;
        if(e.getNumOfPointers()==0){
            motion_started=false;
        }
    }

    @Override
    public void onPointerDown(TouchScreenEvent e) {
    latest_event=e;

        if(e.getNumOfPointers()==1) {

            g=e.getGameObject(0);
            if(g!=null && (gameObject()==null || gameObject()==g)){
                motion_started=true;
                initial_x=e.getX(0);
                initial_y=e.getY(0);
                initial_tarsnform=new Transform(g.transform);
            }

        }

    }

    @Override
    public void onPointerMove(TouchScreenEvent e) {
        latest_event=e;
       if(motion_started){
           Log.d("angelos","object moved");
           onDrag(e,g);
       }
    }
}
