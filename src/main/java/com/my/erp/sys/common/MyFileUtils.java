package com.my.erp.sys.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

/**
 * 文件上传下载的工具类
 */
public class MyFileUtils {
    //文件上传的值
    public static  String UPLOAD_PATH="D:/upload/"; //默认值
    public static  String UPLOAD_COMPANY_PATH="D:/upload/company"; //默认值
    public static  String LOG_PATH="D:/logger"; //默认值

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
        String path2 = properties.getProperty("companypath");
        if(null!= path2){
            UPLOAD_COMPANY_PATH = path2;
        }
        String path3 = properties.getProperty("logpath");
        if(null!= path3){
            UPLOAD_COMPANY_PATH = path3;
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
    public static  OutputStream createResponseEntity(String path, HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("application/octet-stream");
        //1构造文件对象
        File file = new File(UPLOAD_PATH,path);
        Thumbnails.of(file).size(800,800).outputQuality(0.3f).toOutputStream(outputStream);
        return outputStream;
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
        if(!Constast.IMG_DEFAUL.equals(oldPath) && !Constast.IMG2_DEFAUL.equals(oldPath)) {
            File file = new File(UPLOAD_PATH, oldPath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
