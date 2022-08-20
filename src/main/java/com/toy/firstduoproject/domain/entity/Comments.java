package com.toy.firstduoproject.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comments {
    //id
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //내용
    private String comment;

    //작성일
    private String createdDate;

    //포스트
    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    //멤버
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Comments(String comment, String createdDate, Posts posts, Member member) {
        this.comment = comment;
        this.createdDate = createdDate;
        this.posts = posts;
        this.member = member;
    }
}
