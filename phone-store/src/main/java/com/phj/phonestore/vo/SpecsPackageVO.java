package com.phj.phonestore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 31637
 * @date 2020/7/24 23:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecsPackageVO {
    private Map<String,String> goods;
    private SkuVO sku;
}
