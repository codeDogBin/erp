package com.my.erp.bus.cache;


import com.my.erp.bus.domain.*;
import com.my.erp.sys.cache.CachePool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@EnableAspectJAutoProxy
public class BusinessCacheAspect {
    /**
     * 日志处理类
     */
    private Log log = LogFactory.getLog(BusinessCacheAspect.class);

    //声明一个缓存容器
    private Map<String,Object> CACHE_CONTAINER = CachePool.CACHE_CONTAINER;

    //声明切面表达式
    private static final String POINTCUT_CUSTOMER_UPDATE="execution (* com.my.erp.bus.service.impl.CustomerServiceImpl.updateById(..))";
    private static final String POINTCUT_CUSTOMER_GET="execution (* com.my.erp.bus.service.impl.CustomerServiceImpl.getById(..))";
    private static final String POINTCUT_CUSTOMER_REMOVE="execution (* com.my.erp.bus.service.impl.CustomerServiceImpl.removeById(..))";
    private static final String POINTCUT_CUSTOMER_ADD="execution (* com.my.erp.bus.service.impl.CustomerServiceImpl.save(..))";
    private static final String POINTCUT_CUSTOMER_ROMOVES="execution (* com.my.erp.bus.service.impl.CustomerServiceImpl.removeByIds(..))";

    private static final String CACHE_CUSTOMER_PROFIX="customer:";

    /**
     * 客户查询切入
     */
    @Around(value=POINTCUT_CUSTOMER_GET)
    public Object cacheCustomerGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id =(Integer) joinPoint.getArgs()[0];
        //查询缓存中是否有数据
        Customer result =(Customer) CACHE_CONTAINER.get(CACHE_CUSTOMER_PROFIX+id);
        log.info("已从缓存中找到客户对象"+CACHE_CUSTOMER_PROFIX+id);
        if(result!=null){
            return result;
        }else{
            //将数据存入缓存 并且返回
            log.info("未从缓存中找到客户对象,去数据库查询并放入缓存");
            Customer res2 = (Customer) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX+res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 客户更新的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_CUSTOMER_UPDATE)
    public Object cacheCustomerUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Customer customer = (Customer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Customer newcustomer = (Customer) CACHE_CONTAINER.get(customer.getId());
            if (null == newcustomer) {
                newcustomer = new Customer();
                BeanUtils.copyProperties(customer, newcustomer);
                log.info("客户对象缓存已更新"+CACHE_CUSTOMER_PROFIX + newcustomer.getId());
                CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + newcustomer.getId(), newcustomer);
            }
        }
        return isSuccess;
    }

    /**
     * 客户添加的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_CUSTOMER_ADD)
    public Object cacheCustomerAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Customer customer = (Customer) joinPoint.getArgs()[0];
        //执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            CACHE_CONTAINER.put(CACHE_CUSTOMER_PROFIX + customer.getId(), customer);
            log.info("客户对象缓存已增加"+CACHE_CUSTOMER_PROFIX + customer.getId());
        }
        return isSuccess;
    }


    /**
     * 客户删除的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_CUSTOMER_REMOVE)
    public Object cacheCustomerDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_CUSTOMER_PROFIX+id);
            log.info("已从缓存中删除客户对象"+CACHE_CUSTOMER_PROFIX+id);
        }
        return isSuccess;
    }

    /**
     * 客户批量删除的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_CUSTOMER_ROMOVES)
    public Object cacheCustomerBatchDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        List<Serializable> idList = (List<Serializable>) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            for (Serializable id : idList) {
                CACHE_CONTAINER.remove(CACHE_CUSTOMER_PROFIX+id);
                log.info("已从缓存中删除客户对象"+CACHE_CUSTOMER_PROFIX+id);
            }
        }
        return isSuccess;
    }

    //声明切面表达式
    private static final String POINTCUT_PROVIDER_UPDATE="execution (* com.my.erp.bus.service.impl.ProviderServiceImpl.updateById(..))";
    private static final String POINTCUT_PROVIDER_GET="execution (* com.my.erp.bus.service.impl.ProviderServiceImpl.getById(..))";
    private static final String POINTCUT_PROVIDER_REMOVE="execution (* com.my.erp.bus.service.impl.ProviderServiceImpl.removeById(..))";
    private static final String POINTCUT_PROVIDER_ADD="execution (* com.my.erp.bus.service.impl.ProviderServiceImpl.save(..))";
    private static final String POINTCUT_PROVIDER_ROMOVES="execution (* com.my.erp.bus.service.impl.ProviderServiceImpl.removeByIds(..))";

    private static final String CACHE_PROVIDER_PROFIX="provider:";

    /**
     * 供应商查询切入
     */
    @Around(value=POINTCUT_PROVIDER_GET)
    public Object cacheProviderGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id =(Integer) joinPoint.getArgs()[0];
        //查询缓存中是否有数据
        Provider result =(Provider) CACHE_CONTAINER.get(CACHE_PROVIDER_PROFIX+id);
        log.info("已从缓存中找到供应商对象"+CACHE_PROVIDER_PROFIX+id);
        if(result!=null){
            return result;
        }else{
            //将数据存入缓存 并且返回
            log.info("未从缓存中找到供应商对象,去数据库查询并放入缓存");
            Provider res2 = (Provider) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_PROVIDER_PROFIX+res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 供应商更新的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_PROVIDER_UPDATE)
    public Object cacheProviderUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Provider provider = (Provider) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Provider newprovider = (Provider) CACHE_CONTAINER.get(provider.getId());
            if (null == newprovider) {
                newprovider = new Provider();
                BeanUtils.copyProperties(provider, newprovider);
                log.info("供应商对象缓存已更新"+CACHE_PROVIDER_PROFIX + newprovider.getId());
                CACHE_CONTAINER.put(CACHE_PROVIDER_PROFIX + newprovider.getId(), newprovider);
            }
        }
        return isSuccess;
    }

    /**
     * 供应商添加的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_PROVIDER_ADD)
    public Object cacheProviderAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Provider provider = (Provider) joinPoint.getArgs()[0];
        //执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            CACHE_CONTAINER.put(CACHE_PROVIDER_PROFIX + provider.getId(), provider);
            log.info("供应商对象缓存已增加"+CACHE_PROVIDER_PROFIX + provider.getId());
        }
        return isSuccess;
    }


    /**
     * 供应商删除的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_PROVIDER_REMOVE)
    public Object cacheProviderDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_PROVIDER_PROFIX+id);
            log.info("已从缓存中删除供应商对象"+CACHE_PROVIDER_PROFIX+id);
        }
        return isSuccess;
    }

    /**
     * 供应商批量删除的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_PROVIDER_ROMOVES)
    public Object cacheProviderBatchDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        List<Serializable> idList = (List<Serializable>) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            for (Serializable id : idList) {
                CACHE_CONTAINER.remove(CACHE_PROVIDER_PROFIX+id);
                log.info("已从缓存中删除供应商对象"+CACHE_PROVIDER_PROFIX+id);
            }
        }
        return isSuccess;
    }

    //声明切面表达式
    private static final String POINTCUT_GOODS_UPDATE="execution (* com.my.erp.bus.service.impl.GoodsServiceImpl.updateById(..))";
    private static final String POINTCUT_GOODS_GET="execution (* com.my.erp.bus.service.impl.GoodsServiceImpl.getById(..))";
    private static final String POINTCUT_GOODS_REMOVE="execution (* com.my.erp.bus.service.impl.GoodsServiceImpl.removeById(..))";
    private static final String POINTCUT_GOODS_ADD="execution (* com.my.erp.bus.service.impl.GoodsServiceImpl.save(..))";

    private static final String CACHE_GOODS_PROFIX="goods:";

    /**
     * 商品查询切入
     */
    @Around(value=POINTCUT_GOODS_GET)
    public Object cacheGoodsGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id =(Integer) joinPoint.getArgs()[0];
        //查询缓存中是否有数据
        Goods result =(Goods) CACHE_CONTAINER.get(CACHE_GOODS_PROFIX+id);
        log.info("已从缓存中找到商品对象"+CACHE_GOODS_PROFIX+id);
        if(result!=null){
            return result;
        }else{
            //将数据存入缓存 并且返回
            log.info("未从缓存中找到商品对象,去数据库查询并放入缓存");
            Goods res2 = (Goods) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_GOODS_PROFIX+res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 商品更新的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_GOODS_UPDATE)
    public Object cacheGoodsUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Goods goods = (Goods) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Goods newgoods = (Goods) CACHE_CONTAINER.get(goods.getId());
            if (null == newgoods) {
                newgoods = new Goods();
                BeanUtils.copyProperties(goods, newgoods);
                log.info("商品对象缓存已更新"+CACHE_GOODS_PROFIX + newgoods.getId());
                CACHE_CONTAINER.put(CACHE_GOODS_PROFIX + newgoods.getId(), newgoods);
            }
        }
        return isSuccess;
    }

    /**
     * 商品添加的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_GOODS_ADD)
    public Object cacheGoodsAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Goods goods = (Goods) joinPoint.getArgs()[0];
        //执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            CACHE_CONTAINER.put(CACHE_GOODS_PROFIX + goods.getId(), goods);
            log.info("商品对象缓存已增加"+CACHE_GOODS_PROFIX + goods.getId());
        }
        return isSuccess;
    }


    /**
     * 商品删除的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_GOODS_REMOVE)
    public Object cacheGoodsDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_GOODS_PROFIX+id);
            log.info("已从缓存中删除商品对象"+CACHE_GOODS_PROFIX+id);
        }
        return isSuccess;
    }


    //声明切面表达式
    private static final String POINTCUT_PROOFREAD_UPDATE="execution (* com.my.erp.bus.service.impl.ProofreadServiceImpl.updateById(..))";
    private static final String POINTCUT_PROOFREAD_GET="execution (* com.my.erp.bus.service.impl.ProofreadServiceImpl.getById(..))";
    private static final String POINTCUT_PROOFREAD_REMOVE="execution (* com.my.erp.bus.service.impl.ProofreadServiceImpl.removeById(..))";
    private static final String POINTCUT_PROOFREAD_ADD="execution (* com.my.erp.bus.service.impl.ProofreadServiceImpl.save(..))";

    private static final String CACHE_PROOFREAD_PROFIX="proofread:";

    /**
     * 业务查询切入
     */
    @Around(value=POINTCUT_PROOFREAD_GET)
    public Object cacheProofreadGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id =(Integer) joinPoint.getArgs()[0];
        //查询缓存中是否有数据
        Proofread result =(Proofread) CACHE_CONTAINER.get(CACHE_PROOFREAD_PROFIX+id);
        log.info("已从缓存中找到业务对象"+CACHE_PROOFREAD_PROFIX+id);
        if(result!=null){
            return result;
        }else{
            //将数据存入缓存 并且返回
            log.info("未从缓存中找到业务对象,去数据库查询并放入缓存");
            Proofread res2 = (Proofread) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_PROOFREAD_PROFIX+res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 业务更新的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_PROOFREAD_UPDATE)
    public Object cacheProofreadUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Proofread proofread = (Proofread) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Proofread newproofread = (Proofread) CACHE_CONTAINER.get(proofread.getId());
            if (null == newproofread) {
                newproofread = new Proofread();
                BeanUtils.copyProperties(proofread, newproofread);
                log.info("业务对象缓存已更新"+CACHE_PROOFREAD_PROFIX + newproofread.getId());
                CACHE_CONTAINER.put(CACHE_PROOFREAD_PROFIX + newproofread.getId(), newproofread);
            }
        }
        return isSuccess;
    }

    /**
     * 业务添加的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_PROOFREAD_ADD)
    public Object cacheProofreadAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Proofread proofread = (Proofread) joinPoint.getArgs()[0];
        //执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            CACHE_CONTAINER.put(CACHE_PROOFREAD_PROFIX + proofread.getId(), proofread);
            log.info("业务对象缓存已增加"+CACHE_PROOFREAD_PROFIX + proofread.getId());
        }
        return isSuccess;
    }


    /**
     * 业务删除的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_PROOFREAD_REMOVE)
    public Object cacheProofreadDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id = (Integer) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            //删除缓存
            CACHE_CONTAINER.remove(CACHE_PROOFREAD_PROFIX+id);
            log.info("已从缓存中删除业务对象"+CACHE_PROOFREAD_PROFIX+id);
        }
        return isSuccess;
    }
    
    
    //声明切面表达式
    private static final String POINTCUT_COMPANY_UPDATE="execution (* com.my.erp.bus.service.impl.CompanyServiceImpl.updateById(..))";
    private static final String POINTCUT_COMPANY_GET="execution (* com.my.erp.bus.service.impl.CompanyServiceImpl.getById(..))";
    private static final String POINTCUT_COMPANY_ADD="execution (* com.my.erp.bus.service.impl.CompanyServiceImpl.registerCompany(..))";
    
    private static final String CACHE_COMPANY_PROFIX="company:";

    /**
     * 公司查询切入
     */
    @Around(value=POINTCUT_COMPANY_GET)
    public Object cacheCompanyGet(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Integer id =(Integer) joinPoint.getArgs()[0];
        //查询缓存中是否有数据
        Company result =(Company) CACHE_CONTAINER.get(CACHE_COMPANY_PROFIX+id);
        log.info("已从缓存中找到公司对象"+CACHE_COMPANY_PROFIX+id);
        if(result!=null){
            return result;
        }else{
            //将数据存入缓存 并且返回
            log.info("未从缓存中找到公司对象,去数据库查询并放入缓存");
            Company res2 = (Company) joinPoint.proceed();
            CACHE_CONTAINER.put(CACHE_COMPANY_PROFIX+res2.getId(), res2);
            return res2;
        }
    }

    /**
     * 公司更新的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_COMPANY_UPDATE)
    public Object cacheCompanyUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Company company = (Company) joinPoint.getArgs()[0];
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            Company newcompany = (Company) CACHE_CONTAINER.get(company.getId());
            if (null == newcompany) {
                newcompany = new Company();
                BeanUtils.copyProperties(company, newcompany);
                log.info("公司对象缓存已更新"+CACHE_COMPANY_PROFIX + newcompany.getId());
                CACHE_CONTAINER.put(CACHE_COMPANY_PROFIX + newcompany.getId(), newcompany);
            }
        }
        return isSuccess;
    }

    /**
     * 公司添加的切入
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value=POINTCUT_COMPANY_ADD)
    public Object cacheCompanyAdd(ProceedingJoinPoint joinPoint) throws Throwable {
        //取出第一个参数
        Company company = (Company) joinPoint.getArgs()[0];
        //执行目标方法
        Boolean isSuccess = (Boolean) joinPoint.proceed();
        if (isSuccess) {
            CACHE_CONTAINER.put(CACHE_COMPANY_PROFIX + company.getId(), company);
            log.info("公司对象缓存已增加"+CACHE_COMPANY_PROFIX + company.getId());
        }
        return isSuccess;
    }


}
