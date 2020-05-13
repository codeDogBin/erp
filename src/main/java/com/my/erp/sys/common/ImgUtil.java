package com.my.erp.sys.common;

import com.my.erp.bus.domain.Fil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;



public class ImgUtil {

    private static String[] suffixes=new String[]{"jpg","JPG","png","PNG","jpeg","bmp"};
    public static boolean isImg(String suffix){
        for (String s : suffixes) {
            if(s.equals(suffix))
                return true;
        }
        return false;
    }

    public static List<Fil> setImgs(List<Fil> fils) throws IOException {
            for (Fil fil : fils) {
                setImg(fil);
            }
            return fils;
        }

    public static void setImg(Fil fil) throws IOException {
        String suffix = fil.getName().substring(fil.getName().lastIndexOf(".")+1);
        if(isImg(suffix)){//判断是否为照片文件
            String imgWay = fil.getWay();
            imgWay= imgWay.substring(MyFileUtils.UPLOAD_PATH.length()).replaceAll("\\\\","/");
            fil.setImgway(imgWay);
        }else if("doc".equals(suffix)||"docx".equals(suffix)){
            fil.setImgway("img/doc.jpg");
        } else if("pdf".equals(suffix)){
            fil.setImgway("img/pdf.jpg");
        } else{
            fil.setImgway("img/file.jpg");
        }
    }

}
