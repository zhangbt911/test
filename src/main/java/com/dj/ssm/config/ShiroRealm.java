package com.dj.ssm.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import com.dj.ssm.utils.PasswordSecurityUtil;
import lombok.SneakyThrows;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义Realm
 */
@Component
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService;



    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 创建简单授权信息
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//        simpleAuthorInfo.addStringPermission("resource:add");
        //获取当前登录用户的信息
        Session session = SecurityUtils.getSubject().getSession();

        return simpleAuthorInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 得到用户名
        String username = (String) authenticationToken.getPrincipal();
        // 得到密码
        String password = new String((char[]) authenticationToken.getCredentials());
        try {
           QueryWrapper<User> queryUserName = new QueryWrapper();
            queryUserName.or(i -> i.eq("user_name", username)
                    .or().eq("email", username)
                    .or().eq("phone", username));
            User salt = userService.getOne(queryUserName);

           /* String md5Pwd = PasswordSecurityUtil.enCode32(user.getPassword());*/
            String newPwd = PasswordSecurityUtil.enCode32(password + salt.getSalt());

            QueryWrapper<User> queryWrapper = new QueryWrapper();
            //用户名/手机号/邮箱+密码登陆
            queryWrapper.or(i -> i.eq("user_name", username)
                    .or().eq("email", username)
                    .or().eq("phone", username));
            queryWrapper.eq("password", newPwd);
            User userOne = userService.getOne(queryWrapper);
            if (null == userOne){
 //               return new ResultModel<>().error("用户名或密码不正确");
                throw new AccountException("用户名或密码不正确");
            }
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("user", userOne);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AccountException(e.getMessage());
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
