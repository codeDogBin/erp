package com.my.erp.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.erp.bus.domain.Company;
import com.my.erp.bus.domain.Folder;
import com.my.erp.bus.service.CompanyService;
import com.my.erp.bus.service.CustomerService;
import com.my.erp.bus.service.FolderService;
import com.my.erp.sys.common.CreateCompanyFolderUtil;
import com.my.erp.sys.common.CreateFolderUtil;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.config.Log;
import com.my.erp.sys.domain.User;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公司 前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@RestController
@RequestMapping("bus")
public class CompanyController {
    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private FolderService folderService;

    private String[] folderName = new String[]{"证照", "文档", "财报"};

    /*
     * 功能描述 检查公司名是否占用
     * @Author bin
     * @param name
     * @return java.lang.String
     */
    @RequestMapping("examineCompanyNameAJAX.do")
    public String examineCompanyNameAJAX(String name) {
        Company company = companyService.findByName(name);
        if (company == null) {
            return "OK";
        }
        return "FAIL";
    }

    /*
     * 功能描述 创建公司
     * @Author bin
     * @param name
     * @param request
     * @return java.lang.String
     */
    @Log("创建新公司")
    @RequestMapping("/createCompanyAJAX.do")
    public String createCompanyAJAX(String name) {
        //创建新公司对象
        Company company = new Company();
        try {
            //创建公司
            company.setName(name);
            String way = CreateCompanyFolderUtil.createFloder();
            company.setWay(way);
            company.setCtime(new Timestamp(System.currentTimeMillis()));
            //数据库新增
            companyService.registerCompany(company);
            //创建文件夹 证照和文件
            for (int i = 0; i < folderName.length; i++) {
                String folderWay = CreateFolderUtil.createFolder(way);
                Folder folder = new Folder();
                folder.setCompanyid(company.getId());
                folder.setName(folderName[i]);
                folder.setFwayid(0);
                folder.setWay(folderWay);
                folder.setCtime(new Timestamp(System.currentTimeMillis()));
                folderService.insertFolder(folder);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return "FAIL";
        }
        return "OK";
    }

    /*
     * 功能描述 查询客户绑定的公司
     * @Author bin
     * @param customerid
     * @return java.util.List
     */
    @RequestMapping("/findCustomerCompanyAJAX.do")
    public List findCustomerCompanyAJAX(Integer customerid) {
        List<Company> customerCompanies = companyService.findCompanyByCustomerID(customerid);
        return customerCompanies;
    }

    /*
     * 功能描述 查询未绑定的的公司
     * @Author bin
     * @param name
     * @param customer_id
     * @return java.util.List<com.bin.domain.Company>
     */
    @RequestMapping("findUnbindCompanyByNameAJAX.do")
    public List<Company> findUnbindCompanyByName(String name, int customerid) {
        List<Company> companies = companyService.SelectByNameNoPage(name);
        List<Company> companyByUserID = companyService.findCompanyByCustomerID(customerid);
        companies.removeAll(companyByUserID);
        return companies;
    }

    /**
     * 去主页的
     *
     * @param session
     * @return
     */
    @RequestMapping("/toIndexAjax.do")
    public Map index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer customerid = user.getCustomerid();
        List<Company> companies;
        if (null == customerid || 0 == customerid) {//如果当前权限是读取所有公司的权限
            companies = companyService.selectAll();
        } else {
            companies = companyService.findCompanyByCustomerIDLimit(customerid, 0);
        }
        Map<String, Object> map = new HashMap();
        map.put("customerName", user.getName());
        map.put("companies", companies);
        return map;
    }

    /*
     * 功能描述 查询的接口
     * @Author bin
     * @param name
     * @return java.util.Map
     */
    @RequestMapping("/selectCompanyByNameIndexAJAX.do")
    public Map selectCompanyByNameIndexAJAX(String name) {
        List<Company> companies;
        companies = companyService.SelectByName(name);
        Map<String, Object> Map = new HashMap<>();
        Map.put("companies", companies);
        return Map;
    }

    /**
     * 查找客户
     *
     * @param customername
     * @return
     */
    @RequestMapping("/findCustomerByNameLikeAJAX.do")
    protected List findCustomerByNameLikeAJAX(String customername) {
        QueryWrapper qw = new QueryWrapper();
        qw.like(StringUtils.isNoneBlank(customername), "customername", customername);
        List list = customerService.list(qw);
        return list;

    }

    /*
     * 功能描述 绑定客户和公司
     * @Author bin
     * @param customer_id
     * @param company_id
     * @return voiz
     * 单个绑定
     */
    @Log("绑定公司和客户")
    @Transactional
    @RequestMapping("/bindCustomerCompanyAJAX.do")
    public synchronized String bindCustomerCompanyAJAX(int customerid, int[] companies_id) {
        try {
            for (int companyid : companies_id) {
                companyService.insertCustomerCompany(customerid, companyid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return "FAIL";
        }
        return "OK";
    }

    /*
     * 功能描述 unbindUserCompany 解绑客户和公司
     * @Author bin
     * @param customer_id
     * @param companies_id
     * @return java.lang.String
     */
    @Log("解绑客户和公司")
    @Transactional
    @RequestMapping("/unbindCustomerCompanyAJAX.do")
    public synchronized String unbindCustomerCompanyAJAX(int customerid, int[] companies_id) {
        try {
            for (int companyid : companies_id) {
                companyService.delCustomerCompany(customerid, companyid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return "FAIL";
        }
        return "OK";
    }

    /**
     * 修改公司的名称
     *
     * @param companyid
     * @param name
     * @return
     */
    @Log("修改公司名称")
    @RequestMapping("updateCompanyName")
    public synchronized ResultObj updateCompanyName(int companyid, String name) {
        try {
            Company company = companyService.getById(companyid);
            company.setName(name);
            companyService.updateById(company);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return ResultObj.UPDATE_ERROR;
        }
    }
}

