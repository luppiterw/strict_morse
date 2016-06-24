package vivisystem.com.test_system_checker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

/**
 * Created by hughie on 16/6/23.
 */
public class BatteryChecker {

    public enum BatteryStatus
    {
        Unknown(BatteryManager.BATTERY_STATUS_UNKNOWN, "Unknown Status"),
        Charging(BatteryManager.BATTERY_STATUS_CHARGING, "Charging Status"),
        DisCharging(BatteryManager.BATTERY_STATUS_DISCHARGING, "DisCharging Status"),
        NotCharging(BatteryManager.BATTERY_STATUS_NOT_CHARGING, "NotCharging Status"),
        Full(BatteryManager.BATTERY_STATUS_FULL, "Full Status");

        BatteryStatus(int id, String comment) {
            this.id = id;
            this.comment = comment;
        }

        public static String getComment(int id) {
            for (BatteryStatus v : values()) {
                if (v.id == id)
                    return v.comment;
            }
            return null;
        }

        int id;
        String comment;
    }

    public enum BatteryHealth
    {
        Unknown(BatteryManager.BATTERY_HEALTH_UNKNOWN, "Unknown Health"),
        Good(BatteryManager.BATTERY_HEALTH_GOOD, "Good Health"),
        OverHeat(BatteryManager.BATTERY_HEALTH_OVERHEAT, "OverHeat Health"),
        Dead(BatteryManager.BATTERY_HEALTH_DEAD, "Dead Health"),
        OverVoltage(BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE, "OverVoltage Health"),
        Unspecified(BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE, "Unspecified Health"),
        Cold(BatteryManager.BATTERY_HEALTH_COLD, "Cold Status");

        BatteryHealth(int id, String comment) {
            this.id = id;
            this.comment = comment;
        }

        public static String getComment(int id) {
            for (BatteryHealth v : values()) {
                if (v.id == id)
                    return v.comment;
            }
            return null;
        }

        int id;
        String comment;
    }

    public enum BatteryPlugged
    {
        AC(BatteryManager.BATTERY_PLUGGED_AC, "AC Plugged"),
        USB(BatteryManager.BATTERY_PLUGGED_USB, "USB Plugged"),
        Wireless(BatteryManager.BATTERY_PLUGGED_WIRELESS, "Wireless Plugged"),
        Any(BatteryManager.BATTERY_PLUGGED_AC | BatteryManager.BATTERY_PLUGGED_USB |BatteryManager.BATTERY_PLUGGED_WIRELESS,
                "Dead Plugged");

        BatteryPlugged(int id, String comment) {
            this.id = id;
            this.comment = comment;
        }

        public static String getComment(int id) {
            for (BatteryPlugged v : values()) {
                if (v.id == id)
                    return v.comment;
            }
            return null;
        }

        int id;
        String comment;
    }

    public static void doReceive(Context context, Intent intent){
        batteryReceiver.onReceive(context, intent);
    }
    private static BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent == null)// || intent.getAction() != Intent.ACTION_BATTERY_CHANGED)
                return;

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            boolean present = intent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10;
            String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);

            Log.d("Hughie", "********** Battery Information ********** Action = " + intent.getAction() +
                    "\n     status = " + status + " " + BatteryStatus.getComment(status) +
                    "\n     health = " + health + " " + BatteryHealth.getComment(health) +
                    "\n     present = " + present + " " +
                    "\n     level = " + level + "" +
                    "\n     scale = " + scale + "" +
                    "\n     plugged = " + plugged + " " + BatteryPlugged.getComment(plugged) +
                    "\n     voltage = " + voltage + "mV" +
                    "\n     temperature = " + temperature + "°" +
                    "\n     technology = " + technology + ""
            );
        }
    };

    /**
     *  Intent.ACTION_BATTERY_CHANGED只能通过registerReceiver注册，
     *  不能通过AndroidManifest.xml注册，而且作为系统 protected intent ，
     *  只能通过被动获取，不能通过主动方式获取
     * */
    public static void registerReceiver(Context context) {
        /**
         * 因为ACTION_BATTERY_CHANGED是sticky-broadcast方式，可以不用设置接收器，
         * 且不会像一般的broadcast(通过registerReceiver方式注册而非配置文件)会在activity暂停之后失效，
         * 需要重新registerReceiver才能生效
         * */
        context.registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        ///<  sticky-intent可以通过此方式直接读取
//        Intent stickyIntent = context.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }

    public static void unregisterReceiver(Context context) {
        context.unregisterReceiver(batteryReceiver);
    }

}

/**
 *在Android中，BatteryService.java提供了电池相关的访问接口，实际上也是从系统文件中获取相关信息的，
 * 理论上可以直接从下述文件获取信息，而不需要通过Service-Intent-Broadcast的方式进行获取.
 #define AC_ONLINE_PATH "/sys/class/power_supply/ac/online"
 #define USB_ONLINE_PATH "/sys/class/power_supply/usb/online"
 #define BATTERY_STATUS_PATH "/sys/class/power_supply/battery/status"
 #define BATTERY_HEALTH_PATH "/sys/class/power_supply/battery/health"
 #define BATTERY_PRESENT_PATH "/sys/class/power_supply/battery/present"
 #define BATTERY_CAPACITY_PATH "/sys/class/power_supply/battery/capacity"
 #define BATTERY_VOLTAGE_PATH "/sys/class/power_supply/battery/batt_vol"
 #define BATTERY_TEMPERATURE_PATH "/sys/class/power_supply/battery/batt_temp"
 #define BATTERY_TECHNOLOGY_PATH "/sys/class/power_supply/battery/technology"
 * */
