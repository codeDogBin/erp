package com.my.erp.sys.mapper;

import com.my.erp.sys.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2020-04-21
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Delete("delete from sys_role_permission where rid = #{id} ")
    void deleteRolePermissionByRid(@Param("id") Serializable id);

    @Delete("delete from sys_role_user where rid = #{id} ")
    void deleteRoleUserByRId(Serializable id);

    @Select("select pid from sys_role_permission  where rid = #{roleId}")
    List<Integer> selectRolePermissionByRole(Integer roleId);

    @Insert("insert into sys_role_permission (rid,pid) values(#{rid} ,#{pid}) ")
    void saveRolePermission(@Param("rid")Integer roleId, @Param("pid") Integer pid);

    @Select("select rid from sys_role_user where uid = #{id}")
    List<Integer> selectUserRoleIdsByUserId(Integer id);


}
