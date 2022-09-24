package com.toy.firstduoproject.comments;

import com.toy.firstduoproject.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //c
    @PostMapping("/posts/{postId}/comment/write")
    public String createComment(@PathVariable Long postId, CommentRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        commentService.saveComment(requestDto, postId, principalDetails.getMember());
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/posts/{postId}/comment/{commentId}")
    public String createReComment(@PathVariable Long postId, @PathVariable Long commentId,
                                  CommentRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        commentService.saveReComment(requestDto, commentId, principalDetails.getMember());

        return "redirect:/posts/" + postId;
    }

    //r?

    //u

    //d

    /**
     * 생성도, 수정도 같은 페이지에서 포스트요청을 보낸다.
     * 분리하려면 수정 요청을 다른곳에 빼야하나?
     *
     * 글 삭제가 이미 포스트 요청을 차지하고있다!
     * 댓글은 아예 다른곳으로 빼야한다.
     */
}
