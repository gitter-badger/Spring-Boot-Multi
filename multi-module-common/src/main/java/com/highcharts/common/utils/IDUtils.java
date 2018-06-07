package com.highcharts.common.utils;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.Random;

/**
 * id生成策略
 * <p>Title: IDUtils</p>
 * <p>Description: </p>
 * <p></p>
 *
 * @author
 * @version 1.0
 * @date
 */
public class IDUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(IDUtils.class);
    /**
     * 锁
     */
    private static final Object ID_LOCK = new Object();
    /**
     * 当前秒数
     */
    private static long CURRENT_SECOND = System.currentTimeMillis() / 1000L;
    private static int ID = 0;

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
    /**
     * 获取唯一一个id
     *
     * @return long
     */
    public static long getId() {
        int tempId;
        long tempCurSec = System.currentTimeMillis() / 1000L;
        synchronized (ID_LOCK) {
            ID += 1;
            tempId = ID;
            int i = 65000;
            if (ID > i) {
                ID = 0;
                CURRENT_SECOND += 1L;
            }
            if (tempCurSec > CURRENT_SECOND) {
                CURRENT_SECOND = tempCurSec;
            } else {
                tempCurSec = CURRENT_SECOND;
            }
        }
        return ((tempCurSec) << 16 | tempId & 0xFFFF);
    }

    /**
     * 获取随机16位
     * @return
     */
    public static String genRandomChar() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] state = new byte[16];
        secureRandom.nextBytes(state);
        String s = Hex.encodeHexString(state);
        return s.toUpperCase();
    }
    public static void main(String[] args) {

        LOGGER.info(String.valueOf(Integer.MAX_VALUE / (365 * 24 * 60 * 60)));
        LOGGER.info(Integer.toBinaryString((int) (System.currentTimeMillis() / 1000)));
    }
}
