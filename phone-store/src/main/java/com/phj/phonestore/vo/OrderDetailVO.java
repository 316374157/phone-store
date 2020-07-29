package com.phj.phonestore.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 31637
 * @date 2020/7/25 21:44
 */
@Data
public class OrderDetailVO {

    private String orderId;
    private String buyerName;
    @JsonProperty("tel")
    private String buyerTel;
    @JsonProperty("address")
    private String buyerAddress;
    private String phoneName;
    @JsonProperty("num")
    private Integer phoneQuantity;
    @JsonProperty("icon")
    private String phoneIcon;
    @JsonProperty("specs")
    private String specsName;
    @JsonProperty("price")
    private String specsPrice;
    @JsonProperty("amount")
    private BigDecimal orderAmount;
    private Integer payStatus;
    private Integer freight;
}
