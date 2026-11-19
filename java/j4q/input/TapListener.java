package j4q.input;

import j4q.J4Q;
import j4q.models.Component;
import j4q.models.GameObject;

public abstract class TapListener extends Component implements TouchScreenEventListener{
    private int moved=20;
    private GameObject g;

    @Override
    public void addedAsComponentOf(GameObject gameObject){
        super.addedAsComponentOf(gameObject);

        TouchScreen screen = J4Q.getInputDevice(TouchScreen.class);
        if(screen!=null) screen.addTouchEventListener(this);
    }

    public abstract void onTap(TouchScreenEvent e,GameObject g);

    @Override
    public void onPointerUp(TouchScreenEvent e) {
        if(moved<2)
            onTap(e,this.g);
    }

    @Override
    public void onPointerDown(TouchScreenEvent e) {

        moved=0;
        if(e.getNumOfPointers()>1)moved=10;
        else {
            g=e.getGameObject(0);
            if(g==null)moved=10;
            else if(gameObject()!=null && gameObject()!=g) moved=10;
        }
    }

    @Override
    public void onPointerMove(TouchScreenEvent e) {
        moved+=1;
    }
}
