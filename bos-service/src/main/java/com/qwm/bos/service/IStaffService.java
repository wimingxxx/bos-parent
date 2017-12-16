package com.qwm.bos.service;

import com.qwm.bos.domain.Staff;
import com.qwm.bos.utils.PageBean;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/14 下午11:09
 * @className: IStaffService
 * @description:
 */
public interface IStaffService {
    void save(Staff staff);

    void pageQuery(PageBean pageBean);

    void deleteBatch(String ids);

    void reductionBatch(String ids);

    Staff findById(String id);

    void update(Staff staff);
}
