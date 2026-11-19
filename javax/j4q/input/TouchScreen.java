package j4q.input;

import android.view.MotionEvent;
import android.view.View;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

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
        pastEventQueue=eventQueue;
        eventQueue=new ConcurrentLinkedQueue<>();
        GameObject[] g=new GameObject[fingers_down];
        if(fingers_down>0){
            objectPicker.begin();
            scene.root.draw(objectPicker.shader);
            for(int i=0;i<fingers_down;i++) {
                id[i]=objectPicker.pick((int)touch_x[i],(int)touch_y[i]);
                g[i]=J4Q.getObject(id[i]);
            }
            objectPicker.end();
            }
        while(!pastEventQueue.isEmpty()){
            TouchScreenEvent e=pastEventQueue.poll();
            e.setGameObjects(g);
            switch(e.getType()){
                case TouchScreenEvent.EVENT_POINTER_UP:
                    for (TouchScreenEventListener listener : listeners) {
                        listener.onPointerUp(e);
                    }
                break;
                case TouchScreenEvent.EVENT_POINTER_DOWN:
                    for (TouchScreenEventListener listener : listeners) {
                        listener.onPointerDown(e);
                    }
                    break;
                case TouchScreenEvent.EVENT_POINTER_MOVE:
                    for (TouchScreenEventListener listener : listeners) {
                        listener.onPointerMove(e);
                    }
                    break;
            }
        }
        for (FrameListener listener : frame_listeners) {
            listener.onDraw();
        }
    }

    private final List<TouchScreenEventListener> listeners = new CopyOnWriteArrayList<>();

    private final List<FrameListener> frame_listeners = new CopyOnWriteArrayList<>();


    public void addTouchEventListener(TouchScreenEventListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeTouchEventListener(TouchScreenEventListener listener) {
        listeners.remove(listener);
    }

    public void addFrameListener(FrameListener listener) {
        if (listener != null && !frame_listeners.contains(listener)) {
            frame_listeners.add(listener);
        }
    }

    public void removeFrameListener(FrameListener listener) {
        frame_listeners.remove(listener);
    }

    public int pick(int fingerSlot){
        return id[fingerSlot];
    }

    public GameObject pickObject(int fingerSlot){
        return J4Q.getObject(id[fingerSlot]);
    }

    private Queue<TouchScreenEvent> pastEventQueue = new ConcurrentLinkedQueue<>();
    private Queue<TouchScreenEvent> eventQueue = new ConcurrentLinkedQueue<>();

    private void onPointerDown() {
        int length = Math.min(fingers_down, touch_x.length);
        float[] x=new float[length];
        float[] y=new float[length];
        System.arraycopy(touch_x, 0, x, 0, length);
        System.arraycopy(touch_y, 0, y, 0, length);

        eventQueue.offer(new TouchScreenEvent(TouchScreenEvent.EVENT_POINTER_DOWN,x,y));
    }

    private void onPointerUp() {
        int length = Math.min(fingers_down, touch_x.length);
        float[] x=new float[length];
        float[] y=new float[length];
        System.arraycopy(touch_x, 0, x, 0, length);
        System.arraycopy(touch_y, 0, y, 0, length);

        eventQueue.offer(new TouchScreenEvent(TouchScreenEvent.EVENT_POINTER_UP,x,y));
    }

    private void onPointerMove() {
        int length = Math.min(fingers_down, touch_x.length);
        float[] x=new float[length];
        float[] y=new float[length];
        System.arraycopy(touch_x, 0, x, 0, length);
        System.arraycopy(touch_y, 0, y, 0, length);

        eventQueue.offer(new TouchScreenEvent(TouchScreenEvent.EVENT_POINTER_MOVE,x,y));
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
                onPointerDown();
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
                onPointerDown();
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
                onPointerUp();
                break;
            case MotionEvent.ACTION_UP:
                fingers_down=0;
                for(int i=0;i<touch_x.length;i++){
                    touch_x[i]=0;
                    touch_y[i]=0;
                }
                //processTouchEvent(t.fingers_down, t.touch_x,t.touch_y);
                onPointerUp();
                break;
            case MotionEvent.ACTION_CANCEL:
                fingers_down=0;
                for(int i=0;i<touch_x.length;i++){
                    touch_x[i]=0;
                    touch_y[i]=0;
                }
                //processTouchEvent(t.fingers_down, t.touch_x,t.touch_y);
                onPointerUp();
                break;
            case MotionEvent.ACTION_MOVE:
                onPointerMove();
                break;
        }

        return true;
    }


}
