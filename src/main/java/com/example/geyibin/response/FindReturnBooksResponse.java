package com.example.geyibin.response;

import lombok.Data;

import java.util.List;

@Data
public class FindReturnBooksResponse {

    private List<FindReturnBookResponse> findReturnBookResponseList;

    private Integer total;
}
