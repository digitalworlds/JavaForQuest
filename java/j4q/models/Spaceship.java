package j4q.models;


import j4q.shaders.ColorPhongShader;

public class Spaceship extends GameObject {

    public static int TYPES=35;

    public Spaceship(int id){
        this(id,null);
    }

    public Spaceship(int id, GameObject gameObject){

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

else if(id==2){

            final float[] PEARL = {252 / 255f, 230 / 255f, 211 / 255f}; //#FCE6D3
            final float[] PINK = {251 / 255f, 183 / 255f, 199 / 255f}; //#FBB7C7
            final float[] BLUSH = {250 / 255f, 217 / 255f, 213 / 255f}; //#FAD9D5
            final float[] AQUA = {192 / 255f, 233 / 255f, 237 / 255f}; //#C0E9ED
            final float[] RECESS = {205 / 255f, 237 / 255f, 221 / 255f}; //#CDEDDD
            final float[] MINT = {205 / 255f, 237 / 255f, 221 / 255f}; //#CDE

            // Rounded central fuselage and contrasting underside.
            maker.identity();
            maker.translate(0, -0.10f, 0.10f);
            maker.color(PINK);
            maker.sphere(0.98f, 0.40f, 2.38f, 20);

            maker.identity();
            maker.translate(0, 0, -0.08f);
            maker.color(PEARL);
            maker.sphere(1.02f, 0.49f, 2.55f, 20);
// Long tapered nose; cones point along local +Y before rotation.
            maker.identity();
            maker.translate(0, 0, -0.94f);
            maker.rotateX(-90);
            maker.color(PEARL);
            maker.cone(0.66f, 0.78f, 0.30f, 24);
// Blush-pink cockpit surround and raised aqua canopy.
            maker.identity();
            maker.translate(0, 0.21f, -0.38f);
            maker.color(BLUSH);
            maker.sphere(0.73f, 0.31f, 1.12f, 20);

            maker.identity();
            maker.translate(0, 0.30f, -0.40f);
            maker.color(AQUA);
            maker.sphere(0.62f, 0.43f, 0.94f, 20);

            maker.identity();
            maker.translate(-0.13f, 0.47f, -0.51f);
            maker.color(PEARL);
            maker.sphere(0.09f, 0.025f, 0.36f, 20);
// Mirrored swept petal wings and twin engine nacelles.
            for (int side = -1; side <= 1; side += 2) {
                maker.identity();
                maker.translate(side * 0.94f, -0.035f, 0.30f);
                maker.rotateY(side * -38);
                maker.color(BLUSH);
                maker.sphere(1.92f, 0.17f, 0.64f, 20);
                maker.translate(0, 0.055f, 0);
                maker.color(PEARL);
                maker.sphere(1.83f, 0.14f, 0.55f, 20);
                maker.translate(side * 0.06f, 0.065f, 0.015f);
                maker.color(PINK);
                maker.sphere(1.46f, 0.065f, 0.29f, 20);
// Small outer petals curl forward to form a crescent silhouette.
                maker.identity();
                maker.translate(side * 1.54f, 0.015f, 0.48f);
                maker.rotateY(side * 30);
                maker.color(PEARL);
                maker.sphere(0.34f, 0.17f, 1.05f, 20);
// Keep the light in the outer petal's rotated coordinates.
// Its base overlaps the wing surface so it cannot float above it.
                maker.translate(0, 0.065f, -0.30f);
                maker.color(AQUA);
                maker.sphere(0.13f, 0.10f, 0.18f, 20);
                maker.identity();
                maker.translate(side * 0.86f, -0.07f, 0.85f);
                maker.color(PINK);
                maker.sphere(0.43f, 0.40f, 1.12f, 20);
                maker.identity();
                maker.translate(side * 0.86f, -0.07f, 1.27f);
                maker.color(BLUSH);
                maker.cylinderZ(0.46f, 0.43f, 0.18f, 24);
                maker.translate(0, 0, 0.105f);
                maker.color(RECESS);
                maker.cylinderZ(0.35f, 0.32f, 0.05f, 24);
                maker.translate(0, 0, 0.035f);
                maker.color(MINT);
                maker.cylinderZ(0.26f, 0.24f, 0.04f, 24);
                maker.translate(0, 0, 0.025f);
                maker.color(AQUA);
                maker.rotateX(90);
                maker.cone(0.17f, 0.43f, 0.17f, 20);
// Three small blush-pink portholes along each side.
                for (int i = 0; i < 3; i++) {
                    maker.identity();
                    maker.translate(side * 0.47f, 0.11f, 0.06f + i * 0.23f);
                    maker.color(BLUSH);
                    maker.sphere(0.08f, 0.12f, 0.12f, 20);

                    maker.identity();
                    maker.translate(side * 0.50f, 0.12f, 0.06f + i * 0.23f);
                    maker.color(AQUA);
                    maker.sphere(0.035f, 0.075f, 0.075f, 20);
                }
            }
// Upright rear petal fin adds a readable side silhouette.
            maker.identity();
            maker.translate(0, 0.32f, 0.87f);
            maker.rotateX(28);
            maker.color(BLUSH);
            maker.sphere(0.12f, 0.85f, 0.51f, 20);
            maker.color(PINK);
            maker.sphere(0.14f, 0.72f, 0.40f, 20);
// Flower crest: five pearl petals surrounding a mint jewel.
            for (int i = 0; i < 5; i++) {
                double angle = Math.toRadians(i * 72);
                maker.identity();
                maker.translate((float) Math.sin(angle) * 0.13f, 0.255f, 0.43f + (float) Math.cos(angle) * 0.13f);
                maker.color(BLUSH);
                maker.sphere(0.14f, 0.065f, 0.14f, 20);
            }
            maker.identity();
            maker.translate(0, 0.29f, 0.43f);
            maker.color(MINT);
            maker.sphere(0.14f, 0.10f, 0.14f, 20);

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
else if(id==4){
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

else if(id==5){
            maker.identity();
            maker.color(0.85f,0.85f,0.9f);//light grey
            maker.cylinderX(2f,0.5f,0.5f,20);
//nose cone
            maker.identity();
            maker.translate(1f,0,0);
            maker.rotateZ(-90);
            maker.cone(0.5f,0.8f,0.5f,20);
            maker.identity();
            maker.translate(1.65f,0,0);
            maker.rotateZ(-90);
            maker.color(1,0,0);
            maker.cone(0.15f,0.3f,0.15f,12);
            maker.identity();
            maker.translate(0.6f,0,0);
            maker.cylinderX(0.1f,0.52f,0.52f,20);
            maker.identity();
            maker.translate(0.5f,0.2f,0);
            maker.color(0.3f,0.8f,1f);//light blue
            maker.sphere(0.6f,0.3f,0.3f,16);
            for(int side=-1;side<=1;side+=2){
                maker.identity();
                maker.translate(-0.3f,0,0);
                maker.rotateX(90*side);
                maker.translate(0,0.75f,0);
                maker.color(0.2f,0.3f,0.8f);//blue
                maker.trapezoid(1.3f,1.3f,0.06f,0.4f,0.06f);
                maker.translate(0,0.65f,0);
                maker.color(1,0.9f,0.2f);
                maker.sphere(0.15f,0.15f,0.15f,10);
            }
            maker.identity();
            maker.translate(-0.75f,0.45f,0);
            maker.rotateZ(25);
            maker.color(0.2f,0.3f,0.8f);
            maker.trapezoid(0.5f,0.6f,0.05f,0.25f,0.05f);
            maker.color(0.1f,0.1f,0.3f);
            for(int i=0;i<3;i++){
                maker.identity();
                maker.translate(-0.4f+i*0.25f,0.05f,0.24f);
                maker.sphere(0.1f,0.1f,0.05f,8);
                maker.identity();
                maker.translate(-0.4f+i*0.25f,0.05f,-0.24f);
                maker.sphere(0.1f,0.1f,0.05f,8);
            }
            for(int side=-1;side<=1;side+=2){
                maker.identity();
                maker.translate(-0.5f,-0.15f,side*0.6f);
                maker.color(0.3f,0.3f,0.3f);//dark grey
                maker.cylinderX(0.9f,0.25f,0.25f,16);
                maker.translate(-0.45f,0,0);
                maker.rotateZ(90);
                maker.color(1,0.5f,0);//orange
                maker.cone(0.22f,0.5f,0.22f,12);
                maker.color(1,1,0);//yellow
                maker.cone(0.1f,0.7f,0.1f,8);
            }
            maker.identity();
            maker.translate(-1f,0,0);
            maker.rotateZ(90);
            maker.color(1,0.5f,0);
            maker.cone(0.4f,0.7f,0.4f,16);
            maker.color(1,1,0);
            maker.cone(0.2f,1f,0.2f,12);
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

else if(id==11f){
            float S=0.75f; //global size of the ship (keeps it inside the screen)
// "Comet Striker" - a custom starfighter built with ObjectMaker.
// The ship points towards +X (nose on the right), Y is up and Z is the wing span.
// Note: rotateZ(-90) maps the local Y axis to world +X, so cylinders and cones
// drawn after it are aligned with the body of the ship.
//---------------- FUSELAGE ----------------
            maker.identity();
            maker.scale(S,S,S);
            maker.pushMatrix();
            maker.rotateZ(-90);
            maker.color(0.88f,0.9f,0.93f); //light silver body
            maker.cylinder(0.42f,2.4f,0.42f,0.5f,0.5f,24); //tapered tube from x=-1.2 to x=1.2
            maker.translate(0,1.2f,0);
            maker.color(0.85f,0.1f,0.12f); //red nose cone
            maker.cone(0.5f,0.9f,0.5f,24);
            maker.popMatrix();
//red racing stripes around the fuselage
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(0.75f,0,0);
            maker.rotateZ(-90);
            maker.color(0.85f,0.1f,0.12f);
            maker.cylinder(0.5f,0.07f,0.5f,24);
            maker.translate(0,-0.15f,0);
            maker.cylinder(0.5f,0.07f,0.5f,24);
//---------------- COCKPIT ----------------
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(0.45f,0.19f,0);
            maker.color(0.3f,0.85f,1f); //cyan glass canopy
            maker.sphere(0.8f,0.32f,0.3f,20);
            maker.translate(-0.42f,0,0);
            maker.color(0.2f,0.22f,0.28f); //dark canopy base
            maker.box(0.12f,0.12f,0.26f);
//antenna with a red light on top
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-0.25f,0.33f,0);
            maker.color(0.3f,0.3f,0.3f);
            maker.cylinderY(0.025f,0.3f,0.025f,8);
            maker.translate(0,0.17f,0);
            maker.color(1f,0.15f,0.15f);
            maker.sphere(0.07f,0.07f,0.07f,10);
//---------------- MAIN WINGS (swept back) ----------------
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-0.25f,-0.05f,0.7f);
            maker.rotateY(-25);
            maker.color(0.12f,0.22f,0.55f); //navy blue wing (right)
            maker.box(0.8f,0.05f,1.1f);
            maker.translate(0,0.03f,0);
            maker.color(1f,0.55f,0f); //orange wing stripe
            maker.box(0.15f,0.02f,1.0f);
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-0.25f,-0.05f,-0.7f);
            maker.rotateY(25);
            maker.color(0.12f,0.22f,0.55f); //navy blue wing (left)
            maker.box(0.8f,0.05f,1.1f);
            maker.translate(0,0.03f,0);
            maker.color(1f,0.55f,0f); //orange wing stripe
            maker.box(0.15f,0.02f,1.0f);
//---------------- WING-TIP LASER CANNONS ----------------
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-0.4f,-0.05f,1.2f);
            maker.rotateZ(-90);
            maker.color(0.35f,0.35f,0.38f); //dark gray cannon (right)
            maker.cylinder(0.09f,0.9f,0.09f,12);
            maker.translate(0,0.45f,0);
            maker.color(1f,0.1f,0.1f); //red laser tip
            maker.cone(0.09f,0.25f,0.09f,12);
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-0.4f,-0.05f,-1.2f);
            maker.rotateZ(-90);
            maker.color(0.35f,0.35f,0.38f); //dark gray cannon (left)
            maker.cylinder(0.09f,0.9f,0.09f,12);
            maker.translate(0,0.45f,0);
            maker.color(1f,0.1f,0.1f); //red laser tip
            maker.cone(0.09f,0.25f,0.09f,12);
//navigation lights (green right, red left)
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-0.75f,-0.05f,1.28f);
            maker.color(0.1f,1f,0.2f);
            maker.sphere(0.08f,0.08f,0.08f,10);
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-0.75f,-0.05f,-1.28f);
            maker.color(1f,0.1f,0.1f);
            maker.sphere(0.08f,0.08f,0.08f,10);
//---------------- FRONT CANARDS ----------------
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(0.85f,-0.02f,0.3f);
            maker.rotateY(-30);
            maker.color(0.12f,0.22f,0.55f);
            maker.box(0.25f,0.04f,0.3f);
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(0.85f,-0.02f,-0.3f);
            maker.rotateY(30);
            maker.color(0.12f,0.22f,0.55f);
            maker.box(0.25f,0.04f,0.3f);
//---------------- TAIL FIN ----------------
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-0.85f,0.35f,0);
            maker.rotateZ(30);
            maker.color(1f,0.55f,0f); //orange fin
            maker.box(0.55f,0.5f,0.05f);
            maker.translate(0,0.25f,0);
            maker.color(0.12f,0.22f,0.55f); //navy fin tip
            maker.box(0.55f,0.06f,0.07f);
//---------------- ENGINES ----------------
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-1.25f,0,0);
            maker.color(0.3f,0.3f,0.33f); //engine block closing the back of the ship
            maker.box(0.15f,0.45f,0.9f);
//right engine: nozzle + flames (rotateZ(90) makes local Y point to world -X)
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-1.5f,0,0.3f);
            maker.rotateZ(90);
            maker.color(0.55f,0.55f,0.6f); //gray nozzle
            maker.cylinder(0.3f,0.4f,0.3f,0.36f,0.36f,20);
            maker.translate(0,0.2f,0);
            maker.color(1f,0.45f,0f); //orange outer flame
            maker.cone(0.32f,0.6f,0.32f,20);
            maker.color(1f,0.95f,0.2f); //yellow inner flame
            maker.cone(0.18f,0.9f,0.18f,20);
//left engine
            maker.identity();
            maker.scale(S,S,S);
            maker.translate(-1.5f,0,-0.3f);
            maker.rotateZ(90);
            maker.color(0.55f,0.55f,0.6f);
            maker.cylinder(0.3f,0.4f,0.3f,0.36f,0.36f,20);
            maker.translate(0,0.2f,0);
            maker.color(1f,0.45f,0f);
            maker.cone(0.32f,0.6f,0.32f,20);
            maker.color(1f,0.95f,0.2f);
            maker.cone(0.18f,0.9f,0.18f,20);
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


else if(id==14){
            maker.identity();
// 1. Saucer Section (Primary Hull) - Star Trek Enterprise style disc at the front/top
            maker.color(0.8f, 0.8f, 0.85f); // Light silver/gray
            maker.pushMatrix();
            maker.translate(0f, 0.5f, 0.5f);
            maker.cylinder(1.4f, 0.15f, 1.4f, 32); // Saucer disc
// Command Bridge Dome on top of saucer
            maker.translate(0f, 0.12f, 0f);
            maker.color(0.2f, 0.7f, 0.9f); // Cyan accent
            maker.sphere(0.3f, 0.2f, 0.3f, 16);
            maker.popMatrix();
// 2. Neck / Pylon connecting saucer to secondary hull
            maker.color(0.6f, 0.6f, 0.65f);
            maker.pushMatrix();
            maker.translate(0f, 0.25f, 0f);
            maker.box(0.25f, 0.4f, 0.6f);
            maker.popMatrix();
// 3. Secondary Hull (Engineering Hull) - Elongated cylinder extending backward
            maker.color(0.7f, 0.7f, 0.75f);
            maker.pushMatrix();
            maker.translate(0f, -0.2f, -0.2f);
            maker.rotateX(90f); // Align along Z axis
            maker.cylinder(0.35f, 1.6f, 0.35f, 16);
// Deflector Dish at the front of secondary hull
            maker.translate(0f, 0.85f, 0f);
            maker.color(0.9f, 0.5f, 0.1f); // Copper/Orange deflector dish
            maker.cone(0.25f, 0.3f, 0.25f, 16);
            maker.popMatrix();
// 4. Left Warp Nacelle (Pylon + Nacelle Engine)
            maker.color(0.5f, 0.5f, 0.55f);
            maker.pushMatrix();
            maker.translate(-0.4f, 0f, -0.2f);
            maker.box(0.5f, 0.1f, 0.3f);
// Nacelle cylinder
            maker.translate(-0.3f, 0f, 0f);
            maker.rotateX(90f);
            maker.color(0.7f, 0.7f, 0.75f);
            maker.cylinder(0.2f, 1.4f, 0.2f, 16);
// Nacelle Bussard Collector (Red front cap)
            maker.translate(0f, 0.75f, 0f);
            maker.color(0.9f, 0.1f, 0.1f);
            maker.sphere(0.22f, 0.25f, 0.22f, 16);
// Nacelle Glow (Blue exhaust stripe)
            maker.translate(0f, -1.5f, 0f);
            maker.color(0.1f, 0.4f, 0.9f);
            maker.cylinder(0.21f, 0.8f, 0.21f, 16);
            maker.popMatrix();
// 5. Right Warp Nacelle (Pylon + Nacelle Engine)
            maker.color(0.5f, 0.5f, 0.55f);
            maker.pushMatrix();
            maker.translate(0.4f, 0f, -0.2f);
            maker.box(0.5f, 0.1f, 0.3f);
// Nacelle cylinder
            maker.translate(0.3f, 0f, 0f);
            maker.rotateX(90f);
            maker.color(0.7f, 0.7f, 0.75f);
            maker.cylinder(0.2f, 1.4f, 0.2f, 16);
// Nacelle Bussard Collector (Red front cap)
            maker.translate(0f, 0.75f, 0f);
            maker.color(0.9f, 0.1f, 0.1f);
            maker.sphere(0.22f, 0.25f, 0.22f, 16);
// Nacelle Glow (Blue exhaust stripe)
            maker.translate(0f, -1.5f, 0f);
            maker.color(0.1f, 0.4f, 0.9f);
            maker.cylinder(0.21f, 0.8f, 0.21f, 16);
            maker.popMatrix();
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
            // middle green cylinder
            maker.color(125f/255f,1,155f/255f);
            maker.cylinderY(3.5f, 0.15f, 3.5f, 10);
// top gray cone
            maker.color(192f/255f,192f/255f,192f/255f);
            maker.translate(0, 0.08f, 0);
            maker.cone(3.5f, 0.35f, 3.5f, 10);
// green dome base
            maker.color(125f/255f,1,155f/255f);
            maker.cylinderY(1.7f, 0.5f, 1.7f, 10);
// glass dome
            maker.translate(0, 0.15f, 0);
            maker.color(1,1,1);
            maker.sphere(1.5f, 1.5f, 1.5f,10);
// yellow lights
            maker.translate(0, -0.1f, 0);
            maker.color(1, 242f/255f, 5f/255f);
            int count = 12;
            float radius = 1.4f;
            for (int i = 0; i < count; i++) {
                float angle = i * (360f / count);
                maker.pushMatrix();
                maker.rotateY(angle);
                maker.translate(radius, 0, 0);
                maker.sphere(0.15f, 0.15f, 0.15f, 10);
                maker.popMatrix();
            }
// bottom gray cone
            maker.translate(0, -0.2f, 0);
            maker.color(192f/255f,192f/255f,192f/255f);
            maker.cone(3.5f,-0.35f,3.5f,10);
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

else if(id==22){
            final float[] IVORY = {0.90f, 0.91f, 0.86f};
            final float[] NAVY = {0.075f, 0.14f, 0.25f};
            final float[] STEEL = {0.34f, 0.44f, 0.56f};
            final float[] ORANGE = {1.00f, 0.34f, 0.075f};
            final float[] GLASS = {0.06f, 0.68f, 0.78f};
            final float[] AQUA = {0.20f, 0.91f, 1.00f};
            final float[] CORE = {0.68f, 1.00f, 0.98f};
            final float[] PORT_LIGHT = {1.00f, 0.16f, 0.30f};
            final float[] STARBOARD_LIGHT = {0.30f, 0.97f, 0.52f};


            maker.scale(0.70f, 0.70f, 0.70f);
            maker.pushMatrix();
            maker.translate(0f, -0.015f, -0.17f);
            maker.rotateX(90f);
            maker.color(IVORY);
            maker.cylinder(0.94f, 1.98f, 0.50f, 1.00f, 0.46f, 8);
            maker.translate(0f, 1.42f, 0f);
            maker.cylinder(1.015f, 0.96f, 0.465f, 0.28f, 0.18f, 8);
            maker.translate(0f, 0.55f, 0f);
            maker.color(ORANGE);
            maker.cylinder(0.31f, 0.21f, 0.20f, 0.085f, 0.065f, 8);
            maker.popMatrix();
// Dorsal equipment bay and three transverse cooling ribs.
            maker.pushMatrix();
            maker.translate(0f, 0.24f, -0.61f);
            maker.color(NAVY);
            maker.trapezoid(0.66f, 0.13f, 1.04f, 0.42f, 0.84f);
            maker.translate(0f, 0.067f, -0.18f);
            maker.color(STEEL);
            for (int rib = 0; rib < 3; rib++) {
                maker.box(0.32f, 0.024f, 0.07f);
                maker.translate(0f, 0f, 0.15f);
            }
            maker.popMatrix();
            maker.pushMatrix();
            maker.translate(0f, 0.19f, 0.62f);
            maker.color(NAVY);
            maker.sphere(0.82f, 0.36f, 1.15f, 28);
            maker.translate(0f, 0.07f, 0.04f);
            maker.color(GLASS);
            maker.sphere(0.65f, 0.42f, 0.99f, 32);
// A transverse frame follows the curve of the canopy.
            maker.translate(0f, 0f, -0.105f);
            maker.color(NAVY);
            maker.sphere(0.661f, 0.435f, 0.09f, 24);
            maker.popMatrix();
            maker.pushMatrix();
            maker.translate(0f, -0.23f, -0.18f);
            maker.rotateX(180f);
            maker.color(STEEL);
            maker.trapezoid(0.82f, 0.29f, 1.96f, 0.51f, 1.53f);
            maker.popMatrix();
            maker.pushMatrix();
            maker.translate(0f, -0.33f, 0.26f);
            maker.rotateX(180f);
            maker.color(NAVY);
            maker.trapezoid(0.56f, 0.20f, 0.75f, 0.42f, 0.57f);
            maker.translate(0f, 0.10f, -0.04f);
            maker.color(AQUA);
            maker.sphere(0.32f, 0.17f, 0.37f, 24);
            maker.popMatrix();
            maker.pushMatrix();
            maker.translate(0f, -0.373f, -0.88f);
            maker.color(ORANGE);
            for (int rib = 0; rib < 3; rib++) {
                maker.box(0.43f, 0.044f, 0.065f);
                maker.translate(0f, 0f, 0.15f);
            }
            maker.popMatrix();
            for (int side = -1; side <= 1; side += 2) {
                maker.pushMatrix();
                maker.translate(side * 0.97f, -0.065f, -0.38f);
                maker.rotateY(side * 18f);
                maker.rotateZ(-side * 90f);
                maker.color(NAVY);
                maker.trapezoid(0.26f, 1.64f, 1.96f, 0.13f, 0.66f);
// The smaller trapezoids are the upper wing skin and its orange stripe.
                maker.translate(-side * 0.085f, 0.09f, 0.05f);
                maker.color(IVORY);
                maker.trapezoid(0.11f, 1.26f, 1.30f, 0.064f, 0.48f);
                maker.translate(-side * 0.046f, 0f, 0.16f);
                maker.color(ORANGE);
                maker.trapezoid(0.037f, 1.16f, 0.23f, 0.031f, 0.13f);
                maker.popMatrix();
                maker.pushMatrix();
                maker.translate(side * 1.735f, -0.04f, -0.52f);
                maker.rotateY(side * 18f);
                maker.color(STEEL);
                maker.box(0.20f, 0.14f, 0.30f);
                maker.translate(0f, 0.075f, 0.04f);
                maker.color(side < 0 ? PORT_LIGHT : STARBOARD_LIGHT);
                maker.sphere(0.135f, 0.13f, 0.16f, 16);
                maker.popMatrix();
                maker.pushMatrix();
                maker.translate(side * 0.88f, -0.173f, -0.05f);
                maker.rotateY(side * 18f);
                maker.color(STEEL);
                maker.box(0.31f, 0.09f, 0.62f);
                maker.translate(0f, -0.043f, 0f);
                maker.color(ORANGE);
                maker.box(0.22f, 0.045f, 0.49f);
                maker.popMatrix();
                maker.pushMatrix();
                maker.translate(side * 1.36f, 0.015f, -0.84f);
                maker.rotateX(90f);
                maker.color(NAVY);
                maker.cylinder(0.56f, 1.20f, 0.56f, 0.49f, 0.49f, 24);
                maker.translate(0f, 0.67f, 0f);
                maker.color(ORANGE);
                maker.cylinder(0.50f, 0.27f, 0.50f, 0.31f, 0.31f, 24);
                maker.translate(0f, 0.128f, 0f);
                maker.color(NAVY);
                maker.cylinderY(0.23f, 0.039f, 0.23f, 24);
// Rear collar, flared nozzle and concentric exhaust details.
                maker.translate(0f, -1.13f, 0f);
                maker.color(ORANGE);
                maker.cylinderY(0.60f, 0.22f, 0.60f, 24);
                maker.translate(0f, -0.314f, 0f);
                maker.color(STEEL);
                maker.cylinder(0.59f, 0.31f, 0.59f, 0.53f, 0.53f, 24);
                maker.translate(0f, -0.156f, 0f);
                maker.color(NAVY);
                maker.cylinderY(0.48f, 0.056f, 0.48f, 24);
                maker.translate(0f, -0.025f, 0f);
                maker.color(AQUA);
                maker.cylinderY(0.35f, 0.037f, 0.35f, 24);
                maker.translate(0f, -0.019f, 0f);
                maker.color(CORE);
                maker.cylinderY(0.17f, 0.026f, 0.17f, 24);
                maker.popMatrix();
                maker.pushMatrix();
                maker.translate(side * 0.40f, 0.34f, -0.75f);
                maker.rotateZ(-side * 17f);
                maker.rotateX(-22f);
                maker.color(NAVY);
                maker.trapezoid(0.18f, 0.68f, 0.78f, 0.095f, 0.34f);
                maker.translate(0f, 0.28f, 0f);
                maker.color(ORANGE);
                maker.trapezoid(0.119f, 0.16f, 0.43f, 0.093f, 0.32f);
                maker.popMatrix();
            }
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

        if(id==1){maker2.rotateY(-180/2f);}
        else if(id==3){maker2.rotateX(-180/2f);}
        else if(id==4){maker2.rotateX(-180/2f);}
        else if(id==5){maker2.rotateY(180/2f);}
        else if(id==6){maker2.rotateX(-180/2f);}
        else if(id==7){maker2.rotateY(180);}
        else if(id==9){maker2.rotateX(-180/2f);}
        else if(id==10){maker2.rotateY(180);}
        else if(id==11){maker2.rotateY(180/2f);}
        else if(id==12){maker2.rotateX(-180/2f);}
        else if(id==13){maker2.rotateX(-180/2f);}
        else if(id==14){maker2.rotateY(180);}
        else if(id==15){maker2.rotateX(180);maker2.rotateY(180);}
        else if(id==16){maker2.rotateX(-180/2f);}
        else if(id==18){maker2.rotateX(-180/2f);}
        else if(id==21){maker2.rotateX(-180/2f);}
        else if(id==22){maker2.rotateY(180);}
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

        if(gameObject ==null){
            maker2.flushModel(this,true,false,true);
            this.shader=new ColorPhongShader();
        }
        else {
            maker2.flushModel(gameObject,true,false,true);
            gameObject.shader=new ColorPhongShader();
        }
    }


}
