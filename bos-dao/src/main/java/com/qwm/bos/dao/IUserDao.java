package com.qwm.bos.dao;

import com.qwm.bos.dao.base.IBaseDao;
import com.qwm.bos.domain.User;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/11 下午10:02
 * @className: IUserDao
 * @description:
 */
public interface IUserDao extends IBaseDao<User>{
    /**
     * 获取用户
     * @param username
     * @param password
     * @return
     */
    public User findUserByUsernameAndPassword(String username, String password);
}

