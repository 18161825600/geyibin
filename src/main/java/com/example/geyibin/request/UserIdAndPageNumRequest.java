package com.example.geyibin.request;

import lombok.Data;

@Data
public class UserIdAndPageNumRequest {

    private Long userId;

    private Integer pageNum=1;
}
