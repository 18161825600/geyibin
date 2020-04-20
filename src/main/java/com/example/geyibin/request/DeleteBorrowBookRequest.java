package com.example.geyibin.request;

import lombok.Data;

import java.util.List;

@Data
public class DeleteBorrowBookRequest {

    private List<Long> ids;
}
