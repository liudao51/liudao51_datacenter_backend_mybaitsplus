package com.liudao51.datacenter.core.controller;

import com.liudao51.datacenter.core.entity.SysUser;
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
public class SysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/list")
    @ApiOperation(value = "用户查询列表")
    public ApiResponseBody selectList() throws Exception {
        Map args = new HashMap<String, Object>();
        args.put("userName", "zhangsan");
        args.put("realName", "张三丰");

        List<SysUser> sysUserList = sysUserService.selectList(args);

        return new ApiResponse().success(sysUserList);
    }
}
