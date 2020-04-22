package com.my.erp.sys.service.impl;

import com.my.erp.sys.domain.User;
import com.my.erp.sys.mapper.UserMapper;
import com.my.erp.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
