package com.my.erp.sys.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class CreateCompanyFolderUtil {
    //创建公司的文件夹的工具类
    public static  String rootPath= MyFileUtils.UPLOAD_PATH+"/company/"; //默认值

    static {
        InputStream is = MyFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String path = properties.getProperty("companypath");
        if(null!= path){
            rootPath = path;
        }
    }
    public static String createFloder() throws Exception {
        File file = createFile();
        while(file.exists()){
            file = createFile();
        }
        try {
            file.mkdir();
            return file.getPath();
        } catch (Exception e) {
            throw new Exception("文件夹创建失败");
        }
    }
    private static File createFile(){
        return new File(rootPath +File.separator+ UUID.randomUUID().toString().replaceAll("-", "")+File.separator);
    }

}
