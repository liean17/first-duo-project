package com.toy.firstduoproject.repository;

import com.toy.firstduoproject.domain.entity.Member;
import com.toy.firstduoproject.domain.entity.PostType;
import com.toy.firstduoproject.domain.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByMemberOrderByIdDesc(Member member);
    List<Posts> findAllByPostType(PostType postType);
}
