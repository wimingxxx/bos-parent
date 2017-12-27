package com.qwm.bos.web.action;

import com.qwm.bos.domain.Noticebill;
import com.qwm.bos.service.INoticebillService;
import com.qwm.bos.utils.Customer;
import com.qwm.bos.utils.ICustomerService;
import com.qwm.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/27 下午9:24
 * @className: NoticebillAction
 * @description:
 * 业务受理分单
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {

    /**
     * CRM 服务
     */
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private INoticebillService noticebillService;

    /**
     * 远程调用CRM服务,根据手机号查询客户信息
     * @return
     */
    public String findCustomerByTelephone(){
        String telephone = model.getTelephone();
        Customer customer = customerService.findCustomerByTelephone(telephone);
        java2Json(customer,new String[]{});
        return NONE;
    }

    /**
     * 保存一个业务通知单,并尝试自动分单
     * @return
     */
    public String add(){
        noticebillService.save(model);
        return "noticebill_add";
    }
}
