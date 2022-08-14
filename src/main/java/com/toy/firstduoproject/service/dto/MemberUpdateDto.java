package com.toy.firstduoproject.service.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class MemberUpdateDto {
    private String nickname;
    private String email;
    private MultipartFile profileImage;

    @Builder
    public MemberUpdateDto(String nickname, String email, MultipartFile profileImage) {
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }
}
