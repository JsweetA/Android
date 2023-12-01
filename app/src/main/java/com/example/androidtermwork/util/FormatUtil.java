package com.example.androidtermwork.util;

import java.text.DecimalFormat;

public class FormatUtil
{
    /**
     * 将数值格式化成指定格式
     * @param num
     * @param pattern
     * @param <T>
     * @return
     */
    public static <T> String simpleFormat(T num, String pattern){
        return new DecimalFormat(pattern).format(num);
    }

    /**
     * 以默认的”###,###“的格式将数值格式化成字符串
     * @param num
     * @param <T>
     * @return
     */
    public static <T> String simpleFormat(T num){
        return simpleFormat(num, "###,###");
    }

    /**
     * 获取数据只精确到百位的值
     *
     * @return 数据精确到百位的值
     */
    public static long getFloorCount(long count){
        return count/100*100;
    }
}
