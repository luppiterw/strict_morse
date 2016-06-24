package vivisystem.com.test_system_checker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hughie on 16/6/24.
 */
public class BatteryLevelBroadcastReceiver  extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        BatteryChecker.doReceive(context, intent);
    }
}
