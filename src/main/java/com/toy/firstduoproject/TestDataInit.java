package com.toy.firstduoproject;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.repository.MemberRepository;
import com.toy.firstduoproject.repository.PostRepository;
import com.toy.firstduoproject.domain.entity.Posts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void inIt(){
        log.info("test data init");
        String password1 = bCryptPasswordEncoder.encode("1234");
        String password2 = bCryptPasswordEncoder.encode("5678");
        Member member1 = memberRepository.save(new Member("1234", password1, "도경", null,"ROLE_USER", null));
        Member member2 = memberRepository.save(new Member("5678",password2,"윤조",null,"ROLE_ADMIN", null));
        postRepository.save(new Posts("사랑합니다",member1,"반가워요", null));
        postRepository.save(new Posts("좋아합니다",member1,"감사해요", null));
        postRepository.save(new Posts("고맙습니다",member1,"안녕하세요", null));
        postRepository.save(new Posts("관리자 뭐하냐",member2,"밑에지워라", null));
        postRepository.save(new Posts("아름답네요",member2,"밑에지워라", null));

    }

}
