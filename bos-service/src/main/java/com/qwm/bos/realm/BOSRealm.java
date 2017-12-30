package com.qwm.bos.realm;

import com.qwm.bos.dao.IUserDao;
import com.qwm.bos.domain.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

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
        return null;
    }
}
