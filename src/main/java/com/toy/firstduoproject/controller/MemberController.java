package com.toy.firstduoproject.controller;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.service.MemberService;
import com.toy.firstduoproject.service.PostService;
import com.toy.firstduoproject.service.dto.MemberLoginDto;
import com.toy.firstduoproject.service.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PostService postService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberSaveRequestDto memberSaveRequestDto) {
        memberService.createMember(memberSaveRequestDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberLoginDto memberLoginDto) {
        memberService.verifyMember(memberLoginDto);
        return "redirect:/posts";
    }

    @GetMapping("/member/{member-id}")
    public String getMember(@PathVariable("member-id") Long memberId, Model model) {
        Member member = memberService.findMember(memberId);
        List<Posts> posts = postService.findAllByMember(member);
        model.addAttribute("posts", posts);
        return "posts";
    }
}
