package com.highcharts.web;

import com.github.abel533.echarts.Option;
import com.github.pagehelper.PageHelper;
import com.highcharts.common.exception.PageException;
import com.highcharts.common.pojo.CustomResult;
import com.highcharts.common.utils.ConfUtil;
import com.highcharts.pojo.Girl;
import com.highcharts.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>multi-module/com.highcharts.web</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-24 09:19
 **/
@RestController
public class GirlController {

    @Autowired
    private GirlService girlService;

    @Autowired
    private PageException pageException;

    @GetMapping("/girl/{age}")
    public List<Girl> likeName(@PathVariable(value = "age") Integer age, HttpServletRequest request, HttpServletResponse response){
        if(age == -1){
            try {
                throw new Exception("发生未知错误");
            } catch (Exception e) {
                pageException.resolveException(request,response,"发生未知错误02",e);
            }

        }
       PageHelper.startPage(0,1);
        return girlService.likeName(age);
    }

    @PostMapping(value = "/girl/save")
    public String saveGirl(Girl girl){
        long l = girlService.saveGirl(girl);
        return String.valueOf(girl.getId())+ConfUtil.getProperty("girl");
    }

    @RequestMapping(value = "/medicine",produces = "application/json; charset=utf-8")
    public CustomResult getMedicineList(){
        Option option = girlService.selectRemoveCauses();
        return CustomResult.build(200,"success",option);
    }
}
