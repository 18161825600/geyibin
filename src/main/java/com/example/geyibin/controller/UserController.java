package com.example.geyibin.controller;

import com.example.geyibin.common.BooksManagementJsonResult;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindAllUserResponse;
import com.example.geyibin.response.FindUserByIdResponse;
import com.example.geyibin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public BooksManagementJsonResult<Integer> register(@RequestBody RegisterRequest request){
        try {
            userService.register(request);
            return BooksManagementJsonResult.ok("注册成功");
        }catch (Exception e){
            return BooksManagementJsonResult.errorMsg("用户名已存在");
        }
    }

    @PostMapping("loginIn")
    public BooksManagementJsonResult<FindUserByIdResponse> loginIn(LoginInRequest request){
        FindUserByIdResponse findUserByIdResponse = userService.loginIn(request);
        if(findUserByIdResponse!=null){
            return BooksManagementJsonResult.ok(findUserByIdResponse);
        }else return BooksManagementJsonResult.errorMsg("用户名或密码错误");
    }

    @PostMapping("update/user/info")
    public BooksManagementJsonResult<Integer> updateUserBaseInfo(@RequestBody UpdateUserBaseInfoRequest request){
        return BooksManagementJsonResult.ok(userService.updateUserBaseInfo(request));
    }

    @PostMapping("invest/money")
    public BooksManagementJsonResult<Integer> investMoney(@RequestBody InvestMoneyRequest request){
        return BooksManagementJsonResult.ok(userService.investMoney(request));
    }

    @PostMapping("pay/deposit")
    public BooksManagementJsonResult<Integer> payDeposit(@RequestBody PayDepositRequest request){
        Integer integer = userService.payDeposit(request);
        if(integer==1) {
            return BooksManagementJsonResult.ok("押金缴付成功");
        }else return BooksManagementJsonResult.errorMsg("余额不足");
    }

    @PostMapping("find/user/by/id")
    public BooksManagementJsonResult<FindUserByIdResponse> findUserById(@RequestBody UserIdRequest request){
        return BooksManagementJsonResult.ok(userService.findUserById(request));
    }

    @PostMapping("find/all/user")
    public BooksManagementJsonResult<FindAllUserResponse> findAllUser(@RequestBody PageNumRequest request){
        return BooksManagementJsonResult.ok(userService.findAllUser(request));
    }

}
