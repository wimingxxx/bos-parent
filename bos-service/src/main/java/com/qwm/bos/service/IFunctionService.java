package com.qwm.bos.service;

import com.qwm.bos.domain.Function;
import com.qwm.bos.utils.PageBean;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 下午6:37
 * @className: IFunctionService
 * @description:
 */
public interface IFunctionService  {
    /**
     * 查询所有权限
     * @return
     */
    public List<Function> findAll();

    /**
     * 保存权限
     * @param function
     */
    public void save(Function function);

    /**
     * 分页查询
     * @param pageBean
     */
    void pageQuery(PageBean pageBean);

    /**
     * 根据当前登陆人查询对应的菜单数据,返回json
     * @return
     */
    List<Function> findMenu();
}
