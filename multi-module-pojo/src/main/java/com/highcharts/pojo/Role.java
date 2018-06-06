package com.highcharts.pojo;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * @program: Spring-Boot-Multi
 * @description: 角色
 * @author: Brucezheng
 * @create: 2018-03-29 15:31
 **/
@Data
@Accessors(chain = true)
public class Role {
    private Integer id;
    private String rolename;
}
