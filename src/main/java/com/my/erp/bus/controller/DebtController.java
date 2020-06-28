package com.my.erp.bus.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.Customer;
import com.my.erp.bus.domain.Debt;
import com.my.erp.bus.service.CustomerService;
import com.my.erp.bus.service.DebtService;
import com.my.erp.bus.vo.DebtVo;

import com.my.erp.sys.common.Constast;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.config.Log;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 债务 前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/debt")
public class DebtController {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(DebtController.class);

    @Autowired
    private DebtService debtService;
    @Autowired
    private CustomerService customerService;

    /**
     * 查询债务
     * @param debtVo
     * @return
     */
    @RequestMapping("/loadAllDebt")
    public DataGridView loadAllDebt(DebtVo debtVo){
        //创建page对象
        IPage<Debt> page =new Page<>(debtVo.getPage(),debtVo.getLimit());
        //创建条件器
        QueryWrapper<Debt> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq(debtVo.getCustomerid()!=null,"customerid",debtVo.getCustomerid());
        queryWrapper.ge(debtVo.getStartTime() != null, "cuizhangtime", debtVo.getStartTime());
        queryWrapper.le(debtVo.getEndTime() != null, "cuizhangtime", debtVo.getEndTime());
        //查询
        debtService.page(page,queryWrapper);
        List<Debt> records = page.getRecords();
        List<Debt> depts = setData(records);
        return new DataGridView(page.getTotal(),depts);
    }
    /**
     * 查询债务
     * @param debtVo
     * @return
     */
    @RequestMapping("/loadCzDebt")
    public DataGridView loadCzDebt(DebtVo debtVo){
        //创建page对象
        IPage<Debt> page =new Page<>(debtVo.getPage(),debtVo.getLimit());
        //创建条件器
        QueryWrapper<Debt> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq("ispay","0");
        queryWrapper.le("cuizhangtime",new Date());
        //查询
        debtService.page(page,queryWrapper);
        List<Debt> records = page.getRecords();
        List<Debt> depts = setData(records);
        return new DataGridView(page.getTotal(),depts);
    }




    /**
     * 添加债务
     * @param debtVo
     * @return
     */
    @Log("添加债务")
    @RequestMapping("/addDebt")
    public ResultObj addDebt(DebtVo debtVo){
        try {
            debtVo.setIspay(false);
            debtService.save(debtVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return ResultObj.ADD_ERROR;
        }
    }
    /**
     * 修改债务
     * @param debtVo
     * @param session
     * @return
     */
    @Log("修改债务")
    @RequestMapping("/updateDebt")
    public ResultObj updateDebt(DebtVo debtVo, HttpSession session){
        try {
            debtService.updateById(debtVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除债务
     * @param id
     * @return
     */
    @Log("删除债务")
    @RequestMapping("/deleteDebt")
    public ResultObj deleteDebt(Integer id){
        try {
            debtService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 审核债务
     * @param id
     * @return
     */
    @Log("审核债务")
    @RequestMapping("/auditDebt")
    public ResultObj auditDebt(Integer id){
        try {
            Debt debt = debtService.getById(id);
            debt.setIspay(true);
            debtService.updateById(debt);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return  ResultObj.OPERATE_ERROR;
        }
    }

    /**
     * 催账债务
     * @param id
     * @return
     */
    @Log("催账债务")
    @RequestMapping("/czDebt")
    public ResultObj czDebt(Integer id){
        try {
            Debt debt = debtService.getById(id);
            debt.setIscz(true);
            debtService.updateById(debt);
            return ResultObj.OPERATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            return  ResultObj.OPERATE_ERROR;
        }
    }






    private List<Debt> setData(List<Debt> records){
        Date date = new Date();
        for (Debt record : records) {
            Customer customer = customerService.getById(record.getCustomerid());
            record.setCustomername(customer.getCustomername());
            record.setConnectionperson(customer.getConnectionperson());
            record.setCustomertel(customer.getPhone());
            if (record.isIspay() == true){
                record.setState(Constast.STATE_YF);
            }else if( record.getEndtime().before(date) ){
                record.setState(Constast.STATE_ZZJSWF);
            }else if( record.getCuizhangtime().before(date) && record.isIscz()){
                record.setState(Constast.STATE_CZYCWF);
            }else if( record.getCuizhangtime().before(date) && !record.isIscz()){
                record.setState(Constast.STATE_CZWCWF);
            }
            System.out.println("dept:"+record);
        }
        return  records;
    }
    
}

