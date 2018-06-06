package com.highcharts.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: Spring-Boot-Multi
 * @description: 用户
 * @author: Brucezheng
 * @create: 2018-03-29 15:21
 **/
@Data
@Accessors(chain = true)
public class User {
    private Integer id;
    private String username;
    private String password;
}
