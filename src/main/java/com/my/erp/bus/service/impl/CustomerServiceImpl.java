package com.my.erp.bus.service.impl;

import com.my.erp.bus.domain.Customer;
import com.my.erp.bus.mapper.CustomerMapper;
import com.my.erp.bus.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-24
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {


    @Override
    public boolean save(Customer entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {

        return super.removeById(id);
    }

    @Override
    public boolean updateById(Customer entity) {
        return super.updateById(entity);
    }

    @Override
    public Customer getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    public Integer getccByCusId(Integer id) {
        return getBaseMapper().getccByCusId(id);
    }
}
