package com.my.erp.sys.service.impl;

import com.my.erp.sys.domain.User;
import com.my.erp.sys.mapper.UserMapper;
import com.my.erp.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-17
 */
@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean save(User entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        //删除用户角色中间表的事情
        UserMapper userMapper = getBaseMapper();
        userMapper.deleteRoleUserByUid(id);
        //删除用户头像 如果是默认头像则不删除
        return super.removeById(id);
    }

    @Override
    public boolean updateById(User entity) {
        return super.updateById(entity);
    }

    @Override
    public User getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public void saveUserRole(Integer uid, Integer[] ids) {
        //根据用户uid删除sys_role_user里面的数据
        UserMapper userMapper = getBaseMapper();
        userMapper.deleteRoleUserByUid(uid);
        //根据ids数组来增加
        if(null!=ids&&ids.length>0){
            for (Integer rid : ids) {
                userMapper.insertUserRole(uid,rid);
            }
        }

    }

}
