package com.toy.firstduoproject.posts.repository;

import com.toy.firstduoproject.member.entity.Member;
import com.toy.firstduoproject.postType.entity.PostType;
import com.toy.firstduoproject.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByMemberOrderByIdDesc(Member member);
    List<Posts> findAllByPostType(PostType postType);
}
