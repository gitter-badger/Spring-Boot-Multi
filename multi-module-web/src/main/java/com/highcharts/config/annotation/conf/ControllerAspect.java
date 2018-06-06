package com.highcharts.config.annotation.conf;

import com.highcharts.common.base.Base;
import com.highcharts.common.exception.AdminException;
import com.highcharts.common.utils.JsonUtils;
import com.highcharts.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import java.lang.reflect.Method;

/**
 * @program: multi-module
 * @description:
 * @author: Brucezheng
 * @create: 2018-04-20 13:55
 **/
@Aspect
@Component
public class ControllerAspect extends Base {

    @Autowired
    private RedisService redisService;

  //  @Autowired
   // private LoginService loginService;

    private final static Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    /**
     * 定义切点
     */
    @Pointcut("execution(public * com.highcharts.web.*.*(..))")
    public void privilege() {
    }

    /**
     * 权限环绕通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @ResponseBody
    @Around("privilege()")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取访问目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        //得到方法的访问权限
        final String methodAccess = AnnotationParse.privilegeParse(targetMethod);

        //如果该方法上没有权限注解，直接调用目标方法
        if (StringUtils.isBlank(methodAccess)) {
            return joinPoint.proceed();
        } else {
            if (httpServletRequest.getRequestURI().equals("/login")){//拦截器--》到Controller（被该注解拦截）
                return joinPoint.proceed();
            }
            //获取当前用户的权限,这里是自定义的发那个发

            Cookie[] cookies = httpServletRequest.getCookies();//获取所有kookie值
            if (cookies.length <= 0) {
                throw new AdminException("401", "未登录");
            }
            String cookieVaule = null;
            //寻找匹配cookie值
            if (cookies.length > 0) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("jiabohui")) {
                        // cookieVaule= URLDecoder.decode(cookies[i].getValue(), "UTF-8");//也能取到cookie值。
                        String value = cookies[i].getValue();
                        cookieVaule = value;
                    }
                }
            }
            //判断cookie是否取到值。
            if (cookieVaule == null || cookieVaule == "") {
                //跳转到登录页面，把用户请求的url作为参数传递给登录页面。
                throw new AdminException("401", "未登录");
            }

            String uid = redisService.hget(cookieVaule, "uid");

           // Admin info = loginService.getInfo(uid);


            /*if (info == null) {
                throw new AdminException("401", "未登录");
            } else {
                httpServletRequest.getSession().setAttribute("user", info);
                logger.info("访问用户，{}", JsonUtils.objectToJson(info));
                return joinPoint.proceed();
            }*/

            return joinPoint.proceed();
            /*if(methodAccess.equals(currentUser.getRole().toString())){
                return joinPoint.proceed();
            }else {
                throw new LoginException("登录失败");
            }*/
        }
    }
}
