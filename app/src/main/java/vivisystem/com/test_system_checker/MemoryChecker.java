package vivisystem.com.test_system_checker;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by hughie on 16/6/23.
 */
public class MemoryChecker {

    /**
     * 获取总的内存信息
     * */
    public static void getTotalMemory() {
        String str1 = "/proc/meminfo";
        String str2="";
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                Log.d("Hughie", "       " + str2);
            }
        } catch (IOException e) {
        }
    }

    /**
     * 获取ROM大小
     * */
    public static long[] getRomMemroy() {
        long[] romInfo = new long[2];
        //Total rom memory
        romInfo[0] = getTotalInternalMemorySize();

        //Available rom memory
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        romInfo[1] = blockSize * availableBlocks;
        getVersion();
        return romInfo;
    }

    /**
     * 获取
     * */
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    /**
     * 获取系统版本
     * */
    public static String[] getVersion(){
        String[] version={"null","null","null","null"};
        String str1 = "/proc/version";
        String str2;
        String[] arrayOfString;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            version[0]=arrayOfString[2];//KernelVersion
            localBufferedReader.close();
        } catch (IOException e) {
        }
        version[1] = Build.VERSION.RELEASE;// firmware version
        version[2]=Build.MODEL;//model
        version[3]=Build.DISPLAY;//system version
        return version;
    }

    public static String[] getOtherInfo(Context context){
        String[] other={"null","null"};
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if(wifiInfo.getMacAddress()!=null){
            other[0]=wifiInfo.getMacAddress();
        } else {
            other[0] = "Fail";
        }
        other[1] = getTimes(context);
        return other;
    }
    private static String getTimes(Context context) {
        long ut = SystemClock.elapsedRealtime() / 1000;
        if (ut == 0) {
            ut = 1;
        }
        int m = (int) ((ut / 60) % 60);
        int h = (int) ((ut / 3600));
        return m + "月 " + h + "时";
    }
    public static void print(Context context)
    {
        getTotalMemory();
        Log.d("Hughie", "**********Memory信息列表 打印开始**********" +
                "\n     RomMemory[0] = "+getRomMemroy()[0] + "; RomMemory[1] = " + getRomMemroy()[1] +
                "\n     Version  Kernel = " + getVersion()[0] + "; FirmWare = " + getVersion()[1] + "; Model = " + getVersion()[2] + "; System = " + getVersion()[3] +
                "\n     MacAddress =  " + getOtherInfo(context)[0] + "; ElapseTimeAfterBoot = " + getOtherInfo(context)[1] +
                ""
        );

    }
}
