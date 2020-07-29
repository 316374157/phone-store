package com.phj.project1.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.phj.project1.bean.Book;
import com.phj.project1.bean.Msg;
import com.phj.project1.service.BookService;
import org.springframework.web.bind.annotation.*;

/**
 *  Book控制器
 * @author phj
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 获取分页数据
     * @param pageNow 当前页码
     * @param size 页面数据数目
     * @return 封装好的返回消息
     */
    @GetMapping("/getPage/{pageNow}/{size}")
    public Msg getAllBook(@PathVariable Integer pageNow, @PathVariable Integer size){
        Page<Book> bookPage = bookService.page(new Page<>(pageNow, size));
        return Msg.success().add("bookPage", bookPage);
    }

    /**
     * 添加图书
     * @param book 要添加的图书
     * @return 封装好的返回消息
     */
    @PostMapping("/add")
    public Msg addBook(@RequestBody Book book){
        boolean flag = bookService.save(book);
        if (flag){
            return Msg.success().add("msg", "添加成功");
        }else {
            return Msg.fail().add("msg", "添加失败");
        }
    }

    /**
     * 更新图书
     * @param book 图书
     * @return 封装好的信息
     */
    @PutMapping("/update")
    public Msg updateBook(@RequestBody Book book){
        boolean flag = bookService.updateById(book);
        if (flag){
            return Msg.success().add("msg", "更新成功");
        }else {
            return Msg.fail().add("msg", "更新失败");
        }
    }

    /**
     * 根据id删除图书
     * @param id id
     * @return 封装好的返回信息
     */
    @DeleteMapping("/delete/{id}")
    public Msg deleteBook(@PathVariable Integer id){
        boolean flag = bookService.removeById(id);
        if (flag){
            return Msg.success().add("msg", "删除成功");
        }else {
            return Msg.fail().add("msg", "删除失败");
        }
    }

}
