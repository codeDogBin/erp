package com.my.erp.bus.mapper;

import com.my.erp.bus.domain.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 公司 Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@Repository
public interface CompanyMapper extends BaseMapper<Company> {


    @Select("select c.* from bus_customer_company as cc, bus_company as c where cc.customerid = #{customerid} and cc.companyid = c.id limit #{start},20")
    List<Company> findCompanyByCustomerIDLimit(@Param("customerid") int customer, @Param("start") int start);

    @Select("select c.* from bus_customer_company as cc, bus_company as c where cc.customerid=#{customerid} and cc.companyid = c.id ")
    List<Company> findCompanyByCustomerID(@Param("customerid") int customerid);

    @Select("select * from bus_company order by ctime desc limit 1000 ")
    List<Company> selectAll();

    @Select("select * from bus_company where name like #{name} limit 100")
    List<Company> selectByName(@Param("name") String name);



    @Select("select * from bus_company where name like #{name}")
    List<Company> selectByNameNoPage(String bus_companyName);

    @Select("select * from bus_company where name = #{name} limit 1")
    Company findCompanyByName(String name);


    @Insert("insert into bus_customer_company (customerid,companyid) values(#{customerid},#{companyid})   ")
    Integer insertCustomerCompany(Integer customerid,Integer companyid);



    @Delete("delete from bus_customer_company where customerid = #{customerid} and companyid = #{companyid}")
    void deleteCustomerCompany(Integer customerid,Integer companyid );


}
