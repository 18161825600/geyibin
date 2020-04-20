package com.example.geyibin.request;

import lombok.Data;

@Data
public class FindBookByAuthorNameRequest {

    private String authorName;

    private Integer pageNum;
}
