package com.qwm.bos.service;

import com.qwm.bos.domain.Region;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午12:57
 * @className: IRegionService
 * @description:
 */
public interface IRegionService {
    void saveBatch(List<Region> regionList);
}
