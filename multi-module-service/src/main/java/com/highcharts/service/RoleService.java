package com.highcharts.service;

import com.highcharts.mapper.UserMapper;
import com.highcharts.pojo.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: Spring-Boot-Multi
 * @description: 角色
 * @author: Brucezheng
 * @create: 2018-03-29 15:50
 **/
@Service
public class RoleService {
    @Resource
    private UserMapper userMapper;

    public List<Role> getrRole() {
        return userMapper.getrRole();
    }

    public List<Role> getRoleByUserId(Integer id) {
        return userMapper.getRoleByUserId(id);
    }
}
