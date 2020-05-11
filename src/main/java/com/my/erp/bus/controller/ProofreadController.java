package com.my.erp.bus.controller;


import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.bus.domain.*;
import com.my.erp.bus.service.*;
import com.my.erp.bus.vo.ProofreadVo;
import com.my.erp.sys.cache.CacheAspect;
import com.my.erp.sys.common.*;
import com.my.erp.sys.config.Log;
import com.my.erp.sys.domain.Dept;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.DeptService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * my所用的表 前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-28
 */
@RestController
@RequestMapping("/proofread")
public class ProofreadController {

    private final static Logger logger = LoggerFactory.getLogger(ProofreadController.class);

    private final static int YWID = 11;
    private final static int JLID = 12;
    private final static int CWID = 13;

    @Autowired
    private ProofreadService proofreadService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private AutidingService autidingService;

    /**
     * 查询业务表
     *
     * @param proofreadVo
     * @return
     */
    @RequestMapping("/loadAllProofread")
    public DataGridView loadAllProofread(ProofreadVo proofreadVo) {
        //创建page对象
        IPage<Proofread> page = new Page<>(proofreadVo.getPage(), proofreadVo.getLimit());
        //创建条件器
        QueryWrapper<Proofread> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.eq(proofreadVo.getCustomerid() != null, "customerid", proofreadVo.getCustomerid());
        queryWrapper.eq(proofreadVo.getDeptid() != null, "deptid", proofreadVo.getDeptid());
        queryWrapper.eq(proofreadVo.getAuditingid() != null, "auditingid", proofreadVo.getAuditingid());
        queryWrapper.like(StringUtils.isNoneBlank(proofreadVo.getOperateperson()), "operateperson", proofreadVo.getOperateperson());
        queryWrapper.like(StringUtils.isNoneBlank(proofreadVo.getProofreadname()), "proofreadname", proofreadVo.getProofreadname());
        queryWrapper.like(StringUtils.isNoneBlank(proofreadVo.getRemark()), "remark", proofreadVo.getRemark());
        queryWrapper.ge(proofreadVo.getStartTime() != null, "createtime", proofreadVo.getStartTime());
        queryWrapper.le(proofreadVo.getEndTime() != null, "createtime", proofreadVo.getEndTime());
        queryWrapper.orderByDesc("createtime");
        //查询
        proofreadService.page(page, queryWrapper);
        List<Proofread> records = page.getRecords();
        for (Proofread proofread : records) {
            Customer customer = customerService.getById(proofread.getCustomerid());
            if (null != customer) {
                proofread.setCustomername(customer.getCustomername());
            }
            Dept dept = deptService.getById(proofread.getDeptid());
            if (null != dept) {
                proofread.setDepttitle(dept.getTitle());
            }
            Autiding autiding = autidingService.getById(proofread.getAuditingid());
            if (null != autiding) {
                proofread.setAuditingname(autiding.getAuditingname());
            }
            if (StringUtils.isNoneBlank(proofread.getContent())) {
                //将字符串转化成数组
                proofread.setContents(ContentUtil.stringToArray(proofread.getContent()));
            }
        }
        return new DataGridView(page.getTotal(), records);
    }

    /**
     * 添加业务表
     *
     * @param proofreadVo
     * @return
     */
    @Log("查询业务表")
    @RequestMapping("/addProofread")
    public ResultObj addProofread(ProofreadVo proofreadVo, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            //将字符串数组转化成字符串
            proofreadVo.setContent(ContentUtil.arrayToString(proofreadVo.getContents()));
            //设置操作人和部门
            proofreadVo.setOperateperson(user.getName());
            proofreadVo.setDeptid(user.getDeptid());
            //设置审批状态
            proofreadVo.setAuditingid(Constast.AUDITING_YENO_CANO);
            //设置创建时间
            proofreadVo.setCreatetime(new Date());
            if (proofreadVo.getCustomerimg() != null && proofreadVo.getCustomerimg().endsWith("_temp")) {
                String newName = MyFileUtils.renameFile(proofreadVo.getCustomerimg());
                proofreadVo.setCustomerimg(newName);
            }
            if (proofreadVo.getOperateimg() != null && proofreadVo.getOperateimg().endsWith("_temp")) {
                String newName = MyFileUtils.renameFile(proofreadVo.getOperateimg());
                proofreadVo.setOperateimg(newName);
            }
            proofreadService.save(proofreadVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改业务表
     *
     * @param proofreadVo
     * @return
     */
    @RequestMapping("/updateProofread")
    public ResultObj updateProofread(ProofreadVo proofreadVo) {
        try {
            //将字符串数组转化成字符串
            proofreadVo.setContent(ContentUtil.arrayToString(proofreadVo.getContents()));
            //如果不是默认图片
            if (!(proofreadVo.getCustomerimg() != null && proofreadVo.getCustomerimg().equals(Constast.IMG_DEFAUL))) {
                //如果图片的结尾是_temp
                if (proofreadVo.getCustomerimg().endsWith("_temp")) {
                    //设置新的名字将_temp去掉
                    String newName = MyFileUtils.renameFile(proofreadVo.getCustomerimg());
                    proofreadVo.setCustomerimg(newName);
                    //去除老名字 然后将原来的文件删除
                    String oldPath = proofreadService.getById(proofreadVo.getId()).getCustomerimg();
                    MyFileUtils.removeFileByPath(oldPath);
                }
            }
            if (!(proofreadVo.getOperateimg() != null && proofreadVo.getOperateimg().equals(Constast.IMG_DEFAUL))) {
                //如果图片的结尾是_temp
                if (proofreadVo.getOperateimg().endsWith("_temp")) {
                    //设置新的名字将_temp去掉
                    String newName = MyFileUtils.renameFile(proofreadVo.getOperateimg());
                    proofreadVo.setOperateimg(newName);
                    //去除老名字 然后将原来的文件删除
                    String oldPath = proofreadService.getById(proofreadVo.getId()).getOperateimg();
                    MyFileUtils.removeFileByPath(oldPath);
                }
            }
            //重新修改状态
            Integer auditingid = proofreadService.getById(proofreadVo.getId()).getAuditingid();
            if (auditingid == Constast.AUDITING_YERE_CAPA) auditingid = Constast.AUDITING_YENO_CAPA;
            if (auditingid == Constast.AUDITING_YERE_CANO) auditingid = Constast.AUDITING_YENO_CANO;
            if (auditingid == Constast.AUDITING_YERE_CARE) auditingid = Constast.AUDITING_YENO_CANO;
            if (auditingid == Constast.AUDITING_YEPA_CARE) auditingid = Constast.AUDITING_YEPA_CANO;
            if (auditingid == Constast.AUDITING_YENO_CARE) auditingid = Constast.AUDITING_YENO_CANO;
            proofreadVo.setAuditingid(auditingid);
            proofreadService.updateById(proofreadVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }


    /**
     * 删除业务表
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteProofread")
    public ResultObj batchDeleteProofread(Integer id, String customerimg, String operateimg) {
        try {
            //删除原文件
            MyFileUtils.removeFileByPath(customerimg);
            MyFileUtils.removeFileByPath(operateimg);
            proofreadService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 自动业务审核业务表
     *
     * @param proofreadVo
     * @return
     */
    @RequestMapping("/yewuAuditProofread")
    public ResultObj yewuAuditProofread(ProofreadVo proofreadVo) {
        ResultObj resultObj;
        try {
            //将字符串数组转化成字符串
            String auditcontent = ContentUtil.arrayToString(proofreadVo.getAuditcontents());
            //设置审核的内容
            proofreadVo.setAuditcontent(auditcontent);
            //获取业务单
            Proofread proofread = proofreadService.getById(proofreadVo.getId());
            //获取业务员填写的内容
            String content = proofread.getContent();
            //获取当前表的审核状态
            Integer auditingid = proofread.getAuditingid();
            //比较两个内容是否一致
            if (content.equals(auditcontent)) {
                resultObj = ResultObj.AUDIT_PASS;
                if (auditingid == Constast.AUDITING_YENO_CANO || auditingid == Constast.AUDITING_YERE_CANO) {
                    auditingid = Constast.AUDITING_YEPA_CANO;
                } else if (auditingid == Constast.AUDITING_YENO_CAPA || auditingid == Constast.AUDITING_YERE_CAPA) {
                    auditingid = Constast.AUDITING_YEPA_CAPA;
                } else if (auditingid == Constast.AUDITING_YENO_CARE || auditingid == Constast.AUDITING_YERE_CARE) {
                    auditingid = Constast.AUDITING_YEPA_CARE;
                }
            } else {
                resultObj = ResultObj.AUDIT_NOPASS;
                if (auditingid == Constast.AUDITING_YENO_CANO) {
                    auditingid = Constast.AUDITING_YERE_CANO;
                } else if (auditingid == Constast.AUDITING_YENO_CAPA) {
                    auditingid = Constast.AUDITING_YERE_CAPA;
                } else if (auditingid == Constast.AUDITING_YENO_CARE) {
                    auditingid = Constast.AUDITING_YERE_CARE;
                }
            }
            //设置当前表单的新状态和审核内容
            proofread.setAuditingid(auditingid);
            proofread.setAuditcontent(auditcontent);
            proofreadService.updateById(proofread);
            return resultObj;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.AUDIT_ERROR;
        }
    }


    /**
     * 财务审核业务表
     *
     * @param operateimg
     * @return
     */
    @RequestMapping("/caiwuAuditProofread")
    public ResultObj caiwuAuditProofread(Integer id, String operateimg, Boolean audit) {
        ResultObj resultObj;
        try {
            Proofread proofread = proofreadService.getById(id);
            //取出老的名字
            String oldOperateimg = proofread.getOperateimg();
            //取出原来的审核状态
            Integer auditingid = proofread.getAuditingid();
            if (!operateimg.equals(oldOperateimg)) {
                //设置新的名字将_temp去掉
                String newName = MyFileUtils.renameFile(operateimg);
                proofread.setOperateimg(newName);
                //去除老名字 然后将原来的文件删除
                MyFileUtils.removeFileByPath(oldOperateimg);
            }
            if (audit) {
                if (auditingid == Constast.AUDITING_YERE_CARE || auditingid == Constast.AUDITING_YERE_CANO)
                    auditingid = Constast.AUDITING_YERE_CAPA;
                if (auditingid == Constast.AUDITING_YENO_CARE || auditingid == Constast.AUDITING_YENO_CANO)
                    auditingid = Constast.AUDITING_YENO_CAPA;
                if (auditingid == Constast.AUDITING_YEPA_CARE || auditingid == Constast.AUDITING_YEPA_CANO)
                    auditingid = Constast.AUDITING_YEPA_CAPA;
                resultObj = ResultObj.AUDIT_PASS;
            } else {
                if (auditingid == Constast.AUDITING_YERE_CANO) auditingid = Constast.AUDITING_YERE_CARE;
                if (auditingid == Constast.AUDITING_YENO_CANO) auditingid = Constast.AUDITING_YENO_CARE;
                if (auditingid == Constast.AUDITING_YEPA_CANO) auditingid = Constast.AUDITING_YEPA_CARE;
                resultObj = ResultObj.AUDIT_NOPASS;
            }
            proofread.setAuditingid(auditingid);
            proofreadService.updateById(proofread);
            return resultObj;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return ResultObj.AUDIT_ERROR;
        }
    }

    /**
     *
     */
    @RequestMapping("getMassage")
    public Integer getMassage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        //要去查角色id
        List<Integer> rids =proofreadService.getRidByUid(user.getId());
        int count = 0;
        for (Integer rid : rids) {
            if(rid == YWID){
                count += proofreadService.getCountByYw(user.getName());
            }
            if(rid == JLID){
                count += proofreadService.getCountByJL(user.getDeptid());
            }
            if(rid == CWID){
                count += proofreadService.getCountByCw();
            }
        }
        return count;
    }

    /**
     * 加载所有可用的部门
     */
    @RequestMapping("loadAllAuditingForSelect")
    public DataGridView loadAllAuditingForSelect() {
        List<Autiding> list = autidingService.list();
        return new DataGridView(list);
    }


}

