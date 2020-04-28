package com.my.erp.bus.service.impl;

import com.my.erp.bus.domain.Goods;
import com.my.erp.bus.domain.Inport;
import com.my.erp.bus.mapper.GoodsMapper;
import com.my.erp.bus.mapper.InportMapper;

import com.my.erp.bus.service.InportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-26
 */
@Service
public class InportServiceImpl extends ServiceImpl<InportMapper, Inport> implements InportService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public boolean save(Inport entity) {
        Goods goods = goodsMapper.selectById(entity.getGoodsid());
        goods.setNumber(entity.getNumber()+goods.getNumber());
        goodsMapper.updateById(goods);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Inport entity) {
        //根据id查到之前的进货信息
        Inport inport = getBaseMapper().selectById(entity.getId());
        Goods goods = goodsMapper.selectById(entity.getGoodsid());
        goods.setNumber(goods.getNumber()-inport.getNumber()+entity.getNumber());
        goodsMapper.updateById(goods);
        return super.updateById(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        //查询到进货单信息
        Inport inport = getBaseMapper().selectById(id);
        //查询到进货单里面的货物信息
        Goods goods = goodsMapper.selectById(inport.getGoodsid());
        //设置货物的数量
        goods.setNumber(goods.getNumber()-inport.getNumber());
        //更新货物数量
        goodsMapper.updateById(goods);
        return super.removeById(id);
    }
}
