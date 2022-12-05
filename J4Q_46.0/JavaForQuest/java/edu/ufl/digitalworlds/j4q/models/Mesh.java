package edu.ufl.digitalworlds.j4q.models;

import android.opengl.GLES30;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

public class Mesh {

    public int triangleLength=0;

    public int vertexArrayObject;
    private int indexBuffer=0;
    private int[] arrayBuffers;



    public Mesh() {
        int[] i=new int[1];
        GLES30.glGenVertexArrays( 1,i,0 );
        vertexArrayObject=i[0];
        arrayBuffers=new int[0];
    }

    private int getArrayBuffer(int slot){
        if(slot<arrayBuffers.length){
            if(arrayBuffers[slot]==0){
                int i[] = new int[1];
                GLES30.glGenBuffers(1, i, 0);
                arrayBuffers[slot] = i[0];
            }
            return arrayBuffers[slot];
        }else{
            int[] newarray=new int[slot+1];
            for(int i=0;i<arrayBuffers.length;i++)newarray[i]=arrayBuffers[i];
            arrayBuffers=newarray;

            int i[] = new int[1];
            GLES30.glGenBuffers(1, i, 0);
            arrayBuffers[slot] = i[0];

            return arrayBuffers[slot];
        }
    }

    public void setXYZ(ArrayList<Float> vertices){
        if(vertices==null || vertices.size()==0)return;

        float v[]=new float[vertices.size()];
        int i = 0;
        for (Float f : vertices) {
            v[i++] = f;
        }
        setXYZ(v);
    }

    public void setXYZ(float[] vertices){
        if(keep_data)this.xyz=vertices;
        setArrayBuffer3f(vertices,0);
    }

    public void setUV(ArrayList<Float> vertices){
        if(vertices==null || vertices.size()==0)return;

        float v[]=new float[vertices.size()];
        int i = 0;
        for (Float f : vertices) {
            v[i++] = f;
        }
        setUV(v);
    }

    public void setUV(float[] uv){
        setArrayBuffer2f(uv,2);
    }

    public void setNormals(ArrayList<Float> vertices){
        if(vertices==null || vertices.size()==0)return;

        float v[]=new float[vertices.size()];
        int i = 0;
        for (Float f : vertices) {
            v[i++] = f;
        }
        setNormals(v);
    }

    public void setNormals(float[] normals){
        setArrayBuffer3f(normals,1);
    }
    public void setColors(float[] colors){
        setArrayBuffer3f(colors,2);
    }

    public void setArrayBuffer1f(float[] array,int slot){
        setArrayBufferf(array,1,slot);
    }
    public void setArrayBuffer2f(float[] array,int slot){
        setArrayBufferf(array,2,slot);
    }
    public void setArrayBuffer3f(float[] array,int slot){
        setArrayBufferf(array,3,slot);
    }
    public void setArrayBuffer4f(float[] array,int slot){
        setArrayBufferf(array,4,slot);
    }

    private void setArrayBufferf(float[] array,int dimensions,int slot){
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                array.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer vBuffer = bb.asFloatBuffer();
        vBuffer.put(array);
        vBuffer.position(0);

        int arrayBuffer=getArrayBuffer(slot);
        GLES30.glBindBuffer( GLES30.GL_ARRAY_BUFFER, arrayBuffer ) ;
        GLES30.glBufferData( GLES30.GL_ARRAY_BUFFER, array.length*4, vBuffer, GLES30.GL_STATIC_DRAW );
        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER,0);


        GLES30.glBindVertexArray( vertexArrayObject );
        GLES30.glBindBuffer( GLES30.GL_ARRAY_BUFFER, arrayBuffer );

        GLES30.glEnableVertexAttribArray( slot );
        GLES30.glVertexAttribPointer(slot, dimensions,
                GLES30.GL_FLOAT, false,
                0, 0);

        GLES30.glBindVertexArray(0);
    }


    public void setTriangles(ArrayList<Short> vertices){
        if(vertices==null || vertices.size()==0)return;

        short v[]=new short[vertices.size()];
        int i = 0;
        for (Short f : vertices) {
            v[i++] = f;
        }
        setTriangles(v);
    }



    public void setTriangles(short[] triangles){

        if(keep_data)this.tri=triangles;
        triangleLength=triangles.length;

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                triangles.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        ShortBuffer triangleBuffer = dlb.asShortBuffer();
        triangleBuffer.put(triangles);
        triangleBuffer.position(0);

        if(indexBuffer==0) {
            int[] i = new int[1];
            GLES30.glGenBuffers(1, i, 0);
            indexBuffer = i[0];
        }
        GLES30.glBindBuffer( GLES30.GL_ELEMENT_ARRAY_BUFFER, indexBuffer) ;
        GLES30.glBufferData( GLES30.GL_ELEMENT_ARRAY_BUFFER, triangles.length*2, triangleBuffer, GLES30.GL_STATIC_DRAW);
        GLES30.glBindBuffer( GLES30.GL_ELEMENT_ARRAY_BUFFER, 0 ) ;

        GLES30.glBindVertexArray( vertexArrayObject );
        GLES30.glBindBuffer( GLES30.GL_ELEMENT_ARRAY_BUFFER, indexBuffer );
        GLES30.glBindVertexArray(0);
    }

    private boolean keep_data=false;

    public void keepData(boolean flag){
        keep_data=flag;
        if(!keep_data){
            xyz=null;
            tri=null;
        }
    }

    private float[] xyz;
    public float[] getXYZ(){return xyz;}

    short[] tri;
    public short[] getTriangles(){return tri;}

    public void computeNormals(){

        float[] XYZ=this.getXYZ();
        float[] NRM=new float[XYZ.length];
        short[] TRI=this.getTriangles();
        int l=TRI.length/3;
        int t=0;
        for(int i=0;i<l;i++)
        {
            int idx1=Short.toUnsignedInt(TRI[t])*3;
            float[] p1=new float[]{XYZ[idx1],XYZ[idx1+1],XYZ[idx1+2]};
            int idx2=Short.toUnsignedInt(TRI[t+1])*3;
            float[] p2=new float[]{XYZ[idx2],XYZ[idx2+1],XYZ[idx2+2]};
            int idx3=Short.toUnsignedInt(TRI[t+2])*3;
            float[] p3=new float[]{XYZ[idx3],XYZ[idx3+1],XYZ[idx3+2]};
            float[] v1=new float[]{p2[0]-p1[0],p2[1]-p1[1],p2[2]-p1[2]};
            float mag1=(float)Math.sqrt(v1[0]*v1[0]+v1[1]*v1[1]+v1[2]*v1[2]);
            float v2[]=new float[]{p3[0]-p1[0],p3[1]-p1[1],p3[2]-p1[2]};
            float mag2=(float)Math.sqrt(v2[0]*v2[0]+v2[1]*v2[1]+v2[2]*v2[2]);
            if(mag1>0&&mag2>0)//&& Math.abs(v1[0]*v2[0]+v1[1]*v2[1]+v1[2]*v2[2])/(mag1*mag2)<0.99)
            {
                float n[]=new float[]{v1[1]*v2[2]-v1[2]*v2[1],v1[2]*v2[0]-v1[0]*v2[2],v1[0]*v2[1]-v1[1]*v2[0]};
                float mag=(float)Math.sqrt(n[0]*n[0]+n[1]*n[1]+n[2]*n[2]);
                if(mag>0){n[0]/=mag;n[1]/=mag;n[2]/=mag;
                    NRM[idx1]+=n[0];NRM[idx1+1]+=n[1];NRM[idx1+2]+=n[2];
                    NRM[idx2]+=n[0];NRM[idx2+1]+=n[1];NRM[idx2+2]+=n[2];
                    NRM[idx3]+=n[0];NRM[idx3+1]+=n[1];NRM[idx3+2]+=n[2];
                }
            }
            t+=3;
        }
        l=NRM.length/3;
        t=0;
        for(int i=0;i<l;i++)
        {
            float mag=(float)Math.sqrt(NRM[t]*NRM[t]+NRM[t+1]*NRM[t+1]+NRM[t+2]*NRM[t+2]);
            if(mag>0){NRM[t]/=mag;NRM[t+1]/=mag;NRM[t+2]/=mag;}
            t+=3;
        }
        this.setNormals(NRM);

    }



}
