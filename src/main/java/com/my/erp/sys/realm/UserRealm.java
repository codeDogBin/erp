package com.my.erp.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.erp.sys.common.ActiverUser;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //通过名字查询
        queryWrapper.eq("loginname", authenticationToken.getPrincipal().toString());
        User user = userService.getOne(queryWrapper);//数据库查询到对象
        if (null!=user){
            //创建一个带角色和权限的用户对象
            ActiverUser activerUser = new ActiverUser();
            //设置用户
            activerUser.setUser(user);
            //设置盐
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
            ////进行密码验证
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser,user.getPwd(),credentialsSalt,this.getName());
            return info;
        }
        return null;
    }
}
