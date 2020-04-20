package com.example.geyibin.dao;

import com.example.geyibin.mapper.BorrowBookMapper;
import com.example.geyibin.pojo.BorrowBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class BorrowBookDao {

    @Autowired
    private BorrowBookMapper borrowBookMapper;

    public Integer addBorrowBook(BorrowBook borrowBook){
        return borrowBookMapper.insert(borrowBook);
    }

    public Integer deleteBorrowBook(List<Long> ids){
        Example example = new Example(BorrowBook.class);
        example.createCriteria().andIn("id",ids);
        return borrowBookMapper.deleteByExample(example);
    }

    public Integer updateBorrowBook(BorrowBook borrowBook){
        return borrowBookMapper.updateByPrimaryKeySelective(borrowBook);
    }

    public BorrowBook findBorrowBookById(Long id){
        return borrowBookMapper.selectByPrimaryKey(id);
    }

    public List<BorrowBook> findBorrowBookByUserId(Long userId){
        Example example = new Example(BorrowBook.class);
        example.createCriteria().andEqualTo("userId",userId);
        return borrowBookMapper.selectByExample(example);
    }

    public List<BorrowBook> findBorrowBookByBookId(Long bookId){
        Example example = new Example(BorrowBook.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return borrowBookMapper.selectByExample(example);
    }

    public List<BorrowBook> findAllBorrowBook(){
        return borrowBookMapper.selectAll();
    }

    public Integer countBorrowBookByUserId(Long userId){
        Example example = new Example(BorrowBook.class);
        example.createCriteria().andEqualTo("userId",userId);
        return borrowBookMapper.selectCountByExample(example);
    }

    public Integer countBorrowBookByBookId(Long bookId){
        Example example = new Example(BorrowBook.class);
        example.createCriteria().andEqualTo("bookId",bookId);
        return borrowBookMapper.selectCountByExample(example);
    }

    public Integer countAllBorrowBook(){
        Example example = new Example(BorrowBook.class);
        return borrowBookMapper.selectCountByExample(example);
    }
}
