package edu.ufl.digitalworlds.j4q;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class Audio extends MediaPlayer {

    public Audio(Context context, String filename){
        AssetFileDescriptor descriptor = null;
        try {
            descriptor = context.getAssets().openFd(filename);
            setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
