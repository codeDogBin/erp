package com.my.erp.bus.controller;


import cn.hutool.core.date.DateUtil;
import com.my.erp.bus.domain.Fil;
import com.my.erp.bus.domain.Folder;
import com.my.erp.bus.service.FilService;
import com.my.erp.bus.service.FolderService;
import com.my.erp.sys.common.MyFileUtils;
import com.my.erp.sys.common.TimeUtil;
import com.my.erp.sys.common.ZipUtil;
import com.my.erp.sys.controller.LoginHandler;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.zip.ZipOutputStream;

/*
 * 功能描述 系统定时自动任务
 * @Author bin
 * @param null
 * @return
 */
@Component
public class AutoController {

    private final static Logger logger = LoggerFactory.getLogger(AutoController.class);

    @Autowired
    private FolderService folderService;
    @Autowired
    private FilService filService;

    //表示每个月1日凌晨2点执行该任务
    @Scheduled(cron = "0 0 2 1 * ? ")
    public void autoZip() throws IOException {
        logger.info("自动整理上月资料启动");
        String[] lastTime = TimeUtil.getLastMonth();
        String yearMonth = lastTime[0];
        //设置时间戳
        Timestamp startTime = new Timestamp(TimeUtil.getLastTime().getTime());
        Timestamp endTime = new Timestamp(TimeUtil.getThisTime().getTime());
        //找到最近一个月内的所有文件
        List<Fil> fils = filService.getFilByMonth(startTime, endTime);
        //设置存储文件夹ID和对应路径的Map
        Map<Integer, String> wayMap = new HashMap();
        //将所有文件夹id和对应路径存入
        folderService.allContent(wayMap);
        //将所有文件的名字和文件存好
        List<String[]> filNameAndWay = filNameAndWay(fils, wayMap);
        //设置存储地址
        String zipName = yearMonth + "新增客户文件.zip";
        //将压缩文件的地址拼接并且创建好
        String zipFilePath = MyFileUtils.UPLOAD_PATH + File.separator + lastTime[1] + File.separator + lastTime[2];
        {
            File zip = new File(zipFilePath);
            if (!zip.exists()) {
                zip.mkdirs();
            }
        }
        zipFilePath = zipFilePath + File.separator + zipName;
        //压缩文件的-创建
        File zip = new File(zipFilePath);
        if (!zip.exists()) {
            zip.createNewFile();
        }
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
            ZipUtil.zipFile(filNameAndWay, zos); //调用util工具创建
            zos.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getLocalizedMessage());
        }
    }

    //每天早上凌晨5点触发发该任务
    @Scheduled(cron = "0 0 5 * * *")
    //自动删除30天前的过期文件和数据库中的内容
    public void autoDelete() {
        logger.info("清理过期文件启动");
        //获取时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 30 * 24 * 3600 * 1000l);
        //获取过期文件夹
        List<Folder> expireFol = folderService.getExpireFol(timestamp);
        //获取过期文件
        List<Fil> expireFil = filService.getExpireFil(timestamp);
        for (Fil fil : expireFil) {
            File file = new File(fil.getWay());
            file.delete();
            filService.delFilByID(fil.getId());
        }
        for (Folder folder : expireFol) {
            File file = new File(folder.getWay());
            deleteFolder(file);
            //删除数据库中文件夹下的内容
            folderService.delFolByID(folder.getId());
        }
    }

    //每天晚上11点触发发该任务
    @Scheduled(cron = "0 0 23 * * *")
    //自动删除前一天生成的图片文件
    public void autoDeleteImg() {
        logger.info("清理当天的照片启动");
        String dirName = MyFileUtils.UPLOAD_PATH + DateUtil.format(new Date(), "yyyy-MM-dd");
        File dirFile = new File(MyFileUtils.UPLOAD_PATH + dirName);
        if (!dirFile.exists()) {
            return;
        }
        String[] list = dirFile.list();
        for (String s : list) {
            if (s.length() > 5 && "_temp".equals(s.substring(s.length() - 5))) {
                new File(MyFileUtils.UPLOAD_PATH + dirName + s).delete();
            }
        }
    }


    //删除文件夹的方法
    public static void deleteFolder(File file) {
        if (file.isDirectory()) {
            String[] list = file.list();
            for (int i = 0; i < list.length; i++) {
                deleteFolder(new File(file, list[i]));
            }
        }
        file.delete();
    }

    /*
     * 功能描述 filNameAndWay 获取文件的全路径名和名称
     * @Author bin
     * @param fils 文件集合
     * @param wayMap 路径集合
     * @return java.util.List<java.lang.String[]>
     */
    private static List<String[]> filNameAndWay(List<Fil> fils, Map<Integer, String> wayMap) {
        List<String[]> filNameAndWay = new ArrayList<>();
        for (Fil fil : fils) {
            //如果父文件夹为null，则跳过
            if (wayMap.get(fil.getFolid()) == null)
                continue;
            filNameAndWay.add(new String[]{wayMap.get(fil.getFolid()) + fil.getName(), fil.getWay()});
        }
        return filNameAndWay;
    }
}
