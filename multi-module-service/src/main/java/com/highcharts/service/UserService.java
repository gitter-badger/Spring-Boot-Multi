package com.highcharts.service;

import com.highcharts.mapper.UserMapper;
import com.highcharts.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: Spring-Boot-Multi
 * @description: 用户信息
 * @author: Brucezheng
 * @create: 2018-03-29 15:20
 **/
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getByName(String username) {
        User userByUserName = userMapper.getUserByUserName(username);
        return userByUserName;
    }


}
