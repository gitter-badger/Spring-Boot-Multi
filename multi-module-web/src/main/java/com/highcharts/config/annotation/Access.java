package com.highcharts.config.annotation;

import java.lang.annotation.*;

/**
 * @program: multi-module
 * @description:
 * @author: Brucezheng
 * @create: 2018-04-20 11:46
 **/
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {
    String[] value() default {};

    String[] authorities() default {};

    String[] roles() default {};
}
