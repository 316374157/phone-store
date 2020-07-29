package com.phj.project1.service.impl;

import com.phj.project1.bean.Book;
import com.phj.project1.dao.BookMapper;
import com.phj.project1.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phj
 * @since 2020-07-22
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
