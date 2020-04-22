package com.my.erp.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.domain.Dept;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.DeptService;
import com.my.erp.sys.service.UserService;
import com.my.erp.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    /**
     * 用户全查询
     */
    @RequestMapping("loadAllUser")
    public DataGridView loadAllUser(UserVo userVo){
        IPage<User> page = new Page<>(userVo.getPage(),userVo.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNoneBlank(userVo.getName()),"name",userVo.getName())
                .or().like(StringUtils.isNoneBlank(userVo.getName()),"loginname",userVo.getName());
        queryWrapper.like(StringUtils.isNoneBlank(userVo.getAddress()),"address",userVo.getAddress());
        queryWrapper.eq(userVo.getDeptid()!=null,"deptid",userVo.getDeptid());
        queryWrapper.eq("type", Constast.USER_TYPE_NORMAL);
        userService.page(page, queryWrapper);
        List<User> list = page.getRecords();
        for (User user : list) {
            Integer deptid = user.getDeptid();
            if (deptid!=null){ ;
                Dept one = deptService.getById(deptid);
                user.setDeptname(one.getTitle());
            }
            Integer mgr = user.getMgr();
            if(mgr != null){
                User one = userService.getById(mgr);
                user.setLeadername(one.getName());
            }
        }
        System.out.println("查询到的列表:"+list);
        return new DataGridView(list);
    }

}

