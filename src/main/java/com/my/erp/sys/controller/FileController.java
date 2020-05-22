package com.my.erp.sys.controller;

import cn.hutool.core.date.DateUtil;
import com.my.erp.sys.common.MyFileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 */
@RestController
@RequestMapping("file")
public class FileController {


    /**
     * 文件上传
     */
    @RequestMapping("uploadFile")
    public Map uploadFile(MultipartFile mf){
        //1.得到文件名
        String oldname = mf.getOriginalFilename();
        //2.根据文件名生产新的文件名
        String newName =  MyFileUtils.createNewFileName(oldname);
        //3.得到当前日期的
        String dirName = DateUtil.format(new Date(),"yyyy-MM-dd");
        //4.构造文件夹
        File dirFile = new File(MyFileUtils.UPLOAD_PATH+dirName);
        //5.判断文件夹是否存在
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        //6.构造文件对象
        File file = new File(dirFile,newName+"_temp");
        //7 把mf里面的图片信息写入file
        try {
            mf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("path",dirName+"/"+newName+"_temp");
        return  map;
    }

    /**
     * 文件下载
     */
    @RequestMapping("showImageByPath")
    public void showImageByPath(String path, HttpServletResponse response) throws IOException {
        if (StringUtils.isNoneBlank(path)){
            OutputStream outputStream = MyFileUtils.createResponseEntity(path, response);
            outputStream.flush();
            outputStream.close();
        }
    }

}
