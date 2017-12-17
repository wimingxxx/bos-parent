package com.qwm.bos.web.action;

import com.qwm.bos.domain.Subarea;
import com.qwm.bos.service.ISubareaService;
import com.qwm.bos.web.action.base.BaseAction;
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
        subareaService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return NONE;
    }
}
