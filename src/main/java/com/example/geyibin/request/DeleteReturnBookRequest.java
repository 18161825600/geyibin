package com.example.geyibin.request;

import lombok.Data;

import java.util.List;


@Data
public class DeleteReturnBookRequest {

    private List<Long> ids;
}
