package com.my.erp.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.sys.common.*;

import com.my.erp.sys.domain.Permission;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.PermissionService;

import com.my.erp.sys.service.RoleService;
import com.my.erp.sys.service.UserService;
import com.my.erp.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 *  菜单管理前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private PermissionService permissionService;


    @Autowired
    private RoleService roleService;

    /**
     * 加载左侧菜单
     * @param session
     * @return
     */
    @RequestMapping("/loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(HttpSession session){
        //查询所有菜单
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        //设置只需要查询菜单
        queryWrapper.eq("type", Constast.TYPE_MENU);
        //设置可以使用
        queryWrapper.eq("available",Constast.AVAILABLE_TRUE);
        User user = (User) session.getAttribute("user");
        List<Permission> list = null;
        if(user.getType() ==Constast.USER_TYPE_SUPER){//如果是超管用户
             list  = permissionService.list(queryWrapper);
        }else{
            //根据用户ID+角色+权限查询
            Integer uid = user.getId();
            //根据用户ID查询角色ID
            List<Integer> currentUserRoleIds = roleService.selectUserRoleIdsByUserId(uid);
            //根据角色获取权限和菜单ID
            Set<Integer> pids = new HashSet<>();
            for (Integer roleId : currentUserRoleIds) {
                List<Integer> permissionIds = roleService.selectRolePermissionByRole(roleId);
                pids.addAll(permissionIds);
            }
            if(pids.size()>0){
                queryWrapper.in("id",pids);
                list  = permissionService.list(queryWrapper);
            }else{
                list = new ArrayList<>();
            }
        }
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission : list) {
            Integer id = permission.getId();
            Integer pid = permission.getPid();
            String title = permission.getTitle();
            String href = permission.getHref();
            String icon = permission.getIcon();
            Boolean spread = permission.getOpen()==Constast.OPEN_TRUE?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }
        //构建层级关系
        List<TreeNode> nodes = TreeNodeBuilder.build(treeNodes, 1);
        return new DataGridView(nodes);
    }

    /***************************菜单管理开始***********************************/
    /**
     * 加载菜单管理的json树
     */
    @RequestMapping("/loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(PermissionVo permissionVo){
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
    @RequestMapping("/loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo){
        //创建page对象
        IPage<Permission> page =new Page<>(permissionVo.getPage(),permissionVo.getLimit());
        //创建条件器
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq("type",Constast.TYPE_MENU);
        queryWrapper.like(StringUtils.isNoneBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        queryWrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId()).or().eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        queryWrapper.orderByAsc("ordernum");
        //查询
        permissionService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 新增菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("/addMenu")
    public ResultObj addMenu(PermissionVo permissionVo){
        try {
            permissionVo.setType(Constast.TYPE_MENU);
            permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }


    /**
     * 修改菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("/updateMenu")
    public ResultObj updateMenu(PermissionVo permissionVo){
        try {
            permissionService.updateById(permissionVo);
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
    @RequestMapping("/loadMenuMaxOrderNum")
    public Map<String,Object> loadMenuMaxOrderNum(){
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Permission> page=new Page<>(1,1);
        List<Permission> list = permissionService.page(page,queryWrapper).getRecords();
        System.out.println(list);
        if(list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else{
            map.put("value",1);
        }
        return  map;
    }

    /**
     * 查询该节点是否还有子菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("/checkMenuHasChildrenNode")
    public Boolean checkMenuHasChildrenNode(PermissionVo permissionVo){
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",permissionVo.getId());
        List<Permission> list = permissionService.list(queryWrapper);
        return list.size()>0?true:false;
    }

    /**
     * 删除菜单
     * @param permissionVo
     * @return
     */
    @RequestMapping("/deleteMenu")
    public ResultObj deleteMenu(PermissionVo permissionVo){
        try {
            permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }






    /***************************菜单管理结束***********************************/


}

