package com.toy.firstduoproject.posts;

import com.toy.firstduoproject.comments.Comments;
import com.toy.firstduoproject.postType.PostType;
import com.toy.firstduoproject.comments.CommentResponseDto;
import lombok.Getter;

import java.util.List;
import java.util.Map;
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
//        this.comments = entity.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());

        /**
         * 댓글과 대댓글을 그룹짓기
         * post.getCommentList()는 댓글과 대댓글이 모두 조회된다.
         */

        Map<Comments, List<Comments>> commentsListMap = entity.getComments().stream()
                .filter(comment -> comment.getParent() != null)
                .collect(Collectors.groupingBy(Comments::getParent));

        /**
         * 댓글과 대댓글을 통해 CommentInfoDto 생성
         */

        this.comments = commentsListMap.keySet().stream()
                .map(comment -> new CommentResponseDto(comment, commentsListMap.get(comment)))
                .collect(Collectors.toList());
    }
}
