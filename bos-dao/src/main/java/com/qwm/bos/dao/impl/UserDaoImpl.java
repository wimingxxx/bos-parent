package com.qwm.bos.dao.impl;

import com.qwm.bos.dao.IUserDao;
import com.qwm.bos.dao.base.impl.BaseDaoImpl;
import com.qwm.bos.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/11 下午10:03
 * @className: UserDaoImpl
 * @description:
 */

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
    /**
     * 获取用户
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        String hql = "FROM User u WHERE u.username=? AND u.password=?";
        List<User> list = (List<User>)getHibernateTemplate().find(hql,username,password);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
