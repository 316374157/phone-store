package com.phj.phonestore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.phj.phonestore.po.Phone;
import com.phj.phonestore.po.Specs;
import com.phj.phonestore.dao.CategoryMapper;
import com.phj.phonestore.dao.PhoneMapper;
import com.phj.phonestore.dao.SpecsMapper;
import com.phj.phonestore.enums.ResultEnum;
import com.phj.phonestore.exception.PhoneException;
import com.phj.phonestore.service.PhoneService;
import com.phj.phonestore.utils.PhoneUtil;
import com.phj.phonestore.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements PhoneService {

    private final CategoryMapper categoryMapper;
    private final SpecsMapper specsMapper;

    public PhoneServiceImpl(CategoryMapper categoryMapper, SpecsMapper specsMapper) {
        this.categoryMapper = categoryMapper;
        this.specsMapper = specsMapper;
    }

    @Override
    public DataVO getDataVO() {
        DataVO dataVO = new DataVO();
        dataVO.setCategories(getCategories());
        dataVO.setPhones(getPhonesByType(1));
        return dataVO;
    }

    @Override
    public List<PhoneInfoVO> getPhonesByType(Integer categoryType) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("category_type", categoryType);
        return baseMapper.selectByMap(hashMap).stream().map(e -> new PhoneInfoVO(
                e.getPhoneId(), e.getPhoneName(), e.getPhonePrice()+"",
                e.getPhoneDesc(), PhoneUtil.createTag(e.getPhoneTag()), e.getPhoneIcon()))
                .collect(Collectors.toList());
    }

    @Override
    public SpecsPackageVO getSpecsPackageVOByPhoneId(Integer phoneId) {
        Phone phone = this.baseMapper.selectById(phoneId);
        return new SpecsPackageVO(getGoods(phone.getPhoneIcon()),
                getSkuVO(phone.getPhonePrice(),phone.getPhoneStock(),phoneId));
    }

    /**
     * 封装页头数据
     * @return 封装好的数据
     */
    private List<CategoryVO> getCategories(){
        return categoryMapper.selectList(null).stream().
                map(e -> new CategoryVO(e.getCategoryName(), e.getCategoryType()))
                .collect(Collectors.toList());
    }

    /**
     * 封装商品展览图片信息
     * @param phoneIcon 图片位置
     * @return 封装好的数据
     */
    private Map<String,String> getGoods(String phoneIcon){
        Map<String,String> goods = new HashMap<>();
        goods.put("picture",phoneIcon);
        return goods;
    }

    /**
     * 封装商品规格信息
     * @param price 默认价格
     * @param stockNum 默认库存
     * @param phoneId 商品编号
     * @return 封装好的数据模型
     */
    private SkuVO getSkuVO(BigDecimal price, Integer stockNum, Integer phoneId){
        Map<String,Object> map = new HashMap<>();
        map.put("phone_id", phoneId);
        List<Specs> specsList = specsMapper.selectByMap(map);
        return new SkuVO(getTreeVO(specsList),getSpecsCasVO(specsList),
                price+"",stockNum,false,false);
    }

    /**
     * 封装商品规格数据里边的树形结构
     * @param specsList 规格数据集
     * @return 封装好的数据模型
     */
    private List<TreeVO> getTreeVO(List<Specs> specsList){
        List<TreeVO> treeVOList = new ArrayList<>();
        TreeVO treeVO = new TreeVO("规格", getSpecsVO(specsList),"s1");
        treeVOList.add(treeVO);
        return treeVOList;
    }

    /**
     * 树形结构里边的数据集
     * @param specsList 数据集
     * @return 封装好的数据集
     */
    private List<SpecsVO> getSpecsVO(List<Specs> specsList){
        List<SpecsVO> specsVOList = new ArrayList<>();
        SpecsVO specsVO;
        for (Specs specs : specsList) {
            specsVO = new SpecsVO();
            BeanUtils.copyProperties(specs, specsVO);
            specsVOList.add(specsVO);
        }
        return specsVOList;
    }

    /**
     * 封装商品规格里边的集合属性
     * @param specsList 数据集
     * @return 封装好的数据集
     */
    private List<SpecsCasVO> getSpecsCasVO(List<Specs> specsList){
        List<SpecsCasVO> specsCasVOList = new ArrayList<>();
        SpecsCasVO specsCasVO;
        for (Specs specs : specsList) {
            specsCasVO = new SpecsCasVO();
            BeanUtils.copyProperties(specs, specsCasVO);
            specsCasVOList.add(specsCasVO);
        }
        return specsCasVOList;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        Specs specs = specsMapper.selectById(specsId);
        specs.setSpecsStock(specs.getSpecsStock()-quantity);
        if(specs.getSpecsStock()<0){
            log.error("库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        Phone phone = this.baseMapper.selectById(specs.getPhoneId());
        phone.setPhoneStock(phone.getPhoneStock()-quantity);
        if(phone.getPhoneStock()<0){
            log.error("库存不足");
            throw new PhoneException(ResultEnum.PHONE_STOCK_ERROR);
        }
        specsMapper.updateById(specs);
        this.baseMapper.updateById(phone);
    }
}
