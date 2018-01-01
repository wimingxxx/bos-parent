package com.qwm.bos.dao.impl;

import com.qwm.bos.dao.IWorkbillDao;
import com.qwm.bos.dao.base.impl.BaseDaoImpl;
import com.qwm.bos.domain.Workbill;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/27 下午10:21
 * @className: WorkbillDaoImpl
 * @description:
 */
@Repository
public class WorkbillDaoImpl extends BaseDaoImpl<Workbill> implements IWorkbillDao {
    /**
     * 查询新单
     *
     * @return
     */
    @Override
    public List<Workbill> findNewWorkbill() {
        String hql = "FROM Workbill r WHERE r.type = ?";
        List<Workbill> list = (List<Workbill>) getHibernateTemplate().find(hql,Workbill.TYPE_1);
        return list;
    }
}
