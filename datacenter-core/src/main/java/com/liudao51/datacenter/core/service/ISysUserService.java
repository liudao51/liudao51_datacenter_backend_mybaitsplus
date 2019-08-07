package com.liudao51.datacenter.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liudao51.datacenter.common.page.Pager;
import com.liudao51.datacenter.core.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户 服务类
 * </p>
 */
public interface ISysUserService {
    List<SysUser> selectList(Map args);

    Pager<SysUser> selectPage(Map args);
}
