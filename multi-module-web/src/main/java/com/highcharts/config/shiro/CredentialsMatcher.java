package com.highcharts.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: Spring-Boot-Multi
 * @description: 重写Shiro验证器
 * @author: Brucezheng
 * @create: 2018-03-29 15:06
 **/
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    private final static Logger LOGGER = LoggerFactory.getLogger(CredentialsMatcher.class);
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken authcToken = (UsernamePasswordToken) token;
        LOGGER.info("++++校验账号密码++++");
        //Object tokenCredentials = EncryptUtils.md5(authcToken.getUsername()+String.valueOf(authcToken.getPassword()));
        //Object accountCredentials = getCredentials(info);
        String username = authcToken.getUsername();
        char[] password = authcToken.getPassword();
        String s = String.valueOf(password);
        return s.equals("123456");
    }
}
