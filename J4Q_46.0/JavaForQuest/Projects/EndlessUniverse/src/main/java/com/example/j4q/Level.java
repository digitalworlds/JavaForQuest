package com.example.j4q;

import android.opengl.Matrix;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.ObjectMaker;
import edu.ufl.digitalworlds.j4q.shaders.Texture;

public class Level extends Model {

    LevelSegment[] segments;

    float[] xyz;
    short[] tri;
    float[] uv;

    public static int KEY_FRAME_FREQUENCY=5;
    public static int TOTAL_KEY_FRAMES=5;
    public static int RESOLUTION=31;//number of vertices in a circle around the pipe

    float t=0;

    public ObjectMaker path_maker;
    public float[] path_maker_orientation=null;
    public float[] path_maker_orientation_inv=new float[16];


    Texture path_texture;
    Texture planet_texture;
    Texture side_texture;

    public Level(){

        int res=RESOLUTION;

        //calculate the xyz of a circle for the path object
        xyz=new float[3*res];
        int c1=0;
        for(int i=0;i<res;i++)
        {
            this.xyz[c1]=(float)(0.5*Math.cos(2*3.1416*i/(res-1)-3.1416/2));c1+=1;
            this.xyz[c1]=(float)(0.5*Math.sin(2*3.1416*i/(res-1)-3.1416/2));c1+=1;
            this.xyz[c1]=0;c1+=1;
        }

        //create a fixed list of triangles and UV map for the path object
        this.uv=new float[2*res*2];
        this.tri=new short[6*(res-1)];
        int c2=0;
        int c3=0;
        int c4=0;
        for(int j=0;j<2;j++)
            for(int i=0;i<res;i++)
            {
                this.uv[c2]=1f-i/(res-1f);c2+=1;
                this.uv[c2]=(j*1f/KEY_FRAME_FREQUENCY);c2+=1;
                if(j<1&&i<res-1)
                {
                    this.tri[c3]=(short)c4;c3+=1;this.tri[c3]=(short)(c4+res);c3+=1;this.tri[c3]=(short)(c4+1);c3+=1;
                    this.tri[c3]=(short)(c4+1);c3+=1;this.tri[c3]=(short)(c4+res);c3+=1;this.tri[c3]=(short)(c4+res+1);c3+=1;
                }
                c4+=1;
            }

        path_maker=new ObjectMaker();
        side_texture=new Texture("textures/box.png");
        path_texture=new Texture("textures/metal.jpg");
        planet_texture=new Texture("textures/planet_3_d.jpg");

        segments=new LevelSegment[KEY_FRAME_FREQUENCY*TOTAL_KEY_FRAMES];
        for(int i=0;i<segments.length;i++) {
            segments[i] = new LevelSegment(this);
            if(i==0)segments[i].buildSegment(null);
            else segments[i].buildSegment(segments[i-1]);
            appendChild(segments[i]);
        }
    }

    int previous_i=0;
    int previous_slot=0;

    @Override
    public void Update(){



        t+=2.5f*(J4Q.rightController.joystick.getY()+1)*(J4Q.leftController.joystick.getY()+1)* J4Q.perSec();

        int i=(int)Math.floor(t/LevelSegment.LENGTH);

        //check if we passed the segment
        if(i>previous_i){

            for(;previous_i<i;previous_i++){

                //update the geometry of the segments that we just passed
                int previous=previous_slot-1;
                if(previous<0)previous=segments.length-1;
                segments[previous_slot].buildSegment(segments[previous]);

                previous_slot+=1;
                if(previous_slot==segments.length)previous_slot=0;
            }

        }

        float w=t/LevelSegment.LENGTH-i;
        float w1=2+w;w1=(3-w1)*(3-w1)/2;
        float w2=1+w;w2=(-2*w2*w2+6*w2-3)/2;
        float w3=w;w3=w3*w3/2;


        int next_slot=previous_slot+1;
        if(next_slot==segments.length)next_slot=0;

        float[] o1=this.segments[previous_slot].orientation1;
        float[] o2=this.segments[previous_slot].orientation2;
        float[] o3=this.segments[next_slot].orientation2;
        float[] m=new float[]{1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1};
        for(int j=0;j<16;j++)
            m[j]=o1[j]*w1+o2[j]*w2+o3[j]*w3;

        float[] copy=new float[16];
        System.arraycopy(m,0,copy,0,16);
        Matrix.invertM(m,0,copy,0);


        transform.identity();
        transform.translate(0,-1.5f,0);
        //renderer.view.rotate(angle,0,0,1);
        transform.multiply(m);
    }


}
