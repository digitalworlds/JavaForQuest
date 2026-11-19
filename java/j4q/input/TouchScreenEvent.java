package j4q.input;

import static java.lang.Float.NaN;

import j4q.models.GameObject;

public class TouchScreenEvent {

    public static final short EVENT_POINTER_DOWN=1;
    public static final short EVENT_POINTER_UP=2;
    public static final short EVENT_POINTER_MOVE=3;

    private short type;
    private float[] x;
    private float[] y;

    private GameObject[] g;
    public TouchScreenEvent(short type, float[] x, float[] y){
        this.type=type;
        this.x=x;
        this.y=y;
    }

    public void setGameObjects(GameObject[] g){this.g=g;}

    public int getNumOfPointers(){if(x==null)return 0;else return x.length;}
    public float getX(int pointer){if(pointer<x.length)return x[pointer];else return NaN;}
    public float getY(int pointer){if(pointer<y.length)return y[pointer];else return NaN;}
    public GameObject getGameObject(int pointer){if(pointer<g.length)return g[pointer];else return null;}


    public short getType(){return this.type;}


}
