package com.example.geyibin.response;

import lombok.Data;

import java.util.List;

@Data
public class FindBorrowBooksResponse {

    private List<FindBorrowBookResponse> borrowBookResponseList;

    private Integer total;
}
