package com.toy.firstduoproject.controller;

import com.toy.firstduoproject.config.auth.PrincipalDetails;
import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.handler.ex.NoPermissionException;
import com.toy.firstduoproject.service.MemberService;
import com.toy.firstduoproject.service.dto.MemberResponseDto;
import com.toy.firstduoproject.service.dto.MemberSaveRequestDto;
import com.toy.firstduoproject.service.dto.MemberUpdateDto;
import com.toy.firstduoproject.service.file.FileStore;
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
