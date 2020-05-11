package com.my.erp.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.common.TreeNode;
import com.my.erp.sys.domain.Permission;
import com.my.erp.sys.service.PermissionService;
import com.my.erp.sys.service.impl.PermissionServiceImpl;
import com.my.erp.sys.vo.PermissionVo;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    private final static Logger logger = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private PermissionService permissionService;

    /**
     * 加载部门管理的json树
     */
    @RequestMapping("/loadPermissionManagerLeftTreeJson")
    public DataGridView loadPermissionManagerLeftTreeJson(PermissionVo permissionVo){
        QueryWrapper<Permission>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",Constast.TYPE_MENU);
        List<Permission> list = permissionService.list(queryWrapper);
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (Permission menu : list) {
            Boolean spread = menu.getOpen()==1?true:false;
            treeNodeList.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),spread));
        }
        return new DataGridView(treeNodeList);
    }

    /**
     *  加载表格所有菜单信息
     * @param permissionVo
     * @return
     */
    @RequestMapping("/loadAllPermission")
    public DataGridView loadAllPermission(PermissionVo permissionVo){
        //创建page对象
        IPage<Permission> page =new Page<>(permissionVo.getPage(),permissionVo.getLimit());
        //创建条件器
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq("type",Constast.TYPE_PERMISSION);
        queryWrapper.like(StringUtils.isNoneBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        queryWrapper.like(StringUtils.isNoneBlank(permissionVo.getPercode()),"percode",permissionVo.getPercode());
        queryWrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId()).or().eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        queryWrapper.orderByAsc("ordernum");
        //查询
        permissionService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 新增权限
     * @param permissionVo
     * @return
     */
    @RequestMapping("/addPermission")
    public ResultObj addPermission(PermissionVo permissionVo){
        try {
            permissionVo.setType(Constast.TYPE_PERMISSION);
            permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 修改权限
     * @param permissionVo
     * @return
     */
    @RequestMapping("/updatePermission")
    public ResultObj updatePermission(PermissionVo permissionVo){
        try {
            permissionService.updateById(permissionVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 返回最大排序码
     * @return
     */
    @RequestMapping("/loadPermissionMaxOrderNum")
    public Map<String,Object> loadPermissionMaxOrderNum(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Permission> page=new Page<>(1,1);
        List<Permission> list = permissionService.page(page,queryWrapper).getRecords();
        if(list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else{
            map.put("value",1);
        }
        return  map;
    }

    /**
     * 删除菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("/deletePermission")
    public ResultObj deletePermission(PermissionVo permissionVo){
        try {
            permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }




}

