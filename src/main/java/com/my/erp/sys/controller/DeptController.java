package com.my.erp.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.common.TreeNode;
import com.my.erp.sys.domain.Dept;
import com.my.erp.sys.domain.Notice;
import com.my.erp.sys.domain.Permission;
import com.my.erp.sys.service.DeptService;
import com.my.erp.sys.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 加载部门管理的json树
     */
    @RequestMapping("/loadDeptManagerLeftTreeJson")
    public DataGridView loadDeptManagerLeftTreeJson(DeptVo deptVo){
        List<Dept> list = deptService.list();
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (Dept dept : list) {
            Boolean spread = dept.getOpen()==1?true:false;
            treeNodeList.add(new TreeNode(dept.getId(),dept.getPid(),dept.getTitle(),spread));
        }
        return new DataGridView(treeNodeList);
    }

    /**
     *  加载表格所有dept信息
     * @param deptVo
     * @return
     */
    @RequestMapping("/loadAllDept")
    public DataGridView loadAllDept(DeptVo deptVo){
        //创建page对象
        IPage<Dept> page =new Page<>(deptVo.getPage(),deptVo.getLimit());
        //创建条件器
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.like(StringUtils.isNoneBlank(deptVo.getTitle()),"title",deptVo.getTitle());
        queryWrapper.like(StringUtils.isNoneBlank(deptVo.getAddress()),"opername",deptVo.getAddress());
        queryWrapper.like(StringUtils.isNoneBlank(deptVo.getRemark()),"remark",deptVo.getAddress());
        queryWrapper.eq(deptVo.getId()!=null,"id",deptVo.getId()).or().eq(deptVo.getId()!=null,"pid",deptVo.getId());
        queryWrapper.orderByAsc("ordernum");
        //查询
        deptService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 新增部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/addDept")
    public ResultObj addDept(DeptVo deptVo){
        try {
            deptVo.setCreatetime(new Date());
            deptService.save(deptVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 修改部门
     * @param deptVo
     * @return
     */
    @RequestMapping("/updateDept")
    public ResultObj updateDept(DeptVo deptVo){
        try {
            deptService.updateById(deptVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 返回最大排序码
     * @return
     */
    @RequestMapping("/loadDeptMaxOrderNum")
    public Map<String,Object> loadDeptMaxOrderNum(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Dept> page=new Page<>(1,1);
        List<Dept> list = deptService.page(page,queryWrapper).getRecords();
        if(list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else{
            map.put("value",1);
        }
        return  map;
    }

    /**
     * 查询该节点是否还有子节点
     * @param deptVo
     * @return
     */
    @RequestMapping("/checkDeptHasChildrenNode")
    public Boolean checkDeptHasChildrenNode(DeptVo deptVo){
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",deptVo.getId());
        List<Dept> list = deptService.list(queryWrapper);
        return list.size()>0?true:false;
    }

    /**
     *
     * @param deptVo
     * @return
     */
    @RequestMapping("/deleteDept")
    public ResultObj deleteDept(DeptVo deptVo){
        try {
            deptService.removeById(deptVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

