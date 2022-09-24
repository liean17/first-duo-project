package com.toy.firstduoproject.posts;

import com.toy.firstduoproject.postType.PostType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateRequestDto {
    private Long postId;
    private String title;
    private String content;
    private PostType postType;

    @Builder
    public PostUpdateRequestDto(String title, String content, PostType postType) {
        this.title = title;
        this.content = content;
        this.postType = postType;
    }
}
