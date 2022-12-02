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
import edu.ufl.digitalworlds.j4q.models.Mesh;

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
                        if(mesh.Triangles_UV.size()==0)mesh.Triangles_UV=null;
                        if(mesh.Triangles_Normals.size()==0)mesh.Triangles_Normals=null;
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

        if(mesh.Triangles_XYZ!=null){
            if(mesh.Triangles_UV.size()==0)mesh.Triangles_UV=null;
            if(mesh.Triangles_Normals.size()==0)mesh.Triangles_Normals=null;
            Meshes.add(mesh);
        }

        reader.close();

        if(UV.size()==0)UV=null;
        if(Normals.size()==0)Normals=null;
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

        Log.d("Angelos","Meshes: "+Meshes.size());

        ArrayList<Mesh> parts=new ArrayList<Mesh>();

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

            int xyzc=0;
            ArrayList<Float> newXYZ=new ArrayList<Float>();
            ArrayList<Float> newUV=new ArrayList<Float>();
            ArrayList<Float> newNormals=new ArrayList<Float>();
            ArrayList<Short> Triangles=new ArrayList<Short>();
            HashMap<Integer,HashMap<Integer,Integer>> XYZmap=new HashMap<Integer,HashMap<Integer,Integer>>();
            HashMap<Integer,HashMap<Integer,HashMap<Integer,Integer>>> XYZmap3=new HashMap<Integer,HashMap<Integer,HashMap<Integer,Integer>>>();


            for(int i=0;i<M.Triangles_XYZ.size();i+=3)
            {
                if(M.Triangles_UV!=null && M.Triangles_Normals!=null)
                {

                    int[] j=new int[]{M.Triangles_XYZ.get(i),M.Triangles_XYZ.get(i+1),M.Triangles_XYZ.get(i+2)} ;
                    int[] juv=new int[]{M.Triangles_UV.get(i),M.Triangles_UV.get(i+1),M.Triangles_UV.get(i+2)} ;
                    int[] jnrm=new int[]{M.Triangles_Normals.get(i),M.Triangles_Normals.get(i+1),M.Triangles_Normals.get(i+2)} ;


                    //for each of the three vertices in this triangle
                    for(int vertex=0;vertex<3;vertex++) {

                        //get a unique ID for this vertex
                        int id = map3(j[vertex] - 1, juv[vertex]-1,jnrm[vertex]-1, xyzc, XYZmap3);

                        if (id == xyzc) {//this vertex has not been indexed before
                            int jj = 3 * (j[vertex] - 1);
                            pushXYZ(jj, XYZ, newXYZ);

                            jj = 3 * (jnrm[vertex] - 1);
                            newNormals.add(Normals.get(jj));
                            newNormals.add(Normals.get(jj + 1));
                            newNormals.add(Normals.get(jj + 2));

                            jj = 2 * (juv[vertex] - 1);
                            newUV.add(UV.get(jj));
                            newUV.add(UV.get(jj + 1));

                            xyzc += 1;
                        }
                        Triangles.add((short)id);
                    }
                }
                else if(M.Triangles_Normals!=null)
                {
                    int[] j=new int[]{M.Triangles_XYZ.get(i),M.Triangles_XYZ.get(i+1),M.Triangles_XYZ.get(i+2)} ;
                    int[] jnrm=new int[]{M.Triangles_Normals.get(i),M.Triangles_Normals.get(i+1),M.Triangles_Normals.get(i+2)} ;


                    //for each of the three vertices in this triangle
                    for(int vertex=0;vertex<3;vertex++) {

                        //get a unique ID for this vertex
                        int id = map(j[vertex] - 1, jnrm[vertex]-1, xyzc, XYZmap);

                        if (id == xyzc) {//this vertex has not been indexed before
                            int jj = 3 * (j[vertex] - 1);
                            pushXYZ(jj, XYZ, newXYZ);

                            jj = 3 * (jnrm[vertex] - 1);
                            newNormals.add(Normals.get(jj));
                            newNormals.add(Normals.get(jj + 1));
                            newNormals.add(Normals.get(jj + 2));

                            if (UV != null) {
                                jj = 2 * (j[vertex] - 1);
                                newUV.add(UV.get(jj));
                                newUV.add(UV.get(jj + 1));
                            }
                            xyzc += 1;
                        }
                        Triangles.add((short)id);
                    }
                }
                else if(M.Triangles_UV!=null)
                {
                    int[] j=new int[]{M.Triangles_XYZ.get(i),M.Triangles_XYZ.get(i+1),M.Triangles_XYZ.get(i+2)} ;
                    int[] juv=new int[]{M.Triangles_UV.get(i),M.Triangles_UV.get(i+1),M.Triangles_UV.get(i+2)} ;


                    //for each of the three vertices in this triangle
                    for(int vertex=0;vertex<3;vertex++) {

                        //get a unique ID for this vertex
                        int id = map(j[vertex] - 1, juv[vertex]-1, xyzc, XYZmap);

                        if (id == xyzc) {//this vertex has not been indexed before
                            int jj = 3 * (j[vertex] - 1);
                            pushXYZ(jj, XYZ, newXYZ);
                            if (Normals != null) {
                                newNormals.add(Normals.get(jj));
                                newNormals.add(Normals.get(jj + 1));
                                newNormals.add(Normals.get(jj + 2));
                            }

                            jj = 2 * (juv[vertex] - 1);
                            newUV.add(UV.get(jj));
                            newUV.add(UV.get(jj + 1));

                            xyzc += 1;
                        }
                        Triangles.add((short)id);
                    }

                }
                else//M.Triangles_UV==null && M.Triangles_Normals==null
                {
                    int[] j=new int[]{M.Triangles_XYZ.get(i),M.Triangles_XYZ.get(i+1),M.Triangles_XYZ.get(i+2)} ;

                    //for each of the three vertices in this triangle
                    for(int vertex=0;vertex<3;vertex++) {

                        //get a unique ID for this vertex
                        int id = map(j[vertex] - 1, 0, xyzc, XYZmap);

                        if (id == xyzc) {//this vertex has not been indexed before
                            int jj = 3 * (j[vertex] - 1);
                            pushXYZ(jj, XYZ, newXYZ);
                            if (Normals != null) {
                                newNormals.add(Normals.get(jj));
                                newNormals.add(Normals.get(jj + 1));
                                newNormals.add(Normals.get(jj + 2));
                            }
                            if (UV != null) {
                                jj = 2 * (j[vertex] - 1);
                                newUV.add(UV.get(jj));
                                newUV.add(UV.get(jj + 1));
                            }
                            xyzc += 1;
                        }
                        Triangles.add((short)id);
                    }

                }

                if(xyzc>=65533){
                    Mesh m=new Mesh();
                    m.keepData(true);
                    m.setXYZ(newXYZ);
                    m.setUV(newUV);
                    m.setTriangles(Triangles);
                    if(newNormals.size()>0) m.setNormals(newNormals);
                    else m.computeNormals();
                    parts.add(m);

                    xyzc=0;
                    newXYZ=new ArrayList<Float>();
                    newUV=new ArrayList<Float>();
                    newNormals=new ArrayList<Float>();
                    Triangles=new ArrayList<Short>();
                    XYZmap=new HashMap<Integer,HashMap<Integer,Integer>>();
                    XYZmap3=new HashMap<Integer,HashMap<Integer,HashMap<Integer,Integer>>>();
                }
            }

            if(newXYZ.size()>0) {
                Mesh m = new Mesh();
                m.keepData(true);
                m.setXYZ(newXYZ);
                m.setUV(newUV);
                m.setTriangles(Triangles);
                if(newNormals.size()>0) m.setNormals(newNormals);
                else m.computeNormals();

                parts.add(m);

                Log.d("Angelos","Part "+parts.size()+": "+xyzc);
            }
        }//For all meshes

    }




}
