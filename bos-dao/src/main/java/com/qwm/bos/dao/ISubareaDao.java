package com.qwm.bos.dao;

import com.qwm.bos.dao.base.IBaseDao;
import com.qwm.bos.domain.Subarea;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午9:05
 * @className: ISubareaDao
 * @description:
 */
public interface ISubareaDao extends IBaseDao<Subarea> {
    /**
     * 查询区域分布图数据
     * @return
     */
    List<Object> findSubareasGroupByProvince();
}