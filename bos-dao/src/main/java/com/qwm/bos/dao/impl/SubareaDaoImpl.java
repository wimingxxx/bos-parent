package com.qwm.bos.dao.impl;

import com.qwm.bos.dao.ISubareaDao;
import com.qwm.bos.dao.base.impl.BaseDaoImpl;
import com.qwm.bos.domain.Subarea;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午9:06
 * @className: SubareaDaoImpl
 * @description:
 */
@Repository
public class SubareaDaoImpl extends BaseDaoImpl<Subarea> implements ISubareaDao {
    /**
     * 查询区域分布图数据
     *
     * @return
     */
    @Override
    public List<Object> findSubareasGroupByProvince() {
        String hql = "SELECT r.province,count(*) FROM Subarea s LEFT OUTER JOIN s.region r GROUP BY r.province";
        return (List<Object>) getHibernateTemplate().find(hql);
    }
}
