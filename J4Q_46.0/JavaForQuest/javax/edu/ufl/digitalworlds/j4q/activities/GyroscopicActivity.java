package edu.ufl.digitalworlds.j4q.activities;

import static android.hardware.SensorManager.AXIS_X;
import static android.hardware.SensorManager.AXIS_Z;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.util.Arrays;

import javax.microedition.khronos.opengles.GL10;

import edu.ufl.digitalworlds.j4q.geometry.Transform;

public abstract class GyroscopicActivity extends GLActivity implements SensorEventListener {
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private String cameraId;
    protected CameraDevice cameraDevice;
    private TextureView textureView;
    private Size imageDimension;
    protected CaptureRequest.Builder captureRequestBuilder;
    protected CameraCaptureSession cameraCaptureSessions;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    TextureView.SurfaceTextureListener textureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            //open your camera here
            openCamera();
        }
        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            // Transform you image captured size according to the surface width and height
        }
        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }
        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        }
    };

    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(CameraDevice camera) {
            //This is called when the camera is open
            cameraDevice = camera;
            createCameraPreview();
        }
        @Override
        public void onDisconnected(CameraDevice camera) {
            cameraDevice.close();
        }
        @Override
        public void onError(CameraDevice camera, int error) {
            cameraDevice.close();
            cameraDevice = null;
        }
    };

    protected void createCameraPreview() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            //assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
            Surface surface = new Surface(texture);
            captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback(){
                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    //The camera is already closed
                    if (null == cameraDevice) {
                        return;
                    }
                    // When the session is ready, we start displaying the preview.
                    cameraCaptureSessions = cameraCaptureSession;
                    updatePreview();
                }
                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(getBaseContext(), "Configuration change", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void openCamera() {
        CameraManager manager = (CameraManager) getBaseContext().getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = manager.getCameraIdList()[0];
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map != null;
            imageDimension = map.getOutputSizes(SurfaceTexture.class)[0];
            // Add permission for camera and let user grant the permission
            if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void closeCamera() {
        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
        }
        /*if (null != imageReader) {
            imageReader.close();
            imageReader = null;
        }*/
    }

    protected void updatePreview() {
        if(null == cameraDevice) {

        }
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
        try {
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    protected void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }
    protected void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try {
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private SensorManager mSensorManager;
    private Sensor magnetic_field;
    private Sensor accelerometer;


    private final float[] currentRotation = new float[16];
    private final float[] currentRotationCalibrated = new float[16];
    private final float[] robustRotation = new float[16];
    private float[] initialRotation = new float[16];
    private final float[] accelerometerReading = new float[3];
    private final float[] magnetometerReading = new float[]{1f,0f,0f};
    private final float[] orientation=new float[3];
    private boolean initialRotationRecorded=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        magnetic_field = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetic_field != null) {
            mSensorManager.registerListener(this, magnetic_field,
                    SensorManager.SENSOR_DELAY_GAME);
        }

        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            mSensorManager.registerListener(this, accelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        scene.update();
        scene.view.identity().multiply(robustRotation).multiply(initialRotation);
        scene.draw();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                System.arraycopy(event.values, 0, accelerometerReading, 0, 3);
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                System.arraycopy(event.values, 0, magnetometerReading, 0, 3);
                break;
            default:
                return;
        }

        if (mSensorManager.getRotationMatrix(currentRotation, null, accelerometerReading, magnetometerReading)) {
            mSensorManager.remapCoordinateSystem(currentRotation, AXIS_X, AXIS_Z, currentRotationCalibrated);
            mSensorManager.getOrientation(currentRotationCalibrated,orientation );

            for(int i=0;i<16;i++)
                robustRotation[i]=robustRotation[i]*0.95f+currentRotation[i]*0.05f;

            //float yaw = (float) (Math.toDegrees(orientation[0]) + declination);
            //float pitch = (float) Math.toDegrees(orientation[1]);
            float roll = (float) Math.toDegrees(orientation[2]);

            if(!initialRotationRecorded){
                initialRotationRecorded=true;

                for(int i=0;i<16;i++)
                    robustRotation[i]=currentRotation[i];

                Transform t=new Transform(currentRotation);
                initialRotation=t.getInvertedMatrix();
                if(roll<-45) {
                    t = new Transform(initialRotation);
                    t.rotateZ(-90);
                    initialRotation = t.matrix;
                }
                else if(roll>45) {
                    t = new Transform(initialRotation);
                    t.rotateZ(90);
                    initialRotation = t.matrix;
                }


            }

        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    public void destroy(){
        if(magnetic_field!=null) mSensorManager.unregisterListener(this,magnetic_field);
        if(accelerometer!=null) mSensorManager.unregisterListener(this,accelerometer);
        super.destroy();
    }

    @Override
    public void onPause(){

        pauseCamera();
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        resumeCamera();
    }

    public void resumeCamera(){
        if(textureView==null)return;
        startBackgroundThread();
        if (textureView.isAvailable()) {
            openCamera();
        } else {
            textureView.setSurfaceTextureListener(textureListener);
        }
    }

    public void pauseCamera(){
        if(textureView==null)return;
        closeCamera();
        stopBackgroundThread();
    }

    public void showCamera(){
        showCamera(getTextureView());
    }

    public void showCamera(View view)
    {
        textureView=(TextureView)view;
        textureView.setSurfaceTextureListener(textureListener);

    }
}
