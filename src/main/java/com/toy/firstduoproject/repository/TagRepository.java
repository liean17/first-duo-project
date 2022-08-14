package com.toy.firstduoproject.repository;

import com.toy.firstduoproject.domain.entity.Tags;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tags, Long> {
}
