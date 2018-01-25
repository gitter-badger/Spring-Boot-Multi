package com.highcharts.pojo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>spring-boot-mybatis/com.boy.zjh.pojo</p>
 *
 * @author Created by BruceZheng
 * @date 2018-01-20 15:28
 **/
public class Girl {
    private Integer id;
    private String size_cup;
    @JSONField(serialize = false)
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSize_cup() {
        return size_cup;
    }

    public void setSize_cup(String size_cup) {
        this.size_cup = size_cup;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
