package com.example.geyibin.response;

import lombok.Data;


@Data
public class FindBookResponse {

    private Long id;

    private String bookName;

    private String coverUrl;

    private String authorName;

    private Integer lastNumber;

    private Double bookPrice;

    private String bookContent;

    private String authorContent;

    private String createTime;
}
