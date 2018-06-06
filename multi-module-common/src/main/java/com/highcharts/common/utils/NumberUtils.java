package com.highcharts.common.utils;

/**
 * @program: multi-module
 * @description:
 * @author: Brucezheng
 * @create: 2018-05-25 10:29
 **/
public class NumberUtils {

    public static boolean isNumber(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNumber(Integer str) {
        String str2 = String.valueOf(str);
        for (int i = str2.length(); --i >= 0; ) {
            if (!Character.isDigit(str2.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
