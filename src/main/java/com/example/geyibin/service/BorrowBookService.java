package com.example.geyibin.service;

import com.example.geyibin.pojo.BorrowBook;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindBorrowBookResponse;
import com.example.geyibin.response.FindBorrowBooksResponse;

import java.util.List;

public interface BorrowBookService {

    Integer addBorrowBook(AddBorrowBookRequest request);

    Integer deleteBorrowBook(DeleteBorrowBookRequest request);

    Integer renew(RenewRequest request);

    FindBorrowBookResponse findBorrowBookById(BorrowBookIdRequest request);

    FindBorrowBooksResponse findBorrowBookByUserId(UserIdAndPageNumRequest request);

    FindBorrowBooksResponse findBorrowBookByBookId(BookIdAndPageNumRequest request);

    FindBorrowBooksResponse findAllBorrowBook(PageNumRequest request);
}
