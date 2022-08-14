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
        Member member1 = memberRepository.save(new Member("1234", password1, "도경", null,"ROLE_USER"));
        Member member2 = memberRepository.save(new Member("5678",password2,"윤조",null,"ROLE_ADMIN"));
        postRepository.save(new Posts("갤 최초냐",member1,"야짤달린다"));
        postRepository.save(new Posts("갤 최초냐",member1,"야짤달린다"));
        postRepository.save(new Posts("갤 최초냐",member1,"야짤달린다"));
        postRepository.save(new Posts("주딱 머하냐",member2,"밑에지워라"));
    }

}
