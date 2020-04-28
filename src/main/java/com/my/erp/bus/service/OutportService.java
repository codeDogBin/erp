package com.my.erp.bus.service;

import com.my.erp.bus.domain.Outport;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author bin
 * @since 2020-04-26
 */
public interface OutportService extends IService<Outport> {

    /**
     * 新增退货
     * @param id 进货单id
     * @param number 退货数量
     * @param remark 备注
     */
    void addOutPort(Integer id, Integer number, String remark,String name);
}
