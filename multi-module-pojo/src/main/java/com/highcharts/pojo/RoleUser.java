package com.highcharts.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: Spring-Boot-Multi
 * @description: 角色权限中间表
 * @author: Brucezheng
 * @create: 2018-03-29 15:35
 **/
@Data
@Accessors(chain = true)
public class RoleUser {
    private Integer id;
    private String groleid;
    private String guserid;
}
