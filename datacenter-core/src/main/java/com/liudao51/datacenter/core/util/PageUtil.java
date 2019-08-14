package com.liudao51.datacenter.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liudao51.datacenter.common.page.Pager;
import com.liudao51.datacenter.common.util.StringX;

/**
 * 分页工具类
 */
public class PageUtil {
    @SuppressWarnings("unchecked")
    public static Pager mybatisPageToPager(IPage page) {
        return new Pager(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    public static Long getPageNo(Object val) {
        Long pageNo = 1L;

        if (!StringX.isEmpty(val)) {
            try {
                pageNo = Long.valueOf(StringX.getString(val, "1"));
                if (pageNo < 0) {
                    pageNo = 1L;
                }
            } catch (Exception e) {
                pageNo = 1L;
            }
        }

        return pageNo;
    }

    public static Long getPageSize(Object val) {
        Long pageSize = 1L;

        if (!StringX.isEmpty(val)) {
            try {
                pageSize = Long.valueOf(StringX.getString(val, "1"));
                if (pageSize < 0) {
                    pageSize = 1L;
                }
            } catch (Exception e) {
                pageSize = 1L;
            }
        }

        return pageSize;
    }
}