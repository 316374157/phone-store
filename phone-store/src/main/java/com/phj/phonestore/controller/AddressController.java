package com.phj.phonestore.controller;


import com.phj.phonestore.exception.PhoneException;
import com.phj.phonestore.from.AddressFrom;
import com.phj.phonestore.service.AddressService;
import com.phj.phonestore.utils.ResultVOUtil;
import com.phj.phonestore.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * 获取地址列表
     * @return 封装好的获取数据
     */
    @GetMapping("/list")
    public ResultVO list(){
        return ResultVOUtil.success(addressService.getAllAddress());
    }

    /**
     * 添加地址
     * @param addressFrom 要添加的地址信息
     * @param bindResult 错误信息
     * @return 封装好的返回数据
     */
    @PostMapping("/create")
    public ResultVO create(@Valid @RequestBody AddressFrom addressFrom, BindingResult bindResult){
        if (bindResult.hasErrors()){
            log.error("【添加地址】参数错误 addressFrom={}",addressFrom);
            throw new PhoneException(bindResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressFrom);
        return ResultVOUtil.success(null);
    }

    /**
     * 更新地址
     * @param addressFrom 要添加的地址信息
     * @param bindResult 错误信息
     * @return 封装好的返回数据
     */
    @PutMapping("/update")
    public ResultVO update(@Valid @RequestBody AddressFrom addressFrom, BindingResult bindResult){
        if (bindResult.hasErrors()){
            log.error("【更新地址】参数错误 addressFrom={}",addressFrom);
            throw new PhoneException(bindResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressFrom);
        return ResultVOUtil.success(null);
    }


}
