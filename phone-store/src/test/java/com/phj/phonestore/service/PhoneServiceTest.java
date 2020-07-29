package com.phj.phonestore.service;

import com.phj.phonestore.po.Phone;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 31637
 * @date 2020/7/24 19:24
 */
@SpringBootTest
class PhoneServiceTest {

    @Autowired
    PhoneService phoneService;

    @Test
    void insert(){
        List<Phone> phoneList = new ArrayList<>();
        Phone phone = new Phone();
        phone.setPhoneName("华为P30");
        phone.setPhonePrice(new BigDecimal("4800.00"));
        phone.setPhoneDesc("流光黑");
        phone.setPhoneStock(1000);
        phone.setPhoneIcon("../static/2222.jfif");
        phone.setCategoryType(3);
        phone.setPhoneTag("2k屏幕&智能芯片&超级像素");
        phoneList.add(phone);
        phoneService.saveBatch(phoneList);
    }

    @Test
    void selectAllByType(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("category_type", 1);
        phoneService.listByMap(hashMap).forEach(System.out::println);
    }

    @Test
    void getSpecsPackageVOByPhoneId(){
        System.out.println(phoneService.getSpecsPackageVOByPhoneId(1));
    }

}