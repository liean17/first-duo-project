package com.toy.firstduoproject.service;

import com.toy.firstduoproject.domain.post.PostRepository;
import com.toy.firstduoproject.domain.post.Posts;
import com.toy.firstduoproject.service.web.dto.PostSaveRequestDto;
import com.toy.firstduoproject.service.web.dto.PostUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    //C
    public Posts create(PostSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity());
    }

    //R
    public Posts findById(Long postId){
        return postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException("글이 존재하지 않습니다."));
    }

    //Rs
    public List<Posts> findAll(){
        return postRepository.findAll();
    }

    //U
    public void update(Long postId, PostUpdateRequestDto requestDto){
        Posts post = postRepository.findById(postId).orElseThrow();

        String title = requestDto.getTitle();
        String content = requestDto.getContent();

        post.update(title,content);
        postRepository.save(post);
        //return post;
    }

    //D
    public void deletePost(Long postId){
        postRepository.deleteById(postId);
    }

}
