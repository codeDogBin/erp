package com.my.erp.bus.service;

import com.my.erp.bus.domain.Company;
import com.my.erp.bus.domain.Fil;
import com.my.erp.bus.domain.Folder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@Service
public interface FolderService extends IService<Folder> {
    /*
     * 功能描述 新增文件夹
     * @Author bin
     * @param folder
     * @return boolean
     */
    public boolean insertFolder(Folder folder);
    /*
     * 功能描述 查询文件夹 通过文件夹父id和公司id
     * @Author bin
     * @param fway_id
     * @param company_id
     * @return java.util.List<com.bin.domain.Folder>
     */
    public List<Folder> findByFidCid(int fway_id, int company_id);
    /*
     * 功能描述 通过父文件夹id找父文件夹
     * @Author bin
     * @param fway_id
     * @return com.bin.domain.Folder
     */
    public Folder findByFidAsId(int fway_id);
    @Transactional
    public void delFolByID(int id);
    /*
     * 功能描述 查找所有过期文件夹
     * @Author bin
     * @param
     * @return java.util.List<com.bin.domain.Folder>
     */
    public List<Folder> allExpire();

    public List<Folder> allFolder();
    public Folder chongMing(Folder folder);

    public Folder findByNameFidCid(String name,int fway_id,int company_id);

    public Folder findExpireByID(int fol_id);
    public Folder findByID(int fol_id);

    public List<Folder> getExpireFol(Timestamp timestamp);

    public void renameFol(String name,int folder_id);

    public void expireFol(Folder folder);

    public void expireChildFol(Folder folder);

    public void recoverFol(Folder fol);

    public void allContent(Map<Integer,String> wayMap);

}
