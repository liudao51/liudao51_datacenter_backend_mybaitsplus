package com.liudao51.datacenter.core.controller.impl;

import com.liudao51.datacenter.common.annotation.ReqValidAnnotation;
import com.liudao51.datacenter.core.controller.ISysUserController;
import com.liudao51.datacenter.core.entity.SysUser;
import com.liudao51.datacenter.core.protocol.sys_user.ListSysUserReq;
import com.liudao51.datacenter.core.response.ApiResponse;
import com.liudao51.datacenter.core.response.ApiResponseBody;
import com.liudao51.datacenter.core.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(value = "系统用户相关", tags = {"系统用户相关接口"})
@RestController
@RequestMapping("/sys_user")
public class SysUserController extends BaseController implements ISysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/list")
    @ApiOperation(value = "用户查询列表")
    @ReqValidAnnotation
    public ApiResponseBody selectList(ListSysUserReq req) throws Exception {

        Map args = new HashMap<String, Object>();

        args.clear();
        args.put("pageNo", req.getPageNo());
        args.put("pageSize", req.getPageSize());
        args.put("userName", req.getUserName());
        args.put("realName", req.getRealName());
        args.put("adress", req.getMobile());
        args.put("email", req.getEmail());
        List<SysUser> sysUserList = sysUserService.selectList(args);

        return new ApiResponse().success(sysUserList);
    }
}
