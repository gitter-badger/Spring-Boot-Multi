package com.highcharts.common.utils;


import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author brucezheng
 */
public class EmojiFilter {
    /**
     * 编码
     * @param nickname
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String nickname) throws UnsupportedEncodingException {

        return URLEncoder.encode(nickname, "utf-8");

    }

    /**
     * 解码
     * @param nickname
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String nickname) throws UnsupportedEncodingException {
        return URLDecoder.decode(nickname, "utf-8");
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return false;

        }
        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }
        return false;

    }


    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));

    }

    /**
     * 38	     * 过滤emoji 或者 其他非文字类型的字符
     * 39	     * @param source
     * 40	     * @return
     * 41
     */


    public static String filterEmoji(String source) {

        if (!containsEmoji(source)) {
            //如果不包含，直接返回
            return source;
        }
        //到这里铁定包含
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            } else {
            }
        }
        if (buf == null) {
            //如果没有找到 emoji表情，则返回源字符串
            return source;
        } else {
            //这里的意义在于尽可能少的toString，因为会重新生成字符串
            if (buf.length() == len) {
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }
    public static void main(String args[]){
      /*  String s = null;
        try {
            s = encode("\\xE2\\x9C\\xA8\\xE2\\x9C\\xA8...");
            String sss = decode(s);
            System.out.println(s);
            System.out.println(sss);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        StringBuilder tempName  = new StringBuilder();
        for (int i = 0; i <10 ; i++) {
            for (int ii = 0; ii < 4; ii++) {
                tempName.append(ChineseName.getChinese());
            }
            System.out.println(tempName);
            tempName.delete(0,tempName.toString().length());

        }


    }
}

