package com.toy.firstduoproject.posts.controller;

import com.toy.firstduoproject.config.auth.PrincipalDetails;
import com.toy.firstduoproject.comments.entity.Comments;
import com.toy.firstduoproject.member.entity.Member;
import com.toy.firstduoproject.postType.entity.PostType;
import com.toy.firstduoproject.handler.ex.NoPermissionException;
import com.toy.firstduoproject.posts.dto.PostSaveRequestDto;
import com.toy.firstduoproject.posts.service.PostService;
import com.toy.firstduoproject.posts.dto.PostUpdateRequestDto;
import com.toy.firstduoproject.posts.entity.Posts;
import com.toy.firstduoproject.utils.uploadFile.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class PostController {
    private final PostService postService;
    private final FileStore fileStore;

    @ModelAttribute("postTypes")
    public PostType[] postType() {
        return Arrays.stream(PostType.values())
                .filter(postType -> !postType.equals(PostType.NOTICE))
                .toArray(PostType[]::new);
    }

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
        //포스트에서 댓글 따로 빼서 넘기기?
        List<Comments> comments = post.getComments();
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "posts/post";
    }

    @GetMapping("/posts")
    public String getPosts(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails,
                           Pageable pageable,
                           @RequestParam(value = "postType", defaultValue = "") PostType postType) {
        if(principalDetails!=null){
            String nickname = principalDetails.getMember().getNickname();
            model.addAttribute("nickname",nickname);
        }
        Page<Posts> posts = postService.findAll(pageable);
        List<Posts> notices = postService.findNotice();
        model.addAttribute("posts", posts);
        model.addAttribute("notice",notices);
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