package com.toy.firstduoproject.member.controller;

import com.toy.firstduoproject.member.service.MemberService;
import com.toy.firstduoproject.member.dto.MemberResponseDto;
import com.toy.firstduoproject.member.dto.MemberSaveRequestDto;
import com.toy.firstduoproject.member.dto.MemberUpdateDto;
import com.toy.firstduoproject.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * 일단 security 관련코드 삭제
 */

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;
    @PostMapping("/api/signup")
    public Long signup(@ModelAttribute("member") @Valid MemberSaveRequestDto memberSaveRequestDto, BindingResult result) {
        if(result.hasErrors()){
            throw new RuntimeException(result.toString());
        }
        return memberService.createMember(memberSaveRequestDto);
    }

    @GetMapping("/api/member/{member-id}")
    public MemberResponseDto getMember(@PathVariable("member-id") Long memberId) {
        Member member = memberService.findMember(memberId);
        return new MemberResponseDto(member);
    }


    @PutMapping("/api/member/edit/{member-id}")
    public Long editMember(@PathVariable("member-id") Long memberId,
                             MemberUpdateDto updateDto) throws IOException {
        return memberService.updateMember(memberId, updateDto);
    }

    @DeleteMapping("/api/member/{member-id}")
    public Long deleteMember(@PathVariable("member-id") Long memberId) {
        memberService.deleteMember(memberId);
        return memberId;
    }
}
