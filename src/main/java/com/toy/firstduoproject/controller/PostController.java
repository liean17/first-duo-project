package com.toy.firstduoproject.controller;

import com.toy.firstduoproject.config.auth.PrincipalDetails;
import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.handler.ex.NoPermissionException;
import com.toy.firstduoproject.service.PostService;
import com.toy.firstduoproject.service.dto.PostSaveRequestDto;
import com.toy.firstduoproject.service.dto.PostUpdateRequestDto;
import com.toy.firstduoproject.service.file.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final FileStore fileStore;

    @GetMapping("/posts/add")
    public String addForm() {
        return "posts/addPost";
    }

    @PostMapping("/posts/add")
    public String createPost(@Valid PostSaveRequestDto requestDto,
                             @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        Member member = principalDetails.getMember();
        Posts post = postService.createPost(requestDto, member);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{post-id}")
    public String getPost(@PathVariable("post-id") Long postId, Model model) {
        Posts post = postService.findPostById(postId);
        model.addAttribute("post", post);
        return "posts/post";
    }

    @GetMapping("/posts")
    public String getPosts(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails!=null){
            String nickname = principalDetails.getMember().getNickname();
            model.addAttribute("nickname",nickname);
        }
        List<Posts> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/edit/{post-id}")
    public String editForm(@PathVariable("post-id") Long postId,
                           Model model,
                           @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Posts post = postService.findPostById(postId);
        if (!Objects.equals(post.getMember().getId(), principalDetails.getMember().getId())) {
            throw new NoPermissionException("글을 수정할 권한이 없습니다.");
        }
        model.addAttribute("post", post);
        return "posts/editPost";
    }

    @PostMapping("/posts/edit/{post-id}")
    public String patchPost(@PathVariable("post-id") Long postId,
                            PostUpdateRequestDto updateDto) {
        postService.updatePost(postId, updateDto);
        return "redirect:/posts/{post-id}";
    }

    @PostMapping("/posts/{post-id}")
    public String deletePost(@PathVariable("post-id") Long postId,
                             @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Posts post = postService.findPostById(postId);
        if (Objects.equals(post.getMember().getId(), principalDetails.getMember().getId()) || principalDetails.getMember().getRole().equals("ROLE_ADMIN")) {
            postService.deletePost(postId);
            return "redirect:/posts";
        }
        throw new NoPermissionException("글을 삭제할 권한이 없습니다.");
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }


}