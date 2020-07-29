package com.phj.phonestore.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
@TableName("tbl_order")
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id", type = IdType.INPUT)
    private String orderId;

    private String buyerName;

    private String buyerTel;

    private String buyerAddress;

    private Integer phoneId;

    private String phoneName;

    private Integer phoneQuantity;

    private String phoneIcon;

    private Integer specsId;

    private String specsName;

    private BigDecimal specsPrice;

    private BigDecimal orderAmount;

    private Integer payStatus;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
