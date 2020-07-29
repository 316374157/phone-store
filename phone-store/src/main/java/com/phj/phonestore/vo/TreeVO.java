package com.phj.phonestore.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 31637
 * @date 2020/7/24 23:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeVO {

    private String k;
    private List<SpecsVO> v;
    @JsonProperty("k_s")
    private String kS;

}
