package vivisystem.com.test_system_checker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

/**
 * Created by hughie on 16/6/24.
 */
public class DockChecker {

    public enum DockState
    {
        UnDocked(Intent.EXTRA_DOCK_STATE_UNDOCKED, "Dock State UnDocked"),
        Car(Intent.EXTRA_DOCK_STATE_CAR, "Dock State Car"),
        LeDesk(Intent.EXTRA_DOCK_STATE_LE_DESK, "Dock State LeDesk"),
        HeDesk(Intent.EXTRA_DOCK_STATE_HE_DESK, "Dock State HeDesk"),;

        DockState(int id, String comment) {
            this.id = id;
            this.comment = comment;
        }

        public static String getComment(int id) {
            for (DockState v : values()) {
                if (v.id == id)
                    return v.comment;
            }
            return null;
        }

        int id;
        String comment;
    }


    private static BroadcastReceiver dockReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent == null)
                return;

            int dockState = intent.getIntExtra(Intent.EXTRA_DOCK_STATE, 0);

            Log.d("Hughie", "********** Dock Information ********** Action = " + intent.getAction() +
                    "\n     status = " + dockState + " " + DockState.getComment(dockState)
            );
        }
    };
    public static void registerReceiver(Context context) {
        /**
         * sticky-broadcast（也可以在AndroidManifest.xml中注册）
         * */
        context.registerReceiver(dockReceiver, new IntentFilter(Intent.ACTION_DOCK_EVENT));

        ///<  sticky-intent可以通过此方式直接读取
//        Intent stickyIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_DOCK_EVENT));

    }
}
