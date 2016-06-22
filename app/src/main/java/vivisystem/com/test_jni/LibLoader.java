package vivisystem.com.test_jni;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hughie on 16/6/22.
 */
public class LibLoader
{
    public static boolean copyFileFromAssets(Context context, String fileName, String path) {
        boolean copyIsFinish = false;
        try {
            InputStream is = context.getAssets().open(fileName);
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = 0;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            copyIsFinish = true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Hughie", "[copyFileFromAssets] IOException "+e.toString());
        }
        return copyIsFinish;
    }

    @SuppressLint("UnsafeDynamicallyLoadedCode")
    public static void copyFromAssetsAndLoad(Context context)
    {
        File dir = context.getDir("jniLibs", Activity.MODE_PRIVATE);
        File distFile = new File(dir.getAbsolutePath() + File.separator + "libtestfunssc.so");
        if(LibLoader.copyFileFromAssets(context, "libtestfunssc.so", distFile.getAbsolutePath()))
        {
            File file = new File(distFile.getAbsolutePath());
            Log.d("Hughie", "000====> " + distFile.getAbsolutePath() + " ?=" + file.exists());



            System.load(distFile.getAbsolutePath());

//            try
//            {
//                Class<?> clazz = Class.forName("testjnis.VoiceShortNative");
//
//                Log.d("Hughie", "000====> clazz= " + clazz);
//
//            }
//            catch (ClassNotFoundException e)
//            {
//                Log.d("Hughie", "000====> ClassNotFoundException");
//                e.printStackTrace();
//            }
//            Log.d("Hughie", "000====> " + LibLoader.firstfunction());
        }
    }

//    public static native int firstfunction();
}
