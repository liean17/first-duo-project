package com.toy.firstduoproject.domain.post;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

//@ExtendWith(SpringExtension.class) //junit
@SpringBootTest//자동으로 h2 데이터베이스 실행
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @AfterEach
    public void clean(){postRepository.deleteAll();}

    @Test
    void 글쓰기(){
        String title = "title";
        String content = "content";
        String author = "author";

        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        List<Posts> postsList = postRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
        assertThat(posts.getAuthor().equals(author));
    }

    @Test
    void 로데타(){
        LocalDateTime before = LocalDateTime.of(2022,7,31,0,0,0);
        postRepository.save(Posts.builder()
                .title("title")
                .author("ㅇㅇ")
                .content("gg")
                .build());

        List<Posts> posts = postRepository.findAll();
        Posts post = posts.get(0);

        assertThat(post.getCreatedAt()).isAfter(before);
    }
}
