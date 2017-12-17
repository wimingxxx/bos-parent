package com.qwm.bos.dao.impl;

import com.qwm.bos.dao.IRegionDao;
import com.qwm.bos.dao.base.impl.BaseDaoImpl;
import com.qwm.bos.domain.Region;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午1:03
 * @className: RegionDaoImpl
 * @description:
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {
    /**
     * 根据q参数进行模糊查询
     */
    @Override
    public List<Region> findByQ(String q) {
        String hql = "FROM Region r WHERE r.shortcode LIKE ? "
                + "	OR r.citycode LIKE ? OR r.province LIKE ? "
                + "OR r.city LIKE ? OR r.district LIKE ?";
        List<Region> list = (List<Region>)getHibernateTemplate().
                find(hql,"%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
        return list;
    }
}