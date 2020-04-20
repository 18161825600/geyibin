package com.example.geyibin.request;

import lombok.Data;

@Data
public class UpdateUserBaseInfoRequest {

    private Long Id;

    private String password;

    private String nickName;

    private String phoneNumber;

    private String payPassword;

    private String imgUrl;
}
