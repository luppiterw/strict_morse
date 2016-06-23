package vivisystem.com.test_system_checker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by hughie on 16/6/23.
 */
public class BatteryChecker {


    private static BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            Log.d("Hughie", " Battery level = " + level);
        }
    };

    public static void registerReceiver(Context context)
    {
        context.registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

}
