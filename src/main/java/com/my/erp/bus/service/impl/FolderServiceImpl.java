package com.my.erp.bus.service.impl;

import com.my.erp.bus.domain.Company;
import com.my.erp.bus.domain.Fil;
import com.my.erp.bus.domain.Folder;
import com.my.erp.bus.mapper.CompanyMapper;
import com.my.erp.bus.mapper.FilMapper;
import com.my.erp.bus.mapper.FolderMapper;
import com.my.erp.bus.service.FilService;
import com.my.erp.bus.service.FolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@Service
public class FolderServiceImpl extends ServiceImpl<FolderMapper, Folder> implements FolderService {
    
    @Autowired
    private CompanyMapper companyMapper;
    
    @Autowired
    private FilMapper filMapper;

    @Autowired
    private FilService filService;
    
    /*
     * 功能描述 新增文件夹
     * @Author bin
     * @param folder
     * @return boolean
     */
    public boolean insertFolder(Folder folder){
        Integer x = getBaseMapper().insert(folder);
        return x==1?true:false;
    }
    /*
     * 功能描述 查询文件夹 通过文件夹父id和公司id
     * @Author bin
     * @param fway_id
     * @param company_id
     * @return java.util.List<com.bin.domain.Folder>
     */
    public List<Folder> findByFidCid(int fway_id, int company_id){
        return getBaseMapper().findByFidCid(fway_id,company_id);
    }
    /*
     * 功能描述 通过父文件夹id找父文件夹
     * @Author bin
     * @param fway_id
     * @return com.bin.domain.Folder
     */
    public Folder findByFidAsId(int fway_id){
        return getBaseMapper().findByFidAsId(fway_id);
    }

    @Transactional
    public void delFolByID(int id){
        //找到所有的子文件夹
        List<Folder> folders = getBaseMapper().findByFid(id);
        //继续遍历子文件夹
        for (Folder folder : folders) {
            delFolByID(folder.getId());
        }
        List<Fil> fils = filMapper.findByFid(id);
        for (Fil fil : fils) {
            filMapper.delFilById(fil.getId());
        }
        getBaseMapper().delFolById(id);
    }
    /*
     * 功能描述 查找所有过期文件夹
     * @Author bin
     * @param
     * @return java.util.List<com.bin.domain.Folder>
     */
    public List<Folder> allExpire(){
        return getBaseMapper().allExpire();
    }

    public List<Folder> allFolder(){
        return getBaseMapper().allFolder();
    }
    public Folder chongMing(Folder folder) {
        Folder temp;
        temp = getBaseMapper().findByNameFidCidState(folder);
        while(temp != null){
            folder.setName(folder.getName()+"_副本");
            temp = getBaseMapper().findByNameFidCidState(folder);
        }
        return folder;
    }

    public Folder findByNameFidCid(String name,int fway_id,int company_id){
        return getBaseMapper().findByNameFidCid(name,fway_id,company_id);
    }

    public Folder findExpireByID(int fol_id){
        return getBaseMapper().findExpireByID(fol_id);
    }

    public Folder findByID(int fol_id){
        return getBaseMapper().findByID(fol_id);
    }

    public List<Folder> getExpireFol(Timestamp timestamp){
        return getBaseMapper().getExpireFol(timestamp);
    }

    public void renameFol(String name,int folder_id){
        getBaseMapper().renameFol(name,folder_id);
    }

    //寻找文件夹下所有的文件和文件夹 设置为过期文件
    public void expireFol(Folder folder) {
        getBaseMapper().expireFol(folder);
    }

    @Override
    public void expireChildFol(Folder folder) {
        //找到所有的子文件夹
        List<Folder> folders = getBaseMapper().findByFid(folder.getId());
        //继续遍历子文件夹
        for (Folder fol : folders) {
            expireChildFol(fol);
        }
        List<Fil> fils = filMapper.findByFid(folder.getId());
        for (Fil fil : fils) {
            fil.setState(-1);
            filService.chongMing(fil);
            filMapper.expireChildFileById(fil);
        }
        folder.setState(-1);
        chongMing(folder);
        getBaseMapper().expireChildFol(folder);
    }


    //回复文件夹
    public void recoverFol(Folder folder) {
        //找到所有的子文件夹
        List<Folder> folders = getBaseMapper().findByFid(folder.getId());
        //继续遍历子文件夹
        for (Folder fol : folders) {
            recoverFol(fol);
        }
        List<Fil> fils = filMapper.findByFid(folder.getId());
        for (Fil fil : fils) {
            fil.setState(1);
            filService.chongMing(fil);
            filMapper.recoverFil(fil);
        }
        folder.setState(1);
        chongMing(folder);
        getBaseMapper().recoverFol(folder);
    }

    //获取所有的内容路径
    public void allContent(Map<Integer,String> wayMap){
        List<Folder> folders = getBaseMapper().allFolder();
        for (Folder folder: folders){
            StringBuilder way = new StringBuilder();
            contents(folder,wayMap,way);
        }
    }
    //加载路径
    private  void contents(Folder folder,Map<Integer,String> wayMap,StringBuilder way){
        //判断父级是不是公司
        int fway_id=folder.getFwayid();
        //递归阶段
        if(fway_id==0){
            Company company = companyMapper.findCompanyById(folder.getCompanyid());
            way.append(company.getName()).append(File.separator).append(folder.getName()).append(File.separator);
            wayMap.put(folder.getId(),way.toString());
        }else{//如果不是公司 尝试去数据库中查找地址
            String s = wayMap.get(fway_id);
            //判断地址是否为空 如果为空，继续递归
            if ("".equals(s)||s==null){
                Folder tempFolder = getBaseMapper().findByFidAsId(fway_id);
                contents(tempFolder, wayMap, way);
                way.append(folder.getName()).append(File.separator);
            }
            //如果不为空，将父级地址拼接上本级地址
            else way= new StringBuilder(s).append(folder.getName()).append(File.separator);
        }
        wayMap.put(folder.getId(),way.toString());
    }
}
