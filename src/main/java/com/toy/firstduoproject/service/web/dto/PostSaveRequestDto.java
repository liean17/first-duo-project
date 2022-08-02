package com.toy.firstduoproject.service.web.dto;

import com.toy.firstduoproject.domain.post.Posts;
import lombok.Getter;

@Getter
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String author;

    public PostSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .author(author)
                .content(content)
                .build();
    }
}
