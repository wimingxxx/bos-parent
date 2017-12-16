package com.qwm.bos.dao.base;

import com.qwm.bos.utils.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/11 下午9:51
 * @className: IBaseDao
 * @description:
 * 持久层通用接口
 */
public interface IBaseDao<T> {
    public void save(T entity);
    public void delete(T entity);
    public void update(T entity);
    public T findById(Serializable id);
    public List<T> findAll();
    public void executeUpdate(String queryName,Object... objects);
    public void pageQuery(PageBean pageBean);
}
