package com.my.erp.bus.vo;

import com.my.erp.bus.domain.Customer;
import com.my.erp.bus.domain.Provider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends Provider {
    /*
     序列化接口
    */
    private static final long serialVersionUID = 1L;
    private Integer[] ids;//id数组 用于接收多个ID
    
    private Integer page = 1 ;
    private Integer limit = 10;

}
