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
    String IMG2_DEFAUL = "img/head.jpg";

    //对应的审核状态
    /**
     * YE 业务  CA财务
     * NO 未审核
     * PA 通过
     * RE 驳回
     */
    Integer AUDITING_YENO_CANO = 1;
    Integer AUDITING_YEPA_CANO = 2;
    Integer AUDITING_YENO_CAPA = 3;
    Integer AUDITING_YEPA_CAPA = 4;
    Integer AUDITING_YERE_CANO = 5;
    Integer AUDITING_YERE_CAPA = 6;
    Integer AUDITING_YENO_CARE = 7;
    Integer AUDITING_YEPA_CARE = 8;
    Integer AUDITING_YERE_CARE = 9;


}
