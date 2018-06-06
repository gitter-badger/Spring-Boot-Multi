package com.highcharts.web;

import com.highcharts.common.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Spring-Boot-Multi
 * @description:
 * @author: Brucezheng
 * @create: 2018-06-06 17:14
 **/
@RestController
@Api(value = "Swagger测试", description = "Swagger测试描述")
public class SwaggerTestController extends BaseController {
    @ApiOperation(value = "swagger test1接口", notes = "test1接口", httpMethod = "GET", produces = JSON_UTF8)
    @RequestMapping(value = "/swagger/test.do", produces = JSON_UTF8, method = {RequestMethod.GET, RequestMethod.POST})
    public String test1() {
        return success();
    }
}
