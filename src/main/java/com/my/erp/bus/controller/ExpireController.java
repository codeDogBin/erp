package com.my.erp.bus.controller;


import com.my.erp.bus.domain.Fil;
import com.my.erp.bus.domain.Folder;
import com.my.erp.bus.service.FilService;
import com.my.erp.bus.service.FolderService;
import com.my.erp.sys.config.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("bus")
public class ExpireController {
    @Autowired
    private FolderService folderService;
    @Autowired
    private FilService filService;

    /*
     * 功能描述 去过期文件页
     * @Author bin
     * @param request
     * @return java.lang.String
     */

    @RequestMapping("/getAllExpire.do")
    public Map getAllExpire () {
        List<Fil> fils = filService.allExpire();
        List<Folder> folders = folderService.allExpire();
        Map<String,List> map = new HashMap<>();
        map.put("files",fils);
        map.put("folders",folders);
        return map;
    }

    /**
     * 恢复过期文件
     * @param fil_id
     * @return
     */
    @Log("恢复过期文件")
    @RequestMapping("/recoverFil.do")
    public String recoverFil(int fil_id){
        Fil fil = filService.findExpireById(fil_id);
        fil.setState(1);
        fil = filService.chongMing(fil);
        filService.recoverFil(fil);
        return "OK";
    }

    /**
     * 恢复过期文件夹
     * @param fol_id
     * @return
     */
    @Log("恢复过期文件夹")
    @RequestMapping("/recoverFolder.do")
    public String recoverFolder(int fol_id){
        Folder fol = folderService.findExpireByID(fol_id);
        fol.setState(1);
        fol=folderService.chongMing(fol);
        folderService.recoverFol(fol);
        return "OK";
    }

}
