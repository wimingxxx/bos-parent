package com.qwm.bos.web.action;

import com.qwm.bos.domain.Decidedzone;
import com.qwm.bos.service.IDecidedzoneService;
import com.qwm.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/19 下午11:46
 * @className: DecidedzoneAction
 * @description:
 * 定区
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone>{

    @Autowired
    private IDecidedzoneService decidedzoneService;

    //属性驱动,使用数组来接收多个分区id
    private String[] subareaid;

    public void setSubareaid(String[] subareaid) {
        this.subareaid = subareaid;
    }

    public String add(){
        decidedzoneService.save(model,subareaid);
        return LIST;
    }

    public String pageQuery(){
        decidedzoneService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{
                "currentPage","detachedCriteria",
                "pageSize","subareas","decidedzones"
        });
        return NONE;
    }
}
