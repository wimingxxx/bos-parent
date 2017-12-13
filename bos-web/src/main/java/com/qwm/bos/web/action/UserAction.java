package com.qwm.bos.web.action;

import com.qwm.bos.domain.User;
import com.qwm.bos.service.IUserService;
import com.qwm.bos.utils.BOSUtils;
import com.qwm.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/11 下午10:13
 * @className: UserAction
 * @description:
 * 用户Action
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{

    //属性驱动，接收页面输入的验证码
    private String checkcode;
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    @Autowired
    private IUserService userService;

    /**
     * 用户登陆
     * @return
     */
    public String login(){
        //从Session中获取生成的验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //校验验证码是否正确
        if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
            //验证码正确
            User user = userService.login(model);
            if(user!=null){
                //登陆成功,将User对象,放入到session中,跳转到首页
                BOSUtils.setLoginUser(user);
                return HOME;
            }else{
                //登录失败，,设置提示信息，跳转到登录页面
                //输入的验证码错误,设置提示信息，跳转到登录页面
                this.addActionError("用户名或者密码输入错误！");
                return LOGIN;
            }
        }else{
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }

    /**
     * 用户注销
     * @return
     */
    public String logout(){
        BOSUtils.getSession().invalidate();
        return LOGIN;
    }
}