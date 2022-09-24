package com.toy.firstduoproject.comments;

import com.toy.firstduoproject.comments.Comments;
import lombok.Getter;

@Getter
public class ReCommentDto {
    //내용
    private String comment;

    //날짜
    private String createdDate;

    //닉네임
    private String nickname;

    //포스트아이디?
    private Long commentId;

    public ReCommentDto(Comments comment) {
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.nickname = comment.getMember().getNickname();
        this.commentId = comment.getParent().getId();
    }
}
