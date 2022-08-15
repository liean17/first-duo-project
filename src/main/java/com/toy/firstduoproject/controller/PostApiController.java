package com.toy.firstduoproject.controller;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.service.PostService;
import com.toy.firstduoproject.service.dto.PostResponseDto;
import com.toy.firstduoproject.service.dto.PostSaveRequestDto;
import com.toy.firstduoproject.service.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 일단 security 관련코드 삭제
 */

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/posts/add")
    public Long createPost(@RequestBody @Valid PostSaveRequestDto requestDto) throws IOException {
        Member member = new Member("도경","12345678","도경",null,"ROLE_USER",null);
        Posts post = postService.createPost(requestDto, member);
        return post.getId();
    }

    @GetMapping("/api/posts/{post-id}")
    public PostResponseDto getPost(@PathVariable("post-id") Long postId) {
        Posts post = postService.findPostById(postId);
        return new PostResponseDto(post);
    }

    @GetMapping("/api/posts")
    public List<PostResponseDto> getPosts() {

        List<Posts> posts = postService.findAll();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Posts post : posts) {
            PostResponseDto responseDto = new PostResponseDto(post);
            postResponseDtos.add(responseDto);
        }
        return postResponseDtos;
    }

    @PutMapping("/api/posts/edit/{post-id}")
    public Long patchPost(@PathVariable("post-id") Long postId,
                            PostUpdateRequestDto updateDto) {
        postService.updatePost(postId, updateDto);
        return postId;
    }

    @DeleteMapping("/api/posts/{post-id}")
    public Long deletePost(@PathVariable("post-id") Long postId) {
        postService.deletePost(postId);
        return postId;
    }
}