package com.toy.firstduoproject.member.dto;

import com.toy.firstduoproject.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long memberId;
    private String username;
    private String nickname;
    private String email;
    private String role;
    private String storeFilename;

    public MemberResponseDto(Member member) {
        this.memberId = member.getId();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.storeFilename = member.getStoreFilename();
    }
}
