package com.my.erp.sys.common;

/**
 * 一些常用的常量
 */
public interface Constast {
    /**
     * 状态码
     */
    Integer OK = 200;
    Integer ERROR = -1;
    /**
     * 菜单权限
     */
    String TYPE_MENU = "menu";
    String TYPE_PERMISSION = "permission";
    /**
     * 可用状态
     */
    Object AVAILABLE_TRUE = 1;
    Object AVAILABLE_FALSE = 0;
    /**
     * 用户类型
     */
    Integer USER_TYPE_SUPER = 0;
    Integer USER_TYPE_NORMAL = 1;
    /**
     * 是否展开
     */
    Integer OPEN_TRUE = 1;
    Integer OPEN_FALSE = 0;

    //默认密码
    String USER_DEFAULT_PWD = "123456";

    //默认图片传输地址
    String IMG_DEFAUL = "img/default.jpg";

    //对应的审核状态
    String AUDITING_NOALL = "待审核";
    String AUDITING_NOYEWU = "待审业务";
    String AUDITING_NOCAIWU = "待审财务";
    String AUDITING_YEWU_ERROR = "业务未通过";
    String AUDITING_CAIWU_ERROR = "财务未通过";
    String AUDITING_PASS = "已审核";
}
