package com.qwm.bos.web.action;

import com.qwm.bos.domain.Role;
import com.qwm.bos.service.IRoleService;
import com.qwm.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 下午9:05
 * @className: RoleAction
 * @description:
 * 角色管理
 */
@Controller
public class RoleAction extends BaseAction<Role> {

    @Autowired
    private IRoleService roleService;

    /**
     * 属性驱动的方式,获取权限的ids
     */
    private String functionIds;

    public void setFunctionIds(String functionIds) {
        this.functionIds = functionIds;
    }

    /**
     * 添加角色
     * @return
     */
    public String add(){
        roleService.save(model,functionIds);
        return LIST;
    }

    /**
     * 分页查询
     * @return
     */
    public String pageQuery(){
        roleService.pageQuery(pageBean);
        java2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","functions","users"});
        return NONE;
    }

    /**
     * 查询所有的角色,返回json
     * @return
     */
    public String listajax(){
        List<Role> list = roleService.findAll();
        java2Json(list,new String[]{"functions","users"});
        return NONE;
    }
}
