package edu.ufl.digitalworlds.j4q.models;

import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.List;

import edu.ufl.digitalworlds.j4q.geometry.Transform;
import edu.ufl.digitalworlds.j4q.shaders.ColorShader;
import edu.ufl.digitalworlds.j4q.shaders.ShadedColorShader;
import edu.ufl.digitalworlds.j4q.shaders.ShadedTextureShader;
import edu.ufl.digitalworlds.j4q.shaders.TextureShader;


public class ObjectMaker extends Transform {

    private float[] getArray(List<Float> floatList){
        float[] floatArray = new float[floatList.size()];
        int i = 0;

        for (Float f : floatList) {
            floatArray[i++] = (f != null ? f : Float.NaN); // Or whatever default you want.
        }
        return floatArray;
    }

    private final List<Float> xyz=new ArrayList<Float>();
    public float[] getXYZ(){return getArray(xyz); }
    private final List<Float> nrm=new ArrayList<Float>();
    public float[] getNormals(){return getArray(nrm); }
    private final List<Integer> tri=new ArrayList<Integer>();
    public short[] getTriangles(){
        short[] intArray = new short[tri.size()];
        int i = 0;

        for (Integer f : tri) {
            intArray[i++] = (short)(f != null ? f : 0); // Or whatever default you want.
        }
        return intArray;
    }
    private final List<Float> uv=new ArrayList<Float>();
    public float[] getUV(){return getArray(uv); }
    private final List<Float> clr=new ArrayList<Float>();
    public float[] getColors(){return getArray(clr); }
    private float[] current_color=new float[]{1,1,1};

    public ObjectMaker(){
        super();
    }


    /*GLObjectMaker.prototype.append=function(options)
        {
        var opt=options||{};

        if(opt.triangles)this.appendTriangles(opt.triangles);
        if(opt.xyz)this.appendXYZ(opt.xyz);
        if(opt.normals)this.appendNormals(opt.normals);
        if(opt.uv)this.appendUV(opt.uv);
        if(opt.colors)this.appendColors(opt.colors)
        };*/

    public void appendXYZ(float[] v){
        int xyz_start=this.xyz.size();
        float[] a=new float[]{0,0,0,0};
        int c=0;
        for(int i=0;i<v.length/3;i++)
        {

            c=i*3;
            Matrix.multiplyMV(a,0,matrix,0,new float[]{v[c],v[c+1],v[c+2],1},0);
            this.xyz.add(new Float(a[0]));
            this.xyz.add(new Float(a[1]));
            this.xyz.add(new Float(a[2]));
        }
    }

    public void appendNormals(float[] v){

        int nrm_start=this.nrm.size();
        float[] a=new float[]{0,0,0,0};
        int c=0;
        float[] nm=new float[16];
        for(int i=0;i<16;i++)nm[i]=matrix[i];
        nm[12]=0;
        nm[13]=0;
        nm[14]=0;
        float[] inv=new float[16];
        Matrix.invertM(inv,0,nm,0);
        Matrix.transposeM(nm,0,inv,0);

        for(int i=0;i<v.length/3;i++)
        {
            c=i*3;
            Matrix.multiplyMV(a,0,nm,0,new float[]{v[c],v[c+1],v[c+2],0},0);
            this.nrm.add(new Float(a[0]));
            this.nrm.add(new Float(a[1]));
            this.nrm.add(new Float(a[2]));
        }

    }

    public void appendUV(float[] v){
        int uv_start=this.uv.size();
        for(int i=0;i<v.length;i++)
        {
            this.uv.add(new Float(v[i]));
        }
    }

    public void appendTriangles(int[] v){
        int v_length=this.xyz.size()/3;
        int tri_start=this.tri.size();
        for(int i=0;i<v.length;i++)
        {
            this.tri.add(new Integer(v[i]+v_length));
            //this.tri[tri_start+i]=v[i]+v_length;
        }
    }

    public void appendTriangles(short[] v){
        int v_length=this.xyz.size()/3;
        int tri_start=this.tri.size();
        for(int i=0;i<v.length;i++)
        {
            this.tri.add(new Integer(v[i]+v_length));
            //this.tri[tri_start+i]=v[i]+v_length;
        }
    }

    public void appendColors(float[] v){
        int color_start=this.clr.size();
        for(int i=0;i<v.length;i++)
        {
            this.clr.add(new Float(v[i]));
        }
    }

    /*
    GLObjectMaker.prototype.appendObject=function(o)
        {
        var v=o.indices['Triangles'];if(v)this.appendTriangles(v.data);
        v=o.buffers['aXYZ'];if(v)this.appendXYZ(v.data);
        v=o.buffers['aNormal'];if(v)this.appendNormals(v.data);
        v=o.buffers['aUV'];if(v)this.appendUV(v.data);
        v=o.buffers['aColor'];if(v)this.appendColors(v.data);
        };
     */


    public void color(float[] color){
        this.current_color=color;
    }

    public void color(float r, float g, float b){
        this.current_color=new float[]{r,g,b};
    }

    public void rectangle(float width,float height,float u,float v){
        float w=width;
        float h=height;
        float u_=u;
        float v_=v;
        this.appendTriangles(new int[]{0,1,2,0,2,3});
        this.appendXYZ(new float[]{w/2,h/2,0,-w/2,h/2,0,-w/2,-h/2,0,w/2,-h/2,0});
        this.appendNormals(new float[]{0,0,1,0,0,1,0,0,1,0,0,1});
        this.appendUV(new float[]{u_,v_,0,v_,0,0,u_,0});
        if(this.current_color!=null)
        {
            float[] clr=new float[3*4];
            int j=0;
            for(int i=0;i<4;i++)
            {
                clr[j]=this.current_color[0];j+=1;clr[j]=this.current_color[1];j+=1;clr[j]=this.current_color[2];j+=1;
            }
            this.appendColors(clr);
        }
    }

    public void rectangle(float width,float height){
        rectangle(width,height,1,1);
    }

    public void box(float width, float height, float depth){

        float x=width;
        float y=height;
        float z=depth;

        this.pushMatrix();
        this.scale(x,y,z);

//this.appendTriangles([0,2,1, 1,2,3, 4,5,6, 6,5,7, 9,11,8, 8,11,10, 13,12,15, 15,12,14, 16,17,18, 18,17,19, 21,20,22, 21,22,23]);
//this.appendXYZ([-0.5,0.5,0.5, 0.5,0.5,0.5, -0.5,-0.5,0.5, 0.5,-0.5,0.5, -0.5,0.5,-0.5, 0.5,0.5,-0.5, -0.5,-0.5,-0.5, 0.5,-0.5,-0.5, 0.5,0.5,0.5, 0.5,-0.5,0.5, 0.5,0.5,-0.5, 0.5,-0.5,-0.5, -0.5,0.5,0.5, -0.5,-0.5,0.5, -0.5,0.5,-0.5, -0.5,-0.5,-0.5, -0.5,0.5,0.5, 0.5,0.5,0.5, -0.5,0.5,-0.5, 0.5,0.5,-0.5, -0.5,-0.5,0.5, 0.5,-0.5,0.5, -0.5,-0.5,-0.5, 0.5,-0.5,-0.5]);
//this.appendNormals([0,0,1, 0,0,1, 0,0,1, 0,0,1, 0,0,-1, 0,0,-1, 0,0,-1, 0,0,-1, 1,0,0, 1,0,0, 1,0,0, 1,0,0, -1,0,0, -1,0,0, -1,0,0, -1,0,0, 0,1,0, 0,1,0, 0,1,0, 0,1,0, 0,-1,0, 0,-1,0, 0,-1,0, 0,-1,0]);
//this.appendUV([0,1, 1,1, 0,0, 1,0, 1,1, 0,1, 1,0, 0,0, 0,1, 0,0, 1,1, 1,0, 1,1, 1,0, 0,1, 0,0, 0,0, 1,0, 0,1, 1,1, 1,0, 0,0, 1,1, 0,1]);
        //if(opt.front)
        {
            this.pushMatrix();
            this.translate(0,0,0.5f);
            this.rectangle(1,1);
            this.popMatrix();
        }

        //if(opt.back)
        {
            this.pushMatrix();
            this.translate(0,0,-0.5f);
            this.rotateY(180);
            this.rectangle(1,1);
            this.popMatrix();
        }

        //if(opt.right)
        {
            this.pushMatrix();
            this.translate(0.5f,0,0);
            this.rotateY(90);
            this.rectangle(1,1);
            this.popMatrix();
        }

        //if(opt.left)
        {
            this.pushMatrix();
            this.translate(-0.5f,0,0);
            this.rotateY(-90);
            this.rectangle(1,1);
            this.popMatrix();
        }

        //if(opt.top)
        {
            this.pushMatrix();
            this.translate(0,0.5f,0);
            this.rotateX(-90);
            this.rectangle(1,1);
            this.popMatrix();
        }

        //if(opt.bottom)
        {
            this.pushMatrix();
            this.translate(0,-0.5f,0);
            this.rotateX(90);
            this.rectangle(1,1);
            this.popMatrix();
        }
        this.popMatrix();
    }

    public void trapezoid(float width, float height, float depth) {
        trapezoid(width,height,depth,width,depth);
    }

    public void trapezoid(float width, float height, float depth, float width2, float depth2){

        float x=width;
        float z=height;
        float y=depth;
        float x2=width2;
        float y2=depth2;

        this.pushMatrix();
        this.rotateX(90);
        this.scale(x,y,z);

        float xx=x2/x;
        float yy=y2/y;

        this.appendTriangles(new int[]{0,2,1,1,2,3,4,5,6,6,5,7,9,11,8,8,11,10,13,12,15,15,12,14,16,17,18,18,17,19,21,20,22,21,22,23});
        this.appendXYZ(new float[]{-0.5f,0.5f,0.5f,0.5f,0.5f,0.5f,-0.5f,-0.5f,0.5f,0.5f,-0.5f,0.5f,-0.5f*xx,0.5f*yy,-0.5f,0.5f*xx,0.5f*yy,-0.5f,-0.5f*xx,-0.5f*yy,-0.5f,0.5f*xx,-0.5f*yy,-0.5f,0.5f,0.5f,0.5f,0.5f,-0.5f,0.5f,0.5f*xx,0.5f*yy,-0.5f,0.5f*xx,-0.5f*yy,-0.5f,-0.5f,0.5f,0.5f,-0.5f,-0.5f,0.5f,-0.5f*xx,0.5f*yy,-0.5f,-0.5f*xx,-0.5f*yy,-0.5f,-0.5f,0.5f,0.5f,0.5f,0.5f,0.5f,-0.5f*xx,0.5f*yy,-0.5f,0.5f*xx,0.5f*yy,-0.5f,-0.5f,-0.5f,0.5f,0.5f,-0.5f,0.5f,-0.5f*xx,-0.5f*yy,-0.5f,0.5f*xx,-0.5f*yy,-0.5f});
        this.appendNormals(new float[]{0,0,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0,0,0,1,0,0,1,0,0,1,0,0,1,0,0,-1,0,0,-1,0,0,-1,0,0,-1,0});
        this.appendUV(new float[]{0,1,1,1,0,0,1,0,1,1,0,1,1,0,0,0,0,1,0,0,1,1,1,0,1,1,1,0,0,1,0,0,0,0,1,0,0,1,1,1,1,0,0,0,1,1,0,1});
        if(this.current_color!=null)
        {
            float[] clr=new float[3*24];
            int j=0;
            for(int i=0;i<24;i++)
            {
                clr[j]=this.current_color[0];j+=1;clr[j]=this.current_color[1];j+=1;clr[j]=this.current_color[2];j+=1;
            }
            this.appendColors(clr);
        }

        this.popMatrix();
    }

    public void pyramid(float width, float height, float depth){
        float x=width;
        float y=height;
        float z=depth;

        this.pushMatrix();
        this.scale(x,y,z);
        this.appendTriangles(new int[]{0,1,2,  3,4,5, 6,7,8, 9,10,11});
        this.appendXYZ(new float[]{-0.5f,0,-0.5f,-0.5f,0,0.5f,0,1,0,-0.5f,0,0.5f,0.5f,0,0.5f,0,1,0,0.5f,0,0.5f,0.5f,0,-0.5f,0,1,0,0.5f,0,-0.5f,-0.5f,0,-0.5f,0,1,0});
        this.appendNormals(new float[]{-1,1,0,-1,1,0,-1,1,0,0,1,1,0,1,1,0,1,1,1,1,0,1,1,0,1,1,0,0,1,-1,0,1,-1,0,1,-1});
        this.appendUV(new float[]{0,1,0,0,0.5f,0.5f,0,0,1,0,0.5f,0.5f,1,0,1,1,0.5f,0.5f,1,1,0,1,0.5f,0.5f});

        if(this.current_color!=null)
        {
            float[] clr=new float[3*12];
            int j=0;
            for(int i=0;i<12;i++)
            {
                clr[j]=this.current_color[0];j+=1;clr[j]=this.current_color[1];j+=1;clr[j]=this.current_color[2];j+=1;
            }
            this.appendColors(clr);
        }

        this.pushMatrix();
        this.rotateX(90);
        this.rectangle(1,1);
        this.popMatrix();

        this.popMatrix();
    }

    public void disc(float width, float height, int resolution){
        float x=width;
        float y=1;
        float z=height;
        int res=resolution;

        float[] xyz=new float[3*(res+1)];
        float[] nrm=new float[3*(res+1)];
        float[] uv=new float[2*(res+1)];
        int[] tri=new int[3*res];
        int c1=0;
        int c2=0;
        int c3=0;
        int c4=0;
        xyz[c1]=0;nrm[c1]=0;c1+=1;
        xyz[c1]=0;nrm[c1]=1;c1+=1;
        xyz[c1]=0;nrm[c1]=0;c1+=1;
        uv[c2]=0.5f;c2+=1;
        uv[c2]=0.5f;c2+=1;
        c4+=1;
        for(int i=0;i<res;i++)
        {
            xyz[c1]=(float)(0.5* Math.cos(2*3.1416*i/res-3.1416/2));nrm[c1]=0;c1+=1;
            xyz[c1]=0;nrm[c1]=1;c1+=1;
            xyz[c1]=(float)(0.5* Math.sin(2*3.1416*i/res-3.1416/2));nrm[c1]=0;c1+=1;
            uv[c2]=xyz[c1-3]+0.5f;c2+=1;
            uv[c2]=0.5f-xyz[c1-1];c2+=1;
            if(i<res-1)
            {
                tri[c3]=0;c3+=1;tri[c3]=c4+1;c3+=1;tri[c3]=c4;c3+=1;
            }
            else
            {
                tri[c3]=0;c3+=1;tri[c3]=1;c3+=1;tri[c3]=c4;c3+=1;
            }
            c4+=1;
        }

        this.pushMatrix();
        this.rotateX(90);
        this.scale(x,y,z);

        this.appendTriangles(tri);
        this.appendXYZ(xyz);
        this.appendNormals(nrm);
        this.appendUV(uv);
        if(this.current_color!=null)
        {
            float[] clr=new float[3*(res+1)];
            int j=0;
            for(int i=0;i<res+1;i++)
            {
                clr[j]=this.current_color[0];j+=1;clr[j]=this.current_color[1];j+=1;clr[j]=this.current_color[2];j+=1;
            }
            this.appendColors(clr);
        }

        this.popMatrix();
    }

    public void cone(float width, float height, float depth){
        cone(width,height,depth,16);
    }

    public void cone(float width, float height, float depth, int resolution){
        float x=width;
        float y=height;
        float z=depth;
        int res=resolution+1;
        float[] xyz=new float[3*res*4];
        float[] nrm=new float[3*res*4];
        float[] uv=new float[2*res*4];
        int[] tri=new int[6*(res-1)*3];
        int c1=0;
        int c2=0;
        int c3=0;
        int c4=0;
        float[] w=new float[]{0,0.25f,0.5f,1};
        for(int j=0;j<4;j++)
            for(int i=0;i<res;i++)
            {
                xyz[c1]=(float)(w[j]*0.5* Math.cos(2*3.1416*i/(res-1f)-3.1416/2));nrm[c1]=(float)(Math.cos(2*3.1416*i/(res-1f)-3.1416/2)*0.866);c1+=1;
                xyz[c1]=1-w[j];nrm[c1]=0.5f;c1+=1;
                xyz[c1]=(float)(w[j]*0.5* Math.sin(2*3.1416*i/(res-1f)-3.1416/2));nrm[c1]=(float)(Math.sin(2*3.1416*i/(res-1f)-3.1416/2)*0.866);c1+=1;

                uv[c2]=1-i/(res-1.0f);c2+=1;
                uv[c2]=1-w[j];c2+=1;
                if(i<res-1&&j<3)
                {
                    tri[c3]=c4;c3+=1;tri[c3]=c4+1;c3+=1;tri[c3]=c4+res;c3+=1;
                    tri[c3]=c4+1;c3+=1;tri[c3]=c4+res+1;c3+=1;tri[c3]=c4+res;c3+=1;
                }
                c4+=1;
            }

        this.pushMatrix();
        this.scale(x,y,z);

        this.appendTriangles(tri);
        this.appendXYZ(xyz);
        this.appendNormals(nrm);
        this.appendUV(uv);
        if(this.current_color!=null)
        {
            float[] clr=new float[3*res*4];
            int j=0;
            for(int i=0;i<res*4;i++)
            {
                clr[j]=this.current_color[0];j+=1;clr[j]=this.current_color[1];j+=1;clr[j]=this.current_color[2];j+=1;
            }
            this.appendColors(clr);
        }

        this.pushMatrix();
        this.rotate(180,1,0,0);
        this.rotateX(-90);
        this.disc(1,1,res-1);
        this.popMatrix();

        this.popMatrix();
    }

    public void cylinder(float width, float height, float depth, float width2, float depth2, int resolution){
        float x=width;
        float y=height;
        float z=depth;

        float xx=1;
        xx=width2/width;
        float zz=1;
        zz=depth2/depth;


        int res=resolution+1;
        float[] xyz=new float[3*res*2];
        float[] nrm=new float[3*res*2];
        float[] uv=new float[2*res*2];
        int[] tri=new int[6*(res-1)];
        int c1=0;
        int c2=0;
        int c3=0;
        int c4=0;
        for(int j=0;j<2;j++)
            for(int i=0;i<res;i++)
            {
                if(j==0)
                {
                    xyz[c1]=(float)(0.5* Math.cos(2*3.1416*i/(res-1f)-3.1416/2));nrm[c1]=xyz[c1];c1+=1;
                    xyz[c1]=(j-0.5f);nrm[c1]=0;c1+=1;
                    xyz[c1]=(float)(0.5* Math.sin(2*3.1416*i/(res-1f)-3.1416/2));nrm[c1]=xyz[c1];c1+=1;
                }
                else
                {
                    xyz[c1]=(float)(xx*0.5* Math.cos(2*3.1416*i/(res-1f)-3.1416/2));nrm[c1]=xyz[c1];c1+=1;
                    xyz[c1]=(j-0.5f);nrm[c1]=0;c1+=1;
                    xyz[c1]=(float)(zz*0.5* Math.sin(2*3.1416*i/(res-1f)-3.1416/2));nrm[c1]=xyz[c1];c1+=1;
                }
                uv[c2]=1-i/(res-1.0f);c2+=1;
                uv[c2]=j;c2+=1;
                if(i<res-1&&j<1)
                {
                    tri[c3]=c4;c3+=1;tri[c3]=c4+res;c3+=1;tri[c3]=c4+1;c3+=1;
                    tri[c3]=c4+1;c3+=1;tri[c3]=c4+res;c3+=1;tri[c3]=c4+res+1;c3+=1;
                }
                c4+=1;
            }

        this.pushMatrix();
        this.scale(x,y,z);

        this.appendTriangles(tri);
        this.appendXYZ(xyz);
        this.appendNormals(nrm);
        this.appendUV(uv);
        if(this.current_color!=null)
        {
            float[] clr=new float[3*res*2];
            int j=0;
            for(int i=0;i<res*2;i++)
            {
                clr[j]=this.current_color[0];j+=1;clr[j]=this.current_color[1];j+=1;clr[j]=this.current_color[2];j+=1;
            }
            this.appendColors(clr);
        }

        //if(opt.noTop){}
        //else
        {
            this.pushMatrix();
            this.translate(0,0.5f,0);
            this.rotateX(-90);
            this.disc(xx,zz,res-1);
            this.popMatrix();
        }
        //if(opt.noBottom){}
        //else
        {
            this.pushMatrix();
            this.translate(0,-0.5f,0);
            this.rotate(180,1,0,0);
            this.rotateX(-90);
            this.disc(1,1,res-1);
            this.popMatrix();
        }
        this.popMatrix();
    }

    public void cylinder(float width, float height, float depth,float width2, float depth2)
    {
        this.cylinder(width,height,depth,width2,depth2,16);
    }

    public void cylinder(float width, float height, float depth)
    {
        this.cylinder(width,height,depth,width,depth,16);
    }

    public void cylinder(float width, float height, float depth, int resolution)
    {
        this.cylinder(width,height,depth,width,depth,resolution);
    }

    public void cylinderY(float width, float height, float depth, int resolution)
    {
        this.cylinder(width,height,depth,width,depth,resolution);
    }


    public void cylinderX(float width, float height, float depth, int resolution)
    {
        this.pushMatrix();
        this.rotate(90,0,0,1);
        this.cylinderY(height,width,depth,resolution);
        this.popMatrix();
    }


    public void cylinderZ(float width, float height, float depth, int resolution)
    {
        this.pushMatrix();
        this.rotate(90,1,0,0);
        this.cylinderY(width,depth,height,resolution);
        this.popMatrix();
    };

    public void sphere(float width, float height, float depth){
        sphere(width,height,depth,16);
    }
    public void sphere(float width, float height, float depth, int resolution){
        float x=width;
        float y=height;
        float z=depth;

        int res=resolution+1;
        float[] xyz=new float[3*res*res];
        float[] nrm=new float[3*res*res];
        float[] uv=new float[2*res*res];
        int[] tri=new int[6*(res-1)*(res-2)];
        int c1=0;
        int c2=0;
        int c3=0;
        int c4=0;
        for(int j=0;j<res;j++)
            for(int i=0;i<res;i++)
            {
                xyz[c1]=(float)(0.5* Math.cos(3.1416*(j/(res-1f)-0.5))* Math.cos(2*3.1416*i/(res-1f)-3.1416/2));nrm[c1]=xyz[c1]*2;c1+=1;
                xyz[c1]=(float)(0.5* Math.sin(3.1416*(j/(res-1f)-0.5)));nrm[c1]=xyz[c1]*2;c1+=1;
                xyz[c1]=(float)(0.5* Math.cos(3.1416*(j/(res-1f)-0.5))* Math.sin(2*3.1416*i/(res-1f)-3.1416/2));nrm[c1]=xyz[c1]*2;c1+=1;
                uv[c2]=1-i/(res-1.0f);c2+=1;
                uv[c2]=1-j/(res-1.0f);c2+=1;
                if(i<res-1&&j<res-1)
                {
                    if(j>0){tri[c3]=c4;c3+=1;tri[c3]=c4+res;c3+=1;tri[c3]=c4+1;c3+=1;}
                    if(j<res-2){tri[c3]=c4+1;c3+=1;tri[c3]=c4+res;c3+=1;tri[c3]=c4+res+1;c3+=1;}
                }
                c4+=1;
            }

        this.pushMatrix();
        this.scale(x,y,z);

        this.appendTriangles(tri);
        this.appendXYZ(xyz);
        this.appendNormals(nrm);
        this.appendUV(uv);
        if(this.current_color!=null)
        {
            float[] clr=new float[3*res*res];
            int j=0;
            for(int i=0;i<res*res;i++)
            {
                clr[j]=this.current_color[0];j+=1;clr[j]=this.current_color[1];j+=1;clr[j]=this.current_color[2];j+=1;
            }
            this.appendColors(clr);
        }

        this.popMatrix();
    }

    public void clear(){
        this.xyz.clear();
        this.nrm.clear();
        this.tri.clear();
        this.uv.clear();
        this.clr.clear();
        //this.current_color=null;
    };

    public void clearColor(){
        this.current_color=new float[]{1,1,1};
    }

    public Model flushTexturedModel(){
        return flushTexturedModel(new Model());
    }
    public Model flushTexturedModel(Model model){
        if(model.shader==null)model.shader=new TextureShader();
        if(model.mesh==null) model.mesh=new Mesh();
        model.mesh.setXYZ(getXYZ());
        //obj.setNormals(getNormals());
        model.mesh.setTriangles(getTriangles());
        model.mesh.setUV(getUV());
        //obj.setColors(getColors());
        clear();
        return model;
    }

    public Model flushShadedTexturedModel(){
        return flushShadedTexturedModel(new Model());
    }
    public Model flushShadedTexturedModel(Model model){
        if(model.shader==null)model.shader=new ShadedTextureShader();
        if(model.mesh==null) model.mesh=new Mesh();
        model.mesh.setXYZ(getXYZ());
        model.mesh.setNormals(getNormals());
        model.mesh.setTriangles(getTriangles());
        model.mesh.setUV(getUV());
        //obj.setColors(getColors());
        clear();
        return model;
    }

    public Model flushColoredModel(){
        return flushColoredModel(new Model());
    }
    public Model flushColoredModel(Model model){
        if(model.shader==null)model.shader=new ColorShader();
        if(model.mesh==null) model.mesh=new Mesh();
        model.mesh.setXYZ(getXYZ());
        //obj.setNormals(getNormals());
        model.mesh.setTriangles(getTriangles());
        //obj.setUV(getUV());
        model.mesh.setColors(getColors());
        clear();
        return model;
    }

    public Model flushShadedColoredModel(){
        return flushShadedColoredModel(new Model());
    }
    public Model flushShadedColoredModel(Model model){
        if(model.shader==null)model.shader=new ShadedColorShader();
        if(model.mesh==null) model.mesh=new Mesh();
        model.mesh.setXYZ(getXYZ());
        model.mesh.setNormals(getNormals());
        model.mesh.setTriangles(getTriangles());
        //obj.setUV(getUV());
        model.mesh.setColors(getColors());
        clear();
        return model;
    }
}













/**
 * This method clears one or more attribute arrays from the object maker before the object it is flushed. You can use this method to remove UV or Normals if they are not needed in the object.
 * @param options An object with one or more of the following fields: xyz, normals, triangles, colors, uv with boolean values to indicate which one to clear. If no object is defined all data are cleaned.
 */
       /* GLObjectMaker.prototype.clear=function(options)
        {
        var opt=options||{xyz:true,normals:true,triangles:true,uv:true};
        if(opt.xyz)this.xyz=[];
        if(opt.uv)this.uv=[];
        if(opt.normals)this.nrm=[];
        if(opt.triangles)this.tri=[];
        if(opt.colors)this.clr=[];
        };*/




