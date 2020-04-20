package com.example.geyibin.service.impl;

import com.example.geyibin.dao.UserDao;
import com.example.geyibin.pojo.User;
import com.example.geyibin.request.*;
import com.example.geyibin.response.FindAllUserResponse;
import com.example.geyibin.response.FindUserByIdResponse;
import com.example.geyibin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer register(RegisterRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request,user);
        user.setLastMoney(0.0);
        user.setIsDeposit((short)0);
        user.setLastDeposit(0.0);
        user.setCreateTime(new Date());
        return userDao.register(user);
    }

    @Override
    public FindUserByIdResponse loginIn(LoginInRequest request) {
        User user = userDao.loginIn(request.getUserName(), request.getPassword());
        return changeFindUserById(user);
    }

    @Override
    public Integer updateUserBaseInfo(UpdateUserBaseInfoRequest request) {
        User user = userDao.findUserById(request.getId());
        BeanUtils.copyProperties(request,user);
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Integer investMoney(InvestMoneyRequest request) {
        User user = userDao.findUserById(request.getId());
        user.setLastMoney(user.getLastMoney()+request.getMoney());
        user.setUpdateTime(new Date());
        return userDao.updateUser(user);
    }

    @Override
    public Integer payDeposit(PayDepositRequest request) {
        User user = userDao.findUserById(request.getId());
        if((user.getLastMoney()+user.getLastDeposit())>=50) {
            user.setLastMoney((user.getLastMoney()+user.getLastDeposit())-50);
            user.setLastDeposit(50.0);
            user.setIsDeposit((short)1);
            user.setUpdateTime(new Date());
            return userDao.updateUser(user);
        }else return 0;
    }

    @Override
    public FindUserByIdResponse findUserById(UserIdRequest request) {
        User user = userDao.findUserById(request.getUserId());
        return changeFindUserById(user);
    }

    @Override
    public FindAllUserResponse findAllUser(PageNumRequest request) {
        PageHelper.startPage(request.getPageNum(),10);
        List<User> allUser = userDao.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<>(allUser);

        List<User> userList = pageInfo.getList();
        FindAllUserResponse response = new FindAllUserResponse();
        List<FindUserByIdResponse> list = new ArrayList<>();

        for (User user : userList) {
            list.add(changeFindUserById(user));
        }
        response.setFindAllUserResponseList(list);
        response.setTotal(userDao.countAllUser());
        return response;
    }

    private FindUserByIdResponse changeFindUserById(User user){
        FindUserByIdResponse findUserByIdResponse = new FindUserByIdResponse();
        BeanUtils.copyProperties(user,findUserByIdResponse);
        if(user.getIsDeposit()==1) {
            findUserByIdResponse.setIsDeposit("已缴押金");
        }else {
            findUserByIdResponse.setIsDeposit("未缴押金");
        }
        findUserByIdResponse.setCreateTime(changeDate(user.getCreateTime()));
        return findUserByIdResponse;
    }

    private String changeDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
