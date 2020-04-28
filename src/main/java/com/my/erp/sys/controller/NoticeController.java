package com.my.erp.sys.controller;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.domain.Notice;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.service.NoticeService;
import com.my.erp.sys.vo.NoticeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-04-19
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 查询公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("/loadAllNotice")
    public DataGridView loadAllNotice(NoticeVo noticeVo){
        //创建page对象
        IPage<Notice> page =new Page<>(noticeVo.getPage(),noticeVo.getLimit());
        //创建条件器
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        //设置条件
        queryWrapper.like(StringUtils.isNoneBlank(noticeVo.getTitle()),"title",noticeVo.getTitle());
        queryWrapper.like(StringUtils.isNoneBlank(noticeVo.getOpername()),"opername",noticeVo.getOpername());
        queryWrapper.ge(noticeVo.getStartTime()!=null,"createtime",noticeVo.getStartTime());
        queryWrapper.le(noticeVo.getEndTime()!=null,"createtime",noticeVo.getEndTime());
        queryWrapper.orderByDesc("createtime");
        //查询
        noticeService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加公告
     * @param noticeVo
     * @param session
     * @return
     */

    @RequestMapping("/addNotice")
    public ResultObj addNotice(NoticeVo noticeVo, HttpSession session){
        try {
            User user =(User) session.getAttribute("user");
            noticeVo.setCreatetime(new Date());
            noticeVo.setOpername(user.getName());
            noticeService.save(noticeVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改公告
     * @param noticeVo
     * @param session
     * @return
     */
    @RequestMapping("/updateNotice")
    public ResultObj updateNotice(NoticeVo noticeVo, HttpSession session){
        try {
            User user =(User) session.getAttribute("user");
            noticeService.updateById(noticeVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 批量删除公告
     * @param noticeVo
     * @return
     */
    @RequestMapping("/batchDeleteNotice")
    public ResultObj batchDeleteNotice(NoticeVo noticeVo){
        try {
            List<Integer> ids = Convert.toList(Integer.class, noticeVo.getIds());
            noticeService.removeByIds(ids);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @RequestMapping("/deleteNotice")
    public ResultObj batchDeleteNotice(Integer id){
        try {
            noticeService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return  ResultObj.DELETE_ERROR;
        }
    }
}

