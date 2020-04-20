package com.example.geyibin.request;

import lombok.Data;

@Data
public class FindBookByBookNameRequest {

    private String bookName;

    private Integer pageNum;
}
