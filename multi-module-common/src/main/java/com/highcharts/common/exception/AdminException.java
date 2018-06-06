package com.highcharts.common.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: multi-module
 * @description:
 * @author: Brucezheng
 * @create: 2018-04-28 14:36
 **/
@Data
@Accessors(chain = true)
@ApiModel(value="AdminException对象",description="自定义未登录消息")
public class AdminException extends RuntimeException {

    public AdminException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    @ApiModelProperty(value = "响应码",name = "code",example = "401")
    private String code;
    @ApiModelProperty(value = "响应消息体",name = "msg",example = "未登录")
    private String msg;

}
