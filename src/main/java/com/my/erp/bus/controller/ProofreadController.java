package com.my.erp.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.Goods;
import com.my.erp.bus.domain.Proofread;
import com.my.erp.bus.domain.Provider;
import com.my.erp.bus.service.CustomerService;
import com.my.erp.bus.service.GoodsService;
import com.my.erp.bus.service.ProofreadService;
import com.my.erp.bus.service.ProviderService;
import com.my.erp.bus.vo.ProofreadVo;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.DeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * my所用的表 前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-28
 */
@RestController
@RequestMapping("/proofread")
public class ProofreadController {



    @Autowired
    private ProofreadService proofreadService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DeptService deptService;

    /**
     * 查询业务表
     *
     * @param proofreadVo
     * @return
     */
    @RequestMapping("/loadAllProofread")
    public DataGridView loadAllProofread(ProofreadVo proofreadVo) {
        //创建page对象
        IPage<Proofread> page = new Page<>(proofreadVo.getPage(), proofreadVo.getLimit());
        //创建条件器
        QueryWrapper<Proofread> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq(proofreadVo.getCustomerid()!=null,"customerid",proofreadVo.getCustomerid());
        queryWrapper.eq(proofreadVo.getDeptid()!=null,"deptid",proofreadVo.getDeptid());
        queryWrapper.like(StringUtils.isNoneBlank(proofreadVo.getOperateperson()),"operateperson",proofreadVo.getOperateperson());
        queryWrapper.like(StringUtils.isNoneBlank(proofreadVo.getName()),"name",proofreadVo.getName());
        queryWrapper.like(StringUtils.isNoneBlank(proofreadVo.getRemark()),"remark",proofreadVo.getRemark());
        queryWrapper.like(StringUtils.isNoneBlank(proofreadVo.getAuditing()),"auditing",proofreadVo.getAuditing());
        queryWrapper.ge(proofreadVo.getStartTime() != null, "proofreadtime", proofreadVo.getStartTime());
        queryWrapper.le(proofreadVo.getEndTime() != null, "proofreadtime", proofreadVo.getEndTime());
        queryWrapper.orderByDesc("proofreadtime");
        //查询
        proofreadService.page(page, queryWrapper);
        return new DataGridView(page);
    }

    /**
     * 添加业务表
     *
     * @param proofreadVo
     * @return
     */

    @RequestMapping("/addProofread")
    public ResultObj addProofread(ProofreadVo proofreadVo, HttpSession session) {
        try {
            User user = (User)session.getAttribute("user");
            proofreadVo.setOperateperson(user.getName());
            proofreadVo.setCreattime(new Date());
            proofreadService.save(proofreadVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改业务表
     *
     * @param proofreadVo
     * @return
     */
    @RequestMapping("/updateProofread")
    public ResultObj updateProofread(ProofreadVo proofreadVo) {
        try {
            proofreadService.updateById(proofreadVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除业务表
     * @param id
     * @return
     */
    @RequestMapping("/deleteProofread")
    public ResultObj batchDeleteProofread(Integer id) {
        try {
            //删除原文件
            proofreadService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

