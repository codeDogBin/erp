package com.my.erp.bus.service;

import com.my.erp.bus.domain.Fil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.my.erp.bus.domain.Folder;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
public interface FilService extends IService<Fil> {
    /*
     * 功能描述  新增一个文件
     * @Author bin
     * @param fil
     * @return boolean
     */
    public boolean insertFil(Fil fil);
    /*
     * 功能描述  通过文件夹id找到当前文件夹下的文件
     * @Author bin
     * @param fol_id
     * @return java.util.List<com.bin.domain.Fil>
     */
    public List<Fil> FindByFid(int fol_id);
    /*
     * 功能描述 文件设置为过期
     * @Author bin
     * @param fil_id
     * @return void
     */
    public void expireFil(Fil fil);


    /*
     * 文件夹下的文件设置为过期状态
     * @param fil
     */
    public void expireChildFil(Fil fil);
    /*
     * 功能描述 查询所有过期文件
     * @Author bin
     * @param
     * @return java.util.List<com.bin.domain.Fil>
     */


    public List<Fil> allExpire();
    /*
     * 功能描述 删除文件ByID
     * @Author bin
     * @param null
     * @return
     */
    public void delFilByID(int fil_id);
    /*
     * 功能描述 找到重名就后面加上副本
     * @Author bin
     * @param fil
     * @return com.bin.domain.Fil
     */
    public Fil chongMing(Fil fil) ;

    public void recoverFil(Fil fil);

    public Fil findById(int fil_id);

    public Fil findExpireByNameFolid(String name,int fol_id);

    public List<Fil> getExpireFil(Timestamp timestamp);

    public Fil findExpireById(int fil_id);


    public List<Fil> getFilByMonth(Timestamp startTime,Timestamp endTime);

    public Fil findByNameFolid(String name, int fol_id);


    public void renameFil(String name, int fil_id);

    public void updateImgWay(Fil fil) ;
}
