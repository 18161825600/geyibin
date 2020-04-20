package com.example.geyibin.dao;

import com.example.geyibin.mapper.BookMapper;
import com.example.geyibin.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class BookDao {

    @Autowired
    private BookMapper bookMapper;

    public Integer addBook(Book book){
        return bookMapper.insert(book);
    }

    public Integer updateBook(Book book){
        return bookMapper.updateByPrimaryKeySelective(book);
    }

    public Integer deleteBook(List<Long> ids){
        Example example = new Example(Book.class);
        example.createCriteria().andIn("id",ids);
        return bookMapper.deleteByExample(example);
    }

    public Book findBookById(Long id){
        return bookMapper.selectByPrimaryKey(id);
    }

    public List<Book> findBookByAuthorName(String authorName){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("authorName",authorName);
        return bookMapper.selectByExample(example);
    }

    public List<Book> findBookByBookName(String bookName){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("bookName",bookName);
        return bookMapper.selectByExample(example);
    }

    public List<Book> findAllBook(){
        return bookMapper.selectAll();
    }

    public Integer countBookByAuthorName(String authorName){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("authorName",authorName);
        return bookMapper.selectCountByExample(example);
    }

    public Integer countBookByBookName(String bookName){
        Example example = new Example(Book.class);
        example.createCriteria().andLike("bookName",bookName);
        return bookMapper.selectCountByExample(example);
    }

    public Integer countAllBook(){
        Example example = new Example(Book.class);
        return bookMapper.selectCountByExample(example);
    }
}
