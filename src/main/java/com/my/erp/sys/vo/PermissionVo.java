package com.my.erp.sys.vo;

import com.my.erp.sys.domain.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {
    /*
      序列化接口
     */
    private static final long serialVersionUID = 1L;
    private Integer page = 1 ;
    private Integer limit = 10;
}
