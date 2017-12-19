package com.qwm.bos.service.impl;

import com.qwm.bos.dao.IDecidedzoneDao;
import com.qwm.bos.dao.ISubareaDao;
import com.qwm.bos.domain.Decidedzone;
import com.qwm.bos.domain.Subarea;
import com.qwm.bos.service.IDecidedzoneService;
import com.qwm.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/19 下午11:53
 * @className: DecidedzoneServiceImpl
 * @description:
 * 定区管理
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService{
    @Autowired
    private IDecidedzoneDao decidedzoneDao;
    @Autowired
    private ISubareaDao subareaDao;

    /**
     * 添加定区
     * @param model
     * @param subareaid
     */
    @Override
    public void save(Decidedzone model, String[] subareaid) {
        decidedzoneDao.save(model);
        for (String subid: subareaid) {
            Subarea subarea = subareaDao.findById(subid);
            //一方（定区）已经放弃维护外键权利，只能由多方（分区）负责维护
            subarea.setDecidedzone(model);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        decidedzoneDao.pageQuery(pageBean);
    }
}
