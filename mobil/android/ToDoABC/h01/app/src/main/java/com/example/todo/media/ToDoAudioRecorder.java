package com.example.todo.media;

import android.media.MediaRecorder;
import android.util.Log;

import java.io.File;
import java.io.IOException;

/**
 * Recording audio
 */
public class ToDoAudioRecorder {
    private static final String TAG = "ToDoAudioRecorder";

    private MediaRecorder mRecorder = null;
    private String mFileName = null;

    public static final String TODO_SOUND_FILE = "todoSound.3gp";

    /**
     * ToDoAudioRecorder
     * @param pFileName target file
     */
    public ToDoAudioRecorder(String pFileName) {
        mFileName = pFileName;
    }

    /**
     * Start audio recording to the given file.
     */
    public void startRecording() {
        mRecorder = new MediaRecorder();
        //Defines the audio source: microphone
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //Defines the output format. Use 3GPP media file.
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //Set File Name
        mRecorder.setOutputFile(mFileName);
        //Defines the audio encoding.
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed", e);
        }

        mRecorder.start();
    }

    /**
     * Stop recording.
     */
    public void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

}
