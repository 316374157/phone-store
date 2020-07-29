package com.phj.phonestore.service;

import com.phj.phonestore.po.Category;
import com.phj.phonestore.dao.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author 31637
 * @date 2020/7/24 19:04
 */
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    CategoryMapper categoryMapper;

    @Test
    void insert(){
        Category category = new Category();
        category.setCategoryName("烈焰红");
        category.setCategoryType(4);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        categoryMapper.insert(category);
        List<Category> categoryList = categoryMapper.selectList(null);
        categoryList.forEach(System.out::println);
    }

}
