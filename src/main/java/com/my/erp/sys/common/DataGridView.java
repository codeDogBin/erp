package com.my.erp.sys.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JSON数据实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Integer code=0;
    private String msg ="";
    private Long count = 0L;
    private Object Data;

    public DataGridView(Long count, Object data) {
        this.count = count;
        Data = data;
    }

    public DataGridView(Object data) {
        Data = data;
    }
}
