package com.my.erp.bus.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * my所用的表
 * </p>
 *
 * @author bin
 * @since 2020-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_proofread")
public class Proofread implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 业务名称
     */
    private String proofreadname;

    private String content;

    private String auditcontent;

    @TableField(exist = false)
    private String[] auditcontents;

    @TableField(exist = false)
    private String[] contents;

    private Integer price;

    private Date createtime;

    private String operateperson;

    private String paytype;

    private Integer customerid;

    @TableField(exist = false)
    private String Customername;

    private String customerimg;

    private String operateimg;

    private String remark;

    private Integer deptid;

    @TableField(exist = false)
    private String depttitle;

    /**
     * 审核id
     */
    private Integer auditingid;

    @TableField(exist = false)
    private String auditingname;

    private Date ywaudittime;

    private Date cwaudittime;

    private Date completetime;

}
