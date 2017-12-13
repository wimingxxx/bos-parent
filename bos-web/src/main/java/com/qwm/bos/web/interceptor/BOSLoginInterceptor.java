package com.qwm.bos.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.qwm.bos.domain.User;
import com.qwm.bos.utils.BOSUtils;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/13 下午9:01
 * @className: BOSLoginInterceptor
 * @description: 登录的拦截器,没有登陆跳到登录页面上
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor{

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //取出登录对象,如果登录对象不存在,那么跳转到登陆页面,如果存在,那么放行
        User user = BOSUtils.getLoginUser();
        if(user!=null){
            actionInvocation.invoke();
        }
        return "login";
    }
}
