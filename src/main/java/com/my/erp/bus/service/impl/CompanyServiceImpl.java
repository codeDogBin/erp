package com.my.erp.bus.service.impl;

import com.my.erp.bus.domain.Company;
import com.my.erp.bus.mapper.CompanyMapper;
import com.my.erp.bus.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公司 服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    
    
    /*
     * 功能描述 注册公司
     * @Author bin
     * @param company
     * @return boolean
     */
    public boolean registerCompany(Company company){
        int x = getBaseMapper().insert(company);
        return  x==1?true:false;
    }
    /*
     * 功能描述 通过公司id查询公司
     * @Author bin
     * @param companyId
     * @return com.bin.domain.Company
     */
    public Company getById(int companyId){
        return getBaseMapper().selectById(companyId);
    }
    /*
     * 功能描述 通过用户id查询公司
     * @Author bin
     * @param customerid
     * @return java.util.List<com.bin.domain.Company>
     */
    public List<Company> findCompanyByCustomerIDLimit(int customerid,int start){
        return  getBaseMapper().findCompanyByCustomerIDLimit(customerid, start);
    }
    public List<Company> findCompanyByCustomerID(int customerid){
        return  getBaseMapper().findCompanyByCustomerID(customerid);
    }

    /*
     * 功能描述 查询所有公司
     * @Author bin
     * @param
     * @return java.util.List<com.bin.domain.Company>
     */
    public List<Company> selectAll(){
        return getBaseMapper().selectAll();
    }
    /*
     * 功能描述 通过公司名称查找公司 分页版
     * @Author bin
     * @param companyName
     * @return java.util.List<com.bin.domain.Company>
     */
    public List<Company> SelectByName(String companyName){
        companyName="%"+companyName+"%";
        return getBaseMapper().selectByName(companyName);
    }
    //功能描述 通过公司名称查找公司 不分页版
    public List<Company> SelectByNameNoPage(String companyName){
        companyName="%"+companyName+"%";
        return getBaseMapper().selectByNameNoPage(companyName);
    }


    public Company findByName(String name){
        return getBaseMapper().findCompanyByName(name);
    }

    public boolean insertCustomerCompany(Integer customerid,Integer companyid){
        Integer x = getBaseMapper().insertCustomerCompany(customerid,companyid);
        return x==1?true:false;
    }
    /*
     * 功能描述 查询用户和公司的关联表
     * @Author bin
     * @param customerid
     * @return java.util.List<com.bin.domain.User_Company>
     */


    public void delCustomerCompany(Integer customerid,Integer companyid){
        getBaseMapper().deleteCustomerCompany(customerid,companyid);
    }


}
