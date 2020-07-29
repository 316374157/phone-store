package com.phj.phonestore.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 31637
 * @date 2020/7/24 23:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkuVO {

    private List<TreeVO> tree;
    private List<SpecsCasVO> list;
    private String price;
    @JsonProperty("stock_num")
    private Integer stockNum;
    @JsonProperty("none_sku")
    private Boolean noneSku;
    @JsonProperty("hide_stock")
    private Boolean hideStock;

}
