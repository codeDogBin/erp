package com.my.erp.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.Goods;
import com.my.erp.bus.domain.Inport;
import com.my.erp.bus.domain.Outport;
import com.my.erp.bus.domain.Provider;
import com.my.erp.bus.service.GoodsService;
import com.my.erp.bus.service.OutportService;
import com.my.erp.bus.service.ProviderService;
import com.my.erp.bus.vo.InportVo;
import com.my.erp.bus.vo.OutportVo;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.config.Log;
import com.my.erp.sys.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-26
 */
@RestController
@RequestMapping("/outport")
public class OutportController {

    private final  static Logger logger = LoggerFactory.getLogger(OutportController.class);

    @Autowired
    private OutportService outportService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 添加退货信息
     */
    @Log("添加退货信息")
    @RequestMapping("addOutport")
    public ResultObj addOutPort(Integer id, Integer number, String remark, HttpSession session){
        try {
            String name = ((User) session.getAttribute("user")).getName();
            outportService.addOutPort(id,number,remark,name);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return  ResultObj.OPERATE_ERROR;
        }
    }

    /**
     * 查询退货单
     *
     * @param outportVo
     * @return
     */
    @RequestMapping("/loadAllOutport")
    public DataGridView loadAllOutport(OutportVo outportVo) {
        //创建page对象
        IPage<Outport> page = new Page<>(outportVo.getPage(), outportVo.getLimit());
        //创建条件器
        QueryWrapper<Outport> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq(null != outportVo.getProviderid() && outportVo.getProviderid() != 0, "providerid", outportVo.getProviderid());//
        queryWrapper.eq(null != outportVo.getGoodsid() && outportVo.getGoodsid() != 0, "goodsid", outportVo.getGoodsid());//退货单id
        queryWrapper.like(StringUtils.isNoneBlank(outportVo.getOperateperson()),"operateperson",outportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNoneBlank(outportVo.getRemark()),"remark",outportVo.getRemark());
        queryWrapper.ge(outportVo.getStartTime() != null, "outputtime", outportVo.getStartTime());
        queryWrapper.le(outportVo.getEndTime() != null, "outputtime", outportVo.getEndTime());
        queryWrapper.orderByDesc("outputtime");
        //查询
        outportService.page(page, queryWrapper);
        List<Outport> records = page.getRecords();
        for (Outport outport : records) {
            Provider provider = providerService.getById(outport.getProviderid());
            if (null != provider) {
                outport.setProvidername(provider.getProvidername());
            }
            Goods goods = goodsService.getById(outport.getGoodsid());
            if (null != goods) {
                outport.setGoodsname(goods.getGoodsname());
                outport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

}

