package com.liudao51.datacenter.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liudao51.datacenter.common.page.Pager;

/**
 * 分页工具类
 */
public class PageUtil {
    @SuppressWarnings("unchecked")
    public static Pager mybatisPageToPager(IPage page) {
        return new Pager(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }
}