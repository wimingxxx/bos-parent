package com.qwm.bos.utils;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/16 下午5:19
 * @className: PageBean
 * @description:
 * 封装分页属性
 */
public class PageBean {
    private int currentPage;//当前页码
    private int pageSize;//每页显示的记录数
    private DetachedCriteria detachedCriteria;//查询条件
    private int total;//总记录数
    private List rows;//当前页需要展示的数据集合

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public DetachedCriteria getDetachedCriteria() {
        return detachedCriteria;
    }

    public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
