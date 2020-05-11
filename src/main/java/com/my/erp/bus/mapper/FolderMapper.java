package com.my.erp.bus.mapper;

import com.my.erp.bus.domain.Folder;
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
public interface FolderMapper extends BaseMapper<Folder> {
    
    @Insert("insert into bus_folder (name,way,fwayid,ctime,companyid,state) values(#{name},#{way},#{fwayid},#{ctime},#{companyid},1)   ")
    int insert(Folder bus_folder);

    @Select("select * from bus_folder where fwayid = #{fwayid} and companyid = #{companyid} and state = 1")
    List<Folder> findByFidCid(@Param("fwayid") int fwayid, @Param("companyid") int companyid);

    @Select("select * from bus_folder where fwayid = #{fwayid}")
    List<Folder> findByFid(@Param("fwayid") int fwayid);

    @Select("select * from bus_folder where name= #{name} and fwayid = #{fwayid} and companyid = #{companyid} and state = 1 limit 1")
    Folder findByNameFidCid(@Param("name") String name,@Param("fwayid") int fwayid,@Param("companyid") int companyid);

    @Select("select * from bus_folder where id = #{fwayid} and state = 1 limit 1")
    Folder findByFidAsId(int fwayid);

    @Select("select * from bus_folder where id = #{id} and state = 0 limit 1")
    Folder findExpireByID(int fol_id);

    @Select("select * from bus_folder where id = #{id} and state = 1 limit 1")
    Folder findByID(int fol_id);

    @Delete("delete from bus_folder where id = #{fol_id}")
    void delFolById(int fol_id);

    @Select("select * from bus_folder where state = 0 order by deltime desc limit 1000 ")
    List<Folder> allExpire();

    @Select("select * from bus_folder where state = 1")
    List<Folder> allFolder();

    @Select("select * from bus_folder where name=#{name} and fwayid = #{fwayid} and companyid = #{companyid} and state = #{state} limit 1")
    Folder findByNameFidCidState(Folder bus_folder);

    @Update("update bus_folder set state = 0, name = #{name}, deltime=#{deltime}  where id = #{id}")
    void expireFol(Folder bus_folder);

    @Update("update bus_folder set state = -1, name = #{name}, deltime=#{deltime}  where id = #{id}")
    void expireChildFol(Folder bus_folder);

    @Update("update bus_folder set state = 1, name = #{name}  where id = #{id}")
    void recoverFol(Folder fol);

    @Update("update bus_folder set  name = #{name}  where id = #{fol_id}")
    void renameFol(@Param("name") String name,@Param("fol_id") int fol_id);



    @Select("select * from bus_folder where state = 0 and deltime < #{timestamp}")
    List<Folder> getExpireFol(Timestamp timestamp);
}
