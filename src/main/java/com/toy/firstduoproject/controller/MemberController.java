package com.toy.firstduoproject.controller;

import com.toy.firstduoproject.config.auth.PrincipalDetails;
import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.handler.ex.NoPermissionException;
import com.toy.firstduoproject.service.MemberService;
import com.toy.firstduoproject.service.dto.MemberSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/signup")
    public String signupForm() {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberSaveRequestDto memberSaveRequestDto) {
        memberService.createMember(memberSaveRequestDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }


    @GetMapping("/member/{member-id}")
    public String getMember(@PathVariable("member-id") Long memberId, Model model) {
        Member member = memberService.findMember(memberId);
        List<Posts> posts = member.getPosts();
        model.addAttribute("memberInfo", member);
        return "member/memberInfo";
    }

    @GetMapping("/member/edit/{member-id}")
    public String editMemberForm(@PathVariable("member-id") Long memberId,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return "member/editMember";
    }

    @PostMapping("/member/edit/{member-id}")
    public String editMember(@PathVariable("member-id") Long memberId,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return "redirect:member/" + memberId;
    }

    @PostMapping("/member/{member-id}")
    public String deleteMember(@PathVariable("member-id") Long memberId,
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(!Objects.equals(memberId, principalDetails.getMember().getId())) {
            throw new NoPermissionException("탈퇴할 권한이 없습니다.");
        }
        memberService.deleteMember(memberId);
        return "redirect:/posts";
    }
}
