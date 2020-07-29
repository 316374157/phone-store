package com.phj.phonestore.controller;


import com.phj.phonestore.dto.OrderDto;
import com.phj.phonestore.exception.PhoneException;
import com.phj.phonestore.from.OrderFrom;
import com.phj.phonestore.service.OrderService;
import com.phj.phonestore.utils.ResultVOUtil;
import com.phj.phonestore.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 创建订单
     * @param orderFrom 订单信息
     * @param bindResult 错误信息
     * @return 封装好的顶单编号数据
     */
    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody OrderFrom orderFrom, BindingResult bindResult){
        if (bindResult.hasErrors()){
            log.error("【创建订单】参数错误 orderFrom={}",orderFrom);
            throw new PhoneException(bindResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = new OrderDto(null, orderFrom.getName()
        , orderFrom.getTel(), orderFrom.getAddress(), orderFrom.getSpecsId()
        , orderFrom.getQuantity());
        OrderDto result = orderService.create(orderDto);
        Map<String,String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

    /**
     * 获取订单详情
     * @param orderId 订单id
     * @return 封装好的订单数据
     */
    @GetMapping("/detail/{orderId}")
    public ResultVO getOrderDetail(@PathVariable String orderId){
        return ResultVOUtil.success(orderService.getOrderDetailVO(orderId));
    }

    /**
     * 支付订单
     * @param orderId 订单id
     * @return 封装好的订单id数据
     */
    @PutMapping("/pay/{orderId}")
    public ResultVO pay(@PathVariable String orderId){
        Map<String,String> map = new HashMap<>();
        map.put("orderId", orderService.pay(orderId));
        return ResultVOUtil.success(map);
    }
}
