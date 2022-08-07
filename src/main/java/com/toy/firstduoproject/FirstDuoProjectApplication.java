package com.toy.firstduoproject;

import com.toy.firstduoproject.repository.MemberRepository;
import com.toy.firstduoproject.repository.PostRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstDuoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstDuoProjectApplication.class, args);
    }

    @Bean
    public TestDataInit dataInit(MemberRepository memberRepository, PostRepository postRepository){return new TestDataInit(postRepository, memberRepository);}
}
