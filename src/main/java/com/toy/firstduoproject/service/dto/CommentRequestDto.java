package com.toy.firstduoproject.service.dto;

import com.toy.firstduoproject.domain.entity.Comments;
import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    //내용
    @NotBlank
    private String comment;

    //날짜
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

    //포스트?
    private Posts posts;

    //멤버?
    private Member member;

}
