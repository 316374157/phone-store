package com.phj.project1.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.phj.project1.bean.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 31637
 * @date 2020/7/21 13:21
 */
@Mapper
public interface BookDao extends BaseMapper<Book> {

}
