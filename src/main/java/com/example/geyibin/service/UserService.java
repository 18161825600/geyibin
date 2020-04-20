package com.example.geyibin.service;

import com.example.geyibin.request.*;
import com.example.geyibin.response.FindAllUserResponse;
import com.example.geyibin.response.FindUserByIdResponse;

public interface UserService {

    Integer register(RegisterRequest request);

    Integer updateUserBaseInfo(UpdateUserBaseInfoRequest request);

    Integer investMoney(InvestMoneyRequest request);

    Integer payDeposit(PayDepositRequest request);

    FindUserByIdResponse findUserById(UserIdRequest request);

    FindAllUserResponse findAllUser(PageNumRequest request);
}
