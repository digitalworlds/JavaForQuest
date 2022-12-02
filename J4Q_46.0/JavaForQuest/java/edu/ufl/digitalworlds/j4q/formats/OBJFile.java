package edu.ufl.digitalworlds.j4q.formats;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ufl.digitalworlds.j4q.J4Q;

public class OBJFile {

    class OBJFileMesh{
        ArrayList<Integer> Triangles_XYZ;
        ArrayList<Integer> Triangles_Normals;
        ArrayList<Integer> Triangles_UV;
        String material;
    }

    public OBJFile(final String filename){
        this(J4Q.activity,filename);
    }

    public OBJFile(final Context context, final String filename) {

        try {
            InputStream is = context.getAssets().open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            parseOBJ(br);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading OBJ file.");
        }
    }

    private void parseOBJ(BufferedReader reader) throws IOException {

        ArrayList<Float> XYZ=new ArrayList<Float>();
        ArrayList<Float> Normals=new ArrayList<Float>();
        ArrayList<Float> UV=new ArrayList<Float>();

        ArrayList<Integer> Triangles_XYZ=new ArrayList<Integer>();
        ArrayList<Integer> Triangles_Normals=new ArrayList<Integer>();
        ArrayList<Integer> Triangles_UV=new ArrayList<Integer>();

        String mtllib="";
        ArrayList<OBJFileMesh> Meshes=new ArrayList<OBJFileMesh>();
        OBJFileMesh mesh=new OBJFileMesh();

        String line = reader.readLine();
        while (line != null) {

            String pieces[]=line.split(" ");



            switch(pieces[0]){
                case"mtllib":
                    String p[]=pieces[1].split("/");//to remove any path info
                    mtllib=p[p.length-1];
                   break;
                case"usemtl":
                    if(mesh.Triangles_XYZ!=null){
                        Meshes.add(mesh);
                    }
                    Triangles_XYZ=new ArrayList<Integer>();
                    Triangles_Normals=new ArrayList<Integer>();
                    Triangles_UV=new ArrayList<Integer>();
                    mesh=new OBJFileMesh();
                    mesh.material=pieces[1];
                    mesh.Triangles_XYZ=Triangles_XYZ;
                    mesh.Triangles_Normals=Triangles_Normals;
                    mesh.Triangles_UV=Triangles_UV;
                    break;
                case"v":
                    XYZ.add(Float.parseFloat(pieces[1]));
                    XYZ.add(Float.parseFloat(pieces[2]));
                    XYZ.add(Float.parseFloat(pieces[3]));
                    break;
                case"vn":
                    Normals.add(Float.parseFloat(pieces[1]));
                    Normals.add(Float.parseFloat(pieces[2]));
                    Normals.add(Float.parseFloat(pieces[3]));
                    break;
                case"vt":
                    UV.add(Float.parseFloat(pieces[1]));
                    UV.add(Float.parseFloat(pieces[2]));
                    break;
                case"f":
                    if(mesh.Triangles_XYZ==null){//no material was found
                        Triangles_XYZ=new ArrayList<Integer>();
                        Triangles_Normals=new ArrayList<Integer>();
                        Triangles_UV=new ArrayList<Integer>();
                        mesh=new OBJFileMesh();
                        mesh.Triangles_XYZ=Triangles_XYZ;
                        mesh.Triangles_Normals=Triangles_Normals;
                        mesh.Triangles_UV=Triangles_UV;
                    }

                    String firstPieces[]=pieces[1].split("/");
                    String secondPieces[]=pieces[2].split("/");
                    String thirdPieces[]=pieces[3].split("/");
                    String fourthPieces[]=null;
                    if(pieces.length>4) fourthPieces=pieces[4].split("/");

                    if(firstPieces.length>0){//XYZ index
                        Triangles_XYZ.add(Integer.parseInt(firstPieces[0]));
                        Triangles_XYZ.add(Integer.parseInt(secondPieces[0]));
                        Triangles_XYZ.add(Integer.parseInt(thirdPieces[0]));
                        if(fourthPieces!=null){//handle quad faces

                        }
                    }
                    if(firstPieces.length>1){//UV index
                        Triangles_UV.add(Integer.parseInt(firstPieces[1]));
                        Triangles_UV.add(Integer.parseInt(secondPieces[1]));
                        Triangles_UV.add(Integer.parseInt(thirdPieces[1]));
                        if(fourthPieces!=null){//handle quad faces

                        }
                    }
                    if(firstPieces.length>2){//Normal index
                        Triangles_Normals.add(Integer.parseInt(firstPieces[2]));
                        Triangles_Normals.add(Integer.parseInt(secondPieces[2]));
                        Triangles_Normals.add(Integer.parseInt(thirdPieces[2]));
                        if(fourthPieces!=null){//handle quad faces

                        }
                    }

                    break;
                default:
                     break;
            }
            // read next line
            line = reader.readLine();
        }


        reader.close();

        postProcessing(XYZ,UV,Normals, Meshes);

    }

    private static int map(int xyz_i, int uv_i, int xyzc,HashMap<Integer,HashMap<Integer,Integer>> XYZmap){
        if(XYZmap.containsKey(xyz_i)){
            if(XYZmap.get(xyz_i).containsKey(uv_i))
                return XYZmap.get(xyz_i).get(uv_i);
            else{
                XYZmap.get(xyz_i).put(uv_i,xyzc);
                return xyzc;
            }
        }
        else{
            HashMap<Integer, Integer> m= new HashMap<Integer, Integer>();
            m.put(uv_i,xyzc);
            XYZmap.put(xyz_i,m);
            return xyzc;
        }
    };

    private static int map3(int xyz_i, int uv_i, int nrm_i, int xyzc,HashMap<Integer,HashMap<Integer,HashMap<Integer,Integer>>> XYZmap){
        if(XYZmap.containsKey(xyz_i)){
            if(XYZmap.get(xyz_i).containsKey(uv_i)) {
                if(XYZmap.get(xyz_i).get(uv_i).containsKey(nrm_i))
                    return XYZmap.get(xyz_i).get(uv_i).get(nrm_i);
                else {
                    XYZmap.get(xyz_i).get(uv_i).put(nrm_i,xyzc);
                    return xyzc;
                }
            }
            else{
                HashMap<Integer, Integer> m= new HashMap<Integer, Integer>();
                m.put(nrm_i,xyzc);
                XYZmap.get(xyz_i).put(uv_i,m);
                return xyzc;
            }
        }
        else{
            HashMap<Integer, HashMap<Integer,Integer>> m= new HashMap<Integer, HashMap<Integer,Integer>>();
            HashMap<Integer, Integer> m2= new HashMap<Integer, Integer>();
            m.put(uv_i,m2);
            m2.put(nrm_i,xyzc);
            XYZmap.put(xyz_i,m);
            return xyzc;
        }
    };

    private static void pushXYZ(int jj, ArrayList<Float> XYZ_src, ArrayList<Float> XYZ_dst)
    {
        float v=XYZ_src.get(jj);
        XYZ_dst.add(v);
        //if(v>maxX)maxX=v;
        //else if(v<minX)minX=v;
        v=XYZ_src.get(jj+1);
        XYZ_dst.add(v);
        //if(v>maxY)maxY=v;
        //else if(v<minY)minY=v;
        v=XYZ_src.get(jj+2);
        XYZ_dst.add(v);
        //if(v>maxZ)maxZ=v;
        //else if(v<minZ)minZ=v;
    }

    private void postProcessing(ArrayList<Float> XYZ, ArrayList<Float> UV, ArrayList<Float> Normals, ArrayList<OBJFileMesh> Meshes) {
        //var output={models:[],range:{X:{min:0,max:0},Y:{min:0,max:0},Z:{min:0,max:0}},numOfTriangles:0,numOfVertices:0};

        //if(data.mtllib&&data.mtllib.length>0)output.mtllib=data.mtllib;

        HashMap<String,HashMap<String,Integer>> XYZmap=new HashMap<String,HashMap<String,Integer>>();

        for(int mi=0;mi<Meshes.size();mi++)
        {
            OBJFileMesh M=Meshes.get(mi);

            /*var out={parts:[],range:{X:{min:0,max:0},Y:{min:0,max:0},Z:{min:0,max:0}},numOfTriangles:0,numOfVertices:0,material:data.Meshes[mi].material};
            output.models.push(out);
            if(data.Normals.length==0)
            {
                data.Normals=this.computeNormals(M,data.XYZ);
                out.normalsCalculated=true;
            }
            var XYZ=[];
            var xyzc=0;
            var UV=[];
            var TRI=[];
            var NRM=[];
            var RGB=[];

            var XYZmap=[];*/







            /*var makeModel=function()
            {
                var p={
                        Triangles:TRI,
                    XYZ:XYZ
			};
                if(UV.length>0&&UV[0]!=null)p.UV=UV;
                if(NRM.length>0)p.Normals=NRM;
                if(RGB.length>0)p.Colors=RGB;
                out.parts.push(p);
                out.numOfTriangles+=TRI.length/3;
                out.numOfVertices+=XYZ.length/3;

                XYZ=[];
                xyzc=0;
                UV=[];
                TRI=[];
                NRM=[];
                RGB=[];
                XYZmap=[];
            }

            var minX=data.XYZ[0];
            var maxX=data.XYZ[0];
            var minY=data.XYZ[1];
            var maxY=data.XYZ[1];
            var minZ=data.XYZ[2];
            var maxZ=data.XYZ[2];


             */


            for(int i=0;i<M.Triangles_XYZ.size();i++)
            {
                if(M.Triangles_UV.size()>0 && M.Triangles_Normals.size()>0)
                {
                    /*var j=M[i];
                    var ii=map3(j[0]-1,j[3]-1,j[6]-1);
                    if(ii==xyzc){

                        var jj=3*(j[0]-1);
                        pushXYZ(jj);
                        jj=3*(j[6]-1);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        jj=2*(j[3]-1);
                        UV.push(data.UV[jj]);
                        UV.push(data.UV[jj+1]);
                        TRI.push(xyzc);
                        xyzc+=1;
                    }
                    else{
                        TRI.push(ii);
                    }

                    var ii=map3(j[1]-1,j[4]-1,j[7]-1);
                    if(ii==xyzc){

                        var jj=3*(j[1]-1);
                        pushXYZ(jj);
                        jj=3*(j[7]-1);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        jj=2*(j[4]-1);
                        UV.push(data.UV[jj]);
                        UV.push(data.UV[jj+1]);
                        TRI.push(xyzc);
                        xyzc+=1;
                    }
                    else{
                        TRI.push(ii);
                    }

                    var ii=map3(j[2]-1,j[5]-1,j[8]-1);
                    if(ii==xyzc){

                        var jj=3*(j[2]-1);
                        pushXYZ(jj);
                        jj=3*(j[8]-1);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        jj=2*(j[5]-1);
                        UV.push(data.UV[jj]);
                        UV.push(data.UV[jj+1]);
                        TRI.push(xyzc);
                        xyzc+=1;
                        if(xyzc>=65533)makeModel();
                    }
                    else{
                        TRI.push(ii);
                        if(xyzc>=65533)makeModel();
                    }*/
                }
                else if(M.Triangles_Normals!=null)
                {

                }
                else if(M.Triangles_UV!=null)
                {
                    /*var j=M[i];
                    var ii=map(j[0]-1,j[3]-1);
                    if(ii==xyzc){

                        var jj=3*(j[0]-1);
                        pushXYZ(jj);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        jj=2*(j[3]-1);
                        UV.push(data.UV[jj]);
                        UV.push(data.UV[jj+1]);
                        TRI.push(xyzc);
                        xyzc+=1;
                    }
                    else{
                        TRI.push(ii);
                    }

                    var ii=map(j[1]-1,j[4]-1);
                    if(ii==xyzc){

                        var jj=3*(j[1]-1);
                        pushXYZ(jj);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        jj=2*(j[4]-1);
                        UV.push(data.UV[jj]);
                        UV.push(data.UV[jj+1]);
                        TRI.push(xyzc);
                        xyzc+=1;
                    }
                    else{
                        TRI.push(ii);
                    }

                    var ii=map(j[2]-1,j[5]-1);
                    if(ii==xyzc){

                        var jj=3*(j[2]-1);
                        pushXYZ(jj);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        jj=2*(j[5]-1);
                        UV.push(data.UV[jj]);
                        UV.push(data.UV[jj+1]);
                        TRI.push(xyzc);
                        xyzc+=1;
                        if(xyzc>=65533)makeModel();
                    }
                    else{
                        TRI.push(ii);
                        if(xyzc>=65533)makeModel();
                    }*/
                }
                else//M.Triangles_UV==null && M.Triangles_Normals==null
                {
                    /*var j=M[i];
                    var ii=map(j[0]-1,'n');
                    if(ii==xyzc){

                        var jj=3*(j[0]-1);
                        pushXYZ(jj);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        if(data.RGB&&data.RGB.length>0){
                            RGB.push(data.RGB[jj]);
                            RGB.push(data.RGB[jj+1]);
                            RGB.push(data.RGB[jj+2]);
                        }
                        if(data.UV&&data.UV.length>0){
                            jj=2*(j[0]-1);
                            UV.push(data.UV[jj]);
                            UV.push(data.UV[jj+1]);
                        }
                        TRI.push(xyzc);
                        xyzc+=1;
                    }
                    else{
                        TRI.push(ii);
                    }

                    var ii=map(j[1]-1,'n');
                    if(ii==xyzc){

                        var jj=3*(j[1]-1);
                        pushXYZ(jj);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        if(data.RGB&&data.RGB.length>0){
                            RGB.push(data.RGB[jj]);
                            RGB.push(data.RGB[jj+1]);
                            RGB.push(data.RGB[jj+2]);
                        }
                        if(data.UV&&data.UV.length>0){
                            jj=2*(j[1]-1);
                            UV.push(data.UV[jj]);
                            UV.push(data.UV[jj+1]);
                        }
                        TRI.push(xyzc);
                        xyzc+=1;
                    }
                    else{
                        TRI.push(ii);
                    }

                    var ii=map(j[2]-1,'n');
                    if(ii==xyzc){

                        var jj=3*(j[2]-1);
                        pushXYZ(jj);
                        NRM.push(data.Normals[jj]);
                        NRM.push(data.Normals[jj+1]);
                        NRM.push(data.Normals[jj+2]);
                        if(data.RGB&&data.RGB.length>0){
                            RGB.push(data.RGB[jj]);
                            RGB.push(data.RGB[jj+1]);
                            RGB.push(data.RGB[jj+2]);
                        }
                        if(data.UV&&data.UV.length>0){
                            jj=2*(j[2]-1);
                            UV.push(data.UV[jj]);
                            UV.push(data.UV[jj+1]);
                        }
                        TRI.push(xyzc);
                        xyzc+=1;
                        if(xyzc>=65533)makeModel();
                    }
                    else{
                        TRI.push(ii);
                        if(xyzc>=65533)makeModel();
                    }*/
                }
            }

            /*makeModel();
            out.range.X.min=minX;
            out.range.X.max=maxX;
            out.range.Y.min=minY;
            out.range.Y.max=maxY;
            out.range.Z.min=minZ;
            out.range.Z.max=maxZ;*/
        }
        /*if(output.models.length>0){
            output.range.X.min=output.models[0].range.X.min;
            output.range.X.max=output.models[0].range.X.max;
            output.range.Y.min=output.models[0].range.Y.min;
            output.range.Y.max=output.models[0].range.Y.max;
            output.range.Z.min=output.models[0].range.Z.min;
            output.range.Z.max=output.models[0].range.Z.max;
            output.numOfTriangles=output.models[0].numOfTriangles;
            output.numOfVertices=output.models[0].numOfVertices;
        }
        for(var i=1;i<output.models.length;i++){
            if(output.models[i].range.X.min<output.range.X.min)
                output.range.X.min=output.models[i].range.X.min;
            if(output.models[i].range.X.max>output.range.X.max)
                output.range.X.max=output.models[i].range.X.max;
            if(output.models[i].range.Y.min<output.range.Y.min)
                output.range.Y.min=output.models[i].range.Y.min;
            if(output.models[i].range.Y.max>output.range.Y.max)
                output.range.Y.max=output.models[i].range.Y.max;
            if(output.models[i].range.Z.min<output.range.Z.min)
                output.range.Z.min=output.models[i].range.Z.min;
            if(output.models[i].range.Z.max>output.range.Z.max)
                output.range.Z.max=output.models[i].range.Z.max;
            output.numOfTriangles+=output.models[i].numOfTriangles;
            output.numOfVertices+=output.models[i].numOfVertices;
        }
        return output;*/
    }




}
