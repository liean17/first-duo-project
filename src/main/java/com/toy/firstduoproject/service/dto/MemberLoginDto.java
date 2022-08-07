package com.toy.firstduoproject.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class MemberLoginDto {
    private String username;
    private String password;

    public MemberLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
