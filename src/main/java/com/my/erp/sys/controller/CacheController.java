package com.my.erp.sys.controller;



import com.my.erp.sys.cache.CachePool;

import com.my.erp.sys.common.CacheBean;
import com.my.erp.sys.common.DataGridView;
import com.my.erp.sys.common.ResultObj;
import com.my.erp.sys.config.Log;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 缓存管理控制器
 */
@RestController
@RequestMapping("cache")
public class CacheController {

    public static volatile Map<String, Object> CACHE_CONTAINER = CachePool.CACHE_CONTAINER;


    /**
     * 查询所有缓存
     */
    @RequestMapping("loadAllCache")
    public DataGridView LoadAllCache(){
        List<CacheBean>list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : CACHE_CONTAINER.entrySet()) {
            list.add(new CacheBean(entry.getKey(),entry.getValue()));
        }
        return new DataGridView(list);
    }


    /**
     *删除缓存
     */
    @Log("删除缓存")
    @RequestMapping("deleteCache")
    public ResultObj deleteCache(String key){
        CachePool.removeCacheByKey(key);
        return ResultObj.DELETE_SUCCESS;
    }

    /**
     * 删除所有缓存
     */
    @RequestMapping("deleteAllCache")
    public ResultObj deleteAllCache(){
        CachePool.removeAllCache();
        return ResultObj.DELETE_SUCCESS;
    }

    /**
     * 同步缓存
     */
    @Log("同步缓存")
    @RequestMapping("syncCache")
    public ResultObj syncCache(){
        CachePool.syncData();
        return ResultObj.OPERATE_SUCCESS;
    }


}
