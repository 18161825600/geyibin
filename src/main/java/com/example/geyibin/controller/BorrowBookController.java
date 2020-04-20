package com.example.geyibin.controller;

import com.example.geyibin.common.BooksManagementJsonResult;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindBorrowBookResponse;
import com.example.geyibin.response.FindBorrowBooksResponse;
import com.example.geyibin.service.BorrowBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class BorrowBookController {

    @Autowired
    private BorrowBookService borrowBookService;

    @PostMapping("add/borrow/book")
    public BooksManagementJsonResult<Integer> addBorrowBook(@RequestBody AddBorrowBookRequest request){
        Integer integer = borrowBookService.addBorrowBook(request);
        if(integer==1){
            return BooksManagementJsonResult.ok("借书成功");
        }else if(integer==-1){
            return BooksManagementJsonResult.errorMsg("借书期限不能小于等于0天");
        }else if(integer==-2){
            return BooksManagementJsonResult.errorMsg("借书期限不能大于30天");
        }else if(integer==-3){
            return BooksManagementJsonResult.errorMsg("用户没有缴押金");
        }else if(integer==-4){
            return BooksManagementJsonResult.errorMsg("图书剩余量不足");
        }else return BooksManagementJsonResult.errorMsg("借书失败");
    }

    @PostMapping("delete/borrow/book")
    public BooksManagementJsonResult<Integer> deleteBorrowBook(@RequestBody DeleteBorrowBookRequest request){
        return BooksManagementJsonResult.ok(borrowBookService.deleteBorrowBook(request));
    }

    @PostMapping("renew")
    public BooksManagementJsonResult<Integer> renew(@RequestBody RenewRequest request){
        Integer renew = borrowBookService.renew(request);
        if(renew==1){
            return BooksManagementJsonResult.ok("续借成功");
        }else if(renew==-1){
            return BooksManagementJsonResult.errorMsg("续借期限不能小于等于0天");
        }else if(renew==-2){
            return BooksManagementJsonResult.errorMsg("续借期限不能大于30天");
        }else return BooksManagementJsonResult.errorMsg("续借失败");
    }

    @PostMapping("find/borrow/book/by/id")
    public BooksManagementJsonResult<FindBorrowBookResponse> findBorrowBookById(@RequestBody BorrowBookIdRequest request){
        return BooksManagementJsonResult.ok(borrowBookService.findBorrowBookById(request));
    }

    @PostMapping("find/borrow/book/by/userId")
    public BooksManagementJsonResult<FindBorrowBooksResponse> findBorrowBookByUserId(@RequestBody UserIdAndPageNumRequest request){
        return BooksManagementJsonResult.ok(borrowBookService.findBorrowBookByUserId(request));
    }

    @PostMapping("find/borrow/book/by/bookId")
    public BooksManagementJsonResult<FindBorrowBooksResponse> findBorrowBookByBookId(@RequestBody BookIdAndPageNumRequest request){
        return BooksManagementJsonResult.ok(borrowBookService.findBorrowBookByBookId(request));
    }

    @PostMapping("find/all/borrow/book")
    public BooksManagementJsonResult<FindBorrowBooksResponse> findAllBorrowBook(@RequestBody PageNumRequest request){
        return BooksManagementJsonResult.ok(borrowBookService.findAllBorrowBook(request));
    }
}
