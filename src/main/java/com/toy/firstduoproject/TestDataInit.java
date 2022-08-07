package com.toy.firstduoproject;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.repository.MemberRepository;
import com.toy.firstduoproject.repository.PostRepository;
import com.toy.firstduoproject.domain.entity.Posts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void inIt(){
        log.info("test data init");
        Member member1 = memberRepository.save(new Member("1234", "1234", "도경", null));
        Member member2 = memberRepository.save(new Member("5678","5678","윤조",null));
        postRepository.save(new Posts("갤 최초냐",member1,"야짤달린다"));
        postRepository.save(new Posts("갤 최초냐",member1,"야짤달린다"));
        postRepository.save(new Posts("갤 최초냐",member1,"야짤달린다"));
        postRepository.save(new Posts("주딱 머하냐",member2,"밑에지워라"));
    }

}
