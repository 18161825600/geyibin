package com.example.geyibin.request;

import lombok.Data;

import java.util.List;

@Data
public class DeleteBookRequest {

    private List<Long> ids;
}
