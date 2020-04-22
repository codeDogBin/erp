package com.my.erp.sys.common;


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
    Integer USER_TYPE_SUPER =  0;
    Integer USER_TYPE_NORMAL =  1;
    /**
     * 是否展开
     */
    Integer OPEN_TRUE = 1;
    Integer OPEN_FALSE = 0;
}
