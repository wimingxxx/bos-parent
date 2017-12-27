package com.qwm.bos.service;

import com.qwm.bos.domain.Noticebill;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/27 下午10:00
 * @className: INoticebillService
 * @description:
 */
public interface INoticebillService {
    /**
     * 保存业务通知,还有尝试自动分单
     * @param model
     */
    void save(Noticebill model);
}
