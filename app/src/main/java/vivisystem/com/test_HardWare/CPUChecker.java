package vivisystem.com.test_HardWare;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hughie on 16/6/23.
 * Needn't to get the root permit ~~~
 */
public class CPUChecker {
    public static final String PATH_SH_CAT = "/system/bin/cat";
    public static final String PATH_CPUFREQ = "/sys/devices/system/cpu/cpu0/cpufreq";
    public static final String PATH_SCALING_CPU_CUR_FREQ = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"; ///< 获取直接从该文件读取对应当前值

    public enum CpuInfo {
        Min("cpuinfo_min_freq"),
//        Cur("cpuinfo_cur_freq"),  ///< 当前频率无法通过与max和min的通用方法获得
        Cur("scaling_cur_freq"),
        Max("cpuinfo_max_freq"),
        AvailableFrequencies("scaling_available_frequencies");
        CpuInfo(String freqName) {
            this.freqName = freqName;
        }
        public String getFreqName() {
            return freqName;
        }
        private String freqName;
    }
    ///< 获取CPU频率(单位KHZ)
    private static String getCpuFreq(CpuInfo freq) {
        String result = "";
        ProcessBuilder cmd;
        try {
            String[] args = {PATH_SH_CAT, PATH_CPUFREQ + File.separator + freq.getFreqName()};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[100];//new byte[24];
            while(in.read(re) != -1) {
                result = result + new String(re);
            }
            in.close();
        } catch (IOException ex){
            ex.printStackTrace();
            result = "N/A";
        }

        return result.trim();
    }
    ///< 获取CPU最大频率(单位KHZ)
    public static String getMaxCpuFreq() {
        return getCpuFreq(CpuInfo.Max);
    }
    ///< 获取CPU当前频率(单位KHZ)
    public static String getCurCpuFreq() {
        return getCpuFreq(CpuInfo.Cur);
//        return getScalingRealCpuFreq();
    }
    ///< 获取CPU最小频率(单位KHZ)
    public static String getMinCpuFreq() {
        return getCpuFreq(CpuInfo.Min);
    }

    public static String getScalingAvailableFrequencies() {
        return getCpuFreq(CpuInfo.AvailableFrequencies);
    }
    public static String[] getScalingAvailableFrequencyArray(){
        return getScalingAvailableFrequencies().split(" ");
    }

    public static String getCPUName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 实时获取CPU当前频率（单位KHZ）
    public static String getScalingRealCpuFreq() {
        String result = "N/A";
        try {
            FileReader fr = new FileReader(PATH_SCALING_CPU_CUR_FREQ);
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            result = text.trim();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void print()
    {
        Log.d("Hughie", "**********CPU信息列表 打印开始**********" +
                "\n     CPU名称 = " + getCPUName() +
                "\n     最小硬频率(KHZ) = " + getMinCpuFreq() +
                "\n     最大硬频率(KHZ) = " + getMaxCpuFreq() +
                "\n     当前压缩频率(KHZ) = " + getCurCpuFreq() +
                "\n     支持压缩频率(KHZ) = " + getScalingAvailableFrequencyArray().length + " (" + CPUChecker.getScalingAvailableFrequencies() + ")"  +


                ""
        );
    }
}

/**
 *TIPS 01:
 * 前缀cpuinfo代表的是cpu硬件上支持的频率，而scaling前缀代表的是可以通过CPUFreq系统用软件进行调节时所支持的频率.
 * cpuinfo_cur_freq代表通过硬件实际上读到的频率值，而scaling_cur_freq则是软件当前的设置值，多数情况下这两个值是一致的，
 * 但是也有可能因为硬件的原因，有微小的差异。scaling_available_frequencies会输出当前软件支持的频率值.
 * */

/**
 * TIPS 02:
 * 多个cpu会生成对应的cpu0,cpu1...cpuN目录，对应各自的cpu信息
 * */
