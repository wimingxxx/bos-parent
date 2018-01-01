package com.qwm.bos.service.impl;

import com.qwm.bos.dao.IUserDao;
import com.qwm.bos.domain.Role;
import com.qwm.bos.domain.User;
import com.qwm.bos.service.IUserService;
import com.qwm.bos.utils.MD5Utils;
import com.qwm.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/11 下午10:16
 * @className: UserServiceImpl
 * @description:
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    /**
     * 用户登录
     * @param model
     * @return
     */
    @Override
    public User login(User model) {
        //使用MD5加密密码
        String password = MD5Utils.md5(model.getPassword());
        return userDao.findUserByUsernameAndPassword(model.getUsername(),password);
    }

    @Override
    public void editPassword(String id, String password) {
        password = MD5Utils.md5(password);
        userDao.executeUpdate("user.editpassword",password,id);
    }

    /**
     * 保存用户
     *
     * @param user
     * @param roleIds
     */
    @Override
    public void save(User user, String[] roleIds) {
        String password = MD5Utils.md5(user.getPassword());
        user.setPassword(password);
        userDao.save(user);
        if(roleIds!=null && roleIds.length>0){
            for (String roleId : roleIds) {
                Role role = new Role(roleId);
                user.getRoles().add(role);
            }
        }
    }

    /**
     * 分页查询
     *
     * @param pageBean
     */
    @Override
    public void pageQuery(PageBean pageBean) {
        userDao.pageQuery(pageBean);
    }
}
