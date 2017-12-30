package com.qwm.bos.web.action;

import com.qwm.bos.domain.User;
import com.qwm.bos.service.IUserService;
import com.qwm.bos.utils.BOSUtils;
import com.qwm.bos.utils.MD5Utils;
import com.qwm.bos.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;

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
     * 用户登陆 使用Shiro框架提供的方式进行认证
     * @return
     */
    public String login(){
        //从Session中获取生成的验证码
        String validatecode = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
        //校验验证码是否正确
        if(StringUtils.isNotBlank(checkcode) && checkcode.equals(validatecode)){
            //验证码正确
            //使用Shiro框架踢提供的方式进行认证
            Subject subject = SecurityUtils.getSubject();//获取当前登录用户对象,现在状态为"未登录"
            //创建用户名密码令牌
            AuthenticationToken token = new UsernamePasswordToken(model.getUsername(), MD5Utils.md5(model.getPassword()));
            //登录
            try{
                //登录,如果错误抛出异常
                subject.login(token);
                //获取登录的用户,这个对象是我们在Realm中存进去的,所以这里能获取到
                User user = (User)subject.getPrincipal();
                //保存用户到Session中
                BOSUtils.setLoginUser(user);
                return HOME;
            }catch (UnknownAccountException e){
                //没有账号,那就说明用户名错误
                this.addActionError("用户名错误！");
                return LOGIN;
            }catch (IncorrectCredentialsException e){
                //认证的凭证错误,我们使用密码作为凭证,所以是密码错误
                this.addActionError("密码错误！");
                return LOGIN;
            } catch (Exception e){
                e.printStackTrace();
                return LOGIN;
            }
        }else{
            this.addActionError("输入的验证码错误");
            return LOGIN;
        }
    }

    /**
     * 用户登陆
     * @return
     */
    public String login_2(){
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

    /**
     * 修改密码
     * @return
     * @throws IOException
     */
    public String editPassword() throws IOException {
        String str = "1";
        //获取当前登录用户
        User user = BOSUtils.getLoginUser();
        try {
            userService.editPassword(user.getId(),model.getPassword());
        }catch (Exception e){
            str = "0";
            e.printStackTrace();
        }
        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().print(str);
        return NONE;
    }
}