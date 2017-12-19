package com.qwm.bos.web.action;

import com.qwm.bos.domain.Staff;
import com.qwm.bos.service.IStaffService;
import com.qwm.bos.web.action.base.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

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

    /**
     * 分页查询
     */
    public String pageQuery() throws IOException {
        staffService.pageQuery(pageBean);
        java2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize"});
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


    /**
     * 获取所有未删除的取派员
     * @return
     */
    public String listajax(){
        List<Staff> list = staffService.findListNotDelete();
        java2Json(list,new String[]{"decidedzones"});
        return NONE;
    }
}
