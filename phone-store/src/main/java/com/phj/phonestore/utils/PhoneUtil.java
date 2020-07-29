package com.phj.phonestore.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手机操作的工具类
 * @author 31637
 * @date 2020/7/24 22:43
 */
public class PhoneUtil {

    public static List<Map<String,String>> createTag(String tag){
        String[] tags = tag.split("&");
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> hashMap;
        for (String s : tags) {
            hashMap = new HashMap<>();
            hashMap.put("name", s);
            list.add(hashMap);
        }
        return list;
    }

}
