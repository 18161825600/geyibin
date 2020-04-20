package com.example.geyibin.service;

import com.example.geyibin.pojo.Book;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindBookResponse;
import com.example.geyibin.response.FindBooksResponse;

import java.util.List;

public interface BookService {

    Integer addBook(AddBookRequest request);

    Integer updateBook(UpdateBookRequest request);

    Integer deleteBook(DeleteBookRequest request);

    FindBookResponse findBookById(BookIdRequest request);

    FindBooksResponse findBookByAuthorName(FindBookByAuthorNameRequest request);

    FindBooksResponse findBookByBookName(FindBookByBookNameRequest request);

    FindBooksResponse findAllBook(PageNumRequest request);
}
