package com.liudao51.datacenter.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liudao51.datacenter.common.constant.RequestConstant;
import com.liudao51.datacenter.common.page.Pager;
import com.liudao51.datacenter.common.util.StringX;
import com.liudao51.datacenter.core.dao.ISysUserDao;
import com.liudao51.datacenter.core.entity.SysUser;
import com.liudao51.datacenter.core.service.ISysUserService;
import com.liudao51.datacenter.core.util.PageUtil;
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
        if (!StringX.isEmpty(args.get("mobile"))) {
            qw.eq("mobile", StringX.getString(args.get("mobile"), ""));
        }
        if (!StringX.isEmpty(args.get("email"))) {
            qw.eq("email", StringX.getString(args.get("email"), ""));
        }

        return sysUserDao.list(qw);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pager<SysUser> selectPage(Map args) {

        QueryWrapper<SysUser> qw = new QueryWrapper<>();

        if (!StringX.isEmpty(args.get("userName"))) {
            qw.eq("user_name", StringX.getString(args.get("userName"), ""));
        }
        if (!StringX.isEmpty(args.get("realName"))) {
            qw.eq("real_name", StringX.getString(args.get("realName"), ""));
        }
        if (!StringX.isEmpty(args.get("mobile"))) {
            qw.eq("mobile", StringX.getString(args.get("mobile"), ""));
        }
        if (!StringX.isEmpty(args.get("email"))) {
            qw.eq("email", StringX.getString(args.get("email"), ""));
        }

        Long pageNo = Long.valueOf(StringX.getString(args.get("pageNo"), RequestConstant.PAGE.PAGE_NO.toString()));
        Long pageSize = Long.valueOf(StringX.getString(args.get("pageSize"), RequestConstant.PAGE.PAGE_SIZE.toString()));
        IPage<SysUser> page = new Page(pageNo, pageSize);

        return PageUtil.mybatisPageToPager(sysUserDao.page(page, qw));
    }
}
