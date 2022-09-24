package com.toy.firstduoproject.member.controller;

import com.toy.firstduoproject.config.auth.PrincipalDetails;
import com.toy.firstduoproject.handler.ex.NoPermissionException;
import com.toy.firstduoproject.member.dto.MemberSaveRequestDto;
import com.toy.firstduoproject.member.service.MemberService;
import com.toy.firstduoproject.member.dto.MemberUpdateDto;
import com.toy.firstduoproject.member.entity.Member;
import com.toy.firstduoproject.utils.uploadFile.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final FileStore fileStore;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("member") MemberSaveRequestDto memberSaveRequestDto) {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("member") @Valid MemberSaveRequestDto memberSaveRequestDto, BindingResult result) {
        if(result.hasErrors()){
            return "signupForm";
        }
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
        model.addAttribute("memberInfo", member);
        return "member/memberInfo";
    }

    @GetMapping("/member/edit/{member-id}")
    public String editMemberForm(@PathVariable("member-id") Long memberId,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails,
                                 Model model) {
        if(!Objects.equals(memberId, principalDetails.getMember().getId())) {
            throw new NoPermissionException("당신은 내가 알던 그녀가 아니야.");
        }
        Member member = memberService.findMember(memberId);
        model.addAttribute("member", member);
        return "member/editMember";
    }

    @PostMapping("/member/edit/{member-id}")
    public String editMember(@PathVariable("member-id") Long memberId,
                             MemberUpdateDto updateDto,
                             @AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
        if(!Objects.equals(memberId, principalDetails.getMember().getId())) {
            throw new NoPermissionException("당신은 내가 알던 그녀가 아니야.");
        }
        memberService.updateMember(memberId, updateDto);
        return "redirect:/member/" + memberId;
    }

    @PostMapping("/member/{member-id}")
    public String deleteMember(@PathVariable("member-id") Long memberId,
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (!Objects.equals(memberId, principalDetails.getMember().getId())) {
            throw new NoPermissionException("탈퇴할 권한이 없습니다.");
        }
        memberService.deleteMember(memberId);
        return "redirect:/posts";
    }
}
