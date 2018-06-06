package com.highcharts.common.exception;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

/**
 * @program: multi-module
 * @description: RetryException
 * @author: Brucezheng
 * @create: 2018-05-15 14:21
 **/
@Builder
@Getter
@ApiModel(value="RetryException对象",description="自定义重试对象")
public class RetryException extends RuntimeException {
    private String status;
    private String msg;
}
