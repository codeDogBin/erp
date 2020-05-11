package com.my.erp.bus.service.impl;

import com.my.erp.bus.domain.Fil;
import com.my.erp.bus.mapper.FilMapper;
import com.my.erp.bus.service.FilService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-05-06
 */
@Service
public class FilServiceImpl extends ServiceImpl<FilMapper, Fil> implements FilService {
    /*
     * 功能描述  新增一个文件
     * @Author bin
     * @param fil
     * @return boolean
     */
    public boolean insertFil(Fil fil){
        Integer x = getBaseMapper().insert(fil);
        return x==1?true:false;
    }
    /*
     * 功能描述  通过文件夹id找到当前文件夹下的文件
     * @Author bin
     * @param fol_id
     * @return java.util.List<com.bin.domain.Fil>
     */
    public List<Fil> FindByFid(int fol_id){
        return getBaseMapper().findByFid(fol_id);
    }
    /*
     * 功能描述 文件设置为过期
     * @Author bin
     * @param fil_id
     * @return void
     */
    public void expireFil(Fil fil){
        getBaseMapper().expireFileById(fil);
    }

    @Override
    public void expireChildFil(Fil fil) {
        getBaseMapper().expireChildFileById(fil);
    }
    /*
     * 功能描述 查询所有过期文件
     * @Author bin
     * @param
     * @return java.util.List<com.bin.domain.Fil>
     */


    public List<Fil> allExpire(){
        return getBaseMapper().allExpire();
    }
    /*
     * 功能描述 删除文件ByID
     * @Author bin
     * @param null
     * @return
     */
    public void delFilByID(int fil_id){
        getBaseMapper().delFilById(fil_id);
    }
    /*
     * 功能描述 找到重名就后面加上副本
     * @Author bin
     * @param fil
     * @return com.bin.domain.Fil
     */
    public Fil chongMing(Fil fil) {
        Fil temp;
        temp = getBaseMapper().findByNameFidState(fil);
        while(temp != null){
            fil.setName(fil.getName().substring(0,fil.getName().lastIndexOf("."))+"_副本"+fil.getName().substring(fil.getName().lastIndexOf(".")));
            temp = getBaseMapper().findByNameFidState(fil);
        }
        return fil;
    }

    public void recoverFil(Fil fil){
        getBaseMapper().recoverFil(fil);
    }

    public Fil findById(int fil_id){
        return getBaseMapper().findById(fil_id);
    }

    public Fil findExpireByNameFolid(String name,int fol_id){
        return getBaseMapper().findExpireByNameFolid(name,fol_id);
    }

    public List<Fil> getExpireFil(Timestamp timestamp){
        return getBaseMapper().getExpireFil(timestamp);
    }

    public Fil findExpireById(int fil_id){
        return getBaseMapper().findExpireById(fil_id);
    }


    public List<Fil> getFilByMonth(Timestamp startTime,Timestamp endTime){
        return  getBaseMapper().getFilByMonth(startTime,endTime);
    }
    public Fil findByNameFolid(String name, int fol_id){
        return getBaseMapper().findByNameFolid(name,fol_id);
    }


    public void renameFil(String name, int fil_id) {
        getBaseMapper().renameFil(name,fil_id);
    }

    public void updateImgWay(Fil fil) {
        getBaseMapper().updateImgWay(fil);
    }
}
