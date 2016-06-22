package vivisystem.com.test_jni;

import android.util.Log;

import VoiceShortJNI.VoiceShortNative;

/**
 * Created by Hughie on 2016/6/22.
 */
public class NormalLoadSO {
    static
    {
        System.loadLibrary("voiceshortjni");
        Log.d("Hughie", "try to load voiceshortjni");
    }
    public static int start()
    {
        return VoiceShortNative.voiceshort_firstfunction();
    }
}
