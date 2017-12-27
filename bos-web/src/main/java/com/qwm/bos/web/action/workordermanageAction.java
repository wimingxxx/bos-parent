package com.qwm.bos.web.action;

import com.qwm.bos.domain.Workordermanage;
import com.qwm.bos.service.IWorkordermanageService;
import com.qwm.bos.web.action.base.BaseAction;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/28 上午12:34
 * @className: workordermanageAction
 * @description:
 * 工作单 管理
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

    @Autowired
    private IWorkordermanageService workordermanageService;

    public String add() throws IOException {
        String f="1";
        try{
            workordermanageService.save(model);
        }catch (Exception e){
            e.printStackTrace();
            f = "0";
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(f);
        return NONE;
    }

}
