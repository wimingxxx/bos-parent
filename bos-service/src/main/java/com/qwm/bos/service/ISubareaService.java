package com.qwm.bos.service;

import com.qwm.bos.domain.Subarea;
import com.qwm.bos.utils.PageBean;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午9:02
 * @className: ISubareaService
 * @description:
 */
public interface ISubareaService {
    void save(Subarea model);

    void pageQuery(PageBean pageBean);

    List<Subarea> findAll();

    List<Subarea> findListNotAssociation();
}
