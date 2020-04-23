package com.my.erp.sys.service.impl;

import com.my.erp.sys.domain.Role;
import com.my.erp.sys.mapper.RoleMapper;
import com.my.erp.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-21
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public boolean removeById(Serializable id) {
        RoleMapper roleMapper = this.getBaseMapper();
        //根据角色ID删除sys_role_permission中的数据
        roleMapper.deleteRolePermissionByRid(id);
        //根据角色ID删除sys_role_user中的数据
        roleMapper.deleteRoleUserByRId(id);
        return super.removeById(id);
    }

    public List<Integer> selectRolePermissionByRole(Integer roleId){

        RoleMapper roleMapper = this.getBaseMapper();
        return roleMapper.selectRolePermissionByRole(roleId);
    }

    @Override
    public void saveRolePermission(Integer roleId, Integer[] ids) {
        RoleMapper roleMapper = this.getBaseMapper();
        //先将数据库中的权限都删掉
        roleMapper.deleteRolePermissionByRid(roleId);
        //然后将数据存入数据库
        if(ids!=null&&ids.length>0) {
            for (Integer pid : ids) {
                roleMapper.saveRolePermission(roleId, pid);
            }
        }

    }

    @Override
    public List<Integer> selectUserRoleIdsByUserId(Integer id) {
        RoleMapper roleMapper = this.getBaseMapper();
        //先将数据库中的权限都删掉
        return roleMapper.selectUserRoleIdsByUserId(id);
    }

}
