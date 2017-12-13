package com.qwm.bos.service;

import com.qwm.bos.domain.User;

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
}