package com.my.erp.bus.service.impl;

import com.my.erp.bus.domain.Goods;
import com.my.erp.bus.domain.Inport;
import com.my.erp.bus.domain.Outport;
import com.my.erp.bus.mapper.GoodsMapper;
import com.my.erp.bus.mapper.InportMapper;
import com.my.erp.bus.mapper.OutportMapper;
import com.my.erp.bus.service.OutportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-26
 */
@Service
@Transactional
public class OutportServiceImpl extends ServiceImpl<OutportMapper, Outport> implements OutportService {

    @Autowired
    private InportMapper inportMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void addOutPort(Integer id, Integer number, String remark,String name) {
        //1根据进货单id查询进货单信息并修改
        Inport inport = inportMapper.selectById(id);
        inport.setNumber(inport.getNumber()-number);
        inportMapper.updateById(inport);
        //2根据商品ID查询商品信息并修改
        Goods goods = goodsMapper.selectById(inport.getGoodsid());
        goods.setNumber(goods.getNumber()-number);
        goodsMapper.updateById(goods);
        //3.添加退货单信息
        Outport entity = new Outport();
        entity.setGoodsid(inport.getGoodsid());
        entity.setOperateperson(name);
        entity.setOutputtime(new Date());
        entity.setOutportprice(inport.getInportprice());
        entity.setNumber(number);
        entity.setPaytype(inport.getPaytype());
        entity.setProviderid(inport.getProviderid());
        entity.setRemark(remark);
        getBaseMapper().insert(entity);
    }
}
