package j4q.activities;

import android.opengl.GLES30;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Date;

import j4q.J4Q;
import j4q.geometry.Transform;
import j4q.models.GameObject;


/**
 * Represents a scene in the game engine, managing the root object, rendering, and scene state.
 * <p>
 * The {@code GameEngineScene} class handles scene graph management, OpenGL buffer setup,
 * projection/view matrix updates, lighting, and activity lifecycle integration.
 * </p>
 */
public class GameEngineScene {

    /**
     * The root GameObject of the scene graph.
     */
    public GameObject root = new GameObject();

    /**
     * Appends a GameObject as a child to the root.
     * @param gameObject The GameObject to append.
     */
    public void appendChild(GameObject gameObject) { root.appendChild(gameObject); }

    /**
     * Prepends a GameObject as a child to the root.
     * @param gameObject The GameObject to prepend.
     */
    public void prependChild(GameObject gameObject) { root.prependChild(gameObject); }

    /**
     * Removes a GameObject child from the root.
     * @param gameObject The GameObject to remove.
     */
    public void removeChild(GameObject gameObject) { root.removeChild(gameObject); }

    /**
     * The normal matrix used for lighting calculations.
     */
    public float[] mNormalMatrix = new float[16];

    /**
     * The direction of the scene's light source.
     */
    public float[] lightDir = new float[3];

    /**
     * Buffer for the projection matrix.
     */
    public FloatBuffer mProjectionMatrixBuffer;

    /**
     * Buffer for the view matrix.
     */
    public FloatBuffer mViewMatrixBuffer;

    /**
     * Buffer for the normal matrix.
     */
    public FloatBuffer mNormalMatrixBuffer;

    /**
     * Buffer for the light direction.
     */
    public FloatBuffer mLightDirBuffer;

    /**
     * The current view transform.
     */
    public Transform view;

    /**
     * OpenGL buffer ID for scene matrices.
     */
    public int sceneMatricesBuffer;

    /**
     * The activity start time in milliseconds.
     */
    private long start_time;

    /**
     * The last recorded time for frame updates.
     */
    double lastTime = 0;

    /**
     * The elapsed display time in seconds.
     */
    float elapsedDisplayTime = 0;

    /**
     * Returns the elapsed display time in seconds.
     * @return The elapsed display time.
     */
    public float getElapsedDisplayTime() { return elapsedDisplayTime; }

    /**
     * The time per frame in seconds.
     */
    float perSec = 0;

    /**
     * Returns the time per frame in seconds.
     * @return The time per frame.
     */
    public float perSec() { return perSec; }

    /**
     * The activity associated with this scene.
     */
    private GameEngineActivity activity;

    /**
     * Constructs a GameEngineScene with the specified activity.
     * Initializes OpenGL buffers and sets up the scene.
     * @param activity The activity to associate with the scene.
     */
    public GameEngineScene(GameEngineActivity activity){
        this.activity=activity;
        J4Q.scene=this;
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                16 * 4);
        bb.order(ByteOrder.nativeOrder());
        mProjectionMatrixBuffer = bb.asFloatBuffer();

        ByteBuffer bb4 = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                mNormalMatrix.length * 4);
        bb.order(ByteOrder.nativeOrder());
        mNormalMatrixBuffer = bb.asFloatBuffer();

        view=new Transform();

        ByteBuffer bb2 = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                view.matrix.length * 4);
        bb2.order(ByteOrder.nativeOrder());
        mViewMatrixBuffer = bb2.asFloatBuffer();

        ByteBuffer bb3 = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                lightDir.length * 4);
        bb3.order(ByteOrder.nativeOrder());
        mLightDirBuffer = bb3.asFloatBuffer();
    }

    /**
     * Sets the background color for the scene.
     * @param r Red component (0-1).
     * @param g Green component (0-1).
     * @param b Blue component (0-1).
     */
    public void background(float r, float g, float b) {
        GLES30.glClearColor(r, g, b, 1.0f);
    }

    /**
     * Updates the projection matrix buffer and sends it to the GPU.
     * @param mProjectionMatrix The projection matrix to set.
     */
    public void setupProjection(float[] mProjectionMatrix) {

        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mProjectionMatrixBuffer.put(mProjectionMatrix);
        mProjectionMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 16*4,16*4,mProjectionMatrixBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);
    }

    /**
     * Updates the view and normal matrix buffers and sends them to the GPU.
     * @param mViewMatrix The view matrix to set.
     */
    public void setupView(float[] mViewMatrix) {
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mViewMatrixBuffer.put(mViewMatrix);
        mViewMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 0,16*4,mViewMatrixBuffer);

        Transform t=new Transform(mViewMatrix);
        mNormalMatrixBuffer.put(t.getNormalMatrix());
        mNormalMatrixBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, 16*2*4,16*4,mNormalMatrixBuffer);

        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);
    }

    /**
     * Updates the scene state, including animation, activity, and global positions.
     */
    public void update() {
        elapsedDisplayTime=(new Date().getTime()-start_time)/1000f;
        perSec=(float)(elapsedDisplayTime-lastTime);
        lastTime=elapsedDisplayTime;

        root.updateAnimation();
        activity.Update();
        root.updateGlobalPositions(false);
    }

    /**
     * Draws the scene, updating the view matrix and rendering the root object.
     */
    public void draw() {
        //update view matrix
        setupView(view.matrix);

        GLES30.glClear( GLES30.GL_COLOR_BUFFER_BIT | GLES30.GL_DEPTH_BUFFER_BIT );
        root.draw();
    }


    /**
     * Initializes OpenGL state, sets up buffers, lighting, and starts the activity.
     */
    public void start() {
        GLES30.glEnable( GLES30.GL_SCISSOR_TEST );
        GLES30.glDepthMask( true );
        GLES30.glEnable( GLES30.GL_DEPTH_TEST );
        GLES30.glDepthFunc( GLES30.GL_LEQUAL );
        GLES30.glEnable( GLES30.GL_CULL_FACE );
        GLES30.glCullFace( GLES30.GL_BACK);

        GLES30.glEnable(GLES30.GL_BLEND);
        GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA, GLES30.GL_ONE_MINUS_SRC_ALPHA);

        int[] i=new int[1];
        GLES30.glGenBuffers(1,i,0);
        sceneMatricesBuffer=i[0];
        GLES30.glBindBuffer(GLES30.GL_UNIFORM_BUFFER, i[0]);
        GLES30.glBufferData(GLES30.GL_UNIFORM_BUFFER, (16*3+4)*4, null, GLES30.GL_STATIC_DRAW);
        GLES30.glBindBuffer(GLES30.GL_UNIFORM_BUFFER, 0);

        GLES30.glBindBufferRange(GLES30.GL_UNIFORM_BUFFER, 0,//map to index 0
                sceneMatricesBuffer, 0, (16*3+4)*4);


        setLightDir(0,0,-1);

        activity.Start();
        start_time=new Date().getTime();
    }

    /**
     * Sets the direction of the scene's light source and updates the GPU buffer.
     * @param x The x component of the light direction.
     * @param y The y component of the light direction.
     * @param z The z component of the light direction.
     */
    public void setLightDir(float x, float y, float z) {
        float mag=(float)Math.sqrt(x*x+y*y+z*z);

        lightDir[0]=-x;
        lightDir[1]=-y;
        lightDir[2]=-z;
        if(mag>0){
            lightDir[0]/=mag;
            lightDir[1]/=mag;
            lightDir[2]/=mag;
        }
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER, sceneMatricesBuffer  );
        mLightDirBuffer.put(lightDir);
        mLightDirBuffer.position(0);
        GLES30.glBufferSubData(GLES30.GL_UNIFORM_BUFFER, (16*3)*4,3*4,mLightDirBuffer);
        GLES30.glBindBuffer( GLES30.GL_UNIFORM_BUFFER,0);
    }

}
