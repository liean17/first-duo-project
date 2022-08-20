package com.toy.firstduoproject.service.dto;

import com.toy.firstduoproject.domain.entity.Comments;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentResponseDto {

    //내용
    private String comment;

    //날짜
    private String createdDate;

    //닉네임
    private String nickname;

    //포스트아이디?
    private Long postsId;

    public CommentResponseDto(Comments comment){
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.nickname = comment.getMember().getNickname();
        this.postsId = comment.getPosts().getId();
    }

}
