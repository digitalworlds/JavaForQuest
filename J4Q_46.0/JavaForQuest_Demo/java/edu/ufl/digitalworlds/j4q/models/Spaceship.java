package edu.ufl.digitalworlds.j4q.models;


public class Spaceship extends Model {

    public static int TYPES=35;

    public Spaceship(int id){
        this(id,null);
    }

    public Spaceship(int id, Model model){

        ObjectMaker maker=new ObjectMaker();
        maker.identity();
        maker.color(0.5f,0.5f,0.5f);

        if(id==0){
            maker.pushMatrix();
            maker.translate(0f,0f,0.25f);
            maker.box(1.2f,0.05f,0.2f);
            maker.rotateX(90f);
            maker.translate(-0.6f,0f,0f);
            maker.color(0.4f,0.2f,0f);
            maker.cylinder(0.1f,0.4f,0.1f,16);
            maker.translate(1.2f,0f,0f);
            maker.cylinder(0.1f,0.4f,0.1f,16);
            maker.popMatrix();
            maker.rotateX(-90f);
            maker.trapezoid(0.7f,1f,0.2f,0.5f,0.05f);
            maker.pushMatrix();
            maker.translate(-0.175f,-0.55f,0f);
            maker.color(0f,0f,0f);
            maker.cylinder(0.2f,0.1f,0.2f,0.1f,0.1f,16);
            maker.translate(0.35f,0f,0f);
            maker.cylinder(0.2f,0.1f,0.2f,0.1f,0.1f,16);
            maker.popMatrix();
            maker.rotateY(-90f);
            maker.translate(0f,-0.25f,-0.5f);
            maker.color(1f,0f,0f);
            maker.trapezoid(0.5f,0.5f,0.05f,0.25f,0.02f);
            maker.translate(0f,0f,1f);
            maker.trapezoid(0.5f,0.5f,0.05f,0.25f,0.02f);
        }

//c8hzf6s5ekux23ug
else if(id==1){
            maker.translate(-0.5f,0f,0f);//move to the left
            maker.color(0.8f,0.8f,0.8f);
            maker.cylinder(0.7f,0.1f,0.7f,20);//make a cylinder
            maker.translate(-0.001f,0f,0f);//move to the left
            maker.color(0f,0.4f,0.7f);
            maker.cylinder(0.7f,0.09f,0.7f,20);//make a cylinder
            maker.identity();//resets your axes
            maker.translate(-0.28f,0f,0f);//move to the left
            maker.color(0.8f,0.8f,0.8f);
            maker.box(0.37f,0.11f,0.1f);
            maker.translate(0.06f,0.f,0.12f);//move to the left
            maker.rotateX(90);//rotate 90 degrees around X
            maker.pyramid(0.5f,0.15f,0.1f);
            maker.translate(0f,-0.22f,-0f);//move to the left
            maker.rotateX(180);//rotate 90 degrees around X
            maker.pyramid(0.5f,0.15f,0.1f);
            maker.identity();//resets your axe
            maker.translate(-0.5f,0f,0f);
            maker.cylinder(0.1f,0.12f,0.1f,20);
        }

//49hdpz3mjdtpa9i4
else if(id==2){
            maker.color(1f,1f,1f);
            maker.box(1f,0.1f,0.3f);//make a box
            maker.translate(-0.5f,0f,0f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cylinder(0.1f,0.5f,0.1f,6);//make a cylinder
            maker.identity();//resets your axes



            maker.color(1f,0f,0f);
            maker.cylinder(1f,0.1f,0.3f,16);//make a box
            maker.translate(-.42f,0f,0f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cylinder(0.3f,1f,.5f,6);//make a cylinder
            maker.identity();//resets your axes

            maker.cylinder(1f,0.1f,0.3f,16);//make a box
            maker.translate(0.5f,0f,0f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cylinder(0.3f,1f,0.5f,6);//make a cylinder
            maker.identity();//resets your axes

            maker.color(0f,0f,1f);
            maker.cone(.9f,0.5f,0.7f,16);//make a box
            maker.translate(-0.43f,0f,.5f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cone(0.1f,0.5f,0.1f,6);//make a cylinder
            maker.identity();//resets your axes


            maker.cone(.8f,0.5f,0.6f);//make a box
            maker.translate(0.5f,0f,.5f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cone(0.1f,0.5f,0.1f,6);//make a cylinder
            maker.identity();//resets your axes

            maker.color(1f,0f,0f);
            maker.trapezoid(1f,0.1f,0.3f,1,0.1f);//make a box
            maker.translate(.02f,-.27f,0f);//move to the left
            maker.rotateX(900f);//rotate 90 degrees around X
            maker.color(.1f,.1f,.1f);
            maker.trapezoid(0.1f,0.5f,0.1f,0.1f,6f);//make a cylinder
            maker.identity();//resets your axes

            maker.color(.1f,.1f,.1f);
            maker.translate(-.42f,0f,-.5f);
            maker.box(.2f,.2f,.15f);
            maker.identity();

            maker.translate(.5f,0f,-.5f);
            maker.box(.2f,.2f,.15f);
        }

//ivr428ukduuxcp80
else if(id==3){
            maker.color(0.3f,0.5f,0.8f);

            maker.box(1f,0.5f,0.75f);//make a box
            maker.translate(0f,0.5f,0f);//move axes
            maker.color(.3f,.5f,.8f);
            maker.trapezoid(1f,0.5f,0.75f,0.5f,0.5f);
            maker.identity();//resets your axes
            maker.translate(0f,0.75f,0f);//move axes
            maker.color(0.2f,0.7f,0.7f);
            maker.pyramid(0.6f,0.5f,0.6f);
            maker.identity();
            maker.translate(0f,-0.33f,0f);
            maker.color(0.3f,0.5f,0.8f);
            maker.box(0.9f,0.18f,0.65f);
            maker.translate(0f,-0.15f,0f);
            maker.color(0.2f,0.7f,0.7f);
            maker.trapezoid(0.6f,0.2f,0.45f,0.8f,0.55f);
            maker.translate(0f,-0.2f,0f);
            maker.color(.3f,.5f,.8f);
            maker.trapezoid(0.3f,0.2f,0.2f,0.6f,0.45f);
            maker.translate(0f,-0.13f,0f);
            maker.color(1f,0.3f,0f);
            maker.cylinder(0.25f,0.07f,0.15f,0.3f,0.2f,16);
            maker.translate(0f,-0.16f,0f);
            maker.cylinder(0.6f,0.25f,0.5f,0.25f,0.15f,16);
            maker.identity();
            maker.translate(0f,1.25f,0f);
            maker.sphere(0.1f,0.1f,0.1f,8);
            maker.identity();
            maker.translate(0f,0.3f,0f);
            maker.color(1f,0.3f,0f);
            maker.box(1.65f,0.2f,0.1f);
            maker.translate(-0.67f,-0.42f,0f);
            maker.color(0.2f,0.7f,0.7f);
            maker.trapezoid(0.15f,0.65f,0.1f,0.3f,0.1f);
            maker.translate(0f,0.69f,0f);
            maker.trapezoid(0.3f,0.35f,0.1f,0.05f,0.1f);
            maker.translate(1.34f,-0.69f,0f);
            maker.color(0.2f,0.7f,0.7f);
            maker.trapezoid(0.15f,0.65f,0.1f,0.3f,0.1f);
            maker.translate(0f,0.69f,0f);
            maker.color(0.2f,0.7f,0.7f);
            maker.trapezoid(0.3f,0.35f,0.1f,0.05f,0.1f);
        }

//6j56hxozr21chgk1
else if(id==4f){
            maker.color(1f,.4f,.5f);
            maker.trapezoid(.8f,1f,1f,0.8f,1f);

            maker.color(.3f,.8f,1f);
            maker.translate(0f,.5f,0f);
            maker.sphere(.5f,.5f,.5f);

            maker.color(.3f,.8f,1f);
            maker.translate(0f,-.5f,0f);
            maker.sphere(.95f,.7f,.7f);

            maker.color(1f,1f,1f);
            maker.translate(0f,-.5f,0f);
            maker.box(.85f,.3f,1f);

            maker.color(1f,.4f,.5f);
            maker.translate(0f,-.3f,0f);
            maker.box(.6f,.5f,.8f);

            maker.color(.3f,.8f,1f);
            maker.translate(0f,.3f,0f);
            maker.rotateX(180);
            maker.box(2f,.1f,.2f);

            maker.color(.3f,.8f,1f);
            maker.translate(0f,.5f,0f);
            maker.cylinder(.5f,.5f,.3f,0.5f,.7f);

            maker.translate(1f,-.5f,0f);
            maker.color(1f,1f,1f);
            maker.cylinder(.3f,.6f,.3f);

            maker.color(1f,1f,1f);
            maker.translate(-2f,0f,0f);
            maker.cylinder(.3f,.6f,.3f);
        }

//qsj9izfmovmmtt9r
else if(id==5f){
           /* float[] black=new float[]{0f,0f,0};
            float[] grey=new float[]{0.4f,0.4f,0.4f};
            float[] white=new float[]{1f,1f,1};

            var thruster={0.4f,0.4f,0.4f,0.3f,height2:0.5f,0.3f,32};
            var connector={0.3f,1.5f,0.3f,0.3f,height2:0.5f,0.3f,32};
            var cap={0.3f,0.15f,0.3f,0.15f,height2:0.5f,0.15f,32};

            maker.translate(0f,1f,0f);
            maker.color(black);
            maker.box(1f,0.1f,0.1f);

            maker.translate(-0.5f,-1f,0f);
            maker.color(grey);
            maker.cylinder(thruster);
            maker.translate(0f,0.6f,0f);
            maker.color(white);
            maker.cylinder(connector);
            maker.translate(0f,0.82f,0f);
            maker.cylinder(cap);
            maker.translate(1f,-1.42f,0f);
            maker.color(grey);
            maker.cylinder(thruster);
            maker.translate(0f,0.6f,0f);
            maker.color(white);
            maker.cylinder(connector);
            maker.translate(0f,0.82f,0f);
            maker.cylinder(cap);
            maker.identity();

            maker.rotateY(90f);
            maker.translate(0f,1f,0f);
            maker.color(black);
            maker.box(1f,0.1f,0.1f);

            maker.translate(-0.5f,-1f,0f);
            maker.color(grey);
            maker.cylinder(thruster);
            maker.translate(0f,0.6f,0f);
            maker.color(white);
            maker.cylinder(connector);
            maker.translate(0f,0.82f,0f);
            maker.cylinder(cap);
            maker.translate(1f,-1.42f,0f);
            maker.color(grey);
            maker.cylinder(thruster);
            maker.translate(0f,0.6f,0f);
            maker.color(white);
            maker.cylinder(connector);
            maker.translate(0f,0.82f,0f);
            maker.cylinder(cap);
            maker.identity();

            maker.translate(0f,1f,0f);
            maker.cylinder(0.6f,1.2f,0.6f,6);
            maker.translate(0f,0.74f,0f);
            maker.cylinder(0.6f,0.3f,0.6f,0.3f,0.5f,0.3f,6f);
            maker.identity();*/
        }

//yiaialsjissm7kxx
else if(id==6){
            maker.color(1f,.5f,1f);
            maker.cone(
                    .7f,0.7f,0.7f,32
            );
            maker.pyramid(
                    1f,1f,.2f
            );
            maker.color(1f,1f,1f);
            maker.translate(0f,-.3f,0f);
            maker.cylinder(
                    1f,0.6f,1f,0.7f,0.7f,32
            );
            maker.translate(0f,-.6f,0f);
            maker.cylinder(
                    1f,0.6f,1f,32
            );
            maker.translate(0f,-.6f,0f);
            maker.cylinder(
                    .7f,0.6f,.7f,1f,1f,32
            );
            maker.color(0.5f,0.5f,0.5f);
            maker.translate(0f,-.2f,0f);
            maker.cylinder(
                    .6f,0.5f,.6f,0.4f,0.4f,32
            );
            maker.translate(0f,.9f,.15f);
            maker.rotateX(90f);
            maker.cylinder(
                    .7f,.7f,.7f,32
            );
            maker.color(0f,1f,1f);
            maker.translate(0f,.2f,0f);
            maker.rotateX(90f);
            maker.sphere(
                    .7f,.7f,.6f,32
            );
            maker.color(1f,.5f,1f);
            maker.translate(0f,.9f,.35f);
            maker.rotateX(90f);
            maker.rotateX(90f);
            maker.pyramid(
                    1.3f,1f,.2f
            );
            maker.rotateY(90f);
            maker.pyramid(
                    1.3f,1f,.2f
            );
            maker.translate(0f,-.2f,0.55f);
            maker.box(
                    .2f,.4f,.2f
            );
            maker.translate(.55f,0f,-0.55f);
            maker.box(
                    .2f,.4f,.2f
            );
            maker.translate(-.55f,0f,-.55f);
            maker.box(
                    .2f,.4f,.2f
            );
            maker.translate(-.55f,0f,0.55f);
            maker.box(
                    .2f,.4f,.2f
            );
            maker.identity();
        }

//fowryc2ok1r00b0w
else if(id==7f){
            maker.sphere(2f,0.2f,2f,10);
            maker.translate(0f,0.05f,0f);//move up
            maker.color(0f,0f,1f);
            maker.sphere(0.5f,0.3f,0.5f,6);
            maker.color(0.5f,0.5f,0.5f);
            maker.translate(0.7f,0f,0.6f);//move right
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cylinder(0.1f,0.5f,0.1f,6);//make a cylinder
            maker.translate(0f,-0.3f,0f);
            maker.sphere(0.2f,0.2f,0.2f,6);
            maker.identity();//resets your axes
            maker.translate(0f,0.05f,0f);//move up
            maker.translate(-0.7f,0f,0.6f);//move right
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cylinder(0.1f,0.5f,0.1f,6);//make a cylinder
            maker.translate(0f,-0.3f,0f);
            maker.sphere(0.2f,0.2f,0.2f,6);
            maker.identity();//resets your axes
            maker.trapezoid(0.5f,0.25f,0.25f,0.25f,0.125f);//make a trapezoid
            maker.identity();
            maker.translate(0.35f,0.05f,-0.3f);
            maker.rotateY(-0.4f);
            maker.cone(0.5f,0.3f,0.05f,6);
            maker.identity();
            maker.translate(-0.35f,0.05f,-0.3f);
            maker.rotateY(0.4f);
            maker.cone(0.5f,0.3f,0.05f,6);
        }

//v4gnbhpiumf315of
else if(id==8){
            maker.translate(0f,0f,-0.5f);
            maker.color(0.8f,0.2f,0.1f);//red
            maker.box(0.8f,0.2f,1.7f);//make a box
            maker.translate(-0.5f,0f,0.5f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X

            maker.color(0f,0f,0f);
            maker.cylinder(0.9f,0.5f,0.1f,6);//make a cylinder
            maker.identity();//resets your axes
            maker.translate(0.5f,0f,0f);
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cylinder(0.9f,0.5f,0.1f,6);

            maker.color(0f,0f,0f);
            maker.translate(0.5f,0f,0f);//cylinder wing
            maker.cylinder(0.4f,1.3f,0.5f,6);
            maker.translate(0f,0f,0.6f);
            maker.cylinder(0.4f,1.3f,0.5f,6);
            maker.color(0.8f,0.2f,0.1f);
            maker.translate(0f,0f,-0.2f);
            maker.box(0.1f,0.2f,0.9f);

            maker.color(0f,0f,0f);
            maker.identity();
            maker.translate(-1.0f,0f,0f);
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.cylinder(0.4f,1.4f,0.5f,6);
            maker.translate(0f,0f,0.6f);
            maker.cylinder(0.4f,1.3f,0.5f,6);
            maker.color(0.8f,0.2f,0.1f);
            maker.translate(0f,0f,-0.2f);
            maker.box(0.1f,0.2f,0.9f);

            maker.color(0.8f,0.2f,0.1f);//middle pillars
            maker.translate(0.4f,0f,0f);//redblock
            maker.box(0.2f,0.7f,0.2f);
            maker.identity();
            maker.rotateX(90f);
            maker.translate(-0.6f,0f,-0.1f);//greyblock
            maker.color(0.8f,0.2f,0.1f);
            maker.color(0.5f,0.5f,0.5f);
            maker.box(0.1f,0.2f,0.9f);
            maker.identity();

            maker.color(0.8f,0.2f,0.1f);
            maker.rotateX(90f);
            maker.translate(0.6f,0f,0.4f);//redblock
            maker.box(0.2f,0.7f,0.2f);

            maker.color(0.8f,0.2f,0.1f);
            maker.color(0.5f,0.5f,0.5f);
            maker.translate(-0f,0f,-0.5f);//greyblock
            maker.box(0.1f,0.2f,0.9f);
            maker.identity();

            maker.color(0f,0f,0f);
            maker.translate(0f,0.5f,0f);//top
            maker.box(1.7f,0.1f,0.6f);
            maker.translate(0.9f,0f,0f);
            maker.rotateX(90f);

            maker.color(0.8f,0.2f,0.1f);//red
            maker.cylinder(0.3f,0.7f,0.3f,4);

            maker.color(0f,0f,0f);//black
            maker.translate(0f,-0.4f,0f);
            maker.cylinder(0.1f,0.1f,0.1f,6);

            maker.color(0.8f,0.2f,0.1f);//red
            maker.identity();
            maker.translate(-0.9f,0.5f,0f);
            maker.rotateX(90f);
            maker.cylinder(0.3f,0.7f,0.3f,4);

            maker.color(0f,0f,0f);
            maker.translate(0f,-0.4f,0f);
            maker.cylinder(0.1f,0.1f,0.1f,6);


            maker.color(0f,0f,0f);//black
            maker.identity();
            maker.translate(0f,0f,-1.3f);
            maker.box(0.5f,0.1f,0.6f);
            maker.translate(0.1f,0f,-0.3f);

            maker.color(0.8f,0.2f,0.1f);//red
            maker.rotateX(90f);
            maker.cylinder(0.1f,0.1f,0.1f,4);
            maker.translate(-0.2f,0f,0f);//shooters
            maker.cylinder(0.1f,0.1f,0.1f,4);

            maker.color(0f,0f,0f);//black
            maker.identity();
            maker.translate(0f,0.2f,0f);
            maker.cylinder(0.4f,0.6f,0.2f,4);

//bottom
            maker.color(0f,0f,0f);
            maker.identity();
            maker.translate(0f,-0.2f,-0.6f);
            maker.box(0.1f,0.2f,1.4f);
            maker.translate(0f,-0.1f,-0f);

            maker.color(0.8f,0.2f,0.1f);
            maker.color(0.5f,0.5f,0.5f);
            maker.box(0.6f,0.2f,0.9f);

            maker.color(0.8f,0.2f,0.1f);

            maker.identity();//stripes
            maker.translate(1.2f,0f,0f);
            maker.box(0.1f,0.2f,0.9f);
            maker.identity();
            maker.translate(-1.2f,0f,0f);
            maker.box(0.1f,0.2f,0.9f);

            maker.identity();//stripes
            maker.translate(1.2f,-0.6f,0f);
            maker.box(0.1f,0.2f,0.9f);
            maker.identity();
            maker.translate(-1.2f,-0.6f,0f);
            maker.box(0.1f,0.2f,0.9f);

            maker.identity();
        }

//9evs11e6niinp0h2
else if(id==9){
            maker.color(.3f,0f,0f);
            maker.cylinder(.8f,1.2f,.8f,.5f,.5f,32);
            maker.translate(0f,.6f,0f);
            maker.cone(.5f,.4f,.5f,32);
            maker.color(.8f,.8f,.8f);
            maker.translate(0f,-1.15f,0f);
            maker.pyramid(1.2f,.7f,.7f);

            maker.color(1f,1f,1f);
            maker.translate(.2f,.8f,0f);
            maker.sphere(.2f,.2f,.2f,32);

            maker.translate(.03f,-.2f,0f);
            maker.sphere(.2f,.2f,.2f,32);

            maker.translate(1f,1f,0f);
        }

//gx2dwwyee141x7zn
else if(id==10){
            maker.identity();
            maker.translate(0f,0f,-0.2f);
            maker.rotateX(90f);
            maker.cylinder(0.5f,1.55f,0.5f);

            maker.identity();
            maker.translate(0f,0f,0.9f);
            maker.rotateX(90f);
            maker.cylinder(0.7f,0.7f,0.7f,0.3f,0.3f);

            maker.identity();
            maker.translate(0.5f,0f,0f);
            maker.box(1f,0.1f,0.25f);//make a box
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.translate(0f,-0.075f,0f);//move back
            maker.cylinder(0.1f,0.4f,0.1f,6);//draw cylinder
            maker.translate(0.5f,0f,0f);
            maker.cylinder(0.1f,0.4f,0.1f,6);

            maker.identity();
            maker.translate(-0.5f,0f,0f);
            maker.box(1f,0.1f,0.25f);//make a box
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.translate(0f,-0.075f,0f);//move back
            maker.cylinder(0.1f,0.4f,0.1f,6);//draw cylinder
            maker.translate(-0.5f,0f,0f);
            maker.cylinder(0.1f,0.4f,0.1f,6);

            maker.identity();
            maker.translate(0f,0.5f,0f);
            maker.box(0.1f,1f,0.25f);//make a box
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.translate(0f,-0.075f,0f);//move back
            maker.cylinder(0.1f,0.4f,0.1f,6);//draw cylinder
            maker.translate(0f,0f,-0.5f);
            maker.cylinder(0.1f,0.4f,0.1f,6);

            maker.identity();
            maker.translate(0f,-0.5f,0f);
            maker.box(0.1f,1f,0.25f);//make a box
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.translate(0f,-0.075f,0f);//move back
            maker.cylinder(0.1f,0.4f,0.1f,6);//draw cylinder
            maker.translate(0f,0f,0.5f);
            maker.cylinder(0.1f,0.4f,0.1f,6);
        }

//rbudh64x1hk34zdt
else if(id==11f){
            maker.color(0f,0f,0f);
            maker.cone(0.7f,0.5f,0.8f);
            maker.translate(0f,-0.1f,0f);
            maker.color(1f,0f,0f);
            maker.sphere(0.5f,0.5f,0.5f);
            maker.translate(0f,-0.3f,0f);
            maker.color(1f,1f,1f);
            maker.cylinder(0.5f,0.6f,0.5f,32);
            maker.translate(1f,0f,0f);
            maker.color(0f,0f,0f);
            maker.translate(-1f,-0.7f,0f);
            maker.trapezoid(0.9f,0.8f,1.2f);
            maker.color(1f,0f,0f);
            maker.translate(0f,-0.5f,0f);
            maker.trapezoid(0.9f,0.8f,1.2f);
        }

//heioqjq1j7wv13al
else if(id==12){
            maker.cylinder(0.3f,0.7f,0.3f);//make a box
            maker.translate(0f,0.35f,0f);//move to the left
            maker.rotateX(0f);//rotate 90 degrees around X
            maker.cone(0.3f,0.3f,0.3f);

            maker.translate(0f,-0.8f,0f);
            maker.cone(0.5f,0.5f,0.5f);

            maker.translate(0.12f,-0.1f,-0.1f);
            maker.cone(0.2f,0.2f,0.2f);
            maker.translate(-0.13f,0f,0.2f);
            maker.cone(0.2f,0.2f,0.2f);
            maker.translate(-0.13f,0f,-0.2f);
            maker.cone(0.2f,0.2f,0.2f);
            maker.translate(0.15f,0.35f,0.1f);
            maker.cone(1f,0.6f,0.1f);
        }

//4dh5sawimoscm83r
else if(id==13){
//Top
            maker.color(0f,0f,0f);
            maker.translate(0f,1f,0f);
            maker.pyramid(0.3f,0.3f,0.2f);
//Body
            maker.translate(0f,-0.5f,0f);
            maker.color(0f,1f,0f);
            maker.trapezoid(1f,1f,0.5f,0.3f,0.2f);
//Wing Left
            maker.translate(0.5f,-.4f,0f);
            maker.color(0f,0.3f,0f);
            maker.trapezoid(0.5f,1f,0.05f,0.7f,0.05f);
//Wing Right
            maker.color(0f,0.3f,0f);
            maker.translate(-1f,0f,0f);
            maker.trapezoid(0.5f,1f,0.05f,0.7f,0.05f);
//Wing Right 2
            maker.color(0f,0.1f,0f);
            maker.translate(1.64f,-0.25f,0f);
            maker.trapezoid(1f,0.2f,0.01f,.6f,.05f);
//Wing Left 2
            maker.color(0f,0.1f,0f);
            maker.translate(-2.28f,0f,0f);
            maker.trapezoid(1f,0.2f,0.01f,.6f,0.05f);
//BodyBottom
            maker.color(0f,1f,0f);
            maker.translate(1.14f,-0.25f,0f);
            maker.trapezoid(0.8f,0.7f,0.3f,1f,0.5f);
//Pod
            maker.color(1f,1f,1f);
            maker.translate(0f,0.6f,0.15f);
/// / maker . trapezoid ( { height : 0.5 f, width : 0.4 f, depth : 0.5 f, height2 : 0.2 f, width2 : 0 f, depth2 : 0.3 } ) ;
            maker.sphere(0.7f,0.5f,0.3f);
//BottomBoost Left
            maker.translate(-.3f,-1f,-0.15f);
            maker.cylinder(0.4f,0.4f,0.4f,0.1f,0.2f);
//BottomBoost Right
            maker.translate(.6f,0f,0f);
            maker.cylinder(0.4f,0.4f,0.4f,0.1f,0.2f);
//BottomBoost Top
//maker . translate ( [ - .35 f, 0 f, .1 ] ) ;
//maker . cylinder ( { height : 0.4 f, width : 0.4 f, depth : 0.4 f, height2 : 0.2 f, width2 : 0.1 f, depth2 : 0.2 } ) ;
//BottomDesign Left
            maker.translate(-.3f,.3f,0.15f);
            maker.trapezoid(0.5f,.05f,.6f,.05f,.2f);
            maker.translate(0f,0f,-0.15f);
        }

//6fr2cefm3w1kyua2
else if(id==14){
            maker.color(0.4f,0.4f,0.4f);
            maker.trapezoid(0.7f,0.4f,0.1f);//make a box
            maker.translate(-0.4f,0f,0f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X

            maker.color(1f,0f,0f);
            maker.cylinder(0.4f,0.7f,0.2f,6);//make a cylinder
            maker.identity();//resets your axes

            maker.translate(0.4f,0f,0f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.color(1f,0f,0f);
            maker.cylinder(0.4f,0.7f,0.2f,6);//make a cylinder

            maker.translate(0.2f,0f,0f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.color(1f,0f,0f);
            maker.cylinder(0.4f,0.3f,0.2f,6);//make a cylinder

            maker.translate(-1.2f,0f,0f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.color(1f,0f,0f);
            maker.cylinder(0.4f,0.4f,0.2f,6);//make a cylinder

            maker.translate(-0.15f,0f,0f);//move to the left
            maker.rotateX(90.5f);//rotate 90 degrees around X
            maker.color(1f,0f,0f);
            maker.cylinder(0.4f,0.25f,0.2f,6);//make a cylinder

            maker.translate(1.6f,0f,0f);//move to the left
            maker.rotateX(90.5f);//rotate 90 degrees around X
            maker.color(1f,0f,0f);
            maker.cylinder(0.4f,0.25f,0.2f,6);//make a cylinder

            maker.translate(-0.85f,0f,-0.3f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.color(0.4f,0.4f,0.4f);
            maker.sphere(0.5f,0.45f,0.5f,4);//make a cylinder

            maker.translate(0f,0.5f,0f);//move to the left
            maker.rotateX(90.2f);//rotate 90 degrees around X
            maker.color(1f,0f,0f);
            maker.sphere(0.5f,0.42f,0.5f,8);//make a cylinder

            maker.translate(0f,0.25f,0.25f);//move to the left
            maker.rotateX(90.3f);//rotate 90 degrees around X
            maker.color(0.4f,0.4f,0.4f);
            maker.cylinder(0.6f,1.1f,0.2f,6);//make a cylinder

            maker.translate(0f,0f,0.57f);//move to the left
            maker.color(0.4f,0.4f,0.4f);
            maker.cylinder(1.4f,0.6f,0.2f,6);//make a cylinder

            maker.translate(0f,0f,-0.72f);//move to the left
            maker.color(1f,0f,0f);
            maker.cylinder(0.8f,1f,0.2f,6);//make a cylinder

            maker.translate(0f,0.3f,0.35f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.color(0.4f,0.4f,0.4f);
            maker.sphere(0.4f,0.4f,0.5f,4);//make a cylinder
        }

//qck704rkhzedw3wc
else if(id==15){
            maker.color(.54f,.27f,.07f);
            maker.rotateX(3.14f);
            maker.trapezoid(.7f,.4f,1.5f,.4f,1.5f);




//mast1
            maker.color(.54f,.27f,.07f);
            maker.translate(0f,-.5f,0f);
            maker.cylinder(.08f,1.25f,.08f);

//mast2
            maker.color(.54f,.27f,.07f);
            maker.translate(0f,0f,-.3f);
            maker.cylinder(.08f,.7f,.08f);

//mast3
            maker.color(.54f,.27f,.07f);
            maker.translate(0f,0f,.6f);
            maker.cylinder(.08f,.7f,.08f);




//sail1
            maker.color(1f,1f,1f);
            maker.translate(0f,-.1f,-.05f);
            maker.box(.4f,.4f,.02f);

//sail2
            maker.color(1f,1f,1f);
            maker.translate(0f,0f,-.6f);
            maker.box(.4f,.4f,.02f);

//sail3
            maker.color(1f,1f,1f);
            maker.translate(0f,-.05f,.3f);
            maker.box(.7f,.6f,.02f);




//cannon1
            maker.identity();//resets your axes
            maker.translate(0f,.1f,0f);
            maker.color(.5f,.5f,.5f);
            maker.rotateZ(90f);
            maker.cylinder(.05f,.8f,.05f);

//cannon2
            maker.identity();//resets your axes
            maker.translate(0f,.1f,.2f);
            maker.color(.5f,.5f,.5f);
            maker.rotateZ(90f);
            maker.cylinder(.05f,.8f,.05f);

//cannon3
            maker.identity();//resets your axes
            maker.translate(0f,.1f,-.2f);
            maker.color(.5f,.5f,.5f);
            maker.rotateZ(90f);
            maker.cylinder(.05f,.8f,.05f);





//cannonflap1
            maker.identity();//resets your axes
            maker.translate(0f,.14f,0f);
            maker.color(.54f,.27f,.07f);
            maker.rotateZ(90f);
            maker.box(.01f,.8f,.05f);

//cannonflap2
            maker.identity();//resets your axes
            maker.translate(0f,.14f,.2f);
            maker.color(.54f,.27f,.07f);
            maker.rotateZ(90f);
            maker.box(.01f,.8f,.05f);

//cannonflap3
            maker.identity();//resets your axes
            maker.translate(0f,.14f,-.2f);
            maker.color(.54f,.27f,.07f);
            maker.rotateZ(90f);
            maker.box(.01f,.8f,.05f);





//thruster1
            maker.identity();//reset your axes
            maker.translate(-.3f,-.2f,-.2f);
            maker.color(1f,.5f,0f);
            maker.rotateZ(45);
            maker.box(.3f,.05f,.01f);

            maker.translate(-.1f,0f,0f);
            maker.color(.5f,.5f,.5f);
            maker.rotateX(90f);
            maker.cylinder(.25f,.1f,.2f,.25f,.1f);

//thruster2
            maker.identity();//reset your axes
            maker.translate(.3f,-.2f,-.2f);
            maker.color(1f,.5f,0f);
            maker.rotateZ(-45);
            maker.box(.3f,.05f,.01f);

            maker.translate(.1f,0f,0f);
            maker.color(.5f,.5f,.5f);
            maker.rotateX(90f);
            maker.cylinder(.25f,.1f,.2f,.25f,.1f);



//crows nest
            maker.identity();//reset your axes
            maker.translate(0f,1f,0f);
            maker.cylinder(.1f,.2f,.1f,.1f,.2f);



//captainsroom
            maker.color(.54f,.27f,.07f);
            maker.identity();//reset your axes
            maker.translate(0f,.3f,-.65f);
            maker.trapezoid(.7f,.55f,.205f,.2f,.05f);
        }

//cjd8j96wpw8ve71t
        else if(id==16f){
            maker.box(0.4f,.5f,0.20f);//make a box
            maker.translate(0f,-.5f,0f);
            maker.trapezoid(.5f,1f,.5f,.25f,.25f);//make a trapezoid BASE
            maker.translate(0f,-.25f,0f);
            maker.box(1f,.20f,.2f);//connector
            maker.translate(.5f,0f,0f);
            maker.cylinder(.25f,.5f,.25f);//Engine1
            maker.translate(-1f,0f,0f);
            maker.cylinder(.25f,.5f,.25f);//Engine2
            maker.translate(.5f,-.25f,0f);
            maker.cylinder(.4f,.3f,.4f,.2f,.2f,32);//Base Engine
            maker.translate(0f,-.1f,0f);
            maker.cylinder(.3f,.2f,.3f,.15f,.15f);//Base Engine 2
            maker.translate(0.5f,.25f,0f);
            maker.cylinder(.3f,.2f,.3f,.2f,.2f);//Engine1.1
            maker.translate(0f,.25f,0f);
            maker.cylinder(.15f,.5f,.15f);//cannon 1
            maker.translate(-1f,0f,0f);
            maker.cylinder(.15f,.5f,.15f);//cannon2
            maker.translate(0f,-.25f,0f);
            maker.cylinder(.3f,.2f,.3f,.2f,.2f);//Engine2.1
            maker.translate(.5f,.1f,0f);
            maker.trapezoid(.3f,.5f,.75f,.2f,.5f);//Base detail
            maker.translate(0f,.75f,0f);
            maker.sphere(.35f,.5f,.35f);//cockpit
            maker.translate(0f,.35f,0f);
            maker.trapezoid(.45f,.3f,.25f,.2f,.2f);
            maker.translate(0f,.1f,0f);
            maker.cone(.4f,.4f,.3f);
        }

//c8oqzyo8a32rc9sj
        else if(id==17){
            maker.color(.4f,.4f,.5f);//grey
            maker.rotateX(-90f);//rotates 90 degrees around X
            maker.trapezoid(1f,3f,1f,0.5f,0.5f);
            maker.color(.5f,.9f,.9f);//light blue
            maker.sphere(1f,1f,1f,32);//middle observation deck
            maker.translate(0f,1.7f,0f);//moves forward
            maker.sphere(.8f,.8f,.8f,32);//forward command deck
            maker.translate(0f,-1.7f,0f);
            maker.translate(0f,-1.4f,0f);//translates back
            maker.color(0f,0f,0f);//black
            maker.cylinder(0.5f,0.5f,0.2f,32);//middle engine
            maker.translate(0f,0f,.3f);//translates up
            maker.cylinder(0.5f,0.5f,0.2f,32);//top engine
            maker.translate(0f,0f,-.6f);//translates down
            maker.cylinder(0.5f,0.5f,0.2f,32);//bottom engine
            maker.translate(0f,.5f,.3f);//translates forward to middle and inside spaceship
            maker.color(0f,.2f,.4f);//blue
            maker.cylinder(2.2f,0.5f,0.1f,32);//side aft wings
            maker.rotateY(90f);//rotates second wing to become top wing
            maker.cylinder(2.2f,0.5f,0.1f,32);//top/bottom aft wings
            maker.translate(0f,1.75f,0f);//moves forward
            maker.cylinder(1.4f,0.3f,0.1f,32);//top/bottom foward wings
            maker.rotateY(90f);//rotates second forward wing to become side forward wings
            maker.cylinder(1.4f,0.3f,0.1f,32);//side forward wings
            maker.translate(0f,.85f,0f);//moves forward
            maker.color(.4f,.4f,.5f);//grey
            maker.sphere(1f,.2f,1f,32);//wind screen structure 1a
            maker.rotateX(90f);
            maker.sphere(1f,.2f,1f,32);//wind screen structure 2a
            maker.rotateZ(90f);
            maker.sphere(1f,.2f,1f,32);//wind screen structure 3a
            maker.translate(0f,0f,1.7f);//moves aft
            maker.sphere(1.25f,.2f,1.25f,32);//wind screen structure 1b
            maker.rotateX(90f);
            maker.sphere(1.25f,.2f,1.25f,32);//wind screen structure 2b
            maker.rotateZ(90f);
            maker.sphere(1.25f,.2f,1.25f,32);//wind screen structure 3b
        }

//kprjtxtbwraeiquq
        else if(id==18){
            maker.sphere(1f,2f,1f);
            maker.translate(0f,0.5f,0f);
            maker.cone(1f,1f,1f);
            maker.identity();
            maker.translate(0f,-0.1f,0.5f);
            maker.sphere(0.2f,0.4f,0.2f);
            maker.identity();
            maker.translate(0.5f,-0.1f,0f);
            maker.sphere(0.2f,0.4f,0.2f);
            maker.identity();
            maker.translate(-0.5f,-0.1f,0f);
            maker.sphere(0.2f,0.4f,0.2f);
            maker.identity();
            maker.translate(0f,-0.1f,-0.5f);
            maker.sphere(0.2f,0.4f,0.2f);
            maker.identity();
            maker.translate(0f,-1.1f,0f);
            maker.cone(0.9f,1f,0.9f);
            maker.identity();
            maker.translate(0f,-1.1f,0.5f);
            maker.rotateX((180/3.1416f)*5.14f/2f);
            maker.box(0.2f,0.6f,0.1f);
            maker.identity();
            maker.translate(0f,-1.1f,-0.5f);
            maker.rotateX((180/3.1416f)*1.14f/2f);
            maker.box(0.2f,0.6f,0.1f);
            maker.identity();
            maker.translate(0.5f,-1.1f,0f);
            maker.rotateZ((180/3.1416f)*1.14f/2f);
            maker.box(0.1f,0.6f,0.2f);
            maker.identity();
            maker.translate(-0.5f,-1.1f,0f);
            maker.rotateZ((180/3.1416f)*5.14f/2f);
            maker.box(0.1f,0.6f,0.2f);
            maker.identity();
        }

//a7j6su4l4ygisgnf
else if(id==19){
            maker.cylinder(.8f,1f,0.8f,6);



            maker.translate(0f,.5f,0f);
            maker.cone(1f,1f,1f,6);

            maker.translate(0f,-.75f,0f);
            maker.box(.1f,1f,.6f);

            maker.translate(0f,-.2f,0f);
            maker.rotateX(90f);
            maker.box(.65f,.1f,.6f);
        }

//53lljsja9eaksstb
else if(id==20){
            maker.color(.9f,.9f,.9f);
            maker.sphere(1f,1f,1f,12);//make a cylinder

            maker.color(.1f,.1f,.1f);
            maker.sphere(1.2f,1.2f,.2f,12);//make a cylinder

            maker.color(.1f,.1f,.1f);
            maker.sphere(.2f,1.2f,1.2f,12);//make a cylinder

            maker.translate(0f,-.25f,0f);

            maker.color(.2f,.1f,.5f);
            maker.sphere(2f,.2f,2f,12);//make a cylinder

            maker.translate(0f,0f,0f);

            maker.color(.1f,.1f,.1f);
            maker.sphere(1.5f,.5f,1.5f,12);//make a cylinder

            maker.translate(0f,0f,0f);

            maker.color(.1f,.1f,.1f);
            maker.box(.1f,.01f,3f);//make a cylinder

            maker.translate(0f,0f,0f);


            maker.box(3f,.01f,.1f);//make a cylinder

            maker.translate(1.6f,0f,0f);

            maker.color(.2f,.1f,.5f);
            maker.box(.2f,.2f,.2f);//make a cylinder
            maker.color(.8f,.8f,.8f);
            maker.sphere(.1f,.3f,.1f);
            maker.translate(-1.6f,0f,0f);
            maker.translate(-1.6f,0f,0f);

            maker.color(.2f,.1f,.5f);
            maker.box(.2f,.2f,.2f);
            maker.color(.8f,.8f,.8f);
            maker.sphere(.1f,.3f,.1f);

            maker.translate(1.6f,-0f,1.6f);

            maker.color(.2f,.1f,.5f);
            maker.box(.2f,.2f,.2f);
            maker.color(.8f,.8f,.8f);
            maker.sphere(.1f,.3f,.1f);
            maker.translate(-1.6f,-0f,-1.6f);
            maker.translate(1.6f,-0f,-1.6f);

            maker.color(.2f,.1f,.5f);
            maker.box(.2f,.2f,.2f);
            maker.color(.8f,.8f,.8f);
            maker.sphere(.1f,.3f,.1f);
            maker.color(1f,0f,0f);
            maker.rotateZ(9.424f);

            maker.translate(0f,.1f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,.5f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,0f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,.5f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(1.6f,-0f,1.6f);
            maker.translate(-1.6f,-0f,1.6f);
            maker.translate(0f,-.4f,0f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,0f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,.5f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,0f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,.5f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(-1.6f,-.4f,-1.6f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,0f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,.5f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,0f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,.5f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(3.2f,-.4f,0f);


            maker.translate(0f,.1f,0f);
            maker.color(1f,0f,0f);
            maker.pyramid(.1f,.1f,.1f);


            maker.translate(0f,.1f,0f);
            maker.color(1f,.5f,0f);
            maker.pyramid(.1f,.1f,.1f);

            maker.translate(0f,.1f,0f);
            maker.pyramid(.1f,.1f,.1f);
            maker.color(1f,0f,0f);

            maker.translate(0f,.1f,0f);
            maker.color(1f,.5f,0f);
            maker.pyramid(.1f,.1f,.1f);
        }

//9fq9dc36z8md7ma5
else if(id==21){
            maker.cylinder(0.5f,1.95f,0.5f,10);//make a cylinder
            maker.translate(0f,.95f,0f);//move to the left
            maker.cone(.5f,1f,.5f,32);//make a cone
            maker.translate(.38f,-2f,0f);//move up
            maker.cylinder(.5f,1f,.5f,.3f,.3f,32);
            maker.translate(-.09f,.4f,0f);
            maker.cone(.5f,.3f,.25f,32);

            maker.identity();
            maker.translate(-.38f,-1f,0f);

            maker.cylinder(.5f,1f,.5f,.3f,.3f,32);
            maker.translate(.09f,.4f,0f);
            maker.cone(.5f,.3f,.25f,32);

            maker.identity();
            maker.rotateY(90f);
            maker.translate(-.38f,-1f,0f);

            maker.cylinder(.5f,1f,.5f,.3f,.3f,32);
            maker.translate(.09f,.4f,0f);
            maker.cone(.5f,.3f,.25f,32);

            maker.identity();
            maker.rotateY(180/.666f);
            maker.translate(-.38f,-1f,0f);

            maker.cylinder(.5f,1f,.5f,.3f,.3f,32);
            maker.translate(.09f,.4f,0f);
            maker.cone(.5f,.3f,.25f,32);
        }

//ltnlhghol0ewa7ml
else if(id==22){
            maker.box(1f,0.1f,0.3f);//make a box
            maker.translate(-0.5f,0f,0f);//move to the left
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.translate(0.5f,2.4f,0f);
            maker.cylinder(2f,4f,0.1f,6);//make a cylinder
            maker.translate(0f,-4.8f,0f);
            maker.cylinder(2f,4f,0.1f,6);//make a cylinder
            maker.identity();//resets your axes
            maker.cylinder(1f,1f,1f);
            maker.translate(0f,0.5f,0f);
            maker.cone(1f,1.0f,1f);
        }
//7ircb6dtftt12bvs
else if(id==22){
            maker.color(0.3f,0.7f,1f);
            maker.sphere(1,1,1,32);//Sphere of spaceship

            maker.identity();
            maker.translate(0.5f,0.5f,0f);
            maker.color(1f,1f,1f);
            maker.pyramid(0.5f,0.5f,1.5f);//right side of spaceship cannons
            maker.pyramid(-0.5f,-0.5f,-1.5f);//right side of spaceship cannons

            maker.identity();
            maker.translate(-0.5f,0.5f,0f);
            maker.pyramid(0.5f,0.5f,1.5f);//left side of spaceship cannons
            maker.pyramid(-0.5f,-0.5f,-1.5f);//left side of spaceship cannons

            maker.identity();
            maker.translate(0f,-0.5f,0f);
            maker.color(0.4f,0.3f,0.8f);
            maker.cone(1.5f,1.2f,1.5f,32);//base and top cone of spaceship

            maker.identity();
            maker.translate(-0.5f,0.6f,0.5f);
            maker.rotateX(90f);
            maker.cylinder(0.3f,0.5f,0.5f,0.2f,0.5f,32);//left cannon blaster of spaceship

            maker.identity();
            maker.translate(0.5f,0.6f,0.5f);
            maker.rotateX(90f);
            maker.cylinder(0.3f,0.5f,0.5f,0.2f,0.5f,32);//right cannon blaster of spaceship

            maker.identity();
            maker.translate(0f,-0.3f,0.5f);
            maker.color(0.8f,0.8f,0f);
            maker.sphere(0.3f,0.2f,0.3f,32);//bottom lights

            maker.identity();
            maker.translate(0.5f,-0.3f,0.1f);
            maker.color(0.8f,0.8f,0f);
            maker.sphere(0.3f,0.2f,0.3f,32);//bottom lights

            maker.identity();
            maker.translate(0f,-0.3f,-0.5f);
            maker.color(0.8f,0.8f,0f);
            maker.sphere(0.3f,0.2f,0.3f,32);//bottom lights

            maker.identity();
            maker.translate(-0.5f,-0.3f,0.1f);
            maker.color(0.8f,0.8f,0f);
            maker.sphere(0.3f,0.2f,0.3f,32);//bottom lights
        }

//6m0529kj66xn603o
else if(id==23f){
            maker.translate(0f,.3f,0f);//move up
            maker.color(0.839216f,0.839216f,0.839216f);
            maker.cylinder(.4f,1f,.4f,.3f,.3f,32);//make a cylinder
            maker.translate(0f,.5f,0f);//move up
            maker.color(1f,0f,0f);
            maker.cone(.3f,.6f,.3f,32);//make cone
            maker.translate(0f,-1.5f,0f);//move down
            maker.color(0.839216f,0.839216f,0.839216f);
            maker.cylinder(.3f,.3f,1f,.4f,.4f,32);//make cylinder
            maker.identity();
            maker.translate(.35f,-.9f,0f);//move to the right and down
            maker.rotateZ(4/12f);//rotate 90 degrees around X
            maker.color(1f,0f,0f);
            maker.pyramid(.6f,.8f,.1f);//make pyramid
            maker.translate(.2f,.1f,0f);//move to the right and up
            maker.rotateZ((180/3.1416f)*(-7.5f/2f));//rotate 90 degrees around X
            maker.pyramid(.3f,.8f,.1f);//make pyramid

            maker.identity();
            maker.translate(-.35f,-.9f,0f);//move to the left and down
            maker.rotateZ(-4/12f);//rotate around X axis
            maker.pyramid(.6f,.8f,.1f);//make pyramid
            maker.translate(-.2f,.1f,0f);//move to the left and up
            maker.rotateZ((180/3.1416f)*(7.5f/2f));//rotate around X axis
            maker.pyramid(.3f,.8f,.1f);//make pyramid

            maker.identity();
            maker.translate(0f,-.9f,-.35f);//move backward and down
            maker.rotateY(-90f);//rotate around Y axis
            maker.rotateZ((180/3.1416f)*(-.7f/2f));//rotate around z axis
            maker.pyramid(.6f,.8f,.1f);//make pyramid
            maker.translate(-.2f,.1f,0f);//move to the left and up
            maker.rotateZ((180/3.1416f)*(7.5f/2f));//rotate around Z axis
            maker.pyramid(.3f,.8f,.1f);//make pyramid

            maker.identity();
            maker.translate(0f,-.9f,.35f);//move forward and down
            maker.rotateY(90f);//rotate around Y axis
            maker.rotateZ((180/3.1416f)*(-.7f/2f));//rotate around z axis
            maker.pyramid(.6f,.8f,.1f);//make pyramid
            maker.translate(-.2f,.1f,0f);//move to the left and up
            maker.rotateZ((180/3.1416f)*(7.5f/2f));//rotate around Z axis
            maker.pyramid(.3f,.8f,.1f);//make pyramid
        }

//yksannmqezmx8b9o
else if(id==24){
//Creates top of spaceship
            maker.translate(0f,0.75f,0f);
            maker.color(.5f,.5f,.5f);
            maker.pyramid(.8f,.8f,1f);

//Creates the body of the spaceship
            maker.translate(0f,-1f,0f);
            maker.color(.4f,.4f,.4f);
            maker.box(.8f,2f,1f);

//Creates the exhausts of spaceship
            maker.translate(-.2f,-1f,.3f);
            maker.color(.8f,.8f,0f);
            maker.cylinder(.5f,.7f,.6f,.1f,.1f,32);

            maker.translate(0f,0f,-.6f);
            maker.color(.8f,.8f,0f);
            maker.cylinder(.5f,.7f,.6f,.1f,.1f,32);

            maker.translate(.4f,0f,0f);
            maker.color(.8f,.8f,0f);
            maker.cylinder(.5f,.7f,.6f,.1f,.1f,32);

            maker.translate(0f,0f,.6f);
            maker.color(.8f,.8f,0f);
            maker.cylinder(.5f,.7f,.6f,.1f,.1f,32);

//Creates the wings/fuel tanks of spaceship
            maker.translate(-.5f,.51f,-.3f);
            maker.rotateX(3.14f);
            maker.color(0f,.9f,.9f);
            maker.trapezoid(.1f,1f,.5f,.8f,.8f);

            maker.translate(.6f,0f,0f);
            maker.color(0f,.9f,.9f);
            maker.trapezoid(.1f,1f,.5f,.8f,.8f);

//Creates windows of spaceship
            maker.translate(-.3f,-.6f,.5f);
            maker.color(0f,.9f,.9f);
            maker.sphere(.6f,1.2f,.1f,32);

            maker.translate(0f,0f,-1f);
            maker.color(0f,.9f,.9f);
            maker.sphere(.6f,1.2f,.1f,32);
        }

//1f9vhwmled3u88hf
else if(id==25){
            maker.color(0.9f,0.5f,0f);
            maker.box(0.9f,0.3f,0.4f);

            maker.translate(0.5f,0f,0f);
            maker.rotateZ(90f);
            maker.pyramid(0.7f,0.7f,0.1f);
            maker.identity();

            maker.translate(-0.3f,-0.1f,0f);
            maker.rotateZ(90f);
            maker.pyramid(0.5f,0.5f,0.1f);
            maker.identity();


            maker.translate(-0.2f,0f,0f);
            maker.rotateZ(90f);
            maker.cylinder(0.4f,0.4f,0.4f,4);
            maker.identity();

            maker.translate(-0.6f,0f,0f);
            maker.rotateZ(-90f);
            maker.sphere(0.4f,0.7f,0.4f,6);
            maker.identity();

            maker.color(0f,0f,0f);
            maker.translate(-0.6f,0f,-0.2f);
            maker.rotateZ(-90f);
            maker.sphere(0.1f,0.1f,0.1f,6);
            maker.identity();

            maker.translate(-0.6f,0f,0.2f);
            maker.rotateZ(-90f);
            maker.sphere(0.1f,0.1f,0.1f,6);
            maker.identity();

            maker.color(0.9f,0.5f,0f);
            maker.translate(0f,0f,-0.2f);
            maker.rotateX(-90f);
            maker.box(0.1f,0.6f,0.1f);
            maker.identity();

            maker.translate(-0.2f,0f,-0.2f);
            maker.rotateX(-90f);
            maker.box(0.1f,0.6f,0.1f);
            maker.identity();



            maker.translate(0f,0f,0.2f);
            maker.rotateX(-90f);
            maker.box(0.1f,0.6f,0.1f);
            maker.identity();

            maker.translate(-0.2f,0f,0.2f);
            maker.rotateX(-90f);
            maker.box(0.1f,0.6f,0.1f);
        }

//8p7anolb2eb2bnez
else if(id==26){
            maker.cylinder(1f,2f,1f,20);//make a cylinder
            maker.translate(0f,1f,0f);//
            maker.cone(1f,1f,1f,32);
            maker.translate(0f,-2f,0f);//move to the left
            maker.identity();//resets your axes
            maker.translate(0f,-1f,0f);//
            maker.cone(1f,1f,1f,32);
            maker.identity();//resets your axes
            maker.translate(0.2f,0.3f,0f);//move to the left
            maker.sphere(0.8f,0.8f,0.6f,32);
            maker.identity();//resets your axes
            maker.translate(-0.2f,0.3f,0f);//move to the left
            maker.sphere(0.8f,0.8f,0.6f,32);
            maker.identity();//resets your axes
            maker.translate(0f,-1.25f,0f);//
            maker.cone(1.5f,1.5f,1.5f,32);
            maker.identity();//resets your axes
            maker.translate(0f,-1.5f,0f);//
            maker.cone(1.5f,1.5f,1.5f,32);
            maker.identity();//resets your axes
            maker.translate(0f,-1.75f,-0.5f);//
            maker.cone(0.5f,0.5f,0.5f,32);
            maker.identity();//resets your axes
            maker.translate(0.3f,-1.75f,0.5f);//
            maker.cone(0.5f,0.5f,0.5f,32);
        }

//dtm9qc9ld814vzgz
else if(id==27f){
            maker.color(1f,1f,0f);
            maker.box(1f,0.2f,0.2f);//box
            maker.translate(-0.5f,0f,0f);//move to the left

            maker.cylinder(0.2f,0.3f,1f,20);//cylinder


            maker.rotateZ(90f);//ninety
            maker.pyramid(0.5f,0.4f,0.3f);//pyramid
            maker.cone(0.9f,0.2f,0.2f);//
        }

//gzju6gc35r18n3g5
else if(id==28){
            maker.box(1f,0.5f,0.3f);//make a box
            maker.box(3f,0.5f,0.1f);//make a box
            maker.trapezoid(1f,0.5f,0.3f);//make a box
            maker.cylinder(0.3f,0.5f,1f,6);//make a cylinder
            maker.translate(0f,1.3f,0f);//move to the down
            maker.sphere(1f,1f,1f,16);//make a sphere
            maker.cylinder(0.3f,3f,0.3f,16);//make a cylinder
            maker.translate(0f,1f,0f);//move to the down
        }

//b8axvqppwxfta286
else if(id==29){
            maker.rotateX(90f);//rotate 90 degrees around
            maker.color(0.7f,0f,.10f);
            maker.sphere(0.7f,0.7f,.2f,30);//make a sphere


            maker.translate(0f,0f,-.1f);//translate object
            maker.color(.9f,1f,0f);
            maker.sphere(0.4f,0.4f,.22f,30);//make a sphere

            maker.translate(0f,.1f,.1f);//translate object
            maker.color(0.7f,0f,0.1f);
            maker.cone(0.6f,1.5f,.2f,30);//make a cone

            maker.translate(0f,-.1f,.17f);//translate object
            maker.color(0.7f,0f,0.1f);
            maker.sphere(0.3f,0.3f,.15f,6);//make a sphere

            maker.translate(0f,.9f,.02f);//translate object
            maker.color(1f,1f,1f);
            maker.cylinder(0.03f,1.7f,.03f,30);//make a cylinder sphere

            maker.translate(.5f,-.75f,-.2f);//translate object
            maker.color(0.7f,0f,0.1f);
            maker.box(1.5f,.2f,.02f);//makes a box

            maker.translate(-1.05f,0f,0f);//translate object
            maker.color(0.7f,0f,0.1f);
            maker.box(1.5f,.2f,.02f);//makes a box

            maker.translate(-.8f,-.10f,0f);//translate object
            maker.color(0.7f,0f,0.1f);
            maker.cone(.2f,.7f,.05f);
            ;//make a cone

            maker.translate(2.65f,0f,0f);//translate object
            maker.color(0.7f,0f,0.1f);
            maker.cone(.2f,.7f,.05f);

            maker.translate(-1.295f,-.4f,0f);//translate object
            maker.color(1f,1f,1f);
            maker.cylinder(.1f,.3f,.1f);//make a cone

            maker.translate(0f,-.4f,0f);//translate object
            maker.color(1f,1f,1f);
            maker.cone(.3f,.5f,.3f);
        }

//sqfdxqv7un5lnclz
else if(id==29){
            maker.color(0f,0f,0f);
            maker.box(3f,0.01f,0.3f);//make a box
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.translate(0f,-0.9f,0f);
            maker.color(0.5f,0.5f,0.5f);
            maker.cone(1.5f,2.2f,0.5f,32);
            maker.translate(0f,1f,-0.1f);
            maker.color(0f,0f,253/255f);
            maker.sphere(0.5f,0.5f,0.4f,32);
            maker.rotateX((180/3.14f)*(6.14f/2f));//rotate 90 degrees around X
            maker.translate(0f,1f,-0.1f);
            maker.color(255f,0f,0f);
            maker.cone(0.5f,0.2f,0.1f,32);
        }

//iv91crbnzgnnkh5r
else if(id==30){
            maker.color(1f,0f,0f);
            maker.translate(0f,.5f,0f);
            maker.cone(.7f,.5f,.7f);


            maker.translate(0f,-.5f,0f);
            maker.color(0f,0f,1f);
            maker.cylinder(.5f,1f,.5f,10);

            maker.translate(0f,-.62f,0f);
            maker.color(1f,0f,0f);
            maker.cylinder(.75f,.25f,.75f,.25f,.25f,32);

            maker.translate(0f,.75f,.20f);
            maker.color(0f,1f,1f);
            maker.sphere(.25f,.25f,.25f,32);
            maker.identity();
        }

//mt3thabvvpmzb4gf
else if(id==31){
//pyramidTop
            maker.translate(0f,.95f,-.5f);
            maker.pyramid(1.7f,.5f,1.7f);
            maker.identity();
//boxBase
            maker.translate(0f,.7f,-.5f);
            maker.box(1.7f,1.7f,.5f);
            maker.identity();
//trapezoidBase
            maker.translate(0f,.3f,-.5f);
            maker.trapezoid(2.5f,.7f,2.5f,1.7f,1.7f);
            maker.identity();
//backright
            maker.translate(.5f,0f,-1f);
            maker.rotateX(180/-1f);
            maker.cylinder(1f,.7f,.7f,32);
            maker.translate(0f,.5f,0f);
            maker.cylinder(.8f,0.07f,.8f,0.6f,0.6f,32);
            maker.translate(0f,.03f,0f);
            maker.cone(0.55f,0.55f,0.6f,32);
            maker.identity();
//backleft
            maker.translate(-.5f,0f,-1f);
            maker.rotateX(180/-1f);
            maker.cylinder(1f,.7f,.7f,32);
            maker.translate(0f,.5f,0f);
            maker.cylinder(.8f,0.07f,.8f,0.6f,0.6f,32);
            maker.translate(0f,.03f,0f);
            maker.cone(0.55f,0.55f,0.6f,32);
            maker.identity();
//frontright
            maker.translate(.5f,0f,0f);
            maker.rotateX(180/-1f);
            maker.cylinder(1f,.7f,.7f,32);
            maker.translate(0f,.5f,0f);
            maker.cylinder(.8f,0.07f,.8f,0.6f,0.6f,32);
            maker.translate(0f,.03f,0f);
            maker.cone(0.55f,0.55f,0.6f,32);
            maker.identity();
//frontleft
            maker.translate(-.5f,0f,0f);
            maker.rotateX(180/-1f);
            maker.cylinder(1f,.7f,.7f,32);
            maker.translate(0f,.5f,0f);
            maker.cylinder(.8f,0.07f,.8f,0.6f,0.6f,32);
            maker.translate(0f,.03f,0f);
            maker.cone(0.55f,0.55f,0.6f,32);
            maker.identity();
//sphereDecorationFront
            maker.translate(0f,1f,.13f);
            maker.sphere(.3f,.3f,.3f,32);
            maker.identity();
//sphereDecorationLeft
            maker.translate(-.6f,1f,-.5f);
            maker.sphere(.3f,.3f,.3f,32);
            maker.identity();
//sphereDecorationRight
            maker.translate(.6f,1f,-.5f);
            maker.sphere(.3f,.3f,.3f,32);
            maker.identity();
//sphereDecorationBack
            maker.translate(0f,1f,-1.1f);
            maker.sphere(.3f,.3f,.3f,32);
        }

//t1uya0mdzjj4dv2c
else if(id==32){
            maker.box(2f,0.3f,0.3f);//make a box
            maker.translate(-1f,0f,0f);//move to the left
            maker.color(0.8f,0.8f,0.8f);
            maker.rotateX(90f);//rotate 90 degrees around X
            maker.sphere(1f,1f,1f);
            maker.identity();//resets your axes
            maker.translate(1f,0f,.5f);
            maker.rotateZ(90f);
            for(int i=0;i<3;i++){
                maker.color(0.8f,0.8f,0.8f);
                maker.cylinder(.5f,.8f,.5f);
                maker.color(.6f,.4f,0f);
                maker.cylinder(0.3f,1f,0.3f,.1f,.1f);
                maker.translate(0f,0f,-.5f);
            }
        }

//ylcsqeh5d352d8o2
else if(id==33){
//center lower
            maker.color(0f,0f,0f);
            maker.box(1f,0.1f,0.3f);//make a box
            maker.color(1f,0f,0f);
            maker.translate(-0.3f,0f,-0.35f);
//left wing -A
            maker.trapezoid(1.5f,0.25f,0.5f,1f,0.5f);
            maker.identity();//resets your axes
            maker.translate(-0.3f,0f,0.35f);
//right wing -A
            maker.trapezoid(1.5f,0.25f,0.5f,1f,0.5f);
            maker.identity();
            maker.translate(0f,0.15f,0f);
            maker.rotateY(90);
            maker.color(1f,0.6f,0f);
//center part higherf, main control room
            maker.trapezoid(0.25f,0.2f,1f,0.1f,0.2f);
            maker.identity();
            maker.color(0f,0f,0f);
//Right wing main
            maker.translate(-0.8f,0f,-0.8f);
            maker.rotateX(-90f);
            maker.trapezoid(3f,0.4f,0.3f,1.2f,0.1f);
            maker.translate(1.4f,0f,0f);
            maker.color(0.6f,0.6f,0.6f);
            maker.box(1.5f,0.3f,0.1f);
            maker.identity();
//Left wing main
            maker.color(0f,0f,0f);
            maker.translate(-0.8f,0f,0.8f);
            maker.rotateX(90f);
            maker.trapezoid(3f,0.4f,0.3f,1.2f,0.1f);
            maker.translate(1.4f,0f,0f);
            maker.color(0.6f,0.6f,0.6f);
            maker.box(1.5f,0.3f,0.1f);
            maker.identity();//resets your axes
        }

//revu54r1rahb0vh9
else if(id==34){
            maker.color(1f,1f,1f);
            maker.sphere(1f,1f,1.5f,100);

            maker.translate(0f,.15f,-.1f);
            maker.color(1f,1f,1f);
            maker.sphere(.6f,1f,1.4f,100);

            maker.translate(0f,-.1f,.65f);
            maker.color(.3f,.3f,1f);
            maker.sphere(.5f,.5f,.5f,100);

            maker.translate(-.25f,-.2f,0f);
            maker.sphere(.2f,.2f,.2f);

            maker.translate(.5f,0f,0f);
            maker.sphere(.2f,.2f,.2f);

            maker.translate(0f,0f,0f);

            maker.translate(-.5f,.35f,-.1f);
            maker.color(1f,1f,1f);
            maker.rotateZ(1/2f);
            maker.cone(.2f,.7f,.2f);

            maker.translate(.5f,-.35f,.1f);

            maker.translate(-.1f,0f,-.1f);
            maker.rotateZ(-1f);
            maker.cone(.2f,.7f,.2f);

            maker.translate(.1f,-.075f,0f);
            maker.rotateZ(-1f);
            maker.cone(.2f,.4f,.2f);

            maker.translate(-.1f,-.65f,0f);
            maker.rotateZ(-3.2f);
            maker.cone(.2f,.4f,.2f);

            maker.identity();

            maker.translate(-.1f,-.3f,.25f);
            maker.rotateZ(2.8f);
            maker.rotateX(.3f);
            maker.cone(.2f,.9f,.2f);

            maker.identity();

            maker.translate(.1f,-.3f,.25f);
            maker.rotateZ(-2.8f);
            maker.rotateX(.3f);
            maker.cone(.2f,.9f,.2f);

            maker.identity();

            maker.translate(.1f,-.3f,-.25f);
            maker.rotateZ(-2.8f);
            maker.rotateX((180/3.1416f)*-.3f);
            maker.cone(.2f,.9f,.2f);

            maker.identity();

            maker.translate(-.1f,-.3f,-.25f);
            maker.rotateZ(2.8f);
            maker.rotateX((180/3.1416f)*-.3f);
            maker.cone(.2f,.9f,.2f);

            maker.identity();

            maker.translate(0f,.3f,-.6f);
            maker.rotateX(-.9f);
            maker.cone(.3f,.9f,.3f);
        }



        ObjectMaker maker2=new ObjectMaker();
        maker2.identity();

        if(id==1){maker2.rotateY(180/2f);}
        else if(id==2){maker2.rotateY(180);}
        else if(id==3){maker2.rotateX(-180/2f);}
        else if(id==4){maker2.rotateX(-180/2f);}
        else if(id==5){maker2.rotateX(-180/2f);}
        else if(id==6){maker2.rotateX(-180/2f);}
        else if(id==7){maker2.rotateY(180);}
        else if(id==9){maker2.rotateX(-180/2f);}
        else if(id==10){maker2.rotateY(180);}
        else if(id==11){maker2.rotateX(-180/2f);}
        else if(id==12){maker2.rotateX(-180/2f);}
        else if(id==13){maker2.rotateX(-180/2f);}
        else if(id==15){maker2.rotateY(180);}
        else if(id==16){maker2.rotateX(-180/2f);}
        else if(id==18){maker2.rotateX(-180/2f);}
        else if(id==19){maker2.rotateX(-180/2f);}
        else if(id==21){maker2.rotateX(-180/2f);}
        else if(id==23){maker2.rotateX(-180/2f);}
        else if(id==24){maker2.rotateX(-180/2f);}
        else if(id==25){maker2.rotateY(-180/2f);}
        else if(id==26){maker2.rotateX(-180/2f);}
        else if(id==27){maker2.rotateY(-180/2f);}
        else if(id==28){maker2.rotateX(-180/2f);}
        else if(id==29){maker2.rotateY(180);}
        else if(id==30){maker2.rotateX(-180/2f);}
        else if(id==31){maker2.rotateX(-180/2f);}
        else if(id==32){maker2.rotateY(-180/2f);}
        else if(id==33){maker2.rotateY(-180/2f);}
        else if(id==34){maker2.rotateY(180);}

        maker2.appendTriangles(maker.getTriangles());
        maker2.appendXYZ(maker.getXYZ());
        maker2.appendNormals(maker.getNormals());
        maker2.appendColors(maker.getColors());

        if(model==null)maker2.flushShadedColoredModel(this);
        else maker2.flushShadedColoredModel(model);
    }


}
