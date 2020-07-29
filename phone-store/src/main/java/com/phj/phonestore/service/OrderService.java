package com.phj.phonestore.service;

import com.phj.phonestore.dto.OrderDto;
import com.phj.phonestore.po.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.phonestore.vo.OrderDetailVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
public interface OrderService extends IService<Order> {
    /**
     * 创建订单
     * @param orderDto 前端的订单数据
     * @return 封装好的订单数据
     */
    public OrderDto create(OrderDto orderDto);

    /**
     * 通过订单ID获取订单详情数据
     * @param orderId 订单id
     * @return 封装好的订单数据
     */
    public OrderDetailVO getOrderDetailVO(String orderId);

    /**
     * 支付订单
     * @param orderId 订单id
     * @return 订单编号
     */
    public String pay(String orderId);


}
