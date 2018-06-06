package com.highcharts.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常类
 *
 * @author Niu Li
 * @date 2016/8/10
 */

/**
 * 在异常抛出地方pageException.resolveException(request,response,"发生错误",e);
 * 如：
 * try {
 * throw new Exception("此处发生异常");
 * } catch (Exception e) {
 * pageException.resolveException(request,response,"发生未知错误02",e);
 * }
 * 配置文件没了,所以使用注解让spring管理
 */
@ControllerAdvice
public class PageException implements HandlerExceptionResolver {

    private Logger logger = LoggerFactory.getLogger(PageException.class);

    public PageException() {
        System.out.println("异常捕获初始化开始");
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest Request, HttpServletResponse Response, Object o, Exception ex) {
        logger.warn("==============异常开始========================================================");
        logger.error("异常:", ex);
        logger.warn("==============异常结束========================================================");
        ModelAndView mv = new ModelAndView();
        mv.addObject("error", ex.getMessage());
        mv.addObject("code", 401);
        mv.setViewName("error");
        return mv;
    }
}
