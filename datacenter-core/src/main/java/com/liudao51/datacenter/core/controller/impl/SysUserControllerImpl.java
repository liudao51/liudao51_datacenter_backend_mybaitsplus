package com.liudao51.datacenter.core.controller.impl;

import com.liudao51.datacenter.common.annotation.RequestParamValid;
import com.liudao51.datacenter.common.constant.ErrorCode;
import com.liudao51.datacenter.common.page.Pager;
import com.liudao51.datacenter.common.util.DateX;
import com.liudao51.datacenter.common.util.StringX;
import com.liudao51.datacenter.core.controller.ISysUserController;
import com.liudao51.datacenter.core.entity.SysUser;
import com.liudao51.datacenter.core.exception.AppException;
import com.liudao51.datacenter.core.protocol.sys_user.*;
import com.liudao51.datacenter.core.response.ApiResponse;
import com.liudao51.datacenter.core.response.ApiResponseBody;
import com.liudao51.datacenter.core.service.ISysUserService;
import com.liudao51.datacenter.core.util.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(value = "系统用户相关", tags = {"系统用户相关接口"})
@RestController
@RequestMapping("/sys_user")
public class SysUserControllerImpl extends BaseControllerImpl implements ISysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/add")
    @ApiOperation(value = "用户新增")
    @RequestParamValid
    @SuppressWarnings("unchecked")
    public ApiResponseBody<Pager<SysUser>> add(AddReq req) throws Exception {
        Date date = DateX.getDate();
        Long dateTime = date.getTime();
        Long id = date.getTime();
        String handler = "admin";

        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setUserName(req.getUserName());
        sysUser.setPassword(ShiroUtil.encryptPassword(req.getPassword(), req.getUserName()));
        sysUser.setRealName(req.getRealName());
        sysUser.setMobile(req.getMobile());
        sysUser.setEmail(req.getEmail());
        sysUser.setDepartmentId(req.getDepartmentId());
        sysUser.setDepartmentName(req.getDepartmentName());
        sysUser.setStatus(1);
        sysUser.setRemark(req.getRemark());
        sysUser.setCreatedBy(handler);
        sysUser.setCreatedTime(dateTime);
        sysUser.setUpdatedBy(handler);
        sysUser.setUpdatedTime(dateTime);
        sysUser.setDeleted(0);
        Boolean isSucceed = sysUserService.add(sysUser);

        if (!isSucceed) {
            throw new AppException(ErrorCode.USER_ADD_ERROR.toValue(), ErrorCode.USER_ADD_ERROR.toCode());
        }

        return new ApiResponse().success();
    }

    @PostMapping("/update")
    @ApiOperation(value = "用户更新")
    @RequestParamValid
    @SuppressWarnings("unchecked")
    public ApiResponseBody<Pager<SysUser>> update(UpdateReq req) throws Exception {
        Date date = DateX.getDate();
        Long dateTime = date.getTime();
        Long id = req.getId();
        String handler = "admin";

        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setUserName(req.getUserName());

        if (!StringX.isEmpty(req.getPassword())) {
            sysUser.setPassword(ShiroUtil.encryptPassword(req.getPassword(), req.getUserName()));
        }

        sysUser.setRealName(req.getRealName());
        sysUser.setMobile(req.getMobile());
        sysUser.setEmail(req.getEmail());
        sysUser.setDepartmentId(req.getDepartmentId());
        sysUser.setDepartmentName(req.getDepartmentName());
        sysUser.setStatus(1);
        sysUser.setRemark(req.getRemark());
        sysUser.setCreatedBy(handler);
        sysUser.setCreatedTime(dateTime);
        sysUser.setUpdatedBy(handler);
        sysUser.setUpdatedTime(dateTime);
        sysUser.setDeleted(0);
        Boolean isSucceed = sysUserService.update(sysUser);

        if (!isSucceed) {
            throw new AppException(ErrorCode.USER_UPDATE_ERROR.toValue(), ErrorCode.USER_UPDATE_ERROR.toCode());
        }

        return new ApiResponse().success();
    }

    @PostMapping("/delete")
    @ApiOperation(value = "用户删除")
    @RequestParamValid
    @SuppressWarnings("unchecked")
    public ApiResponseBody delete(DeleteReq req) throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setId(req.getId());
        sysUser.setDeleted(1);
        Boolean isSucceed = sysUserService.delete(sysUser);

        if (!isSucceed) {
            throw new AppException(ErrorCode.USER_DELETE_ERROR.toValue(), ErrorCode.USER_DELETE_ERROR.toCode());
        }

        return new ApiResponse().success();
    }

    @PostMapping("/get")
    @ApiOperation(value = "查询用户对象")
    @RequestParamValid
    @SuppressWarnings("unchecked")
    public ApiResponseBody<SysUser> get(GetReq req) throws Exception {
        Map args = new HashMap<String, Object>();
        args.put("userName", req.getUserName());
        args.put("realName", req.getRealName());
        args.put("mobile", req.getMobile());
        args.put("email", req.getEmail());
        SysUser sysUser = sysUserService.selectOne(args);

        return new ApiResponse<SysUser>().success(sysUser);
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询用户列表")
    @RequestParamValid
    @SuppressWarnings("unchecked")
    public ApiResponseBody<Pager<SysUser>> list(ListReq req) throws Exception {
        Map args = new HashMap<String, Object>();
        args.put("pageNo", req.getPageNo());
        args.put("pageSize", req.getPageSize());
        args.put("userName", req.getUserName());
        args.put("realName", req.getRealName());
        args.put("mobile", req.getMobile());
        args.put("email", req.getEmail());
        Pager<SysUser> sysUserList = sysUserService.selectPage(args);

        return new ApiResponse<Pager<SysUser>>().success(sysUserList);
    }

    @PostMapping("/list/export")
    @ApiOperation(value = "查询用户列表-导出")
    @RequestParamValid
    @SuppressWarnings("unchecked")
    public ApiResponseBody listExport(ListReq req) throws Exception {

        return new ApiResponse<Pager<SysUser>>().success();
    }

    @PostMapping("/list/filter")
    @ApiOperation(value = "查询用户列表-下拉菜单")
    @RequestParamValid
    @SuppressWarnings("unchecked")
    public ApiResponseBody<Pager<SysUser>> listFilter(ListFilterReq req) throws Exception {
        Map args = new HashMap<String, Object>();
        args.put("pageNo", req.getPageNo());
        args.put("pageSize", req.getPageSize());
        args.put("userName", req.getUserName());
        args.put("realName", req.getRealName());
        args.put("email", req.getEmail());
        Pager<SysUser> sysUserListPage = sysUserService.selectPage(args);

        return new ApiResponse<Pager<SysUser>>().success(sysUserListPage);
    }
}
