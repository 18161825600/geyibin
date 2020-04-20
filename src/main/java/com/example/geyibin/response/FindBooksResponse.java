package com.example.geyibin.response;

import lombok.Data;

import java.util.List;

@Data
public class FindBooksResponse {

    private List<FindBookResponse> findBookResponseList;

    private Integer total;
}
