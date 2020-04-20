package com.example.geyibin.request;

import lombok.Data;

@Data
public class RegisterRequest {

    private String userName;

    private String password;

    private String nickName;

    private String phoneNumber;
}
