package com.liudao51.datacenter.core.service;

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
}
