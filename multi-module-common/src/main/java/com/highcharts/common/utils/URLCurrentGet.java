package com.highcharts.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>BMap/com.highcharts.common.utils</p>
 *
 * @author Created by BruceZheng
 * @date 2018-03-06 10:02
 **/
public class URLCurrentGet {

    public static String getURl(HttpServletRequest request){
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String basePath = scheme + "://" + serverName + ":" + port + path;
        return basePath;
    }
}
