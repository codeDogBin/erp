package com.my.erp.sys.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.my.erp.bus.domain.Customer;
import com.my.erp.bus.domain.Goods;
import com.my.erp.bus.domain.Provider;
import com.my.erp.bus.mapper.CustomerMapper;
import com.my.erp.bus.mapper.GoodsMapper;
import com.my.erp.bus.mapper.ProviderMapper;
import com.my.erp.sys.common.SpringUtil;
import com.my.erp.sys.domain.Dept;
import com.my.erp.sys.domain.User;
import com.my.erp.sys.mapper.DeptMapper;
import com.my.erp.sys.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachePool {
    /**
     * 将所有缓存数据放到这个CACHE_CONTAINER 类似redis
     */

    public static volatile Map<String, Object> CACHE_CONTAINER = new HashMap<>();

    /**
     * 根据Key删除缓存
     *
     * @param key
     */
    public static void removeCacheByKey(String key) {
        if (CACHE_CONTAINER.containsKey(key)) {
            CACHE_CONTAINER.remove(key);
        }
    }

    /**
     * 清除所有缓存
     */
    public static void removeAllCache() {
        CACHE_CONTAINER.clear();
    }


    /**
     * 同步缓存
     */
    public static void syncData() {
        //同步部门数据
        DeptMapper deptMapper = SpringUtil.getBean(DeptMapper.class);
        List<Dept> depts = deptMapper.selectList(null);
        for (Dept dept : depts) {
            CACHE_CONTAINER.put("dept:" + dept.getId(), dept);
        }
        //同步用户数据
        UserMapper userMapper = SpringUtil.getBean(UserMapper.class);
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            CACHE_CONTAINER.put("user:"+user.getId(),user);
        }

        //同步客户数据
        CustomerMapper customerMapper = SpringUtil.getBean(CustomerMapper.class);
        List<Customer> customers = customerMapper.selectList(null);
        for (Customer customer : customers) {
            CACHE_CONTAINER.put("customer:"+customer.getId(),customer);
        }
        //同步供应商数据
        ProviderMapper providerMapper = SpringUtil.getBean(ProviderMapper.class);
        List<Provider> providers = providerMapper.selectList(null);
        for (Provider provider : providers) {
            CACHE_CONTAINER.put("provider:"+provider.getId(),provider);
        }
        //同步商品数据
        GoodsMapper goodsMapper = SpringUtil.getBean(GoodsMapper.class);
        List<Goods> goodsList = goodsMapper.selectList(null);
        for (Goods goods : goodsList) {
            CACHE_CONTAINER.put("goods:"+goods.getId(),goods);
        }
    }

}
