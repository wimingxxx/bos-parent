package com.qwm.bos.service.impl;

import com.qwm.bos.dao.ISubareaDao;
import com.qwm.bos.domain.Subarea;
import com.qwm.bos.service.ISubareaService;
import com.qwm.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午9:03
 * @className: SubareaServiceImpl
 * @description:
 */
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService{

    @Autowired
    private ISubareaDao subareaDao;
    @Override
    public void save(Subarea model) {
        subareaDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public List<Subarea> findAll() {
        return subareaDao.findAll();
    }
}
