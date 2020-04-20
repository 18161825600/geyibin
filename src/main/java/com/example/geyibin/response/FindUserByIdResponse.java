package com.example.geyibin.response;

import lombok.Data;

import java.util.Date;

@Data
public class FindUserByIdResponse {

    private Long id;

    private String userName;

    private String password;

    private String nickName;

    private String phoneNumber;

    private String psyPassword;

    private String imgUrl;

    private Double lastMoney;

    private String isDeposit;

    private Double lastDeposit;

    private String createTime;
}
