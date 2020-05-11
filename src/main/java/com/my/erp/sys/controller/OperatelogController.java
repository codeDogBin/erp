package com.my.erp.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.domain.Operatelog;
import com.my.erp.sys.service.OperatelogService;
import com.my.erp.sys.vo.OperatelogVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/operatelog")
public class OperatelogController {
    @Autowired
    public OperatelogService operatelogService;

    /**
     * 查询登录日志
     */
    @RequestMapping("loadAllOperatelog")
    public DataGridView loadAllLgoinfo(OperatelogVo operatelogVo){
        //mybatis_plus的分页查询
        IPage<Operatelog> page= new Page<>(operatelogVo.getPage(),operatelogVo.getLimit());
        //条件查询器
        QueryWrapper<Operatelog> queryWrapper = new QueryWrapper<>();
        //登录名称
        queryWrapper.like(StringUtils.isNoneBlank(operatelogVo.getUsername()),"username",operatelogVo.getUsername());
        //登录ip
        queryWrapper.like(StringUtils.isNoneBlank(operatelogVo.getDescription()),"description",operatelogVo.getDescription());
        //登录日期
        queryWrapper.ge(operatelogVo.getStartTime()!=null,"ctime",operatelogVo.getStartTime());
        queryWrapper.le(operatelogVo.getEndTime()!=null,"ctime",operatelogVo.getEndTime());
        queryWrapper.orderByDesc("ctime");
        //执行查询
        operatelogService.page(page,queryWrapper);
        //返回数据
        return  new DataGridView(page.getTotal(),page.getRecords());
    }
}

