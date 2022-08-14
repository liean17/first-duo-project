package com.toy.firstduoproject.domain.post;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.PostTag;
import com.toy.firstduoproject.domain.entity.Posts;
import com.toy.firstduoproject.domain.entity.Tags;
import com.toy.firstduoproject.repository.MemberRepository;
import com.toy.firstduoproject.repository.PostRepository;
import com.toy.firstduoproject.repository.TagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@ExtendWith(SpringExtension.class) //junit
@SpringBootTest//자동으로 h2 데이터베이스 실행
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TagRepository tagRepository;

    @AfterEach
    public void clean() {
        postRepository.deleteAll();
        memberRepository.deleteAll();
        tagRepository.deleteAll();
    }

    @Test
    @Transactional
    void test() {
        //given
        Member member = new Member("wjddbswh", "wjddbswh", "wjddbswh", "wjddbswh@wjddbswh", "ROLE_USER");
        memberRepository.save(member);

        Posts post = new Posts("Title", member, "Content");
        Tags tag = new Tags("test");
        PostTag postTag = new PostTag(post, tag);
        post.addPostTag(postTag);
        tag.addTaggedPost(postTag);
        postRepository.save(post);
        tagRepository.save(tag);

        //when
        List<Posts> posts = postRepository.findAll();
        List<Tags> tags = tagRepository.findAll();

        //then
        Posts findPost = posts.get(0);
        Tags findTag = tags.get(0);
        String title = findPost.getTitle();
        String tagText = findTag.getTag();

        List<PostTag> postTags = findPost.getPostTags();
        System.out.println(postTags.get(0).getId());
        System.out.println(postTags.get(0).getPosts().getTitle());
        System.out.println(postTags.get(0).getTags().getTag());

        Assertions.assertThat(title).isEqualTo(post.getTitle());
        Assertions.assertThat(tagText).isEqualTo(tag.getTag());
    }
}
