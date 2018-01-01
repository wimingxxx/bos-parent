package com.qwm.bos.realm;

import com.qwm.bos.dao.IFunctionDao;
import com.qwm.bos.dao.IUserDao;
import com.qwm.bos.domain.Function;
import com.qwm.bos.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/31 上午12:54
 * @className: BOSRealm
 * @description:
 * 认证和授权的类
 */
public class BOSRealm extends AuthorizingRealm {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IFunctionDao functionDao;

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //这里我们完成登录的认证
        //获取到用户名密码令牌,就是UserAction中创建
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //获取界面输入的用户名
        String username = token.getUsername();
        //通过dao查询出对应的用户
        User user = userDao.findUserByUsername(username);
        if(user==null){
            //用户不存在
            return null;
        }
        //封装简单认证信息对象
        //第一个参数是任意对象,这里出入user对象,为了登录方法中能获取到,
        //第二个参数认证凭证,这里使用密码作为凭证
        //第三个参数区分而已
        AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),user.getUsername());
        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登陆用户对象
        User user = (User) principalCollection.getPrimaryPrincipal();
        //根据当前登陆的用户查询数据库,获取实际对应的权限
        List<Function> list = null;
        if(user.getUsername().equals("admin")){
            //admin查询所有权限 这里是查询出所有权限,而不是查询出树形结构的所有权限,所以不使用之前的findAll方法
            DetachedCriteria dc = DetachedCriteria.forClass(Function.class);
            list = functionDao.findByCriteria(dc);
        }else{
            //其他用户,根据用户id查询对应的权限
            list = functionDao.findFunctionListByUserId(user.getId());
        }
        if(list==null)
            return info;
        //循环添加权限
        for (Function function : list) {
            info.addStringPermission(function.getCode());
        }
        return info;
    }
}
