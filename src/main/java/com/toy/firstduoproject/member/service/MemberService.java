package com.toy.firstduoproject.member.service;

import com.toy.firstduoproject.handler.ex.ExistException;
import com.toy.firstduoproject.member.dto.MemberSaveRequestDto;
import com.toy.firstduoproject.member.dto.MemberUpdateDto;
import com.toy.firstduoproject.member.entity.Member;
import com.toy.firstduoproject.member.repository.MemberRepository;
import com.toy.firstduoproject.utils.uploadFile.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final FileStore fileStore;

    public Long createMember(MemberSaveRequestDto memberSaveRequestDto) {
        isExistMember(memberSaveRequestDto.getUsername());

        Member member = Member.builder()
                .username(memberSaveRequestDto.getUsername())
                .password(bCryptPasswordEncoder.encode(memberSaveRequestDto.getPassword()))
                .nickname(memberSaveRequestDto.getNickname())
                .email(memberSaveRequestDto.getEmail())
                .role("ROLE_USER")
                .build();

        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다.") );
    }

    public Long updateMember(Long memberId, MemberUpdateDto memberUpdateDto) throws IOException {
        String storeFilename = fileStore.storeFile(memberUpdateDto.getProfileImage());
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.update(memberUpdateDto.getNickname(), memberUpdateDto.getEmail(), storeFilename);
        Member save = memberRepository.save(member);
        return save.getId();
    }


    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public void isExistMember(String username) {
        Optional<Member> member = memberRepository.findByUsername(username);
        if(member.isPresent()) {
            throw new ExistException("이미 존재하는 회원입니다.");
        }
    }
}
