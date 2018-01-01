package com.qwm.bos.dao.impl;

import com.qwm.bos.dao.IFunctionDao;
import com.qwm.bos.dao.base.impl.BaseDaoImpl;
import com.qwm.bos.domain.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 下午6:39
 * @className: FunctionDaoImpl
 * @description:
 */
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
    public List<Function> findAll() {
        String hql = "FROM Function f WHERE f.parentFunction IS NULL";
        List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 根据用户id获取对应的权限
     *
     * @param uId
     * @return
     */
    @Override
    public List<Function> findFunctionListByUserId(String uId) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id=?";
        List<Function> list = (List<Function>)getHibernateTemplate().find(hql,uId);
        return list;
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    @Override
    public List<Function> findAllMenu() {
        String hql = "SELECT DISTINCT f FROM Function f WHERE f.generatemenu = '1' ORDER BY f.zindex ASC";
        List<Function> list = (List<Function>)getHibernateTemplate().find(hql);
        return list;
    }

    /**
     * 根据用户id查询菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<Function> findMenuByUserId(String userId) {
        String hql = "SELECT DISTINCT f FROM Function f LEFT JOIN f.roles r LEFT OUTER JOIN r.users u WHERE u.id=?"
                +" AND f.generatemenu = '1' ORDER BY f.zindex ASC";
        List<Function> list = (List<Function>)getHibernateTemplate().find(hql,userId);
        return list;
    }
}
