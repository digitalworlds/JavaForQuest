package j4q.input;

import android.view.MotionEvent;
import android.view.View;

import j4q.J4Q;
import j4q.activities.GameEngineScene;
import j4q.models.GameObject;

public class TouchScreen extends InputDevice implements View.OnTouchListener{
    public int fingers_down=0;
    public float[] touch_x=new float[10];
    public float[] touch_y=new float[10];

    public int[] id=new int[10];

    private ObjectPicker objectPicker;

    public void setup(int width, int height){
        if(objectPicker==null)objectPicker=new ObjectPicker();
        objectPicker.setSize(width,height);
    }

    public void capture(GameEngineScene scene){
        objectPicker.begin();
        scene.root.draw(objectPicker.shader);
        for(int i=0;i<fingers_down;i++) {
            id[i]=objectPicker.pick((int)touch_x[i],(int)touch_y[i]);
        }
        objectPicker.end();
    }

    public int pick(int slot){
        return id[slot];
    }

    public GameObject pickObject(int slot){
        return J4Q.getObject(id[slot]);
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {

        //get the object ID

        //getObject(ID).setShader(highlightobject_shader);
        //getObject(ID).transform.scale(1.2);

        switch(event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                fingers_down=1;
                for(int i=0;i<touch_x.length;i++){
                    touch_x[i]=0;
                    touch_y[i]=0;
                }
                touch_x[0]=event.getX();
                touch_y[0]=event.getY();
                //processTouchEvent(t.fingers_down, t.touch_x,t.touch_y);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                fingers_down=event.getPointerCount();
                for(int i=0;i<touch_x.length;i++){
                    touch_x[i]=0;
                    touch_y[i]=0;
                }
                for(int i=0;i<event.getPointerCount()&& i<touch_x.length;i++){
                    touch_x[i] = event.getX(i);
                    touch_y[i] = event.getY(i);
                }
                //processTouchEvent(t.fingers_down, t.touch_x,t.touch_y);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                fingers_down=event.getPointerCount()-1;
                int pointerIndex = event.getActionIndex();
                for(int i=0;i<touch_x.length;i++){
                    touch_x[i]=0;
                    touch_y[i]=0;
                }
                for(int i=0;i<event.getPointerCount()&& i<touch_x.length;i++){
                    if(i<pointerIndex) {
                        touch_x[i] = event.getX(i);
                        touch_y[i] = event.getY(i);
                    }else if(i>pointerIndex){
                        touch_x[i-1] = event.getX(i);
                        touch_y[i-1] = event.getY(i);
                    }
                }
                //processTouchEvent(t.fingers_down, t.touch_x,t.touch_y);
                break;
            case MotionEvent.ACTION_UP:
                fingers_down=0;
                for(int i=0;i<touch_x.length;i++){
                    touch_x[i]=0;
                    touch_y[i]=0;
                }
                //processTouchEvent(t.fingers_down, t.touch_x,t.touch_y);
                break;
            case MotionEvent.ACTION_CANCEL:
                fingers_down=0;
                for(int i=0;i<touch_x.length;i++){
                    touch_x[i]=0;
                    touch_y[i]=0;
                }
                //processTouchEvent(t.fingers_down, t.touch_x,t.touch_y);
                break;
        }

        return true;
    }
}
