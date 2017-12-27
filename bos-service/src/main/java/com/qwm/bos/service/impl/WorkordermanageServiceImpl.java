package com.qwm.bos.service.impl;

import com.qwm.bos.dao.IWorkordermanageDao;
import com.qwm.bos.domain.Workordermanage;
import com.qwm.bos.service.IWorkordermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/28 上午12:41
 * @className: WorkordermanageServiceImpl
 * @description:
 */
@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {

    @Autowired
    private IWorkordermanageDao workordermanageDao;

    @Override
    public void save(Workordermanage workordermanage) {
        workordermanageDao.save(workordermanage);
    }
}
