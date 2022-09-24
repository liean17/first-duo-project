package com.toy.firstduoproject.comments;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<ReCommentDto> reCommentList = new ArrayList<>();

    public CommentResponseDto(Comments comment, List<Comments> reCommentList){
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.nickname = comment.getMember().getNickname();
        this.postsId = comment.getPosts().getId();
        this.reCommentList = reCommentList.stream().map(ReCommentDto::new).collect(Collectors.toList());
    }
}
