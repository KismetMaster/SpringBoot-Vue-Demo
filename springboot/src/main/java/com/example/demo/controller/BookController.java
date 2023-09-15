package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author qiuxiaying
 */
@RestController
@RequestMapping("/book")
public class BookController {
    //    并不规范的快速实现
    @Resource BookMapper BookMapper;
     //postMapping新增，putMapping更新，getMapping查询
    @PostMapping
    public Result<?> save(@RequestBody Book book) {
        BookMapper.insert(book);
        return Result.success();
    }

//    @GetMapping
//    public  Result<?> getById(@PathVariable Long id){
//        return Result.success(BookMapper
//                .selectById(id));
//    }
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id) {
        return Result.success(BookMapper.selectById(id));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Book> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(search)) {
            wrapper.like(Book::getName, search);
        }
        Page<Book> bookPage = BookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(bookPage);
    }

    @PutMapping
    public  Result<?> update(@RequestBody Book book){
        BookMapper.updateById(book);
        return  Result.success();
    }


    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id){
        BookMapper.deleteById(id);
        return Result.success();
    }

//    @PostMapping("/login")
//    public Result<?> login(@RequestBody Book book) {
//        Book res=bookMapper.selectOne(Wrappers.<Book>lambdaQuery().eq(Book::getName,book.getName()).eq(Book::getPassword,book.getPassword()));
//
//        // 判断密码是否正确
//        if (res == null){
//            return Result.error("-1","用户或密码错误");
//        }
//        return Result.success(res);
//    }
//
//    @PostMapping("/register")
//    public Result<?> register(@RequestBody Book book) {
//        Book res=bookMapper.selectOne(Wrappers.<Book>lambdaQuery().eq(Book::getName,book.getName()));
//        if(res!=null){
//            return Result.error("-1","用户存在");
//        }
//        // 判断密码是否正确
//        bookMapper.insert(book);
//        return Result.success(res);
//    }
}
