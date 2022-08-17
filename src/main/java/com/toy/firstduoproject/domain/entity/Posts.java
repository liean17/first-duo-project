package com.toy.firstduoproject.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String storeFilename;

    private PostType postType;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Posts(String title, Member member,String content, String storeFilename, PostType postType) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.storeFilename = storeFilename;
        this.postType = postType;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void changePostType(PostType postType) {
        this.postType = postType;
    }
}
