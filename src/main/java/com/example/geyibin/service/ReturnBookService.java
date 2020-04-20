package com.example.geyibin.service;

import com.example.geyibin.pojo.ReturnBook;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindReturnBookResponse;
import com.example.geyibin.response.FindReturnBooksResponse;

import java.util.List;

public interface ReturnBookService {

    Integer addReturnBook(AddReturnBookRequest request);

    Integer deleteReturnBook(DeleteReturnBookRequest request);

    FindReturnBookResponse findReturnBookById(ReturnBookIdRequest request);

    FindReturnBooksResponse findReturnBooksByUserId(UserIdAndPageNumRequest request);

    FindReturnBooksResponse findReturnBooksByBookId(BookIdAndPageNumRequest request);

    FindReturnBooksResponse findAllReturnBooks(PageNumRequest request);
}
