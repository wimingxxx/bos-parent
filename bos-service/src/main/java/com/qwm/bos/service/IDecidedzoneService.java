package com.qwm.bos.service;

import com.qwm.bos.domain.Decidedzone;
import com.qwm.bos.utils.PageBean;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/19 下午11:53
 * @className: IDecidedzoneService
 * @description:
 */
public interface IDecidedzoneService {
    void save(Decidedzone model, String[] subareaid);

    void pageQuery(PageBean pageBean);
}
