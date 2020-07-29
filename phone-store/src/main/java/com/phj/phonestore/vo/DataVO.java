package com.phj.phonestore.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 31637
 * @date 2020/7/24 21:54
 */
@Data
public class DataVO {
    private List<CategoryVO> categories;
    private List<PhoneInfoVO> phones;
}
