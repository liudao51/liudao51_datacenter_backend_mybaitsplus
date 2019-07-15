package com.liudao51.datacenter.core.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liudao51.datacenter.core.dao.IBaseDao;
import com.liudao51.datacenter.core.mapper.IBaseMapper;
import org.springframework.stereotype.Repository;

/**
 * class:
 * <p>
 * Created by jewel on 2019/7/15.
 */
public class BaseDaoImpl<M extends IBaseMapper<T>, T> extends ServiceImpl<M, T> implements IBaseDao<T> {
    // add new method...
}
