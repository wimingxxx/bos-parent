package com.qwm.bos.service;

import com.qwm.bos.domain.User;
import com.qwm.bos.utils.PageBean;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/11 下午10:16
 * @className: IUserService
 * @description:
 * 用户Service
 */
public interface IUserService {
    public User login(User model);

    public void editPassword(String id, String password);

    /**
     * 保存用户
     * @param model
     * @param roleIds
     */
    void save(User model, String[] roleIds);

    /**
     * 分页查询
     * @param pageBean
     */
    void pageQuery(PageBean pageBean);
}