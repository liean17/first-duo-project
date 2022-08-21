package com.toy.firstduoproject.service;

import com.toy.firstduoproject.domain.entity.Comments;
import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.repository.CommentRepository;
import com.toy.firstduoproject.repository.PostRepository;
import com.toy.firstduoproject.service.dto.CommentRequestDto;
import com.toy.firstduoproject.service.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.Comment;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    //c 리퀘스트 받아서 엔티티 만들고 저장
    public Comments saveComment(CommentRequestDto dto,Long postId,Member member){
        Comments comments = Comments.builder()
                .comment(dto.getComment())
                .createdDate(dto.getCreatedDate())
                .posts(postRepository.findById(postId).orElseThrow())
                .member(member)
                .build();
        commentRepository.save(comments);
        System.out.println("Comments"+comments.getComment());
        System.out.println("DTO"+dto.getComment());
        System.out.println(dto.getCreatedDate());
        return comments;
    }

    public void saveReComment(CommentRequestDto requestDto, Long commentId, Member member) {
        Comments parent = commentRepository.findById(commentId).orElseThrow();
        Posts post = postRepository.findById(parent.getPosts().getId()).orElseThrow();

        Comments reComment = Comments.builder()
                .comment(requestDto.getComment())
                .createdDate(requestDto.getCreatedDate())
                .posts(post)
                .member(member)
                .parent(parent)
                .build();

        commentRepository.save(reComment);
    }

    // 나중에 지울 수도 있음.
    public Comments findComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow();
    }

    //u 업데이트 dto 받아서 엔티티에 저장

    //d 댓글 아이디 받아서 삭제
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }


}
