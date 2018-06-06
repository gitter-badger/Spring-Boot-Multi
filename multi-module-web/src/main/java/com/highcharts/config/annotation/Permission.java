package com.highcharts.config.annotation;

import java.lang.annotation.*;

/**
 * @program: multi-module
 * @description: 权限限制（判断是不是后台接口）
 * @author: Brucezheng
 * @create: 2018-04-20 13:53
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String authorities() default "ADMIN";

}
