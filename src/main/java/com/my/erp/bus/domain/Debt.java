package com.my.erp.bus.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 债务
 * </p>
 *
 * @author bin
 * @since 2020-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("bus_debt")
public class Debt implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer customerid;

    @TableField(exist = false)
    private String customername;

    @TableField(exist = false)
    private String customertel;

    @TableField(exist = false)
    private String connectionperson;

    private String type;

    private Integer price;

    private String paytype;
    /**
     * 折扣
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dailitime;
    /**
     * 记账结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date starttime;
    /**
     * 记账开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cuizhangtime;

    /**
     * 折扣
     */
    private String discount;

    @TableField(exist = false)
    private int state;

    private boolean ispay;

    private boolean iscz;


}
