package com.liudao51.datacenter.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class SysUserServiceImpl extends BaseServiceImpl implements ISysUserService {

    @Autowired
    private ISysUserDao sysUserDao;

    /**
     * 把Map参数转换为QueryWrapper
     */
    private QueryWrapper<SysUser> getWrapper(Map args) {
        return this.getWrapper(args, false);
    }

    private QueryWrapper<SysUser> getWrapper(Map args, Boolean withDelete) {
        QueryWrapper<SysUser> qw = new QueryWrapper<SysUser>();
        //删除标识
        if (!withDelete) {
            qw.eq("deleted", 0);
        }

        //其他查询条件
        if (!StringX.isEmpty(args.get("user_name"))) {
            qw.eq("user_name", StringX.getString(args.get("user_name"), ""));
        }
        if (!StringX.isEmpty(args.get("real_name"))) {
            qw.eq("real_name", StringX.getString(args.get("real_name"), ""));
        }
        if (!StringX.isEmpty(args.get("mobile"))) {
            qw.eq("mobile", StringX.getString(args.get("mobile"), ""));
        }
        if (!StringX.isEmpty(args.get("email"))) {
            qw.eq("email", StringX.getString(args.get("email"), ""));
        }
        if (!StringX.isEmpty(args.get("status"))) {
            qw.eq("status", StringX.getString(args.get("status"), ""));
        }

        return qw;
    }

    public boolean add(SysUser sysUser) {
        return sysUserDao.save(sysUser);
    }

    public boolean update(SysUser sysUser) {
        return sysUserDao.updateById(sysUser);
    }

    public boolean delete(SysUser sysUser) {
        return sysUserDao.removeById(sysUser);
    }

    public SysUser selectOne(Map args) {
        QueryWrapper<SysUser> qw = this.getWrapper(args);

        return sysUserDao.getOne(qw);
    }

    public List<SysUser> selectList(Map args) {
        QueryWrapper<SysUser> qw = this.getWrapper(args);

        return sysUserDao.list(qw);
    }

    @SuppressWarnings("unchecked")
    public Pager<SysUser> selectListPage(Map args) {
        QueryWrapper<SysUser> qw = this.getWrapper(args);
        Page page = new Page<SysUser>(PageUtil.getPageNo(args.get("page_no")), PageUtil.getPageSize(args.get("page_size")));

        return PageUtil.mybatisPageToPager(sysUserDao.page(page, qw));
    }
}
