package com.my.erp.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.Goods;
import com.my.erp.bus.domain.Inport;
import com.my.erp.bus.domain.Provider;
import com.my.erp.bus.service.GoodsService;
import com.my.erp.bus.service.InportService;
import com.my.erp.bus.service.ProviderService;
import com.my.erp.bus.vo.InportVo;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.MyFileUtils;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-26
 */
@RestController
@RequestMapping("/inport")
public class InportController {
    @Autowired
    private InportService inportService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 查询进货单
     *
     * @param inportVo
     * @return
     */
    @RequestMapping("/loadAllInport")
    public DataGridView loadAllInport(InportVo inportVo) {
        //创建page对象
        IPage<Inport> page = new Page<>(inportVo.getPage(), inportVo.getLimit());
        //创建条件器
        QueryWrapper<Inport> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq(null != inportVo.getProviderid() && inportVo.getProviderid() != 0, "providerid", inportVo.getProviderid());//
        queryWrapper.eq(null != inportVo.getGoodsid() && inportVo.getGoodsid() != 0, "goodsid", inportVo.getGoodsid());//进货单id
        queryWrapper.like(StringUtils.isNoneBlank(inportVo.getOperateperson()),"operateperson",inportVo.getOperateperson());
        queryWrapper.like(StringUtils.isNoneBlank(inportVo.getRemark()),"remark",inportVo.getRemark());
        queryWrapper.ge(inportVo.getStartTime() != null, "inporttime", inportVo.getStartTime());
        queryWrapper.le(inportVo.getEndTime() != null, "inporttime", inportVo.getEndTime());
        queryWrapper.orderByDesc("inporttime");
        //查询
        inportService.page(page, queryWrapper);
        List<Inport> records = page.getRecords();
        for (Inport inport : records) {
            Provider provider = providerService.getById(inport.getProviderid());
            if (null != provider) {
                inport.setProvidername(provider.getProvidername());
            }
            Goods goods = goodsService.getById(inport.getGoodsid());
            if (null != goods) {
                inport.setGoodsname(goods.getGoodsname());
                inport.setSize(goods.getSize());
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

    /**
     * 添加进货单
     *
     * @param inportVo
     * @return
     */

    @RequestMapping("/addInport")
    public ResultObj addInport(InportVo inportVo, HttpSession session) {
        try {
            User user = (User)session.getAttribute("user");
            inportVo.setOperateperson(user.getName());
            inportVo.setInporttime(new Date());
            inportService.save(inportVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改进货单
     *
     * @param inportVo
     * @return
     */
    @RequestMapping("/updateInport")
    public ResultObj updateInport(InportVo inportVo) {
        try {

            inportService.updateById(inportVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除进货单
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteInport")
    public ResultObj batchDeleteInport(Integer id) {
        try {
            //删除原文件
            inportService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

