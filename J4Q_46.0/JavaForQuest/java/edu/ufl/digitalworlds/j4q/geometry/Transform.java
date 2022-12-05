package edu.ufl.digitalworlds.j4q.geometry;

import android.opengl.Matrix;

import java.util.ArrayList;
import java.util.List;

import edu.ufl.digitalworlds.j4q.models.Model;

public class Transform {
    public float[] matrix=new float[16];
    private final List<float[]> matrixList=new ArrayList<float[]>();

    private boolean modified=true;
    public boolean isModified(){return modified;}
    public void resetModifiedFlag(){modified=false;}

    public Transform(float[] mat){
        System.arraycopy(mat,0,matrix,0,16);
    }
    public Transform(){
        identity();
    }

    public float[] getNormalMatrix() {
        float[] inv=new float[16];
        Matrix.invertM(inv,0,matrix,0);
        float[] trans=new float[16];
        Matrix.transposeM(trans,0,inv,0);
        return trans;
    }

    public Transform rotate(float degrees, float x, float y, float z){
        Matrix.rotateM(matrix, 0, degrees, x, y, z);
        modified=true;
        return this;
    }

    public Transform rotateX(float degrees){
        Matrix.rotateM(matrix, 0, degrees, 1, 0, 0.0f);
        modified=true;
        return this;
    }

    public Transform rotateQ(float a, float x, float y, float z){
        float[] m=new float[16];
        //Column 1
        m[0] = (a*a) + (x*x) - (y*y) - (z*z);
        m[1] = (2*x*y) + (2*a*z);
        m[2] = (2*x*z) - (2*a*y);
        m[3] = 0;
        //Column 2
        m[4] = (2*x*y) - (2*a*z);
        m[5] = (a*a) - (x*x) + (y*y) - (z*z);
        m[6] = (2*y*z) + (2*a*x);
        m[7] = 0;
        //Column 3
        m[8] = (2*x*z) + (2*a*y);
        m[9] = (2*y*z) - (2*a*x);
        m[10] = (a*a) - (x*x) - (y*y) + (z*z);
        m[11] = 0;
        //Column 4
        m[12] = 0;
        m[13] = 0;
        m[14] = 0;
        m[15] = 1;
        return multiply(m);
    }

    public Transform rotate(Orientation q){
        return this.rotateQ(q.w,q.x,q.y,q.z);
    }

    public Transform rotateY(float degrees){
        Matrix.rotateM(matrix, 0, degrees, 0, 1, 0.0f);
        modified=true;
        return this;
    }

    public Transform rotateZ(float degrees){
        Matrix.rotateM(matrix, 0, degrees, 0, 0, 1.0f);
        modified=true;
        return this;
    }

    public Transform translate(Position p){
        return translate(p.x,p.y,p.z);
    }

    public float[] getInvertedMatrix(){
        float[] inv=new float[16];
        Matrix.invertM(inv,0,matrix,0);
        return inv;
    }

    public Transform translate(float x, float y, float z){
        Matrix.translateM(matrix,0,x,y,z);
        modified=true;
        return this;
    }

    public Position getPosition(){
        return new Position(matrix[12],matrix[13],matrix[14]);
    }

    public Transform scale(float x, float y , float z){
        Matrix.scaleM(matrix,0,x,y,z);
        modified=true;
        return this;
    }

    public Transform scale(float scale){
        return this.scale(scale,scale,scale);
    }

    public Transform identity(){
        Matrix.setIdentityM(matrix,0);
        modified=true;
        return this;
    }

    public Transform reset(){
        return identity();
    }

    public Transform reset(float[] m){
        System.arraycopy(m,0,matrix,0,16);
        modified=true;
       return this;
    }

    public Transform reset(Model m){
        if(m!=null)
           return reset(m.globalTransform.matrix);
        else return reset();
    }

    public Transform multiply(float[] mat){
        float[] copy=new float[16];
        System.arraycopy(matrix,0,copy,0,16);
        Matrix.multiplyMM(matrix,0,copy,0,mat,0);
        modified=true;
        return this;
    }

    public Transform pushMatrix(){
        matrixList.add(matrix) ;
        float[] m=new float[16];
        for(int i=0;i<16;i++)m[i]=matrix[i];
        matrix=m;
        return this;
    }

    public Transform save(){
        return pushMatrix();
    }

    public Transform popMatrix(){
        if(matrixList.size()>0)
            matrix=matrixList.remove(matrixList.size()-1);
        return this;
    }

    public Transform restore(){
        return popMatrix();
    }

}
