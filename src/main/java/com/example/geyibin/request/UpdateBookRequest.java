package com.example.geyibin.request;

import lombok.Data;

@Data
public class UpdateBookRequest {

    private Long id;

    private String bookName;

    private String coverUrl;

    private String authorName;

    private Integer lastNumber;

    private Double bookPrice;

    private String bookContent;

    private String authorContent;
}
