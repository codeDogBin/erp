package com.my.erp.bus.controller;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.Provider;
import com.my.erp.bus.service.ProviderService;
import com.my.erp.bus.vo.ProviderVo;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.domain.User;
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
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /**
     * 查询供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("/loadAllProvider")
    public DataGridView loadAllProvider(ProviderVo providerVo){
        //创建page对象
        IPage<Provider> page =new Page<>(providerVo.getPage(),providerVo.getLimit());
        //创建条件器
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.like(StringUtils.isNoneBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        queryWrapper.like(StringUtils.isNoneBlank(providerVo.getPhone()),"phone",providerVo.getPhone());
        queryWrapper.ge(StringUtils.isNoneBlank(providerVo.getConnectionperson()),"connectionperson",providerVo.getConnectionperson());
        //查询
        providerService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }


    /**
     * 添加供应商
     * @param providerVo
     * @param session
     * @return
     */

    @RequestMapping("/addProvider")
    public ResultObj addProvider(ProviderVo providerVo, HttpSession session){
        try {
            providerService.save(providerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改供应商
     * @param providerVo
     * @param session
     * @return
     */
    @RequestMapping("/updateProvider")
    public ResultObj updateProvider(ProviderVo providerVo, HttpSession session){
        try {
            providerService.updateById(providerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 批量删除供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("/batchDeleteProvider")
    public ResultObj batchDeleteProvider(ProviderVo providerVo){
        try {
            List<Integer> ids = Convert.toList(Integer.class, providerVo.getIds());
            providerService.removeByIds(ids);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 删除供应商
     * @param id
     * @return
     */
    @RequestMapping("/deleteProvider")
    public ResultObj batchDeleteProvider(Integer id){
        try {
            providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有可用的供应商
     */
    @RequestMapping("loadAllProviderForSelect")
    public DataGridView loadAllProviderForSelect(){
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Provider> list = providerService.list(queryWrapper);
        return  new DataGridView(list);
    }

}



