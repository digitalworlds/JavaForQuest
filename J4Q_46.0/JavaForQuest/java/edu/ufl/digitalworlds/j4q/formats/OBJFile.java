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

import edu.ufl.digitalworlds.j4q.J4Q;

public class OBJFile {

    public OBJFile(final String filename){
        this(J4Q.activity,filename);
    }

    public OBJFile(final Context context, final String filename) {

        try {
            InputStream is = context.getAssets().open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            parse(br);
            postProcessing();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error loading OBJ file.");
        }
    }

    private void parse(BufferedReader reader) throws IOException {

        ArrayList<Float> XYZ=new ArrayList<Float>();
        ArrayList<Float> Normals=new ArrayList<Float>();
        ArrayList<Float> UV=new ArrayList<Float>();

        String line = reader.readLine();
        while (line != null) {

            String pieces[]=line.split(" ");



            switch(pieces[0]){
                case"mtllib":
                 /*   var p=pieces[1].split("/");//to remove any path info
                    mtllib=strClean(p[p.length-1]);
                   */ break;
                case"usemtl":
                    /*if(Mesh.Triangles!==undefined){
                        Meshes.push(Mesh);
                    }
                    Triangles=[];
                    Mesh={
                            material:strClean(pieces[1]),
                        Triangles:Triangles
			};
                    */break;
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
                  /*  if(Mesh.Triangles===undefined){//no material was found
                        Triangles=[];
                        Mesh={
                                Triangles:Triangles
				};
                    }
                    //console . log ( line ) ;
                    triangleSet=[];
                    firstPieces=pieces[1].split('/');
                    secondPieces=pieces[2].split('/');
                    thirdPieces=pieces[3].split('/');
                    if(firstPieces.length>0){
                        triangleSet.push(parseInt(firstPieces[0]));
                        triangleSet.push(parseInt(secondPieces[0]));
                        triangleSet.push(parseInt(thirdPieces[0]));
                    }
                    if(firstPieces.length>1){
                        triangleSet.push(parseInt(firstPieces[1]));
                        triangleSet.push(parseInt(secondPieces[1]));
                        triangleSet.push(parseInt(thirdPieces[1]));
                    }
                    if(firstPieces.length>2){
                        triangleSet.push(parseInt(firstPieces[2]));
                        triangleSet.push(parseInt(secondPieces[2]));
                        triangleSet.push(parseInt(thirdPieces[2]));
                    }
                    Triangles.push(triangleSet);
                    break;
                default:
                    //console . log ( 'default in mesh' ) ;
                    //console . log ( line ) ;
                 */   break;
            }
            // read next line
            line = reader.readLine();
        }


        reader.close();
    }

    private void postProcessing(){

    }

}
