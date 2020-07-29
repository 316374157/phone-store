package com.phj.phonestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.phonestore.dao.OrderMapper;
import com.phj.phonestore.dao.SpecsMapper;
import com.phj.phonestore.dto.OrderDto;
import com.phj.phonestore.enums.PayStatus;
import com.phj.phonestore.enums.ResultEnum;
import com.phj.phonestore.exception.PhoneException;
import com.phj.phonestore.po.Order;
import com.phj.phonestore.po.Phone;
import com.phj.phonestore.po.Specs;
import com.phj.phonestore.service.OrderService;
import com.phj.phonestore.service.PhoneService;
import com.phj.phonestore.vo.OrderDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final SpecsMapper specsMapper;
    private final PhoneService phoneService;

    public OrderServiceImpl(SpecsMapper specsMapper,PhoneService phoneService) {
        this.specsMapper = specsMapper;
        this.phoneService = phoneService;
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto, order);
        Specs specs = specsMapper.selectById(orderDto.getSpecsId());
        BeanUtils.copyProperties(specs, order);
        Phone phone = phoneService.getById(specs.getPhoneId());
        BeanUtils.copyProperties(phone, order);
        BigDecimal orderAmount = new BigDecimal(order.getSpecsPrice().
                        multiply(new BigDecimal(order.getPhoneQuantity())).
                        divide(new BigDecimal(100))+"");
        order.setOrderAmount(orderAmount);
        order.setOrderId(System.currentTimeMillis()+ order.getSpecsId()+"");
        order.setPayStatus(PayStatus.UNPIAD.getCode());
        BeanUtils.copyProperties(order, orderDto);
        this.baseMapper.insert(order);
        phoneService.subStock(order.getSpecsId(), order.getPhoneQuantity());
        return orderDto;
    }

    @Override
    public OrderDetailVO getOrderDetailVO(String orderId) {
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setFreight(10);
        Order order = this.baseMapper.selectById(orderId);
        if(order == null){
            log.error("【查询订单】 订单为空，order={}",order);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        BeanUtils.copyProperties(order, orderDetailVO);
        orderDetailVO.setSpecsPrice(order.getSpecsPrice().divide(new BigDecimal(100))+"");
        return orderDetailVO;
    }

    @Override
    public String pay(String orderId) {
        Order order = this.baseMapper.selectById(orderId);
        if(order == null){
            log.error("【支付订单】 订单为空，order={}",order);
            throw new PhoneException(ResultEnum.ORDER_NOT_EXIST);
        }
        if(order.getPayStatus().equals(PayStatus.UNPIAD.getCode())){
            order.setPayStatus(PayStatus.PIAD.getCode());
            this.baseMapper.updateById(order);
        }else {
            log.error("【支付订单】 订单已支付，order={}",order);
        }
        return orderId;
    }
}
