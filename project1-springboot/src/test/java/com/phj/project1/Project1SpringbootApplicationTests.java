package com.phj.project1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.phj.project1.bean.Book;
import com.phj.project1.dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Project1SpringbootApplicationTests {

    @Autowired
    private BookDao bookDao;

    @Test
    void selectWrapperTest(){
        List<Book> books = bookDao.selectList(new QueryWrapper<Book>().lt("price", 70).gt("price",40));
        books.forEach(System.out::println);
    }

    @Test
    void insertTest() {
        Book book = new Book();
        book.setAuthor("张三");
        book.setTitle("传奇的张三");
        book.setStock(1000);
        book.setSales(1000);
        book.setPrice(23.33);
        int insert = bookDao.insert(book);
        System.out.println(insert);
    }

    @Test
    void selectListTest() {
        List<Book> books = bookDao.selectList(null);
        books.forEach(System.out::println);
    }

}
