package com.toy.firstduoproject.controller;

import com.toy.firstduoproject.config.auth.PrincipalDetails;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.handler.ex.MemberDifferentException;
import com.toy.firstduoproject.handler.ex.PostEmptyException;
import com.toy.firstduoproject.service.PostService;
import com.toy.firstduoproject.service.dto.PostSaveRequestDto;
import com.toy.firstduoproject.service.dto.PostUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String addPost(@Valid PostSaveRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long id = 0L;
        try {
            PostSaveRequestDto saveRequestDto = new PostSaveRequestDto(requestDto.getTitle(),
                    requestDto.getContent(),
                    principalDetails.getMember());
            Posts post = postService.createPost(saveRequestDto);
            id = post.getId();
        }catch (Exception e){
            throw new PostEmptyException("제목 혹은 내용이 비어있습니다.");
        }
        return "redirect:/posts/"+id;
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
    public String editForm(@PathVariable("post-id") Long postId, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){

        Posts post = postService.findById(postId);

        if(post.getMember().getId()!=principalDetails.getMember().getId()){
            throw new MemberDifferentException("글을 수정할 권리가 없습니다.");
        };

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
    public String deletePost(@PathVariable("post-id") Long postId,@AuthenticationPrincipal PrincipalDetails principalDetails){

        Posts post = postService.findById(postId);

        if(post.getMember().getId()==principalDetails.getMember().getId()||principalDetails.getMember().getRole().equals("ROLE_ADMIN")){
            postService.deletePost(postId);
            return "redirect:/posts";
        };

        throw new MemberDifferentException("글을 수정할 권리가 없습니다.");
    }
}