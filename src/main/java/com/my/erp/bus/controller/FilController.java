package com.my.erp.bus.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.my.erp.bus.domain.Fil;
import com.my.erp.bus.domain.Folder;
import com.my.erp.bus.service.FilService;
import com.my.erp.bus.service.FolderService;
import com.my.erp.sys.common.ImgUtil;
import com.my.erp.sys.common.MyFileUtils;
import com.my.erp.sys.common.TimeUtil;
import com.my.erp.sys.config.Log;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/bus")
public class FilController {

    private final static Logger logger = LoggerFactory.getLogger(FilController.class);
    @Autowired
    private FilService filService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private AutoController autoController;

    private static String[] suffixes=new String[]{"jpg","JPG","png","PNG","jpeg","bmp","gif"};

    public static boolean isImg(String suffix){
        for (String s : suffixes) {
            if(s.equals(suffix))
                return true;
        }
        return false;
    }

    /*
     * 功能描述 上传文件
     * @Author bin
     * @param multipartFiles
     * @param company_id
     * @param fway_id
     * @param request
     * @return java.lang.String
     */
    @Log("上传文件")
    @Transactional
    @RequestMapping("/uploadFile.do")
    public Map uploadFile(@RequestParam("file") MultipartFile[]  multipartFiles,
                          String fway_id, HttpServletRequest request){
        Map<String,String> map = new HashMap();
        if (multipartFiles[0].isEmpty()) {
            map.put("state","FAIL");
            map.put("msg","文件为空,检查是否选择文件");
            return map;
        }
        Folder folder = folderService.findByFidAsId(Integer.parseInt(fway_id));
        String filePath = folder.getWay();
        try {
            for (MultipartFile origFile : multipartFiles) {
                String fileName = origFile.getOriginalFilename();
                String way;
                way = UUID.randomUUID().toString().replaceAll("-","");
                Fil fil = new Fil();
                fil.setName(fileName);
                fil.setWay(folder.getWay()+File.separator+way);
                fil.setFolid(Integer.parseInt(fway_id));
                fil.setCtime(new Timestamp(System.currentTimeMillis()));
                fil.setState(1);
                fil = filService.chongMing(fil);
                try {
                    //上传目的地（staticResourcesTest文件夹下）
                    File file = new File(filePath, way);
                    origFile.transferTo(file);
                    ImgUtil.createImg(fil);
                    filService.insertFil(fil);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.toString());
                    map.put("state","FAIL");
                    map.put("msg","文件上传失败，请检查重试");
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            map.put("state","FAIL");
            map.put("msg","文件上传失败，请检查重试");
            return map;
        }
        map.put("state","OK");
        map.put("msg","文件上传成功");
        return map;
    }
    /*
     * 功能描述 下载文件
     * @Author bin
     * @param way
     * @param name
     * @param response
     * @return void
     */
    @Log("下载文件")
    @RequestMapping("/getFile.do")
    public void getFile(int id, String name, HttpServletResponse response) throws Exception { ;
        Fil fil = filService.findById(id);
        File file = new File(fil.getWay());
        String suffix = name.substring(name.lastIndexOf(".")+1);
        if(!isImg(suffix)){
            name = URLEncoder.encode(name, "UTF-8");
            //设置响应的contentType开启下载模式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-disposition","attachment;fileName="+name);
        }
        InputStream inputStream = new FileInputStream(file);//获取文件的流
        OutputStream outputStream = response.getOutputStream();//获取输出流
        IOUtils.copy(inputStream, outputStream);//
        inputStream.close();
        outputStream.close();
    }
    /*
     * 功能描述 文件设置为过期文件
     * @Author bin
     * @param fil_id
     * @param company_id
     * @param fway_id
     * @param request
     * @return java.lang.String
     */
    @Log("删除文件")
    @RequestMapping("/expireFile.do")
    public Map expireFil(int fil_id, int fway_id,HttpServletRequest request){
        Map<String,String> map = new HashMap<>();
        Fil fil = filService.findById(fil_id);
        try {
            fil.setState(0);
            fil = filService.chongMing(fil);
            if(isImg(fil.getName().substring(fil.getName().lastIndexOf(".")+1))){
                request.getSession().setAttribute("imgWay",fil.getImgway());
                fil.setImgway("");
            }
            fil.setDeltime(new Timestamp(System.currentTimeMillis()));
            filService.expireFil(fil);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            map.put("state","FAIL");
            map.put("msg","删除失败，检查后重试");
            return map;
        }
        map.put("state","OK");
        map.put("msg","删除成功");
        return map;
    }



    /*
     * 功能描述 重命名文件
     * @Author bin
     * @param fil_id
     * @param name
     * @param fway_id
     * @return java.util.Map
     */
    @Log("重命名文件")
    @RequestMapping("renameFil.do")
    public Map renameFil(int fil_id,String name,int fway_id){
        Map<String,String> map = new HashMap<>();
        if(filService.findByNameFolid(name, fway_id)!=null){
            map.put("state","FAIL");
            map.put("msg","有重名文件");
            return map;
        }
        try {
            filService.renameFil(name,fil_id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            map.put("state","FAIL");
            map.put("msg","请重试");
            return map;
        }
        map.put("state","OK");
        map.put("msg","重命名成功");
        return map;
    }


    /*
     * 功能描述 downZip
     * @Author bin
     * @param res
     * @return void
     */
    @Log("下载备份")
    @RequestMapping("/downZip.do")
    public void downZip(HttpServletResponse res) throws IOException {
        String[] lastTime = TimeUtil.getLastMonth();
        String zipName = lastTime[0]+"新增客户文件.zip";
        String zipFilePath = MyFileUtils.UPLOAD_PATH +File.separator+lastTime[1]+File.separator+lastTime[2]+File.separator+zipName;
        File file = new File(zipFilePath);
        if(!file.exists()){
            autoController.autoZip();
        }
        //IO流实现下载的功能
        res.setContentType("text/html; charset=UTF-8"); //设置编码字符
        res.setContentType("application/x-msdownload");//开启下载模式
        res.setHeader("Content-disposition", "attachment;filename="+new String(zipName.getBytes("utf-8"),"ISO-8859-1"));//设置下载的压缩文件名称
        OutputStream out = res.getOutputStream(); //创建页面返回方式为输出流，会自动弹出下载框
        //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));
        IOUtils.copy(bis, out);//拷贝
        bis.close();//关闭输入流
        out.flush();//释放缓存
        out.close();//关闭输出流
    }
}

