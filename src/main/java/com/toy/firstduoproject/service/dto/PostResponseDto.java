package com.toy.firstduoproject.service.dto;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String nickname;

    public PostResponseDto(Posts entity) {
        this.postId = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = entity.getMember().getNickname();
    }
}
