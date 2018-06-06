package com.highcharts.common.utils;

/**
 * <p>BMap/com.highcharts.common.utils</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-31 10:21
 **/
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    protected static char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var1) {
            System.err.println(MD5Utils.class.getName() + "初始化失败，MessageDigest不支持MD5Util。");
            var1.printStackTrace();
        }

    }

    public MD5Utils() {
    }

    public static void main(String[] args) throws IOException {
/*        long begin = System.currentTimeMillis();
        File big = new File("D:/001/45.jpg");
        String md5 = getFileMD5String(big);
        long end = System.currentTimeMillis();
        System.out.println("md5:" + md5 + " time:" + (end - begin) / 1000L + "s");*/
    //37029f3c646ca4d471884ac903754946  e10adc3949ba59abbe56e057f20f883e

        //密码
        String m1 = MD5Utils.getMD5String("123456");
        //账号
        String m2 = MD5Utils.getMD5String("admin001");
        //密码md5+账号md5
        String m3 = MD5Utils.getMD5String(m1+m2);
        boolean b = MD5Utils.checkPassword(m1+m2, m3);
        System.out.println(b);
        System.out.println(m3);
        System.out.println(m1+m2);
    }

    public static String getFileMD5String(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(MapMode.READ_ONLY, 0L, file.length());
        messagedigest.update(byteBuffer);
        return a(messagedigest.digest());
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return a(messagedigest.digest());
    }

    private static String a(byte[] bytes) {
        return a(bytes, 0, bytes.length);
    }

    private static String a(byte[] bytes, int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;

        for (int l = m; l < k; ++l) {
            a(bytes[l], stringbuffer);
        }

        return stringbuffer.toString();
    }

    private static void a(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 240) >> 4];
        char c1 = hexDigits[bt & 15];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }
}

