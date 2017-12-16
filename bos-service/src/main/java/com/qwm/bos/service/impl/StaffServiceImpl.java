package com.qwm.bos.service.impl;

import com.qwm.bos.dao.IStaffDao;
import com.qwm.bos.domain.Staff;
import com.qwm.bos.service.IStaffService;
import com.qwm.bos.utils.PageBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/14 下午11:20
 * @className: StaffServiceImpl
 * @description:
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

    @Autowired
    private IStaffDao staffDao;

    @Override
    public void save(Staff staff) {
        staffDao.save(staff);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    /**
     * 取派员批量删除
     * 逻辑删除，将deltag改为1
     */
    @Override
    public void deleteBatch(String ids) {
        if(StringUtils.isNotBlank(ids)){
            String[] staffIds = ids.split(",");
            for (String id : staffIds) {
                staffDao.executeUpdate("staff.delete", id);
            }
        }
    }

    /**
     * 取派员批量 还原
     * 逻辑删除，将deltag改为1
     */
    @Override
    public void reductionBatch(String ids) {
        if(StringUtils.isNotBlank(ids)){
            String[] staffIds = ids.split(",");
            for (String id : staffIds) {
                staffDao.executeUpdate("staff.reduction", id);
            }
        }
    }

    @Override
    public Staff findById(String id) {
        return staffDao.findById(id);
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }
}
