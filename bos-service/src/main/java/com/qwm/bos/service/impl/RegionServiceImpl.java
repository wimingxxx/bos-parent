package com.qwm.bos.service.impl;

import com.qwm.bos.dao.IRegionDao;
import com.qwm.bos.domain.Region;
import com.qwm.bos.service.IRegionService;
import com.qwm.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午12:57
 * @className: IRegionService
 * @description:
 */
@Controller
@Transactional
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private IRegionDao regionDao;

    /**
     * 批量保存数据
     * @param regionList
     */
    @Override
    public void saveBatch(List<Region> regionList) {
        //我们使用的数据的区域编号,作为id,如果使用 save方法,那么我们
        //第一次导入以后,后面再导入就会出现问题了,如果我们使用update方法
        //那么我们需要自己去判断,所以我们这里使用 saveOrUpdate方法
        for (Region region:regionList) {
            regionDao.saveOrUpdate(region);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public List<Region> findByQ(String q) {
        return regionDao.findByQ(q);
    }

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }
}
