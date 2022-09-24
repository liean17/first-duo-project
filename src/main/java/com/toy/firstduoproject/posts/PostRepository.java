package com.toy.firstduoproject.posts;

import com.toy.firstduoproject.member.Member;
import com.toy.firstduoproject.postType.PostType;
import com.toy.firstduoproject.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByMemberOrderByIdDesc(Member member);
    List<Posts> findAllByPostType(PostType postType);
}
