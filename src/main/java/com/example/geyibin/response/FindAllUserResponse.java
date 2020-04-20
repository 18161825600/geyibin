package com.example.geyibin.response;

import lombok.Data;

import java.util.List;

@Data
public class FindAllUserResponse {

    private List<FindUserByIdResponse> findAllUserResponseList;

    private Integer total;
}
