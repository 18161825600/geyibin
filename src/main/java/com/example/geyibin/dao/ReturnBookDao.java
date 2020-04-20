package com.example.geyibin.dao;

import com.example.geyibin.mapper.ReturnBookMapper;
import com.example.geyibin.pojo.ReturnBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class ReturnBookDao {

    @Autowired
    private ReturnBookMapper returnBookMapper;

    public Integer addReturnBook(ReturnBook returnBook){
        return returnBookMapper.insert(returnBook);
    }

    public Integer deleteReturnBook(List<Long> ids){
        Example example = new Example(ReturnBook.class);
        example.createCriteria().andIn("id",ids);
        return returnBookMapper.deleteByExample(example);
    }

    public ReturnBook findReturnBookById(Long id){
        return returnBookMapper.selectByPrimaryKey(id);
    }

    public List<ReturnBook> findReturnBooksByUserId(Long userId){
        Example example = new Example(ReturnBook.class);
        example.createCriteria().andEqualTo("userId",userId);
        return returnBookMapper.selectByExample(example);
    }

    public List<ReturnBook> findReturnBooksByBookId(Long bookId){
        Example example = new Example(ReturnBook.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return returnBookMapper.selectByExample(example);
    }

    public List<ReturnBook> findAllReturnBooks(){
        return returnBookMapper.selectAll();
    }

    public Integer countReturnBooksByUserId(Long userId){
        Example example = new Example(ReturnBook.class);
        example.createCriteria().andEqualTo("userId",userId);
        return returnBookMapper.selectCountByExample(example);
    }

    public Integer countReturnBooksByBookId(Long bookId){
        Example example = new Example(ReturnBook.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return returnBookMapper.selectCountByExample(example);
    }

    public Integer countAllReturnBooks(){
        Example example = new Example(ReturnBook.class);
        return returnBookMapper.selectCountByExample(example);
    }
}
