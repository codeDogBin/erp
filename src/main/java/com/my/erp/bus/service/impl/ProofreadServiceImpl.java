package com.my.erp.bus.service.impl;

import com.my.erp.bus.domain.Proofread;
import com.my.erp.bus.mapper.ProofreadMapper;
import com.my.erp.bus.service.ProofreadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * my所用的表 服务实现类
 * </p>
 *
 * @author bin
 * @since 2020-04-28
 */
@Service
public class ProofreadServiceImpl extends ServiceImpl<ProofreadMapper, Proofread> implements ProofreadService {
    @Override
    public boolean save(Proofread entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(Proofread entity) {
        return super.updateById(entity);
    }

    @Override
    public Proofread getById(Serializable id) {
        return super.getById(id);
    }

    @Override
    public Integer getCountByCw() {
        return getBaseMapper().getCountByCw();
    }

    @Override
    public List<Integer> getRidByUid(int uid) {
        return getBaseMapper().getRidByUid(uid);
    }

    @Override
    public Integer getCountByYw(String name) {
        return  getBaseMapper().getCountByYW(name);
    }

    @Override
    public Integer getCountByJL(Integer deptid) {
        return getBaseMapper().getCountByJL(deptid);
    }
}
