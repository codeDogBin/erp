package com.my.erp.sys.service.impl;

import com.my.erp.sys.domain.Permission;
import com.my.erp.sys.mapper.PermissionMapper;
import com.my.erp.sys.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public boolean removeById(Serializable id) {
        //根据权限和菜单ID删除权限表和角色的关系表里的数据
        PermissionMapper permissionMapperMapper = this.getBaseMapper();
        permissionMapperMapper.deleteRoloPermissionByPid(id);
        return super.removeById(id);
    }
}
