package com.springboot.mycgv.dto;

import lombok.Data;

@Data
public class SessionDto {
    private int loginResult;
    String name, id;
}
