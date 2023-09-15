package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Book;
import com.example.demo.entity.News;
import org.apache.ibatis.annotations.Mapper;

//写完enity，写mapper
@Mapper
public interface NewsMapper extends BaseMapper<News> {
                                             //操作的是book就写book

}
