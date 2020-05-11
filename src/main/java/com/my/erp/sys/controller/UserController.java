package com.my.erp.sys.controller;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.PinyinUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.Customer;
import com.my.erp.bus.service.CustomerService;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.config.Log;
import com.my.erp.sys.domain.Dept;
import com.my.erp.sys.domain.Role;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.DeptService;
import com.my.erp.sys.service.RoleService;
import com.my.erp.sys.service.UserService;
import com.my.erp.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户全查询
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {
        IPage<User> page = new Page<>(userVo.getPage(), userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNoneBlank(userVo.getName()), "name", userVo.getName())
                .or().like(StringUtils.isNoneBlank(userVo.getName()), "loginname", userVo.getName());
        queryWrapper.like(StringUtils.isNoneBlank(userVo.getAddress()), "address", userVo.getAddress());
        queryWrapper.eq(userVo.getDeptid() != null, "deptid", userVo.getDeptid());
        queryWrapper.eq("type", Constast.USER_TYPE_NORMAL);
        userService.page(page, queryWrapper);
        List<User> list = page.getRecords();
        for (User user : list) {
            Integer deptid = user.getDeptid();
            if (deptid != null) {
                ;
                Dept one = deptService.getById(deptid);
                user.setDeptname(one.getTitle());
            }
            Integer mgr = user.getMgr();
            if (mgr != null && mgr != 0) {
                User one = userService.getById(mgr);
                user.setLeadername(one.getName());
            }
        }
        return new DataGridView(list);
    }

    /**
     * 返回最大排序码
     *
     * @return
     */
    @RequestMapping("/loadUserMaxOrderNum")
    public Map<String, Object> loadDeptMaxOrderNum() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<User> page = new Page<>(1, 1);
        List<User> list = userService.page(page, queryWrapper).getRecords();
        if (list.size() > 0) {
            map.put("value", list.get(0).getOrdernum() + 1);
        } else {
            map.put("value", 1);
        }
        return map;
    }


    /**
     * 根据部门ID查询用户
     */
    @RequestMapping("loadUserByDeptId")
    public DataGridView loadUserByDeptId(Integer deptid) {
        QueryWrapper<User> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.eq(deptid != null, "deptid", deptid);
        QueryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        QueryWrapper.eq("type", Constast.USER_TYPE_NORMAL);
        List<User> list = userService.list(QueryWrapper);
        return new DataGridView(list);
    }

    /**
     * 将用户名称改为拼音
     *
     * @param username
     * @return
     */
    @RequestMapping("changeChineseToPinyin")
    public String changeChineseToPinyin(String username) {
        String loginname = PinyinUtil.getPinYin(username);
        QueryWrapper qw = new QueryWrapper();
        while (true) {
            qw.eq(StringUtils.isNoneBlank(loginname), "loginname", loginname);
            User one = userService.getOne(qw);
            if (one == null) {
                break;
            } else {
                loginname = loginname + "V";
            }
        }
        return loginname;
    }

    /**
     * 将用户名称改为拼音
     *
     * @param loginname
     * @return
     */
    @RequestMapping("checkLoginname")
    public ResultObj checkLoginname(String loginname) {
        QueryWrapper qw = new QueryWrapper();
        qw.eq(StringUtils.isNoneBlank(loginname), "loginname", loginname);
            User one = userService.getOne(qw);
            if(one == null){
                return ResultObj.LOGINNAME_SUCCESS;
            }else return ResultObj.lOGINNNAE_ERROR;
    }


    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @RequestMapping("addUser")
    public ResultObj addUser(UserVo userVo) {
        try {
            userVo.setType(Constast.USER_TYPE_NORMAL);
            userVo.setHiredate(new Date());
            //生成盐
            String salt = IdUtil.simpleUUID().toUpperCase();
            userVo.setSalt(salt);
            //设置密码 使用shiro的md5加密
            userVo.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());
            userService.save(userVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     *
     * @param userVo
     * @return
     */
    @Log("修改用户")
    @RequestMapping("updateUser")
    public ResultObj updateUser(UserVo userVo) {
        try {
            userService.updateById(userVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Log("删除用户")
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(Integer id) {
        try {
            User user = userService.getById(id);
            Integer customerid = user.getCustomerid();
            Integer integer = customerService.getccByCusId(customerid);
            if(integer!=0){
                return ResultObj.DELETE_ERROR;
            }
            userService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 重置密码
     *
     * @param id
     * @return
     */
    @Log("重置密码")
    @RequestMapping("resetPwd")
    public ResultObj resetPwd(Integer id) {
        try {
            User user = userService.getById(id);
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            user.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());
            userService.updateById(user);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /**
     * 修改密码
     *
     * @param
     * @return
     */
    @Log("修改密码")
    @RequestMapping("updatePwd")
    public ResultObj updatePwd(HttpSession session, String pwd) {
        try {
            User user = (User) session.getAttribute("user");
            String salt = IdUtil.simpleUUID().toUpperCase();
            user.setSalt(salt);
            user.setPwd(new Md5Hash(pwd, salt, 2).toString());
            userService.updateById(user);
            return ResultObj.RESET_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }


    /**
     * 查询一个用户通过ID
     *
     * @param id
     * @return
     */
    @RequestMapping("loadUserById")
    public DataGridView loadUserById(Integer id) {
        return new DataGridView(userService.getById(id));
    }

    /**
     * 通过用户ID查询角色并选中已拥有的id
     */
    @RequestMapping("initRoleByUserId")
    public DataGridView initRoleByUserId(Integer id) {
        //查询所有可用的角色
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Map<String, Object>> listMaps = roleService.listMaps(queryWrapper);
        //查询当前用户拥有的角色ID集合
        List<Integer> currentUserRole = roleService.selectUserRoleIdsByUserId(id);
        for (Map<String, Object> map : listMaps) {
            Boolean LAY_CHECKED = false;
            Integer roleId = (Integer) map.get("id");
            for (Integer rid : currentUserRole) {
                if (roleId == rid) {
                    LAY_CHECKED = true;
                    break;
                }
            }
            map.put("LAY_CHECKED", LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(listMaps.size()), listMaps);
    }

    /**
     * 保存用户和角色之间的关系
     */
    @Log("保存用户和角色之间的关系")
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(Integer uid, Integer[] ids) {
        try {
            userService.saveUserRole(uid, ids);
            return ResultObj.DISPATCH_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}

