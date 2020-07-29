package com.phj.phonestore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.phj.phonestore.po.Phone;
import com.phj.phonestore.vo.DataVO;
import com.phj.phonestore.vo.PhoneInfoVO;
import com.phj.phonestore.vo.SpecsPackageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
public interface PhoneService extends IService<Phone> {
    /**
     * 获取首页所需要的数据
     * @return 数据实体模型
     */
    public DataVO getDataVO();

    /**
     * 根据类型获取手机数据
     * @param categoryType 类型
     * @return 数据实体模型
     */
    public List<PhoneInfoVO> getPhonesByType(Integer categoryType);

    /**
     * 通过手机编号获取手机规格
     * @param phoneId 手机编号
     * @return 数据实体模型
     */
    public SpecsPackageVO getSpecsPackageVOByPhoneId(Integer phoneId);

    /**
     * 减库存
     * @param specsId 规格id
     * @param quantity 购买数目
     */
    public void subStock(Integer specsId,Integer quantity);

}
