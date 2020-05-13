package com.my.erp.bus.controller;


import com.my.erp.bus.domain.Company;
import com.my.erp.bus.domain.Fil;
import com.my.erp.bus.domain.Folder;
import com.my.erp.bus.service.CompanyService;
import com.my.erp.bus.service.FilService;
import com.my.erp.bus.service.FolderService;
import com.my.erp.sys.common.CreateFolderUtil;
import com.my.erp.sys.common.ImgUtil;
import com.my.erp.sys.config.Log;
import com.my.erp.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/bus/")
public class FolderController {

    private final static Logger logger = LoggerFactory.getLogger(FolderController.class);

    @Autowired
    private FolderService folderService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private FilService filService;


    /*
     * 功能描述 去文件夹页面
     * @Author bin
     * @param company_id
     * @param fway_id
     * @param request
     * @return java.lang.String
     */

    @RequestMapping("/toFolderAJAX.do")
    public String toFolderAjax(int company_id,
                               int tofway_id,
                               HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            session.setAttribute("company_id",company_id);
            session.setAttribute("fway_id",tofway_id);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            return "FAIL";
        }
        return "OK";
    }


    @RequestMapping("/getFolderAJAX.do")
    public Map getFolder (HttpServletRequest req) throws IOException {
        Map<String,Object> map = new HashMap<>(50);
        int fway_id = (int)req.getSession().getAttribute("fway_id");
        int company_id = (int)req.getSession().getAttribute("company_id");
        //查询当前目录的文件夹
        List<Folder> folders = folderService.findByFidCid(fway_id, company_id);
        //查问当前目录的文件
        Company company = companyService.getById(company_id);
        List<Fil> fils = filService.FindByFid(fway_id);
        //设置img的照片
        fils = ImgUtil.setImgs(fils);
        //文件路径
        List<Folder> ways = new ArrayList<>();
        //查找当前文件夹的位置
        findWay(fway_id,ways);
        User user =(User)req.getSession().getAttribute("user");
        map.put("user",user);
        map.put("ways",ways);
        map.put("company",company);
        map.put("fway_id",fway_id);
        map.put("folders",folders);
        map.put("fils",fils);
        return map;
    }

    /*
     * 功能描述 创建文件夹
     * @Author bin
     * @param name
     * @param fway_id
     * @param company_id
     * @return java.lang.String
     */
    @Log("创建文件夹")
    @RequestMapping("/createFolder.do")
    public  Map  createFolder(String name,int fway_id,int company_id){
        Map<String,String> map = new HashMap<>();
        //查询当前文件夹下是否有同名文件夹
        Folder byNameFidCid = folderService.findByNameFidCid(name, fway_id, company_id);
        if(byNameFidCid != null){
            map.put("state","FAIL");
            map.put("msg","有同名文件夹");
            return map;
        }
        String fway =fway_id == 0 ?companyService.getById(company_id).getWay():folderService.findByFidAsId(fway_id).getWay();
        try {
            fway = CreateFolderUtil.createFolder(fway);
            Folder folder = new Folder();
            folder.setName(name);
            folder.setFwayid(fway_id);
            folder.setCompanyid(company_id);
            folder.setWay(fway);
            folder.setCtime(new Timestamp(System.currentTimeMillis()));
            folderService.insertFolder(folder);
            map.put("state","OK");
            map.put("msg","创建成功");
            return map;
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        map.put("state","FAIL");
        map.put("msg","创建失败，请检查后重试");
        return map;
    }


    /*
     * 功能描述 重命名文件夹
     * @Author bin
     * @param name
     * @param folder
     * @return java.util.Map
     */
    @Log("重命名文件夹")
    @RequestMapping("/renameFolder.do")
    public Map renameFolder(String name,int folder_id,int fway_id){
        Map<String,String> map = new HashMap<>();
        if(folderService.findByNameFidCid(name, fway_id, fway_id)!=null){
            map.put("state","FAIL");
            map.put("msg","有重名文件夹");
            return map;
        }
        try {
            folderService.renameFol(name,folder_id);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            map.put("state","FAIL");
            map.put("msg","请重试");
            return map;
        }
        map.put("state","OK");
        map.put("msg","重命名成功");
        return map;
    }



    /*
     * 功能描述 删除文件夹
     * @Author bin
     * @param folder_id
     * @param fway_id
     * @param company_id
     * @param request
     * @return java.lang.String
     */
    @Log("删除文件夹")
    @Transactional
    @RequestMapping("/expireFolderAJAX.do")
    public Map expireFolderAJAX(int folder_id){
        Map<String,String> map = new HashMap<>();
        try {
            Folder folder = folderService.findByFidAsId(folder_id);
            folder.setState(-1);
            folder.setDeltime(new Timestamp(System.currentTimeMillis()));
            folder = folderService.chongMing(folder);
            //先把文件
            folderService.expireChildFol(folder);
            folder.setState(0);
            folderService.expireFol(folder);
        } catch (Exception e) {
            logger.error(e.toString());
            e.printStackTrace();
            map.put("state","FAIL");
            map.put("msg","删除失败，检查后重试");
            return map;
        }
        map.put("state","OK");
        map.put("msg","删除成功");
        return map;
    }
    /*
     * 功能描述 findWay 查找当前文件夹所在路径
     * @Author bin
     * @param folder_id
     * @param way
     * @return void
     */
    public  void findWay(int folder_id, List<Folder> way){
        if(folder_id == 0){
            return ;
        }
        Folder folder = folderService.findByFidAsId(folder_id);
        findWay(folder.getFwayid(),way);
        way.add(folder);
    }
}

