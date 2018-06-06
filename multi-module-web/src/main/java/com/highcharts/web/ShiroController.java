package com.highcharts.web;

import com.highcharts.common.base.BaseController;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: Spring-Boot-Multi
 * @description: shiro测试
 * @author: Brucezheng
 * @create: 2018-03-29 16:47
 **/
@Controller
public class ShiroController extends BaseController {
    //跳转到登录表单页面
    @RequestMapping(value="login")
    public String login() {
        return "login";
    }



    @RequestMapping(value = "ajaxLogin", produces = JSON_UTF8)
    @ResponseBody
    public String submitLogin(String username, String password, Model model) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            // ShiroToken token = new ShiroToken(username, password);
            return success("success");
        } catch (Exception e) {
          logger.error("登陆失败");
          return error("failed");
        }
        //return error("failed");
    }
}
