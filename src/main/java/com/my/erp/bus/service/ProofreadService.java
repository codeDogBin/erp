package com.my.erp.bus.service;

import com.my.erp.bus.domain.Proofread;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * my所用的表 服务类
 * </p>
 *
 * @author bin
 * @since 2020-04-28
 */
public interface ProofreadService extends IService<Proofread> {

    Integer getCountByCw();

    List<Integer> getRidByUid(int uid);

    Integer getCountByYw(String name);

    Integer getCountByJL(Integer deptid);
}
