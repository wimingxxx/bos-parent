package com.qwm.bos.web.action;

import com.qwm.bos.domain.Function;
import com.qwm.bos.service.IFunctionService;
import com.qwm.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 下午6:33
 * @className: FunctionAction
 * @description:
 * 权限管理
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

    @Autowired
    private IFunctionService functionService;

    /**
     * 查询所有权限,返回json
     * @return
     */
    public String listajax(){
        List<Function> list = functionService.findAll();
        java2Json(list,new String[]{"parentFunction","roles","children"});
        return NONE;
    }

    /**
     * 添加权限
     * @return
     */
    public String add(){
        functionService.save(model);
        return LIST;
    }

    /**
     * 分页查询
     * @return
     */
    public String pageQuery(){
        String page = model.getPage();
        //设置当前页
        pageBean.setCurrentPage( Integer.parseInt(page) );
        functionService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","parentFunction","roles","children"});
        return null;
    }
}
