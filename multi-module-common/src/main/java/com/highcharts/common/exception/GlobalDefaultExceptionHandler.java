package com.highcharts.common.exception;


import com.highcharts.common.pojo.CustomResult;
import com.highcharts.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * 1、新建一个Class,这里取名为GlobalDefaultExceptionHandler
 * 2、在class上添加注解，@ControllerAdvice;
 * 3、在class中添加一个方法
 * 4、在方法上添加@ExcetionHandler拦截相应的异常信息；
 * 5、如果返回的是View -- 方法的返回值是ModelAndView;
 * 6、如果返回的是String或者是Json数据，那么需要在方法上添加@ResponseBody注解.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	//LoginException
	private Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

	@Order(1)
	@ExceptionHandler(value = AdminException.class)
	public ModelAndView myErrorHandler(AdminException ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("code", ex.getCode());
		modelAndView.addObject("msg", ex.getMsg());
		return modelAndView;
	}
	@Order(100)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String defaultExceptionHandler(HttpServletRequest req, Exception e){

		logger.warn("==============异常开始========================================================");
		if (e.getMessage() == null && e.getCause() != null && e.getCause().getMessage() != null){
			logger.error(e.getCause().getMessage());
			return JsonUtils.objectToJson(CustomResult.build(500,e.getCause().getMessage()));
		}else {
			logger.error(e.getMessage());
		}
		logger.warn("==============异常结束========================================================");
		//是返回的String.
		//ModelAndView -- 介绍 模板引擎...?
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName(viewName);
	//	CustomResult.build(500,e.getMessage());
		return JsonUtils.objectToJson(CustomResult.build(500,"服务器繁忙，请稍后再试！"));
	}

}
