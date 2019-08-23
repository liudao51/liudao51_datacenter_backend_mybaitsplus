package com.liudao51.datacenter.core.service;

import com.liudao51.datacenter.common.page.Pager;
import com.liudao51.datacenter.core.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 */
public interface ISysUserService extends IBaseService {
    boolean add(SysUser sysUser);

    boolean update(SysUser sysUser);

    boolean delete(SysUser sysUser);

    SysUser selectOne(Map args);

    List<SysUser> selectList(Map args);

    Pager<SysUser> selectListPage(Map args);
}
