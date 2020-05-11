package com.my.erp.bus.service;

import com.my.erp.bus.domain.Customer;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bin
 * @since 2020-04-24
 */
public interface CustomerService extends IService<Customer> {

    Integer getccByCusId(Integer id);
}
