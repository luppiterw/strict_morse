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

    private static BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int status = intent.getIntExtra("status", 0);
            int health = intent.getIntExtra("health", 0);
            boolean present = intent.getBooleanExtra("present", false);
            int level = intent.getIntExtra("level", 0);
            int scale = intent.getIntExtra("scale", 0);
            int plugged = intent.getIntExtra("plugged", 0);
            int voltage = intent.getIntExtra("voltage", 0);
            int temperature = intent.getIntExtra("temperature", 0) / 10;
            String technology = intent.getStringExtra("technology");

            Log.d("Hughie", "**********Battery Information**********" +
                    "\n     status = " + status + " " + BatteryStatus.getComment(status) +
                    "\n     health = " + health + " " + BatteryHealth.getComment(health) +
                    "\n     present = " + present + " " +
                    "\n     level = " + level + "%" +
                    "\n     scale = " + scale + "%" +
                    "\n     plugged = " + plugged + " " + BatteryPlugged.getComment(plugged) +
                    "\n     voltage = " + voltage + "mV" +
                    "\n     temperature = " + temperature + "°" +
                    "\n     technology = " + technology + ""
            );
        }
    };

    public static void registerReceiver(Context context)
    {
        context.registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

}
