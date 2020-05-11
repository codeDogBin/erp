package com.my.erp.sys.mapper;

import com.my.erp.sys.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2020-04-17
 */
public interface UserMapper extends BaseMapper<User> {

    @Delete("DELETE FROM SYS_ROLE_USER WHERE UID = #{ID}")
    void deleteRoleUserByUid(Serializable id);

    @Insert("INSERT INTO SYS_ROLE_USER(UID,RID) VALUES(#{uid},#{rid})")
    void insertUserRole(@Param("uid") Integer uid, @Param("rid") Integer rid);

    @Select("SELECT RID FROM SYS_ROLE_USER WHERE UID = #{ID}")
    Integer getRoleIDByUserID(Integer id);
}
