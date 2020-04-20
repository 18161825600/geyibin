package com.example.geyibin.request;

import lombok.Data;

@Data
public class BookIdAndPageNumRequest {

    private Long bookId;

    private Integer pageNum=1;
}
