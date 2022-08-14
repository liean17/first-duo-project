package com.toy.firstduoproject.controller;

import com.toy.firstduoproject.config.auth.PrincipalDetails;
import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.handler.ex.NoPermissionException;
import com.toy.firstduoproject.service.MemberService;
import com.toy.firstduoproject.service.dto.MemberSaveRequestDto;
import com.toy.firstduoproject.service.dto.MemberUpdateDto;
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
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final FileStore fileStore;

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

//    @ResponseBody
//    @GetMapping("/images/{filename}")
//    public Resource showImage(@PathVariable String filename) throws MalformedURLException {
//        return new UrlResource("file:" + fileStore.getFullPath(filename));
//    }
}
