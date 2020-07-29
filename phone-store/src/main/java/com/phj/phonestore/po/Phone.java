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
@TableName("tbl_phone")
@Data
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "phone_id", type = IdType.AUTO)
    private Integer phoneId;

    private String phoneName;

    private BigDecimal phonePrice;

    private String phoneDesc;

    private Integer phoneStock;

    private String phoneIcon;

    private Integer categoryType;

    private String phoneTag;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
