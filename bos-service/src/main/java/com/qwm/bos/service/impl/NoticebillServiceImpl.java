package com.qwm.bos.service.impl;

import com.qwm.bos.dao.IDecidedzoneDao;
import com.qwm.bos.dao.INoticebillDao;
import com.qwm.bos.dao.IWorkbillDao;
import com.qwm.bos.domain.*;
import com.qwm.bos.service.INoticebillService;
import com.qwm.bos.utils.BOSUtils;
import com.qwm.bos.utils.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/27 下午10:01
 * @className: NoticebillServiceImpl
 * @description:
 */
@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService {

    @Autowired
    private INoticebillDao noticebillDao;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IDecidedzoneDao decidedzoneDao;

    @Autowired
    private IWorkbillDao workbillDao;

    /**
     * 保存业务通知,还有尝试自动分单
     * 根据取件地址,来获取定区id,通过定区id来自动分单
     * @param noticebill
     */
    @Override
    public void save(Noticebill noticebill) {
        User user = BOSUtils.getLoginUser();
        noticebill.setUser(user);//设置当前登录用户
        noticebillDao.save(noticebill);
        //获取当前用户的取件地址
        String pickAddress = noticebill.getPickaddress();
        //远程调用CRM服务,查询出取件地址对应的定区id
        String decidedzoneId = customerService.findDecidedzoneIdByAddress(pickAddress);
        //判断定区id是否为空,不为空自动分单,为空手动分单
        if(decidedzoneId!=null){
            //获取定区
            Decidedzone decidedzone = decidedzoneDao.findById(decidedzoneId);
            //获取取派员
            Staff staff = decidedzone.getStaff();
            //业务通知单关联取派员对象
            noticebill.setStaff(staff);
            //设置分单类型为自动分单
            noticebill.setOrdertype(Noticebill.ORDERTYPE_AUTO);
            //为取派员创建一个工单
            Workbill workbill = new Workbill();
            workbill.setAttachbilltimes(0);//追单次数
            workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));
            workbill.setNoticebill(noticebill);//工单关联通知单
            workbill.setPickstate(Workbill.PICKSTATE_NO);//取件状态
            workbill.setRemark(noticebill.getRemark());//备注信息
            workbill.setStaff(staff);//工单管理取派员
            workbill.setType(Workbill.TYPE_1);//工单类型
            workbillDao.save(workbill);//保存工单

            //TODO 调用短信平台,发送短信

        }else{
            //没有查询到定区id，不能完成自动分单
            noticebill.setOrdertype(Noticebill.ORDERTYPE_MAN);
        }
    }
}
