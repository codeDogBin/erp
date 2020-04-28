package com.my.erp.bus.service.impl;

import com.my.erp.bus.domain.Goods;
import com.my.erp.bus.mapper.GoodsMapper;
import com.my.erp.bus.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-24
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public boolean save(Goods entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Goods entity) {
        return super.updateById(entity);
    }

    @Override
    public Goods getById(Serializable id) {
        return super.getById(id);
    }
}
