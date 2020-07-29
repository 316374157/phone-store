package com.phj.phonestore.service.impl;

import com.phj.phonestore.from.AddressFrom;
import com.phj.phonestore.po.Address;
import com.phj.phonestore.dao.AddressMapper;
import com.phj.phonestore.service.AddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.phonestore.vo.AddressVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Override
    public List<AddressVO> getAllAddress() {
        List<Address> addressList = this.baseMapper.selectList(null);
        return addressList.stream().
                map(e -> new AddressVO(e.getAddressId(),e.getBuyerName(),
                e.getBuyerTel(),e.getBuyerAddress(),e.getAreaCode())).
                collect(Collectors.toList());
    }

    @Override
    public void saveOrUpdate(AddressFrom addressFrom) {
        Address address = new Address(addressFrom.getId(), addressFrom.getName(), addressFrom.getTel(),
                addressFrom.getProvince()+addressFrom.getCity()+addressFrom.getCounty()+addressFrom.getAddressDetail(),
                    addressFrom.getAreaCode(),null, null);
        if(addressFrom.getId() == null){
            this.baseMapper.insert(address);
        }else{
            this.baseMapper.updateById(address);
        }
    }
}
