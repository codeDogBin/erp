package com.my.erp.bus.mapper;

import com.my.erp.bus.domain.Fil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@Repository
public interface FilMapper extends BaseMapper<Fil> {

    @Insert("insert into  bus_fil (name,way,imgway,folid,ctime,state) values(#{name},#{way},#{imgway},#{folid},#{ctime},1)")
    int insert(Fil fil);

    @Select("select * from bus_fil where folid = #{folid} and state = 1")
    List<Fil> findByFid(int folid);

    @Select("select * from bus_fil where id = #{filid} and state = 1 limit 1")
    Fil findById(int filid);

    @Select("select * from bus_fil where id = #{filid} and state = 0 limit 1")
    Fil findExpireById(int filid);

    @Select("select * from bus_fil where name =#{name} and folid =#{folid} and state = 0")
    Fil findExpireByNameFolid(@Param("name") String name, @Param("folid") int folid);

    @Select("select * from bus_fil where state = 0 order by deltime desc limit 1000")
    List<Fil> allExpire();

    @Update("update bus_fil set state = 0 ,name= #{name}, imgway=#{imgway},deltime= #{deltime} where id = #{id}")
    void expireFileById(Fil fil);

    @Update("update bus_fil set state = -1 ,name= #{name}, imgway=#{imgway},deltime= #{deltime} where id = #{id}")
    void expireChildFileById(Fil fil);

    @Delete("delete from bus_fil where id = #{filid}")
    void delFilById(int filid);

    @Select("select * from bus_fil where name= #{name} and folid = #{folid} and state = #{state} limit 1")
    Fil findByNameFidState(Fil fil);

    @Select("select * from bus_fil where state = 0 and deltime < #{timestamp}")
    List<Fil> getExpireFil(Timestamp timestamp);

    @Update("update bus_fil set state = 1 ,name= #{name} where id = #{id}")
    void recoverFil(Fil fil);

    @Select("select * from bus_fil where state = 1 and ctime between #{startTime} and #{endTime} ")
    List<Fil> getFilByMonth(@Param("startTime") Timestamp startTime,@Param("endTime") Timestamp endTime);

    @Select("select * from bus_fil where name= #{name} and folid = #{folid} and state = 1 limit 1")
    Fil findByNameFolid(@Param("name") String name,@Param("folid") int folid);

    @Update("update bus_fil set name= #{name} where id = #{id}")
    void renameFil(@Param("name") String name, @Param("id") int filid);

    @Update("update bus_fil set imgway= #{imgway} where id = #{id}")
    void updateImgWay(Fil fil);
}
