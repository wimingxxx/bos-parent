package com.qwm.bos.web.action;

import com.qwm.bos.domain.Staff;
import com.qwm.bos.service.IStaffService;
import com.qwm.bos.utils.PageBean;
import com.qwm.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/14 下午11:22
 * @className: StaffAction
 * @description:
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
    @Autowired
    private IStaffService staffService;

    /**
     * 添加取派员
     */
    public String add(){
        staffService.save(getModel());
        return LIST;
    }

    //属性驱动，接收页面提交的分页参数
    private int page;
    private int rows;

    /**
     * 分页查询
     */
    public String pageQuery() throws IOException {
        //参数封装
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPage(page);
        pageBean.setPageSize(rows);
        //创建离线提交查询对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
        pageBean.setDetachedCriteria(detachedCriteria);
        staffService.pageQuery(pageBean);

        //使用json-lib将PageBean对象转为json，通过输出流写回页面中
        //JSONObject---将单一对象转为json
        //JSONArray----将数组或者集合对象转为json
        //指定哪些属性不需要转json
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"currentPage","detachedCriteria","pageSize"});
        String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
        ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(json);
        return NONE;
    }


    //属性驱动，接收页面提交的ids参数
    private String ids;

    /**
     * 取派员批量删除
     */
    public String deleteBatch(){
        staffService.deleteBatch(ids);
        return LIST;
    }

    /**
     * 取派员批量还原
     */
    public String reductionBatch(){
        staffService.reductionBatch(ids);
        return LIST;
    }

    /**
     * 修改取派员信息
     */
    public String edit(){
        //显查询数据库，根据id查询原始数据
        Staff staff = staffService.findById(model.getId());
        //使用页面提交的数据进行覆盖
        staff.setName(model.getName());
        staff.setTelephone(model.getTelephone());
        staff.setHaspda(model.getHaspda());
        staff.setStandard(model.getStandard());
        staff.setStation(model.getStation());
        staffService.update(staff);
        return LIST;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
