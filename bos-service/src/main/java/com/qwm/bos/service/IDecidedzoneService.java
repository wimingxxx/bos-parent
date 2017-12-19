package com.qwm.bos.service;

import com.qwm.bos.domain.Decidedzone;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/19 下午11:53
 * @className: IDecidedzoneService
 * @description:
 */
public interface IDecidedzoneService {
    void save(Decidedzone model, String[] subareaid);
}
