package com.my.erp.bus.vo;

import com.my.erp.bus.domain.Debt;
import com.my.erp.bus.domain.Proofread;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DebtVo extends Debt {
    /*
     序列化接口
    */
    private static final long serialVersionUID = 1L;
    private Integer[] ids;//id数组 用于接收多个ID
    
    private Integer page = 1 ;
    private Integer limit = 10;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
