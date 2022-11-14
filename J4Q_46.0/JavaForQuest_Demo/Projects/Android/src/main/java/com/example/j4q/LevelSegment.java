package com.example.j4q;

import android.opengl.Matrix;

import edu.ufl.digitalworlds.j4q.J4Q;
import edu.ufl.digitalworlds.j4q.models.Mesh;
import edu.ufl.digitalworlds.j4q.models.Model;
import edu.ufl.digitalworlds.j4q.models.ObjectMaker;
import edu.ufl.digitalworlds.j4q.models.Spaceship;
import edu.ufl.digitalworlds.j4q.shaders.ShadedTextureShader;

public class LevelSegment extends Model {

    Model pipe;

    Model sides;

    Model planets;
    float planet_speed;

    Model spaceship;

    public static float LENGTH=4;

    int id=0;
    boolean is_key_frame=false;

    Level level;

    float[] orientation1;
    float[] orientation2;
    float[] rot;

    public LevelSegment(Level level){
        this.level=level;

        spaceship=new Spaceship((int)Math.floor(Math.random()*Spaceship.TYPES));
        appendChild(spaceship);
        spaceship.transform.translate((float)(Math.random()*6-3),2.5f,0);
        spaceship.transform.scale(0.2f);
        spaceship.transform.rotateY(180);
    }

    private void buildSides(){
        if(sides==null){
            sides=new Model();
            sides.mesh=new Mesh();
            sides.mesh.keepData(true);
            appendChild(sides);

            ObjectMaker om=new ObjectMaker();

            om.save();
            //om.multiply(level.path_maker_orientation_inv);
            //om.multiply(this.orientation1);
            om.cylinderX(5,1,1,8);
            if(Math.random()<0.8){
                om.box(5,0.5f,LENGTH);
            }
            if(Math.random()<0.5) {
                om.save();
                om.translate(3.5f, 0, 0);
                float h=(float) (Math.random() * 2 + 2);
                om.box(2, h, 2);
                om.translate(0, h/2, 0);
                if(Math.random()<0.5) {
                    om.save();
                    om.cone(0.2f, (float)Math.random()+1, 0.2f,8);
                    om.restore();
                }
                for(int i=-2;i<=2;i++)
                    for(int j=-2;j<=2;j++)
                        if(Math.random()<0.5) {
                            om.save();
                            om.translate(0.4f*i, 0, 0.4f*j);
                            if(Math.random()<0.5)
                                om.sphere(0.3f, (float)Math.random(), 0.3f,8);
                            else om.box(0.3f, (float)Math.random(), 0.3f);
                            om.restore();
                        }

                om.restore();
            }
            if(Math.random()<0.5) {
                om.save();
                om.translate(-3.5f, 0, 0);
                float h=(float) (Math.random() * 2 + 2);
                om.box(2, h, 2);
                om.translate(0, h/2, 0);
                if(Math.random()<0.5) {
                    om.save();
                    om.cone(0.2f, (float)Math.random()+1, 0.2f,8);
                    om.restore();
                }
                for(int i=-2;i<=2;i++)
                    for(int j=-2;j<=2;j++)
                        if(Math.random()<0.5) {
                            om.save();
                            om.translate(0.4f*i, 0, 0.4f*j);
                            if(Math.random()<0.5)
                                om.sphere(0.3f, (float)Math.random(), 0.3f,8);
                            else om.box(0.3f, (float)Math.random(), 0.3f);
                            om.restore();
                        }
                om.restore();
            }
            if(Math.random()<0.2) {
                for (int i = 0; i < 12; i++) {
                    om.save();
                    om.rotate(i * 180 / 6, 0, 0, 1);
                    om.translate(0, 5, 0);
                    //om.rotate((float)(Math.random()*2*180),1,0,0);
                    om.rotateZ(-180 / 2);
                    om.cylinder(1, 1.8f, 1.8f, 21);
                    om.translate(0, 0.9f, 0);
                    om.rotate(-180 / 12, 0, 0, 1);
                    om.translate(0, 0.5f, 0);
                    om.box(1, 1, 1.8f);
                    om.cylinderZ(0.5f, 0.5f, 3, 8);
                    om.restore();
                }
            }

            om.restore();


            om.flushShadedTexturedModel(sides);
            sides.mesh.computeNormals();

            ((ShadedTextureShader)sides.shader).setTexture(level.side_texture);
        }
    }

    private void buildPlanets(){
        if(planets==null){
            planet_speed=(float)Math.random()*5-2.5f;
            if(planet_speed<0)planet_speed-=1;
            else planet_speed+=1;
            ObjectMaker om=new ObjectMaker();
            for(int i=0;i<20;i++){
                om.save();
                om.rotateZ((float)(Math.random()*360));
                om.translate(0,(float)(8+Math.random()*8),(float)(Math.random()*LENGTH-LENGTH/2));
                om.rotateY((float)(Math.random()*360));
                om.rotateX((float)(Math.random()*180));
                om.scale((float)(Math.random()*0.5+0.5));
                om.sphere(1,1,1,5);
                om.restore();
            }
            planets=om.flushShadedTexturedModel();
            ((ShadedTextureShader)planets.shader).setTexture(level.planet_texture);
            appendChild(planets);
        }
    }

    public void buildSegment(LevelSegment previous){

        if(previous!=null)id=previous.id+1;
        if((id+1)%Level.KEY_FRAME_FREQUENCY==0)is_key_frame=true;

        //compute a random orientation for the new segment
        this.orientation1=new float[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
        if (previous!=null)
            System.arraycopy(previous.orientation2,0,this.orientation1,0,16);

        if(level.path_maker_orientation==null) {
            level.path_maker_orientation = this.orientation1;
            Matrix.invertM(level.path_maker_orientation_inv,0,this.orientation1,0);
        }
        this.orientation2 =new float[]{1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1};
        System.arraycopy(this.orientation1,0,this.orientation2,0,16);
        Matrix.translateM(this.orientation2,0,0,0,-LENGTH);
        float[] r =new float[]{(float)(2 * Math.random() - 1), (float)(2 * Math.random() - 1), 0};
        if (previous!=null) {
            r[0] += previous.rot[0];
            r[1] += previous.rot[1];
            r[2] += previous.rot[2];
        }
        this.rot = r;
        float m = (float)Math.sqrt(r[0] * r[0] + r[1] * r[1] + r[2] * r[2]);
        r[0] /= m;
        r[1] /= m;
        r[2] /= m;
        Matrix.rotateM(this.orientation2,0,180/20,r[0],r[1],r[2]);


        transform.identity();
        transform.multiply(this.orientation1);



        //this set of triangles creates a pipe segment
        level.path_maker.appendTriangles(this.level.tri);

        //this is the set of vertices of the beginning of the pipe segment
        level.path_maker.pushMatrix();
        level.path_maker.multiply(level.path_maker_orientation_inv);
        level.path_maker.multiply(this.orientation1);
        level.path_maker.appendXYZ(this.level.xyz);
        level.path_maker.popMatrix();

        //this is the set of vertices of the end of the pipe segment
        level.path_maker.pushMatrix();
        level.path_maker.multiply(level.path_maker_orientation_inv);
        level.path_maker.multiply(this.orientation2);
        level.path_maker.appendXYZ(this.level.xyz);
        level.path_maker.popMatrix();

        //update UV map, (only V based on the order-id of the segment)
        int c = 1;
        int mod = this.id % Level.KEY_FRAME_FREQUENCY;
        for (int j = 0; j < 2; j++) {
            float v = (mod + j) / (1f*Level.KEY_FRAME_FREQUENCY);
            for (int i = 0; i < Level.RESOLUTION; i++) {
                this.level.uv[c] = v;
                c += 2;
            }
        }
        level.path_maker.appendUV(this.level.uv);


        buildSides();
        buildPlanets();

        //reposition spaceship
        if(spaceship.getParent()==null) {
            appendChild(spaceship);
        }
        spaceship.transform.identity();
        spaceship.transform.translate((float)(Math.random()*6-3),2.5f,0);
        spaceship.transform.scale(0.2f);
        spaceship.transform.rotateY(180);




        if(is_key_frame){

            boolean add_texture=false;
            if(pipe==null){
                pipe=new Model();
                pipe.mesh=new Mesh();
                pipe.mesh.keepData(true);
                appendChild(pipe);
                add_texture=true;
            }

            level.path_maker.flushShadedTexturedModel(pipe);
            pipe.mesh.computeNormals();

            if(add_texture) ((ShadedTextureShader)pipe.shader).setTexture(level.path_texture);

            
            pipe.transform.identity();
            pipe.transform.multiply(transform.getInvertedMatrix());
            pipe.transform.multiply(level.path_maker_orientation);
            level.path_maker_orientation=null;
        }






    }

    @Override
    public void Update(){

        if(planets!=null)planets.transform.rotateZ(planet_speed* J4Q.perSec());

        if(spaceship!=null)spaceship.transform.translate(0,0,-1.5f*J4Q.perSec());


    }


}
