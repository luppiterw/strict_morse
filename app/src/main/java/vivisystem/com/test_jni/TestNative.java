package vivisystem.com.test_jni;

/**
 * Created by hughie on 16/6/21.
 */
public class TestNative
{
    static
    {
        System.loadLibrary("test_jni");
    }
    public static native int init();
}
