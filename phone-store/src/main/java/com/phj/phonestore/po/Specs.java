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
@TableName("tbl_specs")
@Data
public class Specs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "specs_id", type = IdType.AUTO)
    private Integer specsId;

    private Integer phoneId;

    private String specsName;

    private Integer specsStock;

    private BigDecimal specsPrice;

    private String specsIcon;

    private String specsPreview;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
