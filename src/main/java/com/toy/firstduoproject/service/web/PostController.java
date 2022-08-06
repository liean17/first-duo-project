package com.toy.firstduoproject.service.web;

import com.toy.firstduoproject.domain.post.Posts;
import com.toy.firstduoproject.domain.post.PostRepository;
import com.toy.firstduoproject.service.PostService;
import com.toy.firstduoproject.service.web.dto.PostSaveRequestDto;
import com.toy.firstduoproject.service.web.dto.PostUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    //private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public String addPost(@ModelAttribute PostSaveRequestDto requestDto) {
        postService.create(requestDto);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{post-id}")
    public String getPost(@PathVariable("post-id") Long postId, Model model) {
        Posts post = postService.findById(postId);
        model.addAttribute("post", post);
        return "posts/post";
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Posts> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @PatchMapping("/posts/{post-id}")
    public String patchPost(@PathVariable("post-id") Long postId,
                            PostUpdateRequestDto updateDto){
        postService.update(postId,updateDto);
        return "posts/post";
    }

    @DeleteMapping("/posts/{post-id}")
    public String deletePost(@PathVariable("post-id") Long postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}