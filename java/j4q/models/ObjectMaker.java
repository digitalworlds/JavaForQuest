package j4q.models;

import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.List;

import j4q.geometry.Transform;


/**
 * Utility class for constructing 3D objects and geometry buffers.
 * <p>
 * Provides methods to append vertex, normal, UV, color, and triangle data, and to generate common shapes.
 * </p>
 */
public class ObjectMaker extends Transform {

    /**
     * Converts a list of Float values to a float array.
     * @param floatList The list of Float values.
     * @return The resulting float array.
     */
    private float[] getArray(List<Float> floatList){
        float[] floatArray = new float[floatList.size()];
        int i = 0;

        for (Float f : floatList) {
            floatArray[i++] = (f != null ? f : Float.NaN); // Or whatever default you want.
        }
        return floatArray;
    }

    private final List<Float> xyz=new ArrayList<Float>();
    /**
     * Returns the XYZ vertex positions as a float array.
     * @return The XYZ positions.
     */
    public float[] getXYZ(){return getArray(xyz); }
    private final List<Float> nrm=new ArrayList<Float>();
    /**
     * Returns the normal vectors as a float array.
     * @return The normal vectors.
     */
    public float[] getNormals(){return getArray(nrm); }
    private final List<Integer> tri=new ArrayList<Integer>();
    /**
     * Returns the triangle indices as a short array.
     * @return The triangle indices.
     */
    public short[] getTriangles(){
        short[] intArray = new short[tri.size()];
        int i = 0;

        for (Integer f : tri) {
            intArray[i++] = (short)(f != null ? f : 0); // Or whatever default you want.
        }
        return intArray;
    }
    private final List<Float> uv=new ArrayList<Float>();
    /**
     * Returns the UV coordinates as a float array.
     * @return The UV coordinates.
     */
    public float[] getUV(){return getArray(uv); }
    private final List<Float> clr=new ArrayList<Float>();
    /**
     * Returns the color values as a float array.
     * @return The color values.
     */
    public float[] getColors(){return getArray(clr); }
    private float[] current_color=new float[]{1,1,1};

    /**
     * Constructs a new ObjectMaker instance.
     */
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

    /**
     * Appends XYZ vertex positions, transformed by the current matrix.
     * @param v The array of vertex positions.
     */
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

    /**
     * Appends normal vectors, transformed by the current matrix.
     * @param v The array of normal vectors.
     */
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

    /**
     * Appends UV coordinates.
     * @param v The array of UV coordinates.
     */
    public void appendUV(float[] v){
        int uv_start=this.uv.size();
        for(int i=0;i<v.length;i++)
        {
            this.uv.add(new Float(v[i]));
        }
    }

    /**
     * Appends triangle indices from an int array.
     * @param v The array of triangle indices.
     */
    public void appendTriangles(int[] v){
        int v_length=this.xyz.size()/3;
        int tri_start=this.tri.size();
        for(int i=0;i<v.length;i++)
        {
            this.tri.add(new Integer(v[i]+v_length));
            //this.tri[tri_start+i]=v[i]+v_length;
        }
    }

    /**
     * Appends triangle indices from a short array.
     * @param v The array of triangle indices.
     */
    public void appendTriangles(short[] v){
        int v_length=this.xyz.size()/3;
        int tri_start=this.tri.size();
        for(int i=0;i<v.length;i++)
        {
            this.tri.add(new Integer(v[i]+v_length));
            //this.tri[tri_start+i]=v[i]+v_length;
        }
    }

    /**
     * Appends color values.
     * @param v The array of color values.
     */
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


    /**
     * Sets the current color for subsequent geometry.
     * @param color The RGB color array.
     */
    public void color(float[] color){
        this.current_color=color;
    }

    /**
     * Sets the current color for subsequent geometry using individual RGB values.
     * @param r Red component.
     * @param g Green component.
     * @param b Blue component.
     */
    public void color(float r, float g, float b){
        this.current_color=new float[]{r,g,b};
    }

    /**
     * Appends a rectangle shape with specified width, height, and UV scaling.
     * @param width The rectangle width.
     * @param height The rectangle height.
     * @param u The U texture scale.
     * @param v The V texture scale.
     */
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

    /**
     * Appends a rectangle shape with specified width and height.
     * @param width The rectangle width.
     * @param height The rectangle height.
     */
    public void rectangle(float width,float height){
        rectangle(width,height,1,1);
    }

    /**
     * Appends a box shape with specified dimensions.
     * @param width The box width.
     * @param height The box height.
     * @param depth The box depth.
     */
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

    /**
     * Appends a trapezoid shape with specified width, height, and depth.
     * @param width The trapezoid width.
     * @param height The trapezoid height.
     * @param depth The trapezoid depth.
     */
    public void trapezoid(float width, float height, float depth) {
        trapezoid(width,height,depth,width,depth);
    }

    /**
     * Appends a trapezoid shape with different top and bottom dimensions.
     * @param width The bottom width.
     * @param height The height.
     * @param depth The bottom depth.
     * @param width2 The top width.
     * @param depth2 The top depth.
     */
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

    /**
     * Appends a pyramid shape with specified dimensions.
     * @param width The pyramid width.
     * @param height The pyramid height.
     * @param depth The pyramid depth.
     */
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

    /**
     * Appends a disc shape with specified width, height, and resolution.
     * @param width The disc width.
     * @param height The disc height.
     * @param resolution The number of segments.
     */
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

    /**
     * Appends a cone shape with specified dimensions and default resolution.
     * @param width The cone width.
     * @param height The cone height.
     * @param depth The cone depth.
     */
    public void cone(float width, float height, float depth){
        cone(width,height,depth,16);
    }

    /**
     * Appends a cone shape with specified dimensions and resolution.
     * @param width The cone width.
     * @param height The cone height.
     * @param depth The cone depth.
     * @param resolution The number of segments.
     */
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

    /**
     * Appends a cylinder shape with specified dimensions, top/bottom radii, and resolution.
     * @param width The bottom width.
     * @param height The height.
     * @param depth The bottom depth.
     * @param width2 The top width.
     * @param depth2 The top depth.
     * @param resolution The number of segments.
     */
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

    /**
     * Appends a cylinder shape with specified dimensions and top/bottom radii, using default resolution.
     * @param width The bottom width.
     * @param height The height.
     * @param depth The bottom depth.
     * @param width2 The top width.
     * @param depth2 The top depth.
     */
    public void cylinder(float width, float height, float depth,float width2, float depth2)
    {
        this.cylinder(width,height,depth,width2,depth2,16);
    }

    /**
     * Appends a cylinder shape with specified dimensions, using default resolution.
     * @param width The cylinder width.
     * @param height The cylinder height.
     * @param depth The cylinder depth.
     */
    public void cylinder(float width, float height, float depth)
    {
        this.cylinder(width,height,depth,width,depth,16);
    }

    /**
     * Appends a cylinder shape with specified dimensions and resolution.
     * @param width The cylinder width.
     * @param height The cylinder height.
     * @param depth The cylinder depth.
     * @param resolution The number of segments.
     */
    public void cylinder(float width, float height, float depth, int resolution)
    {
        this.cylinder(width,height,depth,width,depth,resolution);
    }

    /**
     * Appends a cylinder shape aligned along the Y axis.
     * @param width The cylinder width.
     * @param height The cylinder height.
     * @param depth The cylinder depth.
     * @param resolution The number of segments.
     */
    public void cylinderY(float width, float height, float depth, int resolution)
    {
        this.cylinder(width,height,depth,width,depth,resolution);
    }


    /**
     * Appends a cylinder shape aligned along the X axis.
     * @param width The cylinder width.
     * @param height The cylinder height.
     * @param depth The cylinder depth.
     * @param resolution The number of segments.
     */
    public void cylinderX(float width, float height, float depth, int resolution)
    {
        this.pushMatrix();
        this.rotate(90,0,0,1);
        this.cylinderY(height,width,depth,resolution);
        this.popMatrix();
    }


    /**
     * Appends a cylinder shape aligned along the Z axis.
     * @param width The cylinder width.
     * @param height The cylinder height.
     * @param depth The cylinder depth.
     * @param resolution The number of segments.
     */
    public void cylinderZ(float width, float height, float depth, int resolution)
    {
        this.pushMatrix();
        this.rotate(90,1,0,0);
        this.cylinderY(width,depth,height,resolution);
        this.popMatrix();
    }

    /**
     * Appends a sphere shape with specified dimensions and default resolution.
     * @param width The sphere width.
     * @param height The sphere height.
     * @param depth The sphere depth.
     */
    public void sphere(float width, float height, float depth){
        sphere(width,height,depth,16);
    }
    /**
     * Appends a sphere shape with specified dimensions and resolution.
     * @param width The sphere width.
     * @param height The sphere height.
     * @param depth The sphere depth.
     * @param resolution The number of segments.
     */
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
                uv[c2]=j/(res-1.0f);c2+=1;
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

    /**
     * Clears all geometry buffers (vertices, normals, triangles, UVs, colors).
     */
    public void clear(){
        this.xyz.clear();
        this.nrm.clear();
        this.tri.clear();
        this.uv.clear();
        this.clr.clear();
        //this.current_color=null;
    };

    /**
     * Resets the current color to white.
     */
    public void clearColor(){
        this.current_color=new float[]{1,1,1};
    }

    /**
     * Creates and returns a GameObject with the current geometry buffers, optionally including normals, UVs, colors, and tangents.
     * @param normals Whether to include normals.
     * @param uv Whether to include UVs.
     * @param colors Whether to include colors.
     * @param tangents Whether to compute tangents.
     * @return The created GameObject.
     */
    public GameObject flushModel(boolean normals, boolean uv, boolean colors, boolean tangents){return flushModel(new GameObject(),normals,uv,colors,tangents);}

    /**
     * Creates and returns a GameObject with the current geometry buffers, optionally including normals, UVs, and colors.
     * @param normals Whether to include normals.
     * @param uv Whether to include UVs.
     * @param colors Whether to include colors.
     * @return The created GameObject.
     */
    public GameObject flushModel(boolean normals, boolean uv, boolean colors){return flushModel(new GameObject(),normals,uv,colors);}

    /**
     * Creates and returns a GameObject with the current geometry buffers, optionally including normals and UVs.
     * @param normals Whether to include normals.
     * @param uv Whether to include UVs.
     * @return The created GameObject.
     */
    public GameObject flushModel(boolean normals, boolean uv){return flushModel(new GameObject(),normals,uv);}

    /**
     * Creates and returns a GameObject with the current geometry buffers, optionally including normals.
     * @param normals Whether to include normals.
     * @return The created GameObject.
     */
    public GameObject flushModel(boolean normals){return flushModel(new GameObject(),normals);}

    /**
     * Creates and returns a GameObject with the current geometry buffers.
     * @return The created GameObject.
     */
    public GameObject flushModel(){return flushModel(new GameObject());}

    /**
     * Creates and returns a GameObject with the current geometry buffers, optionally including normals, UVs, colors, and tangents, using the provided GameObject.
     * @param model The GameObject to populate.
     * @param normals Whether to include normals.
     * @param uv Whether to include UVs.
     * @param colors Whether to include colors.
     * @param tangents Whether to compute tangents.
     * @return The populated GameObject.
     */
    public GameObject flushModel(GameObject model, boolean normals, boolean uv, boolean colors, boolean tangents){
        if(model.mesh==null)model.addComponent(new Mesh());
        if(tangents)model.mesh.keepData(true);
        model.mesh.setXYZ(getXYZ());
        if(normals) model.mesh.setNormals(getNormals());
        model.mesh.setTriangles(getTriangles());
        if(uv) model.mesh.setUV(getUV());
        if(colors) model.mesh.setColors(getColors());
        if(tangents){
            model.mesh.computeTangents();
            model.mesh.keepData(false);
        }
        clear();
        return model;
    }

    /**
     * Creates and returns a GameObject with the current geometry buffers, optionally including normals, UVs, and colors, using the provided GameObject.
     * @param model The GameObject to populate.
     * @param normals Whether to include normals.
     * @param uv Whether to include UVs.
     * @param colors Whether to include colors.
     * @return The populated GameObject.
     */
    public GameObject flushModel(GameObject model, boolean normals, boolean uv, boolean colors){
        return flushModel(model,normals,uv,colors,false);
    }

    /**
     * Creates and returns a GameObject with the current geometry buffers, optionally including normals and UVs, using the provided GameObject.
     * @param model The GameObject to populate.
     * @param normals Whether to include normals.
     * @param uv Whether to include UVs.
     * @return The populated GameObject.
     */
    public GameObject flushModel(GameObject model, boolean normals, boolean uv){
        return flushModel(model,normals,uv,false,false);
    }

    /**
     * Creates and returns a GameObject with the current geometry buffers, optionally including normals, using the provided GameObject.
     * @param model The GameObject to populate.
     * @param normals Whether to include normals.
     * @return The populated GameObject.
     */
    public GameObject flushModel(GameObject model, boolean normals){
        return flushModel(model,normals,false,false,false);
    }

    /**
     * Creates and returns a GameObject with the current geometry buffers, using the provided GameObject.
     * @param model The GameObject to populate.
     * @return The populated GameObject.
     */
    public GameObject flushModel(GameObject model){
        return flushModel(model,false,false,false,false);
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




