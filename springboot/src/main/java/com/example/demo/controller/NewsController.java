package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author qiuxiaying
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    //    并不规范的快速实现
    @Resource NewsMapper newsMapper;
    //postMapping新增，putMapping更新，getMapping查询
    @PostMapping
    public Result<?> save(@RequestBody News news) {
        news.setTime(new Date());
        newsMapper.insert(news);
        return Result.success();
    }

    //    @GetMapping
//    public  Result<?> getById(@PathVariable Long id){
//        return Result.success(NewsMapper
//                .selectById(id));
//    }
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Integer id) {
        return Result.success(newsMapper.selectById(id));
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<News> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(search)) {
            wrapper.like(News::getTitle, search);
        }
        Page<News> newsPage = newsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(newsPage);
    }

    @PutMapping
    public  Result<?> update(@RequestBody News news){
        newsMapper.updateById(news);
        return  Result.success();
    }


    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable long id){
        newsMapper.deleteById(id);
        return Result.success();
    }

//    @PostMapping("/login")
//    public Result<?> login(@RequestBody News news) {
//        News res=newsMapper.selectOne(Wrappers.<News>lambdaQuery().eq(News::getName,news.getName()).eq(News::getPassword,news.getPassword()));
//
//        // 判断密码是否正确
//        if (res == null){
//            return Result.error("-1","用户或密码错误");
//        }
//        return Result.success(res);
//    }
//
//    @PostMapping("/register")
//    public Result<?> register(@RequestBody News news) {
//        News res=newsMapper.selectOne(Wrappers.<News>lambdaQuery().eq(News::getName,news.getName()));
//        if(res!=null){
//            return Result.error("-1","用户存在");
//        }
//        // 判断密码是否正确
//        newsMapper.insert(news);
//        return Result.success(res);
//    }
}
