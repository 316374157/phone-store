package com.phj.phonestore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.phonestore.from.AddressFrom;
import com.phj.phonestore.po.Address;
import com.phj.phonestore.vo.AddressVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
public interface AddressService extends IService<Address> {
    /**
     * 获取所有地址
     * @return 地址数据模型
     */
    public List<AddressVO> getAllAddress();

    /**
     * 增加或者更新地址
     * @param addressFrom 地址
     */
    public void saveOrUpdate(AddressFrom addressFrom);

}
