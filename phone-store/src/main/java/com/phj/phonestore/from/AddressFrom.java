package com.phj.phonestore.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author 31637
 * @date 2020/7/25 12:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFrom {

    private Integer id;
    @NotBlank(message ="名字不能为空")
    private String  name;
    @NotBlank(message ="电话不能为空")
    private String  tel;
    @NotBlank(message ="省份不能为空")
    private String  province;
    @NotBlank(message ="城市不能为空")
    private String  city;
    @NotBlank(message ="区域不能为空")
    private String  county;
    @NotBlank(message ="地区编号不能为空")
    private String  areaCode;
    @NotBlank(message ="详细地址不能为空")
    private String  addressDetail;

}
