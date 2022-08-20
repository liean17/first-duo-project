package com.toy.firstduoproject.repository;

import com.toy.firstduoproject.domain.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments,Long> {
}
