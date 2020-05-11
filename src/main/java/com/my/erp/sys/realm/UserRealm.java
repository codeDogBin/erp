package com.my.erp.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.erp.sys.common.ActiverUser;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.domain.Permission;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.PermissionService;
import com.my.erp.sys.service.RoleService;
import com.my.erp.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            ////进行密码验证
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser,user.getPwd(),credentialsSalt,this.getName());
            return info;
        }
        return null;
    }
}
