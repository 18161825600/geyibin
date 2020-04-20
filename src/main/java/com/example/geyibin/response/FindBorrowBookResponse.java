package com.example.geyibin.response;

import lombok.Data;

import java.util.Date;

@Data
public class FindBorrowBookResponse {

    private Long id;

    private Long userId;

    private String nickName;

    private String imgUrl;

    private Long bookId;

    private String bookName;

    private String coverUrl;

    private String authorName;

    private Integer lastNumber;

    private Double bookPrice;

    private String bookContent;

    private String authorContent;

    private String state;

    private Integer duration;

    private String dueTime;

    private String createTime;
}
