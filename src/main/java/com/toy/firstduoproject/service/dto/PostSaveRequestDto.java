package com.toy.firstduoproject.service.dto;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class PostSaveRequestDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private Member member; // 세션 정보에서 받아와야 한다.

    public PostSaveRequestDto(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .member(member)
                .content(content)
                .build();
    }
}
