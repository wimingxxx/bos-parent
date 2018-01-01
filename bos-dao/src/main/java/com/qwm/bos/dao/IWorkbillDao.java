package com.qwm.bos.dao;

import com.qwm.bos.dao.base.IBaseDao;
import com.qwm.bos.domain.Workbill;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/27 下午10:20
 * @className: IWorkbillDao
 * @description:
 */
public interface IWorkbillDao extends IBaseDao<Workbill> {
    /**
     * 查询新单
     * @return
     */
    List<Workbill> findNewWorkbill();
}
