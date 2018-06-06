package com.highcharts.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: Spring-Boot-Multi
 * @description: 路经转发
 * @author: Brucezheng
 * @create: 2018-03-27 11:25
 **/
@Controller
public class PageController {
    @RequestMapping(value = "/{page}")
    public String page(@PathVariable String page){
        return page;
    }
}
