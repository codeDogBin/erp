package com.my.erp.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.erp.sys.common.ActiverUser;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.domain.Permission;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.PermissionService;
import com.my.erp.sys.service.RoleService;
import com.my.erp.sys.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.*;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        ActiverUser activerUser = (ActiverUser) principals.getPrimaryPrincipal();
        User user = activerUser.getUser();

        ////获取权限
        List<String> permissions = activerUser.getPremissions();
        if (user.getType() == Constast.USER_TYPE_SUPER){
            info.addStringPermission("*:*");
        }else if(null != permissions && permissions.size()>0){
            info.addStringPermissions(permissions);
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (authenticationToken.getPrincipal() == null) {
            return null;
        }
        String loginname = (String)authenticationToken.getPrincipal();
        //条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //通过名字查询
        queryWrapper.eq("loginname", authenticationToken.getPrincipal().toString());
        User user = userService.getOne(queryWrapper);//数据库查询到对象
        //如果获取的登录对象不为空
        if (null!=user){
            //获取session集合
            Collection<Session> sessions =sessionDAO .getActiveSessions();
            //遍历session集合
            for (Session session: sessions) {
                System.out.println("USER_SESSION:"+session.getAttribute("USER_SESSION"));
                User sysUser = (User)session.getAttribute("USER_SESSION");
                // 如果session里面有当前登陆的，则证明是重复登陆的，则将其剔除
                if( sysUser != null ){
                    if( loginname.equals( sysUser.getLoginname() ) ){
                        //将这个时间设置为0
                        session.setTimeout(0);
                    }
                }
            }
            //创建一个带角色和权限的用户对象
            ActiverUser activerUser = new ActiverUser();
            //设置用户
            activerUser.setUser(user);
            //根据用户ID查询percode
            QueryWrapper<Permission> qw = new QueryWrapper<>();
            //设置只需要查询菜单
            qw.eq("type", Constast.TYPE_PERMISSION);
            //设置可以使用
            qw.eq("available",Constast.AVAILABLE_TRUE);
            //根据用户ID+角色+权限查询
            Integer uid = user.getId();
            //根据用户ID查询角色ID
            List<Integer> currentUserRoleIds = roleService.selectUserRoleIdsByUserId(uid);
            //根据角色获取权限和菜单ID
            Set<Integer> pids = new HashSet<>();
            for (Integer roleId : currentUserRoleIds) {
                List<Integer> permissionIds = roleService.selectRolePermissionByRole(roleId);
                pids.addAll(permissionIds);
            }
            List<Permission> list = new ArrayList<>();
            if(pids.size()>0){
                qw.in("id",pids);
                list  = permissionService.list(qw);
            }
            List<String> percodes = new ArrayList<>();
            for (Permission permission : list) {
                percodes.add(permission.getPercode());
            }
            //放入对象中
            activerUser.setPremissions(percodes);
            //设置盐
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
            //进行密码验证
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser,user.getPwd(),credentialsSalt,this.getName());

            return info;
        }
        return null;
    }
}
