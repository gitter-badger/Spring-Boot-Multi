package com.highcharts.common.exception;


import com.highcharts.common.pojo.CustomResult;
import com.highcharts.common.utils.BaseResp;
import com.highcharts.common.utils.JsonUtils;
import com.highcharts.common.utils.ResultStatus;
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
	/**
	 * 全局日志
	 */
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
	public BaseResp<?> defaultExceptionHandler(HttpServletRequest req, Exception e){
		BaseResp baseResp = new BaseResp();
		logger.warn("==============异常开始========================================================");
		if (e.getMessage() == null && e.getCause() != null && e.getCause().getMessage() != null){
			logger.error(e.getCause().getMessage());
			baseResp.setMessage(e.getCause().getMessage());
			baseResp.setCode(ResultStatus.http_status_not_found.getErrorCode());
			baseResp.setData("");
			return baseResp;
		}else {
			logger.error(e.getMessage());
		}
		logger.warn("==============异常结束========================================================");
		baseResp.setMessage(e.toString());
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			baseResp.setCode(ResultStatus.http_status_not_found.getErrorCode());
		} else {
			baseResp.setCode(ResultStatus.http_status_internal_server_error.getErrorCode());
		}
		baseResp.setData("");
		return baseResp;
	}

}
