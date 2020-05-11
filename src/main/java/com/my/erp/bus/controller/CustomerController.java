package com.my.erp.bus.controller;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.Customer;
import com.my.erp.bus.domain.Provider;
import com.my.erp.bus.service.CustomerService;
import com.my.erp.bus.vo.CustomerVo;
import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.config.Log;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-24
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 查询客户
     * @param customerVo
     * @return
     */
    @RequestMapping("/loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo){
        //创建page对象
        IPage<Customer> page =new Page<>(customerVo.getPage(),customerVo.getLimit());
        //创建条件器
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.like(StringUtils.isNoneBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        queryWrapper.like(StringUtils.isNoneBlank(customerVo.getPhone()),"phone",customerVo.getPhone());
        queryWrapper.ge(StringUtils.isNoneBlank(customerVo.getConnectionperson()),"connectionperson",customerVo.getConnectionperson());
        //查询
        customerService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加客户
     * @param customerVo
     * @return
     */
    @Log("添加客户")
    @RequestMapping("/addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo){
        try {
            customerService.save(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改客户
     * @param customerVo
     * @param session
     * @return
     */
    @Log("修改客户")
    @RequestMapping("/updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo, HttpSession session){
        try {
            customerService.updateById(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 批量删除客户
     * @param customerVo
     * @return
     */
    @Log("批量删除客户")
    @Transactional
    @RequestMapping("/batchDeleteCustomer")
    public ResultObj batchDeleteCustomer(CustomerVo customerVo){
        try {
            List<Integer> ids = Convert.toList(Integer.class, customerVo.getIds());
            for (Integer id : ids) {
                Integer integer = customerService.getccByCusId(id);
                if(integer!=0){
                    return ResultObj.DELETE_ERROR;
                }
            }
            customerService.removeByIds(ids);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 删除客户
     * @param id
     * @return
     */
    @Log("删除客户")
    @RequestMapping("/deleteCustomer")
    public ResultObj batchDeleteCustomer(Integer id){
        try {
            Integer integer = customerService.getccByCusId(id);
            if(integer!=0){
                return ResultObj.DELETE_ERROR;
            }
            customerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有可用的客户
     */
    @RequestMapping("loadAllCustomerForSelect")
    public DataGridView loadAllCustomerForSelect(){
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Customer> list = customerService.list(queryWrapper);
        return  new DataGridView(list);
    }
}

