package com.example.geyibin.service.impl;

import com.example.geyibin.dao.BookDao;
import com.example.geyibin.pojo.Book;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindBookResponse;
import com.example.geyibin.response.FindBooksResponse;
import com.example.geyibin.service.BookService;
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
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Integer addBook(AddBookRequest request) {
        Book book = new Book();
        BeanUtils.copyProperties(request,book);
        book.setCreateTime(new Date());
        return bookDao.addBook(book);
    }

    @Override
    public Integer updateBook(UpdateBookRequest request) {
        Book book = bookDao.findBookById(request.getId());
        BeanUtils.copyProperties(request,book);
        book.setUpdateTime(new Date());
        return bookDao.updateBook(book);
    }

    @Override
    public Integer deleteBook(DeleteBookRequest request) {
        return bookDao.deleteBook(request.getIds());
    }

    @Override
    public FindBookResponse findBookById(BookIdRequest request) {
        Book book = bookDao.findBookById(request.getBookId());
        return changeFindBook(book);
    }

    @Override
    public FindBooksResponse findBookByAuthorName(FindBookByAuthorNameRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<Book> bookByAuthorName = bookDao.findBookByAuthorName(request.getAuthorName());
        PageInfo<Book> pageInfo = new PageInfo<>(bookByAuthorName);

        FindBooksResponse response = changeFindBooks(pageInfo);
        response.setTotal(bookDao.countBookByAuthorName(request.getAuthorName()));
        return response;
    }

    @Override
    public FindBooksResponse findBookByBookName(FindBookByBookNameRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<Book> bookByBookName = bookDao.findBookByBookName(request.getBookName());
        PageInfo<Book> pageInfo = new PageInfo<>(bookByBookName);

        FindBooksResponse response = changeFindBooks(pageInfo);
        response.setTotal(bookDao.countBookByBookName(request.getBookName()));
        return response;
    }

    @Override
    public FindBooksResponse findAllBook(PageNumRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<Book> allBook = bookDao.findAllBook();
        PageInfo<Book> pageInfo = new PageInfo<>(allBook);

        FindBooksResponse response = changeFindBooks(pageInfo);
        response.setTotal(bookDao.countAllBook());
        return response;
    }

    private FindBookResponse changeFindBook(Book book){
        FindBookResponse findBookResponse = new FindBookResponse();
        BeanUtils.copyProperties(book,findBookResponse);
        findBookResponse.setCreateTime(changeDate(book.getCreateTime()));
        return findBookResponse;
    }

    private FindBooksResponse changeFindBooks(PageInfo<Book> pageInfo){
        List<Book> bookList = pageInfo.getList();
        FindBooksResponse response = new FindBooksResponse();
        List<FindBookResponse> list = new ArrayList<>();
        for (Book book : bookList) {
            list.add(changeFindBook(book));
        }
        response.setFindBookResponseList(list);
        return response;
    }

    private String changeDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
