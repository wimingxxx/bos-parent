package com.qwm.bos.web.action;

import com.qwm.bos.domain.Decidedzone;
import com.qwm.bos.domain.Subarea;
import com.qwm.bos.service.IDecidedzoneService;
import com.qwm.bos.service.ISubareaService;
import com.qwm.bos.utils.Customer;
import com.qwm.bos.utils.ICustomerService;
import com.qwm.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

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

    //注入crm代理对象
    @Autowired
    private ICustomerService proxy;

    @Autowired
    private IDecidedzoneService decidedzoneService;

    @Autowired
    private ISubareaService subareaService;

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

    /**
     * 调用远程crm服务,获取未关联到定区的客户
     * @return
     */
    public String findListNotAssociation(){
        List<Customer> list = proxy.findListNotAssociation();
        java2Json(list,new String[]{});
        return NONE;
    }

    /**
     * 调用远程crm服务,获取已经关联到定区的客户
     * @return
     */
    public String findListHasAssociation(){
        String id = model.getId();
        List<Customer> list = proxy.findListHasAssociation(id);
        java2Json(list,new String[]{});
        return NONE;
    }

    private List<Integer> customerIds;

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    /**
     * 调用远程crm服务,将客户管理到分区
     * @return
     */
    public String assigncustomerstodecidedzone(){
        proxy.assigncustomerstodecidedzone(model.getId(),customerIds);
        return LIST;
    }

    //属性驱动的方式,接收定区id
    private String decidedzoneId;

    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }

    /**
     * 根据定区id获取分区
     * @return
     */
    public String findListByDecidedzoneId(){
        List<Subarea> list = subareaService.findListByDecidedzoneId(decidedzoneId);
        java2Json(list,new String[]{"decidedzone","subareas"});
        return NONE;
    }
}
