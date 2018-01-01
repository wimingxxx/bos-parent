package com.qwm.bos.service.impl;

import com.qwm.bos.dao.IRoleDao;
import com.qwm.bos.domain.Function;
import com.qwm.bos.domain.Role;
import com.qwm.bos.service.IRoleService;
import com.qwm.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 下午9:10
 * @className: RoleServiceImpl
 * @description:
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    /**
     * 保存角色 同时还需要管理权限
     *
     * @param role
     * @param functionIds
     */
    @Override
    public void save(Role role,String functionIds) {
        roleDao.save(role);
        //获取权限
        if(StringUtils.isNotBlank(functionIds)){
            //把id分隔出来
            String[] fIds = functionIds.split(",");
            //循环遍历,给角色设置权限
            //可以通过,每个id查询一次相关权限,然后设置给角色
            //但是角色中,只需要关联权限的id,所以我们自己创建一个就行了
            for (String fId : fIds) {
                //手动创建一个权限对象,设置id,对象状态为托管状态
                Function function = new Function(fId);
                //角色关联权限
                role.getFunctions().add(function);
            }
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        roleDao.pageQuery(pageBean);
    }

    /**
     * 查询全部角色
     *
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
