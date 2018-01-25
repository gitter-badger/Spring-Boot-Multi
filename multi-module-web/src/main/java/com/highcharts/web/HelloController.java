package com.highcharts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * <p>multi-module/com.highcharts.web</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-24 10:54
 **/
@Controller
public class HelloController {

    @RequestMapping(value = "/hello.html")
    public String hello01(Map<String, Object> map){
        map.put("name","brucezheng");
        return "hello";
    }


    @RequestMapping(value = "/helloFtl.html")
    public String hello02(Map<String, Object> map){
        map.put("hello","brucezheng");
        return "helloFtl";
    }
}
