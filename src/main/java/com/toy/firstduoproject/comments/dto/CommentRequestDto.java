package com.toy.firstduoproject.comments.dto;

import com.toy.firstduoproject.comments.entity.Comments;
import com.toy.firstduoproject.member.entity.Member;
import com.toy.firstduoproject.posts.entity.Posts;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentRequestDto {

    //내용
    @NotBlank
    private String comment;

    //날짜
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    private Comments parent;

    //포스트?
    private Posts posts;

    //멤버?
    private Member member;

    public CommentRequestDto(String comment) {
        this.comment = comment;
    }
}
