package com.qwm.bos.utils;

import com.qwm.bos.domain.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/13 下午9:04
 * @className: BOSUtils
 * @description:
 * 工具类
 */
public class BOSUtils {

    private static final String LOGINUSER = "loginUser";

    /**
     * 获取Session对象
     * @return
     */
    public static HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }

    /**
     * 获取登录的对象
     * @return
     */
    public static User getLoginUser(){
        return (User)getSession().getAttribute(LOGINUSER);
    }

    /**
     * 保存登录对象
     * @param user
     */
    public static void setLoginUser(User user){
        getSession().setAttribute(LOGINUSER,user);
    }
}
