package com.example.geyibin.mapper;

import com.example.geyibin.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends CommonMapper<Book> {
}