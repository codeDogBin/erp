package com.my.erp.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用于返回的登录信息
 */
@Data@AllArgsConstructor@NoArgsConstructor
public class ResultObj {
    //登录相关
    public static final ResultObj LOGIN_SUCCESS = new ResultObj(Constast.OK,"登陆成功");
    public static final ResultObj LOGIN_ERROR_PASS = new ResultObj(Constast.ERROR,"用户名或密码不正确");
    public static final ResultObj LOGIN_ERROR_CODE = new ResultObj(Constast.ERROR,"验证码不正确");
    //删除
    public static final ResultObj DELETE_SUCCESS = new ResultObj(Constast.OK,"删除成功");
    public static final ResultObj DELETE_ERROR = new ResultObj(Constast.ERROR,"删除失败");
    //新增
    public static final ResultObj ADD_SUCCESS = new ResultObj(Constast.OK,"添加成功");
    public static final ResultObj ADD_ERROR = new ResultObj(Constast.ERROR,"添加失败");
    //修改
    public static final ResultObj UPDATE_SUCCESS = new ResultObj(Constast.OK,"修改成功");
    public static final ResultObj UPDATE_ERROR = new ResultObj(Constast.ERROR,"修改失败");
    //分配
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(Constast.OK,"分配成功");
    public static final ResultObj DISPATCH_ERROR = new ResultObj(Constast.ERROR,"分配失败");
    //重置
    public static final ResultObj RESET_SUCCESS = new ResultObj(Constast.OK,"重置成功");
    public static final ResultObj RESET_ERROR = new ResultObj(Constast.ERROR,"重置失败");
    //操作
    public static final ResultObj OPERATE_SUCCESS = new ResultObj(Constast.OK,"操作成功");
    public static final ResultObj OPERATE_ERROR = new ResultObj(Constast.ERROR,"操作失败");
    //审核
    public static final ResultObj AUDIT_PASS = new ResultObj(Constast.OK,"审核通过");
    public static final ResultObj AUDIT_NOPASS = new ResultObj(Constast.OK,"审核不通过");
    public static final ResultObj AUDIT_ERROR = new ResultObj(Constast.ERROR,"审核失败");
    //用户名可用吗
    public static final ResultObj LOGINNAME_SUCCESS = new ResultObj(Constast.OK,"用户名可用");
    public static final ResultObj lOGINNNAE_ERROR = new ResultObj(Constast.ERROR,"用户名不可用");

    private Integer code;
    private String msg;
}
