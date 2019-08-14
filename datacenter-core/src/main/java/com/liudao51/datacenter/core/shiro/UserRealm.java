package com.liudao51.datacenter.core.shiro;

import com.liudao51.datacenter.common.constant.ErrorCode;
import com.liudao51.datacenter.core.entity.SysUser;
import com.liudao51.datacenter.core.exception.AppException;
import com.liudao51.datacenter.core.service.ISysUserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * class: 自定义Realm类
 * <p>
 * 描述: 用于自定义的身份/权限认证操作
 */
@Slf4j
@Data
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private ISysUserService sysUserService;


    /**
     * 执行认证逻辑(即 验证用户输入的账号/密码是否正确)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        /*
         可以从UsernamePasswordToken对象拿到登陆时的用户名和密码的几种方法:
         token.getUsername()  //获得用户名 String
         token.getPrincipal() //获得用户名 Object
         token.getPassword()  //获得密码 char[]
         token.getCredentials() //获得密码 Object
         */
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; //token里的密码是用户输入的明文密码
        String tokenUserName = token.getUsername();
        String tokenPassword = new String(token.getPassword());

        //从数据库获取对应用户名密码的用户
        Map args = new HashMap<String, Object>();
        args.clear();
        args.put("userName", tokenUserName);
        SysUser user = sysUserService.selectOne(args);

        //判断用户名
        if (null == user) {
            throw new AppException(ErrorCode.USER_NOT_EXITS_ERROR.toValue(), ErrorCode.USER_NOT_EXITS_ERROR.toCode());
        }

        //判断密码
        //方式1.使用自定义判断密码是否正确
        /*if (shiroUtil.encryptPassword(tokenPassword, tokenUserName) != user.getPassword()) {
            throw new AppException(ErrorCode.USER_PASSWORD_ERROR.toValue(), ErrorCode.USER_PASSWORD_ERROR.toCode());
        }*/
        //方式2.使用shiro函数判断密码是否正确
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(user.getUserName()), this.getName());

        return info;
    }

    /**
     * 执行授权逻辑(即 验证用户所具有哪些角色,角色所具有哪些权限)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }
}
