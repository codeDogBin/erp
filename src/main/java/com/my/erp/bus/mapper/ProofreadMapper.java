package com.my.erp.bus.mapper;

import com.my.erp.bus.domain.Proofread;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * my所用的表 Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2020-04-28
 */
public interface ProofreadMapper extends BaseMapper<Proofread> {

    @Select("SELECT COUNT(ID) FROM BUS_PROOFREAD WHERE AUDITINGID IN (1,2,5)")
    Integer getCountByCw();

    @Select("SELECT RID FROM SYS_ROLE_USER WHERE UID=#{UID}")
    List<Integer> getRidByUid(int uid);

    @Select("SELECT COUNT(ID) FROM BUS_PROOFREAD WHERE AUDITINGID IN (5,6,7,8,9) AND OPERATEPERSON = #{NAME}")
    Integer getCountByYW(String name);

    @Select("SELECT COUNT(ID) FROM BUS_PROOFREAD WHERE AUDITINGID IN (1,3,7) AND DEPTID = #{DEPTID}")
    Integer getCountByJL(Integer deptid);
}
