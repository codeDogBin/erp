package com.my.erp.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务管理的路由器
 */
@RequestMapping("bus")
@Controller
public class BusinessController {

    /**
     * 跳转到客户管理
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManger(){
        return "business/customer/customerManager";
    }

    /**
     * 跳转到供应商管理
     */
    @RequestMapping("toProviderManager")
    public String toProviderManager(){
        return "business/provider/providerManager";
    }


    /**
     * 跳转到供应商管理
     */
    @RequestMapping("toGoodsManager")
    public String toGoodsManager(){
        return "business/goods/goodsManager";
    }

    /**
     * 跳转到进货管理
     */
    @RequestMapping("toInportManager")
    public String toInportManager(){
        return "business/inport/inportManager";
    }

    /**
     * 跳转到退货查询管理
     */
    @RequestMapping("toOutportManager")
    public String toOutportManager(){
        return "business/outport/outportManager";
    }
}


