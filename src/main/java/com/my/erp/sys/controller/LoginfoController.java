package com.my.erp.sys.controller;



import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.domain.Loginfo;
import com.my.erp.sys.domain.Permission;
import com.my.erp.sys.service.LoginfoService;
import com.my.erp.sys.vo.LoginfoVo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.constraintvalidators.bv.notempty.NotEmptyValidatorForArraysOfLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/loginfo")
public class LoginfoController {

    @Autowired
    public LoginfoService loginfoService;

    /**
     * 查询登录日志
     */
    @RequestMapping("loadAllLoginfo")
    public DataGridView loadAllLgoinfo(LoginfoVo loginfoVo){
        //mybatis_plus的分页查询
        IPage<Loginfo> page= new Page<>(loginfoVo.getPage(),loginfoVo.getLimit());
        //条件查询器
        QueryWrapper<Loginfo> queryWrapper = new QueryWrapper<>();
        //登录名称
        queryWrapper.like(StringUtils.isNoneBlank(loginfoVo.getLoginname()),"loginname",loginfoVo.getLoginname());
        //登录ip
        queryWrapper.like(StringUtils.isNoneBlank(loginfoVo.getLoginip()),"loginip",loginfoVo.getLoginip());
        //登录日期
        queryWrapper.ge(loginfoVo.getStartTime()!=null,"logintime",loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime()!=null,"logintime",loginfoVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        //执行查询
        loginfoService.page(page,queryWrapper);
        //返回数据
        return  new DataGridView(page.getTotal(),page.getRecords());
    }
    /**
     * 删除
     */
    @RequestMapping("deleteLoginfo")
    public ResultObj deleteLoginfo(Integer id){
        try {
            loginfoService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除删除
     */
    @RequestMapping("batchDeleteLoginfo")
    public ResultObj batchDeleteLoginfo(LoginfoVo loginfoVo){
        try {
            List<Integer> ids = Convert.toList(Integer.class, loginfoVo.getIds());
            loginfoService.removeByIds(ids);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

