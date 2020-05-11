package com.my.erp.sys.service;

import com.my.erp.sys.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bin
 * @since 2020-04-17
 */
public interface UserService extends IService<User> {

    void saveUserRole(Integer uid, Integer[] ids);

    Integer getRoleIDByUserID(Integer id);
}
