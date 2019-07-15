package com.liudao51.datacenter.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liudao51.datacenter.common.util.StringX;
import com.liudao51.datacenter.core.dao.ISysUserDao;
import com.liudao51.datacenter.core.entity.SysUser;
import com.liudao51.datacenter.core.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户服务实现类
 * </p>
 */
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private ISysUserDao sysUserDao;

    @Override
    public List<SysUser> selectList(Map args) {

        QueryWrapper<SysUser> qw = new QueryWrapper<>();

        if (!StringX.isEmpty(args.get("userName"))) {
            qw.eq("user_name", StringX.getString(args.get("userName"), ""));
        }
        if (!StringX.isEmpty(args.get("realName"))) {
            qw.eq("real_name", StringX.getString(args.get("realName"), ""));
        }
        if (!StringX.isEmpty(args.get("mobileNumber"))) {
            qw.eq("mobile_number", StringX.getString(args.get("mobileNumber"), ""));
        }
        if (!StringX.isEmpty(args.get("emailAddress"))) {
            qw.eq("email_address", StringX.getString(args.get("emailAddress"), ""));
        }

        List<SysUser> sysUserList = sysUserDao.list(qw);

        return sysUserList;
    }

}
