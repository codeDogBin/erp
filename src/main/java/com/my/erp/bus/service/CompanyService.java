package com.my.erp.bus.service;

import com.my.erp.bus.domain.Company;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 公司 服务类
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
public interface CompanyService extends IService<Company> {
    /*
     * 功能描述 注册公司
     * @Author bin
     * @param company
     * @return boolean
     */
    public boolean registerCompany(Company company);

    /*
     * 功能描述 通过公司id查询公司
     * @Author bin
     * @param companyId
     * @return com.bin.domain.Company
     */



    @Override
    Company getById(Serializable id);


    /*
     * 功能描述 通过用户id查询公司
     * @Author bin
     * @param customerid
     * @return java.util.List<com.bin.domain.Company>
     */
    public List<Company> findCompanyByCustomerIDLimit(int customerid,int start);

    public List<Company> findCompanyByCustomerID(int customerid);

    /*
     * 功能描述 查询所有公司
     * @Author bin
     * @param
     * @return java.util.List<com.bin.domain.Company>
     */
    public List<Company> selectAll();
    /*
     * 功能描述 通过公司名称查找公司 分页版
     * @Author bin
     * @param companyName
     * @return java.util.List<com.bin.domain.Company>
     */
    public List<Company> SelectByName(String companyName);
    //功能描述 通过公司名称查找公司 不分页版

    public List<Company> SelectByNameNoPage(String companyName);




    public Company findByName(String name);

    boolean insertCustomerCompany(Integer customerid,Integer companyid);



    public void delCustomerCompany(Integer customerid,Integer companyid);


}
