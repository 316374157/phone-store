package com.phj.phonestore.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 31637
 * @date 2020/7/24 21:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
}
