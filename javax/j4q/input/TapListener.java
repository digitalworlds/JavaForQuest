package j4q.input;

import android.util.Log;

import j4q.models.GameObject;

public abstract class TapListener implements TouchScreenEventListener{
    private int moved=0;
    private GameObject g;

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
        else g=e.getGameObject()[0];
    }

    @Override
    public void onPointerMove(TouchScreenEvent e) {
        moved+=1;
    }
}
