package com.my.erp.sys.mapper;

import com.my.erp.sys.domain.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2020-04-18
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     *
     * @param id
     */
    @Delete("delete form sys_role_permission where pid = #{id}")
    void deleteRoloPermissionByPid(@Param("id") Serializable id);
}
