package com.phj.phonestore.utils;

import com.phj.phonestore.vo.ResultVO;

/**
 * @author 31637
 * @date 2020/7/25 22:59
 */
public class ResultVOUtil {
    public static <T> ResultVO success(T data){
        return new ResultVO<T>(0,"成功",data);
    }

    public static <T> ResultVO fail(String msg){
        return new ResultVO<T>(1,msg,null);
    }
}
