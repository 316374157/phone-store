package com.phj.phonestore.controller;


import com.phj.phonestore.service.PhoneService;
import com.phj.phonestore.utils.ResultVOUtil;
import com.phj.phonestore.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phj
 * @since 2020-07-24
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    /**
     * 获取首页数据
     * @return 封装好的首页数据模型
     */
    @GetMapping("/index")
    public ResultVO index(){
        return ResultVOUtil.success(phoneService.getDataVO());
    }

    /**
     * 通过类型获取手机数据
     * @param categoryType 类型
     * @return 封装好的数据模型
     */
    @GetMapping("/findByCategoryType/{categoryType}")
    public ResultVO getByCategoryType(@PathVariable Integer categoryType){
        return ResultVOUtil.success(phoneService.getPhonesByType(categoryType));
    }

    /**
     * 通过手机id获取规格信息
     * @param phoneId 手机id
     * @return 封装好的规格信息
     */
    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public ResultVO getSpecsByPhoneId(@PathVariable Integer phoneId){
        return ResultVOUtil.success(phoneService.getSpecsPackageVOByPhoneId(phoneId));
    }
}
