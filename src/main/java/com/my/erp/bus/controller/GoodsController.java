package com.my.erp.bus.controller;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.Goods;
import com.my.erp.bus.domain.Provider;
import com.my.erp.bus.service.GoodsService;
import com.my.erp.bus.service.ProviderService;
import com.my.erp.bus.vo.GoodsVo;
import com.my.erp.bus.vo.ProviderVo;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.MyFileUtils;
import com.my.erp.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-24
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ProviderService providerService;

    /**
     * 查询商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("/loadAllGoods")
    public DataGridView loadAllGoods(GoodsVo goodsVo){
        //创建page对象
        IPage<Goods> page =new Page<>(goodsVo.getPage(),goodsVo.getLimit());
        //创建条件器
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq(null!=goodsVo.getProviderid()&&goodsVo.getProviderid()!=0,"providerid",goodsVo.getProviderid());//商品id
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getGoodsname()),"goodsname",goodsVo.getGoodsname());//商品名称
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getProductcode()),"productcode",goodsVo.getProductcode());//生产批号
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getPromitcode()),"promitcode",goodsVo.getPromitcode());//批准文号
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getDescription()),"description",goodsVo.getDescription());//商品描述
        queryWrapper.like(StringUtils.isNoneBlank(goodsVo.getSize()),"size",goodsVo.getSize());//商品尺寸
        //查询
        goodsService.page(page,queryWrapper);
        List<Goods> records = page.getRecords();
        for (Goods goods : records) {
            Provider provider = providerService.getById(goods.getProviderid());
            if(null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return new DataGridView(page.getTotal(),records);
    }

    /**
     * 添加商品
     * @param goodsVo
     * @return
     */

    @RequestMapping("/addGoods")
    public ResultObj addGoods(GoodsVo goodsVo){
        try {
            //如果是_temp结尾
            if(goodsVo.getGoodsimg()!=null && goodsVo.getGoodsimg().endsWith("_temp")){
              String newName =  MyFileUtils.renameFile(goodsVo.getGoodsimg());
              goodsVo.setGoodsimg(newName);
            }
            goodsService.save(goodsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改商品
     * @param goodsVo
     * @return
     */
    @RequestMapping("/updateGoods")
    public ResultObj updateGoods(GoodsVo goodsVo){
        try {
            //如果不是默认图片
            if(!(goodsVo.getGoodsimg()!=null && goodsVo.getGoodsimg().equals(Constast.IMG_DEFAUL))){
                //如果图片的结尾是_temp
                if(goodsVo.getGoodsimg().endsWith("_temp")){
                    //设置新的名字将_temp去掉
                    String newName = MyFileUtils.renameFile(goodsVo.getGoodsimg());
                    goodsVo.setGoodsimg(newName);
                    //去除老名字 然后将原来的文件删除
                    String oldPath  = goodsService.getById(goodsVo.getId()).getGoodsimg();
                    MyFileUtils.removeFileByPath(oldPath);
                }
            }
            goodsService.updateById(goodsVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
    

    /**
     * 删除商品
     * @param id
     * @return
     */
    @RequestMapping("/deleteGoods")
    public ResultObj batchDeleteGoods(Integer id,String goodsimg){
        try {
            //删除原文件
            MyFileUtils.removeFileByPath(goodsimg);
            goodsService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有可用的商品
     */
    @RequestMapping("loadAllGoodsForSelect")
    public DataGridView loadAllProviderForSelect(){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Goods> list = goodsService.list(queryWrapper);
        for (Goods goods : list) {
            Provider provider = providerService.getById(goods.getProviderid());
            if(null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return  new DataGridView(list);
    }

    /**
     * 根据供应商查询所有可用的商品
     * @param providerid
     * @return
     */
    @RequestMapping("loadGoodsByProviderId")
    public DataGridView loadGoodsByProviderId(Integer providerid){
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        queryWrapper.eq(providerid!=null,"providerid", providerid);
        List<Goods> list = goodsService.list(queryWrapper);
        for (Goods goods : list) {
            Provider provider = providerService.getById(goods.getProviderid());
            if(null!=provider){
                goods.setProvidername(provider.getProvidername());
            }
        }
        return  new DataGridView(list);
    }

}

