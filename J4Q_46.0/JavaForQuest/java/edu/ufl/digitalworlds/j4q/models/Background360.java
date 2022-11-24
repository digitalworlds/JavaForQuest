package edu.ufl.digitalworlds.j4q.models;

import android.opengl.GLES30;

import edu.ufl.digitalworlds.j4q.shaders.Texture;
import edu.ufl.digitalworlds.j4q.shaders.Background360Shader;

public class Background360 extends Model {

    public Background360(){
        ObjectMaker om=new ObjectMaker();
        om.sphere(-100,100,100,32);
        om.flushTexturedModel(this);
        shader=new Background360Shader();
    }

    //FishEye format
    public Background360(double FOV){
        shader=new Background360Shader();
        makeFishEye(FOV);
    }

    private void makeFishEye(double FOV){
        double centerX=0.5;
        double centerY=0.5;
        //double FOV=2*Math.PI;//The field of view of the cameras in radians
        double use=1.0;
        int resolution=64;

//The centers of the two fisheye circles in [0-1] pixel coordinates
        double camera_center_x=centerX;
        double camera_center_y=centerY;


//The percentage of the image to be used (when removing the overlap)
        double crop=use;


        resolution=resolution+1;//each hemisphere will render one 180 image and has resolution x resolution vertices
        int resx=resolution*2;//there are two hemispheres in the 360 image
        int resy=resolution;
        float[] xyz=new float[3*resx*resy];
        float[] uv=new float[2*resx*resy];
        short[] tri=new short[6*(resx-1)*(resy-1)];
        int c1=0;
        int c2=0;
        int c3=0;
        int c4=0;
        int stopj=resy-1;
        float[] psph=new float[3];
        for(int i=0;i<resx;i++)
            for(int j=0;j<resy;j++)
            {
//these lines create a regular flat 360 image
//xyz [ c1 ] = ( i - resolution + 1 ) / ( resolution - 1 ) ; c1 += 1 ;
//xyz [ c1 ] = j / resy - 0.5 ; c1 += 1 ;
//xyz [ c1 ] = - 1 ; c1 += 1 ;

//these lines create the shape of the sphere
                xyz[c1]=-(float)(Math.cos(Math.PI*(j/(resy-1.0)-0.5))*Math.cos(Math.PI*(i-resolution+1.0)/(resolution-1.0)));c1+=1;
                xyz[c1]=(float)(Math.cos(Math.PI*(j/(resy-1.0)-0.5))*Math.sin(Math.PI*(i-resolution+1.0)/(resolution-1.0)));c1+=1;
                xyz[c1]=(float)(Math.sin(Math.PI*(j/(resy-1.0)-0.5)));c1+=1;

                if(Math.PI*j/(resy-1)>FOV/2){
                    xyz[c1-1]=(float)Math.sin((FOV-Math.PI)/2);
                    if(stopj>j)stopj=j;
                }

                if(i<resx-1&&j<stopj)
                {
                    tri[c3]=(short)c4;c3+=1;tri[c3]=(short)(c4+resy);c3+=1;tri[c3]=(short)(c4+1);c3+=1;
                    tri[c3]=(short)(c4+1);c3+=1;tri[c3]=(short)(c4+resy);c3+=1;tri[c3]=(short)(c4+resy+1);c3+=1;
                }
                c4+=1;

//Calculate fisheye angle and radius
                psph[0]=xyz[c1-3];
                psph[1]=xyz[c1-2];
                psph[2]=-xyz[c1-1];
                double theta=Math.atan2(psph[1],psph[0]);
                double phi=Math.atan2(Math.sqrt(psph[0]*psph[0]+psph[1]*psph[1]),psph[2]);
                double r=2*crop*phi/FOV;

//Pixel in fisheye space
                uv[c2]=-(float)(camera_center_x+r*Math.cos(theta)*0.5);c2+=1;
                uv[c2]=(float)(camera_center_y+r*Math.sin(theta)*0.5);c2+=1;
            }

        this.mesh.setXYZ(xyz);
        this.mesh.setTriangles(tri);
        this.mesh.setUV(uv);
    }

    @Override
    public void draw(){
        GLES30.glDisable(GLES30.GL_DEPTH_TEST);
        super.draw();
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);
    }

    public void setTexture(Texture t){
        ((Background360Shader)shader).setTexture(t);
    }

}
