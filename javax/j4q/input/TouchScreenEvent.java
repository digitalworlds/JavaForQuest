package j4q.input;

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
    public float[] getX(){return x;}
    public float[] getY(){return y;}
    public GameObject[] getGameObject(){return this.g;}


    public short getType(){return this.type;}


}
