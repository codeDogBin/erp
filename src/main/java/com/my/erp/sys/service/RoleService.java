package com.my.erp.sys.service;

import com.my.erp.sys.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bin
 * @since 2020-04-21
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据角色ID查询当前角色拥有的所有权限或者菜单ID
     * @param roleId
     * @return
     */
    List<Integer> selectRolePermissionByRole(Integer roleId);

    /**
     * 根据角色ID存储角色权限
     * @param roleId
     * @param ids
     */
    void saveRolePermission(Integer roleId, Integer[] ids);

    List<Integer> selectUserRoleIdsByUserId(Integer id);
}
