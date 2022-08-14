package com.toy.firstduoproject.service.web;

import com.toy.firstduoproject.controller.PostController;
import com.toy.firstduoproject.domain.entity.Member;
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

        //when

        //then
    }

    @Test
    void 글수정(){
    }
}