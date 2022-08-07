package com.toy.firstduoproject.controller;

import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.service.PostService;
import com.toy.firstduoproject.service.dto.PostSaveRequestDto;
import com.toy.firstduoproject.service.dto.PostUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    //private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String main() {
        return "redirect:/posts";
    }

    @GetMapping("/posts/add")
    public String addForm(){
        return "posts/addPost";
    }

    @PostMapping("/posts/add")
    public String addPost(@ModelAttribute PostSaveRequestDto requestDto) {
        postService.createPost(requestDto);
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

    @GetMapping("/posts/{post-id}/edit")
    public String editForm(@PathVariable("post-id") Long postId, Model model){
        Posts post = postService.findById(postId);
        model.addAttribute("post",post);
        return "posts/editPost";
    }

    @PostMapping("/posts/{post-id}/edit")
    public String patchPost(@PathVariable("post-id") Long postId,
                            PostUpdateRequestDto updateDto){
        postService.updatePost(postId,updateDto);
        return "redirect:/posts/{post-id}";
    }

    @PostMapping("/posts/{post-id}")
    public String deletePost(@PathVariable("post-id") Long postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}