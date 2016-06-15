package vivisystem.com.strict_morse.tests;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by hughie on 16/6/15.
 */
public class MorseUtility
{
    public static void call(Context context)
    {
        if(context == null)
        {
            return;
        }
        try
        {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage("com.tencent.mm");
            context.startActivity(intent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://weixin.qq.com"));
            context.startActivity(viewIntent);
        }
    }
}
