package com.phj.phonestore.service;

import com.phj.phonestore.dto.OrderDto;
import com.phj.phonestore.po.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 31637
 * @date 2020/7/24 21:12
 */
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    AddressService addressService;

    @Test
    void insert(){
        Order order = new Order();
        order.setOrderId(System.currentTimeMillis()+"1");
        order.setBuyerName("张三");
        order.setBuyerAddress("广东省深圳市罗湖区科技路123号456栋");
        order.setBuyerTel("13603000000");
        order.setOrderAmount(new BigDecimal("6400.00"));
        order.setPayStatus(0);
        order.setPhoneIcon("../static/2222.jfif");
        order.setPhoneId(1);
        order.setPhoneName("小米8");
        order.setPhoneQuantity(2);
        order.setSpecsId(1);
        order.setSpecsName("64GB");
        order.setSpecsPrice(new BigDecimal("320000.00"));
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        orderService.save(order);
    }

    @Test
    void createTest(){
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("张三");
        orderDto.setBuyerTel("13603000000");
        orderDto.setPhoneQuantity(2);
        orderDto.setSpecsId(1);
        orderDto.setBuyerAddress("广东省深圳市罗湖区科技路123号456栋");
        orderService.create(orderDto);
    }
}