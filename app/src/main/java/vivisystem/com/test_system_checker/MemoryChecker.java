package vivisystem.com.test_system_checker;

import android.util.Log;

import java.io.BufferedReader;
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
                Log.d("Hughie", "---" + str2);
            }
        } catch (IOException e) {
        }
    }

    public static void print()
    {
        Log.d("Hughie", "**********Memory信息列表 打印开始**********" +
                "\n     "+

                ""
        );
        getTotalMemory();
    }
}
