package com.liudao51.datacenter.common.page;

import java.util.List;

/**
 * 自定义分页对象
 */
public class Pager<T> {
    //已知数据
    private Long pageNo;  //当前页
    private Long pageSize;  //每页显示条数
    private Long totalRecord;  //总记录条数
    private List<T> records;  //将每页要显示的数据放在list集合中

    //需要计算得来
    private Long totalPage; //总页数
    private Long prePage; //前一页
    private Long nextPage; //后一页
    private Long startIndex; //记录索引开始位置（在数据库中要从第几行数据开始拿）

    public Pager(Long pageNo, Long pageSize, Long totalRecord) {
        this.init(pageNo, pageSize, totalRecord);
    }

    public Pager(Long pageNo, Long pageSize, Long totalRecord, List<T> records) {
        this.init(pageNo, pageSize, totalRecord);
        this.setRecords(records);
    }

    private void init(Long pageNo, Long pageSize, Long totalRecord) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;

        //总页数
        this.totalPage = (totalRecord % pageSize == 0) ? (totalRecord / pageSize) : (totalRecord / pageSize + 1);

        //前一页
        this.prePage = (pageNo - 1) > 1 ? (pageNo - 1) : 1;

        //后一页
        this.nextPage = (pageNo + 1) < totalRecord ? pageNo + 1 : totalRecord;

        //记录索引开始位置
        this.startIndex = (pageNo - 1) * pageSize;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getTotalRecord() {
        return totalRecord;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public Long getPrePage() {
        return prePage;
    }

    public Long getNextPage() {
        return nextPage;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}