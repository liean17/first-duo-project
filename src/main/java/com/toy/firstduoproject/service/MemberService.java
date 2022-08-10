package com.toy.firstduoproject.service;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.handler.ex.ExistException;
import com.toy.firstduoproject.repository.MemberRepository;
import com.toy.firstduoproject.service.dto.MemberSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.concurrent.ExecutionException;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createMember(MemberSaveRequestDto memberSaveRequestDto) {
        String username = memberSaveRequestDto.getUsername();

        try {
            MemberSaveRequestDto requestDto =
                    new MemberSaveRequestDto(memberSaveRequestDto.getUsername(),
                            bCryptPasswordEncoder.encode(memberSaveRequestDto.getPassword()),
                            memberSaveRequestDto.getNickname(),
                            memberSaveRequestDto.getEmail());
            memberRepository.save(requestDto.toEntity());
        }catch (RuntimeException e){
            throw new ExistException("중복오류입니다.");
        }


    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다.") );
    }
}
