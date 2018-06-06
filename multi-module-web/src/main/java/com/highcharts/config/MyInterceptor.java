package com.highcharts.config;

import com.highcharts.common.utils.URLCurrentGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>Spring-Boot-Demo/cn.mrdear.conf</p>
 * 拦截器
 * @author Created by BruceZheng
 * @date 2018-01-22 14:18
 **/
public class MyInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String basePath = URLCurrentGet.getURl(request);
        request.setAttribute("basePath", basePath);
       /* logger.info("------preHandle------");
        *//**
         * 对来自后台的请求统一进行日志处理
         *//*
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        System.out.println(request.getParameterMap());
        logger.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s", url, method, uri, queryString));
        //获取session
        HttpSession session = request.getSession(true);
        //判断用户ID是否存在，不存在就跳转到登录界面
        if (session.getAttribute("userId") == null) {
            logger.info("------:跳转到login页面！");
            response.sendRedirect(request.getContextPath() + "/hello02?name=qwew");
            return false;
        } else {
            session.setAttribute("userId", session.getAttribute("userId"));
            return true;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
