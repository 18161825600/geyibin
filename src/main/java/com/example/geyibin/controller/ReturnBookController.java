package com.example.geyibin.controller;

import com.example.geyibin.common.BooksManagementJsonResult;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindReturnBookResponse;
import com.example.geyibin.response.FindReturnBooksResponse;
import com.example.geyibin.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReturnBookController {

    @Autowired
    private ReturnBookService returnBookService;

    @PostMapping("add/return/book")
    public BooksManagementJsonResult<Integer> addReturnBook(@RequestBody AddReturnBookRequest request){
        return BooksManagementJsonResult.ok(returnBookService.addReturnBook(request));
    }

    @PostMapping("delete/return/book")
    public BooksManagementJsonResult<Integer> deleteReturnBook(@RequestBody DeleteReturnBookRequest request){
        return BooksManagementJsonResult.ok(returnBookService.deleteReturnBook(request));
    }

    @PostMapping("find/return/book/by/id")
    public BooksManagementJsonResult<FindReturnBookResponse> findReturnBookById(@RequestBody ReturnBookIdRequest request){
        return BooksManagementJsonResult.ok(returnBookService.findReturnBookById(request));
    }

    @PostMapping("find/return/book/by/userId")
    public BooksManagementJsonResult<FindReturnBooksResponse> findReturnBooksByUserId(@RequestBody UserIdAndPageNumRequest request){
        return BooksManagementJsonResult.ok(returnBookService.findReturnBooksByUserId(request));
    }

    @PostMapping("find/return/book/by/bookId")
    public BooksManagementJsonResult<FindReturnBooksResponse> findReturnBooksByBookId(@RequestBody BookIdAndPageNumRequest request){
        return BooksManagementJsonResult.ok(returnBookService.findReturnBooksByBookId(request));
    }

    @PostMapping("find/all/return/book")
    public BooksManagementJsonResult<FindReturnBooksResponse> findAllReturnBooks(@RequestBody PageNumRequest request){
        return BooksManagementJsonResult.ok(returnBookService.findAllReturnBooks(request));
    }
}
