package com.example.geyibin.controller;

import com.example.geyibin.common.BooksManagementJsonResult;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindBookResponse;
import com.example.geyibin.response.FindBooksResponse;
import com.example.geyibin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("add/book")
    public BooksManagementJsonResult<Integer> addBook(@RequestBody AddBookRequest request){
        return BooksManagementJsonResult.ok(bookService.addBook(request));
    }

    @PostMapping("update/book")
    public BooksManagementJsonResult<Integer> updateBook(@RequestBody UpdateBookRequest request){
        return BooksManagementJsonResult.ok(bookService.updateBook(request));
    }

    @PostMapping("delete/book")
    public BooksManagementJsonResult<Integer> deleteBook(@RequestBody DeleteBookRequest request){
        return BooksManagementJsonResult.ok(bookService.deleteBook(request));
    }

    @PostMapping("find/book/by/id")
    public BooksManagementJsonResult<FindBookResponse> findBookById(@RequestBody BookIdRequest request){
        return BooksManagementJsonResult.ok(bookService.findBookById(request));
    }

    @PostMapping("find/book/by/authorName")
    public BooksManagementJsonResult<FindBooksResponse> findBookByAuthorName(@RequestBody FindBookByAuthorNameRequest request){
        return BooksManagementJsonResult.ok(bookService.findBookByAuthorName(request));
    }

    @PostMapping("find/book/by/bookName")
    public BooksManagementJsonResult<FindBooksResponse> findBookByBookName(@RequestBody FindBookByBookNameRequest request){
        return BooksManagementJsonResult.ok(bookService.findBookByBookName(request));
    }

    @PostMapping("find/all/book")
    public BooksManagementJsonResult<FindBooksResponse> findAllBook(@RequestBody PageNumRequest request){
        return BooksManagementJsonResult.ok(bookService.findAllBook(request));
    }
}
