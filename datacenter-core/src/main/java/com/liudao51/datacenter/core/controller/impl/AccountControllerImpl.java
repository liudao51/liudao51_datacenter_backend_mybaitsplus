package com.liudao51.datacenter.core.controller.impl;

import com.liudao51.datacenter.common.annotation.RequestParamValid;
import com.liudao51.datacenter.common.constant.ErrorCode;
import com.liudao51.datacenter.core.controller.IAccountController;
import com.liudao51.datacenter.core.entity.SysUser;
import com.liudao51.datacenter.core.exception.AppException;
import com.liudao51.datacenter.core.protocol.account.LoginReq;
import com.liudao51.datacenter.core.response.ApiResponse;
import com.liudao51.datacenter.core.response.ApiResponseBody;
import com.liudao51.datacenter.core.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(value = "系统账户相关", tags = {"系统账户相关接口"})
@RestController
@RequestMapping("/account")
public class AccountControllerImpl extends BaseControllerImpl implements IAccountController {
    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "用户登录")
    @GetMapping("/login")
    @RequestParamValid
    public ApiResponseBody login(LoginReq req) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(req.getUserName(), req.getPassword());
        try {
            subject.login(token);
        } catch (AppException ae) { //自定义检测规则异常
            return new ApiResponse().fail(ae.getMessage(), ae.getCode());
        } catch (Exception e) { // shiro检测异常
            return new ApiResponse().fail(ErrorCode.USER_USERNAME_PASSWORD_ERROR.toValue(), ErrorCode.USER_USERNAME_PASSWORD_ERROR.toCode());
        }

        //更新最后登录时间
        Map args = new HashMap<String, Object>();
        args.clear();
        args.put("userName", req.getUserName());
        SysUser sysUser = sysUserService.selectOne(args);
        if (sysUser == null) {
            return new ApiResponse().fail(ErrorCode.USER_NOT_EXITS_ERROR.toValue(), ErrorCode.USER_NOT_EXITS_ERROR.toCode());
        }
        sysUser.setLastLoginTime(new Date().getTime());
        sysUser.setLastLoginIp(this.getRequest().getRemoteAddr());
        boolean isUpdateSuccess = sysUserService.update(sysUser);

        //把当前登录用户保存到session中
        Session session = subject.getSession();
        session.setAttribute("user", sysUser);

        return new ApiResponse().success();
    }

    @ApiOperation(value = "用户登出")
    @GetMapping("/logout")
    public ApiResponseBody logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        return new ApiResponse().success();
    }

    @ApiOperation(value = "无权限时跳转")
    @GetMapping("/unauthorized")
    public ApiResponseBody notAuthorize() {
        return new ApiResponse().fail(ErrorCode.USER_NOT_AUTHORIZE_ERROR.toValue(), ErrorCode.USER_NOT_AUTHORIZE_ERROR.toCode());
    }

    @ApiOperation(value = "未登录时跳转")
    @GetMapping("/login/index")
    public ApiResponseBody loginIndex() {
        return new ApiResponse().fail(ErrorCode.USER_NOT_LOGIN_ERROR.toValue(), ErrorCode.USER_NOT_LOGIN_ERROR.toCode());
    }
}
