package com.highcharts.mapper;

import com.highcharts.pojo.Role;
import com.highcharts.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: Spring-Boot-Multi
 * @description: 用户信息
 * @author: Brucezheng
 * @create: 2018-03-29 15:21
 **/
@Mapper
public interface UserMapper {
    @Select(value = "select * from guser where username=#{username}")
    User getUserByUserName(String username);

    @Select(value = "select * from grole")
    List<Role> getrRole();
    /**
     *
     * @param id 用户id
     * @return
     */
    @Select(value = "select * from grole gr where gr.id in (select groleid from grole_guser wehere guserid=#{id})")
    List<Role> getRoleByUserId(Integer id);
}
