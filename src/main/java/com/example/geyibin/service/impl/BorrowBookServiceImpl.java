package com.example.geyibin.service.impl;

import com.example.geyibin.dao.BookDao;
import com.example.geyibin.dao.BorrowBookDao;
import com.example.geyibin.dao.UserDao;
import com.example.geyibin.pojo.Book;
import com.example.geyibin.pojo.BorrowBook;
import com.example.geyibin.pojo.User;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindBorrowBookResponse;
import com.example.geyibin.response.FindBorrowBooksResponse;
import com.example.geyibin.service.BorrowBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {

    @Autowired
    private BorrowBookDao borrowBookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public Integer addBorrowBook(AddBorrowBookRequest request) {
        BorrowBook borrowBook = new BorrowBook();
        if(request.getDuration()<=0){
            return -1;
        }else if(request.getDuration()>30){
            return -2;
        }else {
            User user = userDao.findUserById(request.getUserId());
            Book book = bookDao.findBookById(request.getBookId());
            if(book.getLastNumber()>0) {
                if (user.getIsDeposit() == 1) {
                    borrowBook.setBookId(request.getBookId());
                    borrowBook.setUserId(request.getUserId());
                    borrowBook.setDuration(request.getDuration());
                    borrowBook.setState((short)0);
                    borrowBook.setCreateTime(new Date());

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.roll(calendar.DAY_OF_YEAR,request.getDuration());
                    borrowBook.setDueTime(calendar.getTime());

                    book.setLastNumber(book.getLastNumber()-1);
                    book.setUpdateTime(new Date());
                    bookDao.updateBook(book);

                    return borrowBookDao.addBorrowBook(borrowBook);
                }else {
                    return -3;
                }
            }else return -4;
        }
    }

    @Override
    public Integer deleteBorrowBook(DeleteBorrowBookRequest request) {
        return borrowBookDao.deleteBorrowBook(request.getIds());
    }

    @Override
    public Integer renew(RenewRequest request) {
        BorrowBook borrowBook = borrowBookDao.findBorrowBookById(request.getBorrowId());
        if(request.getDuration()<=0){
            return -1;
        }else if(request.getDuration()>30){
            return -2;
        }else {
            borrowBook.setDuration(borrowBook.getDuration()+request.getDuration());
            borrowBook.setUpdateTime(new Date());
            borrowBook.setState((short)1);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(borrowBook.getDueTime());
            calendar.roll(Calendar.DAY_OF_YEAR,request.getDuration());
            borrowBook.setDueTime(calendar.getTime());

            return borrowBookDao.updateBorrowBook(borrowBook);
        }
    }

    @Override
    public FindBorrowBookResponse findBorrowBookById(BorrowBookIdRequest request) {
        BorrowBook borrowBook = borrowBookDao.findBorrowBookById(request.getBorrowBookId());
        return changeFindBorrowBook(borrowBook);
    }

    @Override
    public FindBorrowBooksResponse findBorrowBookByUserId(UserIdAndPageNumRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<BorrowBook> borrowBookByUserId = borrowBookDao.findBorrowBookByUserId(request.getUserId());
        PageInfo<BorrowBook> pageInfo = new PageInfo<>(borrowBookByUserId);

        FindBorrowBooksResponse response = changeFindBorrowBooks(pageInfo);
        response.setTotal(borrowBookDao.countBorrowBookByUserId(request.getUserId()));
        return response;
    }

    @Override
    public FindBorrowBooksResponse findBorrowBookByBookId(BookIdAndPageNumRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<BorrowBook> borrowBookByBookId = borrowBookDao.findBorrowBookByBookId(request.getBookId());
        PageInfo<BorrowBook> pageInfo = new PageInfo<>(borrowBookByBookId);

        FindBorrowBooksResponse response = changeFindBorrowBooks(pageInfo);
        response.setTotal(borrowBookDao.countBorrowBookByBookId(request.getBookId()));
        return response;
    }

    @Override
    public FindBorrowBooksResponse findAllBorrowBook(PageNumRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<BorrowBook> allBorrowBook = borrowBookDao.findAllBorrowBook();
        PageInfo<BorrowBook> pageInfo = new PageInfo<>(allBorrowBook);

        FindBorrowBooksResponse response = changeFindBorrowBooks(pageInfo);
        response.setTotal(borrowBookDao.countAllBorrowBook());
        return response;
    }

    private FindBorrowBookResponse changeFindBorrowBook(BorrowBook borrowBook){
        Book book = bookDao.findBookById(borrowBook.getBookId());
        User user = userDao.findUserById(borrowBook.getUserId());
        FindBorrowBookResponse findBorrowBookResponse = new FindBorrowBookResponse();
        BeanUtils.copyProperties(borrowBook,findBorrowBookResponse);
        findBorrowBookResponse.setDueTime(changeDate(borrowBook.getDueTime()));
        findBorrowBookResponse.setCreateTime(changeDate(borrowBook.getCreateTime()));

        if(borrowBook.getState()==0){
            findBorrowBookResponse.setState("期限内未归还");
        }else if(borrowBook.getState()==1){
            findBorrowBookResponse.setState("续借");
        }else if(borrowBook.getState()==2){
            findBorrowBookResponse.setState("期限内归还");
        }else if(borrowBook.getState()==3){
            findBorrowBookResponse.setState("逾期归还");
        }else if(borrowBook.getState()==4){
            findBorrowBookResponse.setState("逾期未归还");
        }

        findBorrowBookResponse.setBookName(book.getBookName());
        findBorrowBookResponse.setAuthorName(book.getAuthorName());
        findBorrowBookResponse.setAuthorContent(book.getAuthorContent());
        findBorrowBookResponse.setBookContent(book.getBookContent());
        findBorrowBookResponse.setBookPrice(book.getBookPrice());
        findBorrowBookResponse.setCoverUrl(book.getCoverUrl());
        findBorrowBookResponse.setLastNumber(book.getLastNumber());

        findBorrowBookResponse.setNickName(user.getNickName());
        findBorrowBookResponse.setImgUrl(user.getImgUrl());
        return findBorrowBookResponse;
    }

    private FindBorrowBooksResponse changeFindBorrowBooks(PageInfo<BorrowBook> pageInfo){
        List<BorrowBook> borrowBookList = pageInfo.getList();
        FindBorrowBooksResponse response = new FindBorrowBooksResponse();
        List<FindBorrowBookResponse> list = new ArrayList<>();
        for (BorrowBook borrowBook : borrowBookList) {
            list.add(changeFindBorrowBook(borrowBook));
        }
        response.setBorrowBookResponseList(list);
        return response;
    }

    private String changeDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
