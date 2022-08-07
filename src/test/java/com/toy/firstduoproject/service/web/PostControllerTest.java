package com.toy.firstduoproject.service.web;

import com.toy.firstduoproject.controller.PostController;
import com.toy.firstduoproject.repository.PostRepository;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.service.dto.PostSaveRequestDto;
import com.toy.firstduoproject.service.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    PostController postController;

    @Autowired
    PostRepository postRepository;

    @AfterEach
    void clean(){postRepository.deleteAll();}

    @Test
    void 글등록(){
        //given
        PostSaveRequestDto requestDto = new PostSaveRequestDto("title","content","ㅇㅇ");
        postController.addPost(requestDto);

        //when
        List<Posts> posts = postRepository.findAll();

        //then
        Posts post = posts.get(0);
        assertThat(post.getAuthor()).isEqualTo("ㅇㅇ");
    }

    @Test
    void 글수정(){
        //given
        PostSaveRequestDto requestDto = new PostSaveRequestDto("title","content","ㅇㅇ");
        postController.addPost(requestDto);

        //when
        Long savedPostId = 1L;

        PostUpdateRequestDto updateRequestDto = new PostUpdateRequestDto("제목","내용");
        postController.patchPost(savedPostId,updateRequestDto);

        Optional<Posts> posts = postRepository.findById(savedPostId);

        //then
        assertThat(posts.orElseThrow().getAuthor()).isEqualTo("ㅇㅇ");
        assertThat(posts.orElseThrow().getTitle()).isEqualTo("제목");
    }
}