package com.phj.phonestore.from;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 31637
 * @date 2020/7/25 18:07
 */
@Data
public class OrderFrom {
    @NotBlank(message ="名字不能为空")
    private String name;
    @NotBlank(message ="电话不能为空")
    private String tel;
    @NotBlank(message ="地址不能为空")
    private String address;
    @NotNull(message ="规格不能为空")
    private Integer specsId;
    @NotNull(message ="数量不能为空")
    private Integer quantity;
}
