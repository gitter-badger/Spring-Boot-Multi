package com.highcharts.common.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 各种id生成策略
 * <p>Title: IDUtils</p>
 * <p>Description: </p>
 * <p></p>
 *
 * @author
 * @version 1.0
 * @date
 */
public class IDUtils {

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }

    /**
     * 商品id生成
     */
    public static long genItemId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = null;
        random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

    public static String genRandomChar() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] state = new byte[16];
        secureRandom.nextBytes(state);
        String s = Hex.encodeHexString(state);
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 100; i++) {
            byte[] state = new byte[16];
            secureRandom.nextBytes(state);
            String s = Hex.encodeHexString(state);
            System.out.println(s.toUpperCase());
        }
//            System.out.println(genItemId());
    }
}
