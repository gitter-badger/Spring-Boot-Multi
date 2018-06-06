package com.highcharts.config.shiro;

import com.highcharts.pojo.User;
import com.highcharts.service.RoleService;
import com.highcharts.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: Spring-Boot-Multi
 * @description: realm
 * @author: Brucezheng
 * @create: 2018-03-27 11:37
 **/
public class MyRealm extends AuthorizingRealm {
    private final static Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("##################执行Shiro权限认证##################");
        User user = (User) principalCollection.getPrimaryPrincipal();
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> roleSet = new HashSet<>();
            roleSet.add("100002");
            info.setRoles(roleSet);

            Set<String> permissionSet = new HashSet<>();
            permissionSet.add("权限添加");
            info.setStringPermissions(permissionSet);
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        logger.info("用户验证执行 : " + token.getUsername());
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());
        User user = userService.getByName(username);
        if (user == null){
            logger.error("用户 { " + token.getUsername() + " } 不存在 ");
            throw new AccountException("账户不存在");
        }else {
            //TODO 验证通过 其他操作。如更新数据库
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        }
      /*  User user = userService.getByEmail(token.getUsername(), true);
        if (user == null) {
            logger.error("用户 { " + token.getUsername() + " } 不存在 ");
            throw new AccountException("账户不存在");
        }
        if (user.getStatus() == 0) {
            logger.error("用户 { " + token.getUsername() + " } 被禁止登录 ");
            throw new DisabledAccountException("账号已经禁止登录");
        } else {
            user.setUpdated(DateUtils.getNowTimestamp());
            user.setUpdatedAt(DateUtils.getNowFormatDate(null));
            System.out.println("效验更新前ROLE：" + user.getRole().getRId());
            userService.update(user, true, user.getId());
        }*/
       // return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
       // return null;
    }

    /**
     *  被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        //该句作用是重写shiro的密码验证，让shiro用我自己的验证
         setCredentialsMatcher(new CredentialsMatcher());
    }


}
