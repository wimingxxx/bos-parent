package com.qwm.bos.web.action;

import com.qwm.bos.domain.Region;
import com.qwm.bos.domain.Subarea;
import com.qwm.bos.service.ISubareaService;
import com.qwm.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午8:55
 * @className: SubareaAction
 * @description:
 * 分区管理
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaService;

    /**
     * 添加分区
     * @return
     */
    public String add(){
        subareaService.save(model);
        return LIST;
    }


    /**
     * 分页查询
     * @return
     */
    public String pageQuery(){
        DetachedCriteria dc = pageBean.getDetachedCriteria();
        String addresskey = model.getAddresskey();
        //动态添加过滤条件
        if(StringUtils.isNotBlank(addresskey)){
            //添加过滤条件，根据地址关键字模糊查询
            dc.add(Restrictions.like("addresskey","%"+addresskey+"%"));
        }
        Region region = model.getRegion();
        if(region!=null) {
            String province = region.getProvince();
            String city = region.getCity();
            String district = region.getDistrict();
            //添加过滤条件，根据省份模糊查询-----多表关联查询，使用别名方式实现
            //参数一：分区对象中关联的区域对象属性名称
            //参数二：别名，可以任意
            dc.createAlias("region","r");
            if(StringUtils.isNotBlank(province)){
                dc.add(Restrictions.like("r.province", "%"+province+"%"));
            }
            if(StringUtils.isNotBlank(city)){
                dc.add(Restrictions.like("r.city", "%"+city+"%"));
            }
            if(StringUtils.isNotBlank(district)){
                dc.add(Restrictions.like("r.district", "%"+district+"%"));
            }
        }
        subareaService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return NONE;
    }
}
