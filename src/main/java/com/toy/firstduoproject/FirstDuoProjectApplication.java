package com.toy.firstduoproject;

import com.toy.firstduoproject.member.MemberRepository;
import com.toy.firstduoproject.posts.PostRepository;
import com.toy.firstduoproject.utils.TestDataInit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FirstDuoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstDuoProjectApplication.class, args);
    }

    @Bean
    public TestDataInit dataInit(MemberRepository memberRepository,
                                 PostRepository postRepository,
                                 BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new TestDataInit(postRepository, memberRepository, bCryptPasswordEncoder);
    }
}
