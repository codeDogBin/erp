package com.my.erp.sys.vo;

import com.my.erp.sys.domain.Dept;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeptVo  extends Dept {
    /*
     序列化接口
    */
    private static final long serialVersionUID = 1L;

    private Integer page = 1 ;
    private Integer limit = 10;

}
