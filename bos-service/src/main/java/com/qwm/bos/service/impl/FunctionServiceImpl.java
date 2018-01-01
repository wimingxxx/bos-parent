package com.qwm.bos.service.impl;

import com.qwm.bos.dao.IFunctionDao;
import com.qwm.bos.domain.Function;
import com.qwm.bos.domain.User;
import com.qwm.bos.service.IFunctionService;
import com.qwm.bos.utils.BOSUtils;
import com.qwm.bos.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 下午6:38
 * @className: FunctionServiceImpl
 * @description:
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {

    @Autowired
    private IFunctionDao functionDao;

    /**
     * 查询所有权限
     *
     * @return
     */
    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }

    /**
     * 保存权限
     *
     * @param function
     */
    @Override
    public void save(Function function) {
        //如果没有选择功能节点,那么数据是空字符串,不是null.
        //添加到数据库中,就会报错,因为没有id为字符串的权限,
        // 所以这里需要处理
        Function parentFunction = function.getParentFunction();
        //不等于null ,可以处理伪造请求的情况
        if(parentFunction !=null && parentFunction.getId().equals("") ){
            function.setParentFunction(null);
        }
        functionDao.save(function);
    }

    /**
     * 分页查询
     *
     * @param pageBean
     */
    @Override
    public void pageQuery(PageBean pageBean) {
        functionDao.pageQuery(pageBean);
    }

    /**
     * 根据当前登陆人查询对应的菜单数据,返回json
     *
     * @return
     */
    @Override
    public List<Function> findMenu() {
        List<Function> list = null;
        //获取当前的用户
        User user = BOSUtils.getLoginUser();
        if(user.getUsername().equals("admin")){
            //如果是超级管理员,那么获取所有的菜单
            list = functionDao.findAllMenu();
        }else{
            list = functionDao.findMenuByUserId(user.getId());
        }
        return list;
    }
}
