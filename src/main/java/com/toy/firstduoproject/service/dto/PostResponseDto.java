package com.toy.firstduoproject.service.dto;

import com.toy.firstduoproject.domain.entity.PostType;
import com.toy.firstduoproject.domain.entity.Posts;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String nickname;
    private PostType postType;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Posts entity) {
        this.postId = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = entity.getMember().getNickname();
        this.postType = entity.getPostType();
        this.comments = entity.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
