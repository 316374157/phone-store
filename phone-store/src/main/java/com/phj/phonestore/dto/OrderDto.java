package com.phj.phonestore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 31637
 * @date 2020/7/25 18:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String orderId;
    private String buyerName;
    private String buyerTel;
    private String buyerAddress;
    private Integer specsId;
    private Integer phoneQuantity;
}
