package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
//写完enity，写mapper
@Mapper
public interface BookMapper extends BaseMapper<Book> {
                                             //操作的是book就写book

}
