package com.phj.phonestore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 31637
 * @date 2020/7/25 11:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO {

    private Integer id;
    private String name;
    private String tel;
    private String address;
    private String areaCode;

}
