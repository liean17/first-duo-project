package com.toy.firstduoproject.service;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.repository.MemberRepository;
import com.toy.firstduoproject.repository.PostRepository;
import com.toy.firstduoproject.service.dto.MemberLoginDto;
import com.toy.firstduoproject.service.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    public void createMember(MemberSaveRequestDto memberSaveRequestDto) {
        memberRepository.save(memberSaveRequestDto.toEntity());
    }

    public Member verifyMember(MemberLoginDto memberLoginDto){
        String username = memberLoginDto.getUsername();
        String password = memberLoginDto.getPassword();
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        if(!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다.") );
    }
}
