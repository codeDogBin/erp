package com.my.erp.sys.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 文件上传下载的工具类
 */
public class MyFileUtils {
    //文件上传的值
    public static  String UPLOAD_PATH="D:/upload/"; //默认值

    static {
        InputStream is = MyFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = properties.getProperty("filepath");
        if(null!= path){
            UPLOAD_PATH = path;
        }
    }

    /**
     * 根据老名字得到新名字
     * @param oldname
     * @return
     */
    public static String createNewFileName(String oldname) {
        String stuff = oldname.substring(oldname.lastIndexOf("."));
        return IdUtil.simpleUUID().toUpperCase()+stuff;
    }

    /**
     * 文件下载
     * @return
     */
    public static ResponseEntity createResponseEntity(String path) {
        //1构造文件对象
        File file = new File(UPLOAD_PATH,path);
        byte[] bytes = null;
        if(file.exists()){
            bytes = FileUtil.readBytes(file);
            //创建封装响应头信息的对象
            HttpHeaders headers = new HttpHeaders();
            //封装相应内容类型
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //创建ResponseEntity对象
            ResponseEntity entity = new ResponseEntity(bytes,headers, HttpStatus.CREATED);
            return entity;
        }

        return  null;
    }

    /**
     * 根据路径修改名称
     * @param goodsimg
     * @return
     */
    public static String renameFile(String goodsimg) {
        File file =new File(UPLOAD_PATH,goodsimg);
        String newName = goodsimg.replaceAll("_temp","");
        if(file.exists()){
               file.renameTo(new File(UPLOAD_PATH,newName));
        }
        return newName;
    }

    /**
     * 根据老路径删除图片
     * @param oldPath
     */
    public static void removeFileByPath(String oldPath) {
        if(!Constast.IMG_DEFAUL.equals(oldPath)) {
            File file = new File(UPLOAD_PATH, oldPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
