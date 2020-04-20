package com.example.geyibin.request;

import lombok.Data;

@Data
public class AddBorrowBookRequest {

    private Long userId;

    private Long bookId;

    private Integer duration;
}
