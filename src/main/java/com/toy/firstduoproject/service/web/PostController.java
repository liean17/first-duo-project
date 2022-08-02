package com.toy.firstduoproject.service.web;

import com.toy.firstduoproject.domain.post.Posts;
import com.toy.firstduoproject.domain.post.PostRepository;
import com.toy.firstduoproject.service.web.dto.PostSaveRequestDto;
import com.toy.firstduoproject.service.web.dto.PostUpdateRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private PostRepository postRepository;

    @GetMapping("/posts/{post-id}")
    public String getPost(@PathVariable("post-id") Long postId, Model model) {
        Optional<Posts> post = postRepository.findById(postId);
        model.addAttribute("post", post);
        return "posts/post";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Posts> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @PostMapping("/posts")
    public String addPost(@ModelAttribute PostSaveRequestDto requestDto) {
        postRepository.save(requestDto.toEntity());
        return "redirect:/posts";
    }

    @PatchMapping("/posts/{post-id}")
    public String patchPost(@PathVariable("post-id") Long postId,
                            PostUpdateRequestDto updateDto){
        Optional<Posts> posts = postRepository.findById(postId);
        String content = updateDto.getContent();
        String title = updateDto.getTitle();
        posts.orElseThrow().update(title, content);

        return "posts/post";
    }

    @DeleteMapping("/posts/{post-id}")
    public String deletePost(@PathVariable("post-id") Long postId){
        postRepository.deleteById(postId);
        return "redirect:/posts";
    }
}