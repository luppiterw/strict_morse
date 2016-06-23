package vivisystem.com.test_HardWare;

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
public class CPUChecker
{
    public static final String PATH_SH_CAT = "/system/bin/cat";
    public static final String PATH_CPUFREQ = "/sys/devices/system/cpu/cpu0/cpufreq";
    public static final String PATH_SCALING_CPU_CUR_FREQ = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"; ///< 获取直接从该文件读取对应当前值

    public enum CpuInfoFreq
    {
        Min("cpuinfo_min_freq"),
//        Cur("cpuinfo_cur_freq"),  ///< 当前频率无法通过与max和min的通用方法获得
        Max("cpuinfo_max_freq");
        CpuInfoFreq(String freqName)
        {
            this.freqName = freqName;
        }
        public String getFreqName()
        {
            return freqName;
        }
        private String freqName;
    }
    ///< 获取CPU频率(单位KHZ)
    private static String getCpuFreq(CpuInfoFreq freq)
    {
        String result = "";
        ProcessBuilder cmd;
        try
        {
            String[] args = {PATH_SH_CAT, PATH_CPUFREQ + File.separator + freq.getFreqName()};
            cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[24];
            while(in.read(re) != -1)
            {
                result = result + new String(re);
            }
            in.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            result = "N/A";
        }

        return result.trim();
    }
    ///< 获取CPU最大频率(单位KHZ)
    public static String getMaxCpuFreq()
    {
        return getCpuFreq(CpuInfoFreq.Max);
    }
    ///< 获取CPU当前频率(单位KHZ)
    public static String getCurCpuFreq()
    {
//        return getCpuFreq(CpuInfoFreq.Cur);
        return getCurRealCpuFreq();
    }
    ///< 获取CPU最小频率(单位KHZ)
    public static String getMinCpuFreq()
    {
        return getCpuFreq(CpuInfoFreq.Min);
    }


    // 实时获取CPU当前频率（单位KHZ）
    public static String getCurRealCpuFreq() {
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
}
