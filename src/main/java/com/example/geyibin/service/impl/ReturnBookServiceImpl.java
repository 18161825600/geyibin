package com.example.geyibin.service.impl;

import com.example.geyibin.dao.BookDao;
import com.example.geyibin.dao.BorrowBookDao;
import com.example.geyibin.dao.ReturnBookDao;
import com.example.geyibin.dao.UserDao;
import com.example.geyibin.pojo.Book;
import com.example.geyibin.pojo.BorrowBook;
import com.example.geyibin.pojo.ReturnBook;
import com.example.geyibin.pojo.User;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindReturnBookResponse;
import com.example.geyibin.response.FindReturnBooksResponse;
import com.example.geyibin.service.ReturnBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReturnBookServiceImpl implements ReturnBookService {

    @Autowired
    private ReturnBookDao returnBookDao;
    @Autowired
    private BorrowBookDao borrowBookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    @Override
    public Integer addReturnBook(AddReturnBookRequest request) {
        BorrowBook borrowBook = borrowBookDao.findBorrowBookById(request.getBorrowId());
        Book book = bookDao.findBookById(borrowBook.getBookId());
        User user = userDao.findUserById(borrowBook.getUserId());

        ReturnBook returnBook = new ReturnBook();
        returnBook.setBookId(borrowBook.getBookId());
        returnBook.setUserId(borrowBook.getUserId());
        returnBook.setBorrowId(borrowBook.getId());
        returnBook.setCreateTime(new Date());

        //拿到借书的日期和当前日期
        Date oldTime = borrowBook.getCreateTime();
        Date nowTime = new Date();

        Long day =(nowTime.getTime()-oldTime.getTime())/(24*60*60*1000);
        if(day <= borrowBook.getDuration()){
            borrowBook.setState((short)2);
            borrowBook.setUpdateTime(new Date());
            borrowBookDao.updateBorrowBook(borrowBook);

            book.setLastNumber(book.getLastNumber()+1);
            book.setUpdateTime(new Date());
            bookDao.updateBook(book);

            returnBook.setIndemnity(0.0);
            return returnBookDao.addReturnBook(returnBook);
        }else {
            borrowBook.setState((short)3);
            borrowBook.setUpdateTime(new Date());
            borrowBookDao.updateBorrowBook(borrowBook);

            book.setLastNumber(book.getLastNumber()+1);
            book.setUpdateTime(new Date());
            bookDao.updateBook(book);

            Double fine =(day-borrowBook.getDuration())*0.5;//逾期罚款金额

            user.setIsDeposit((short)0);
            user.setLastDeposit(user.getLastMoney()-fine);
            user.setUpdateTime(new Date());
            userDao.updateUser(user);

            returnBook.setIndemnity(fine);
            return returnBookDao.addReturnBook(returnBook);
        }
    }

    @Override
    public Integer deleteReturnBook(DeleteReturnBookRequest request) {
        return returnBookDao.deleteReturnBook(request.getIds());
    }

    @Override
    public FindReturnBookResponse findReturnBookById(ReturnBookIdRequest request) {
        ReturnBook returnBook = returnBookDao.findReturnBookById(request.getReturnBookId());
        return changeFindReturnBook(returnBook);
    }

    @Override
    public FindReturnBooksResponse findReturnBooksByUserId(UserIdAndPageNumRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<ReturnBook> returnBooksByUserId = returnBookDao.findReturnBooksByUserId(request.getUserId());
        PageInfo<ReturnBook> pageInfo = new PageInfo<>(returnBooksByUserId);

        FindReturnBooksResponse response = changeFindReturnBooks(pageInfo);
        response.setTotal(returnBookDao.countReturnBooksByUserId(request.getUserId()));
        return response;
    }

    @Override
    public FindReturnBooksResponse findReturnBooksByBookId(BookIdAndPageNumRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<ReturnBook> returnBooksByBookId = returnBookDao.findReturnBooksByBookId(request.getBookId());
        PageInfo<ReturnBook> pageInfo = new PageInfo<>(returnBooksByBookId);

        FindReturnBooksResponse response = changeFindReturnBooks(pageInfo);
        response.setTotal(returnBookDao.countReturnBooksByBookId(request.getBookId()));
        return response;
    }

    @Override
    public FindReturnBooksResponse findAllReturnBooks(PageNumRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<ReturnBook> allReturnBooks = returnBookDao.findAllReturnBooks();
        PageInfo<ReturnBook> pageInfo = new PageInfo<>(allReturnBooks);

        FindReturnBooksResponse response = changeFindReturnBooks(pageInfo);
        response.setTotal(returnBookDao.countAllReturnBooks());
        return response;
    }

    private FindReturnBooksResponse changeFindReturnBooks(PageInfo<ReturnBook> pageInfo){
        List<ReturnBook> returnBookList = pageInfo.getList();
        FindReturnBooksResponse response = new FindReturnBooksResponse();
        List<FindReturnBookResponse> list = new ArrayList<>();

        for (ReturnBook returnBook : returnBookList) {
            list.add(changeFindReturnBook(returnBook));
        }
        response.setFindReturnBookResponseList(list);
        return response;
    }

    private FindReturnBookResponse changeFindReturnBook(ReturnBook returnBook){
        BorrowBook borrowBook = borrowBookDao.findBorrowBookById(returnBook.getBorrowId());
        Book book = bookDao.findBookById(returnBook.getBookId());
        User user = userDao.findUserById(returnBook.getUserId());

        FindReturnBookResponse findReturnBookResponse = new FindReturnBookResponse();
        BeanUtils.copyProperties(returnBook,findReturnBookResponse);
        findReturnBookResponse.setReturnTime(changeDate(returnBook.getCreateTime()));

        findReturnBookResponse.setNickName(user.getNickName());
        findReturnBookResponse.setImgUrl(user.getImgUrl());

        findReturnBookResponse.setBookName(book.getBookName());
        findReturnBookResponse.setAuthorContent(book.getAuthorContent());
        findReturnBookResponse.setAuthorName(book.getAuthorName());
        findReturnBookResponse.setBookContent(book.getBookContent());
        findReturnBookResponse.setBookPrice(book.getBookPrice());
        findReturnBookResponse.setCoverUrl(book.getCoverUrl());
        findReturnBookResponse.setLastNumber(book.getLastNumber());

        if(borrowBook.getState()==0){
            findReturnBookResponse.setState("期限内未归还");
        }else if(borrowBook.getState()==1){
            findReturnBookResponse.setState("续借");
        }else if(borrowBook.getState()==2){
            findReturnBookResponse.setState("期限内归还");
        }else if(borrowBook.getState()==3){
            findReturnBookResponse.setState("逾期归还");
        }else if(borrowBook.getState()==4){
            findReturnBookResponse.setState("逾期未归还");
        }

        findReturnBookResponse.setCreateTime(changeDate(borrowBook.getCreateTime()));
        findReturnBookResponse.setDueTime(changeDate(borrowBook.getDueTime()));
        findReturnBookResponse.setDuration(borrowBook.getDuration());
        return findReturnBookResponse;
    }

    private String changeDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
