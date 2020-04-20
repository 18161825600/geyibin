package com.example.geyibin.request;

import lombok.Data;

@Data
public class RenewRequest {

    private Long borrowId;

    private Integer duration;
}
