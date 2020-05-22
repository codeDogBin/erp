package com.my.erp.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 业务管理的路由器
 */
@RequestMapping("bus")
@Controller
public class BusinessHandler {

    /**
     * 跳转到客户管理
     */
    @RequestMapping("toCustomerManager")
    public String toCustomerManger() {
        return "business/customer/customerManager";
    }

    /**
     * 跳转到供应商管理
     */
    @RequestMapping("toProviderManager")
    public String toProviderManager() {
        return "business/provider/providerManager";
    }


    /**
     * 跳转到供应商管理
     */
    @RequestMapping("toGoodsManager")
    public String toGoodsManager() {
        return "business/goods/goodsManager";
    }

    /**
     * 跳转到进货管理
     */
    @RequestMapping("toInportManager")
    public String toInportManager() {
        return "business/inport/inportManager";
    }

    /**
     * 跳转到退货查询管理
     */
    @RequestMapping("toOutportManager")
    public String toOutportManager() {
        return "business/outport/outportManager";
    }

    /**
     * 跳转到业务管理
     */
    @RequestMapping("toProofreadManager")
    public String toProofreadManager() {
        return "business/proofread/proofreadManager";
    }

    /**
     * 跳转到催债管理
     */
    @RequestMapping("toDebt")
    public String toDebt() {
        return "business/debt/debtManager";
    }
    /**
     * 跳转到审核管理
     */
    @RequestMapping("toAuditManager")
    public String toAuditManager() {
        return "business/audit/auditManager";
    }

    /**
     * 跳转到文件主页管理
     */
    @RequestMapping("toFileMain")
    public String toFileMain() {
        return "business/file/main";
    }

    /**
     * 跳转到分配管理
     */
    @RequestMapping("toFileDispatcher")
    public String toFileDispatcher() {
        return "business/file/dispatcherCrop";
    }

    /**
     * 跳转到回收站
     */
    @RequestMapping("toFileRecycle")
    public String toFileRecycle() {
        return "business/file/recycleBin";
    }

    /**
     * 去文件夹页面
     *
     * @return
     */
    @RequestMapping("/toFolder.do")
    public String toFolder() {
        return "business/file/folder";
    }
}


