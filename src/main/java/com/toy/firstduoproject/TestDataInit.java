package com.toy.firstduoproject;

import com.toy.firstduoproject.domain.post.PostRepository;
import com.toy.firstduoproject.domain.post.Posts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final PostRepository postRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void inIt(){
        log.info("test data init");
        postRepository.save(new Posts("갤 최초냐","ㅇㅇ","야짤달린다"));
        postRepository.save(new Posts("주딱 머하냐","dd","밑에지워라"));
    }

}
