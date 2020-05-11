package com.my.erp.bus.mapper;

import com.my.erp.bus.domain.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author bin
 * @since 2020-04-24
 */
@Repository
public interface CustomerMapper extends BaseMapper<Customer> {
    @Select("SELECT COUNT(*) FROM BUS_CUSTOMER_COMPANY where CUSTOMERID = #{id}")
    Integer getccByCusId(Integer id);
}
