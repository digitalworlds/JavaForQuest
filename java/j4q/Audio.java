package j4q;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Wrapper for MediaPlayer to load and play audio files from assets.
 */
public class Audio extends MediaPlayer {

    /**
     * Constructs an Audio object and loads an audio file from assets.
     * @param context The Android context.
     * @param filename The asset filename of the audio file.
     */
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
