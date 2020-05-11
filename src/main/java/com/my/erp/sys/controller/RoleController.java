package com.my.erp.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.common.TreeNode;
import com.my.erp.sys.config.Log;
import com.my.erp.sys.domain.Permission;
import com.my.erp.sys.domain.Role;
import com.my.erp.sys.service.PermissionService;
import com.my.erp.sys.service.RoleService;
import com.my.erp.sys.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-21
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询角色
     * @param roleVo
     * @return
     */
    @RequestMapping("/loadAllRole")
    public DataGridView loadAllRole(RoleVo roleVo){
        //创建page对象
        IPage<Role> page =new Page<>(roleVo.getPage(),roleVo.getLimit());
        //创建条件器
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.like(StringUtils.isNoneBlank(roleVo.getName()),"name",roleVo.getName());
        queryWrapper.like(StringUtils.isNoneBlank(roleVo.getRemark()),"remark",roleVo.getRemark());
        queryWrapper.eq(roleVo.getAvailable()!=null,"available",roleVo.getAvailable());
        queryWrapper.orderByDesc("createtime");
        //查询
        roleService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加角色
     * @param roleVo
     * @param session
     * @return
     */
    @Log("添加角色")
    @RequestMapping("/addRole")
    public ResultObj addRole(RoleVo roleVo, HttpSession session){
        try {
            roleVo.setCreatetime(new Date());
            roleService.save(roleVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 更新角色
     * @param roleVo
     * @param session
     * @return
     */
    @Log("更新角色")
    @RequestMapping("/updateRole")
    public ResultObj updateRole(RoleVo roleVo, HttpSession session){
        try {
            roleService.updateById(roleVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @Log("删除角色")
    @RequestMapping("/deleteRole")
    public ResultObj batchDeleteRole(Integer id){
        try {
            roleService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 根据角色ID加载菜单和权限树的json串
     */
    @RequestMapping("initPermissionByRoleId")
    public DataGridView initPermissionByRoleId(Integer roleId){
        //查询所有的菜单和权限
        QueryWrapper <Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Permission> allPermission = permissionService.list(queryWrapper);
        //根据角色ID查询当前角色拥有的菜单和权限
        List<Integer> currentRolePermission = roleService.selectRolePermissionByRole(roleId);
        List<Permission> currentPermission =null;
        if (currentRolePermission.size()>0){//如果角色有权限  则查询出所有的权限 否则设置为空集合
            queryWrapper.in("id",currentRolePermission);
            currentPermission =  permissionService.list(queryWrapper);
        }else {
            currentPermission = new ArrayList<>();
        }
        //构造list<TreeNode>
        List<TreeNode> nodes = new ArrayList<>();

        for (Permission permission : allPermission) {
            String checkArr = "0";
            //判断是否在时选中状态
            for (Permission permission1 : currentPermission) {
                if (permission1.getId()==permission.getId()){
                    checkArr = "1";
                    break;
                }
            }
            //判断是否是打开状态
            Boolean spread = (permission.getOpen()==null||permission.getOpen()==0)?false:true;
            nodes.add(new TreeNode(permission.getId(),permission.getPid(),permission.getTitle(),spread,checkArr));
        }
        return new DataGridView(nodes);
    }

    @RequestMapping("saveRolePermission")
    public ResultObj saveRolePermission(Integer rid,Integer[] ids){
        try{
            roleService.saveRolePermission(rid,ids);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }

    }
}

