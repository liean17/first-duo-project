package com.toy.firstduoproject.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "posts", orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Posts(String title, Member member,String content) {
        this.title = title;
        this.content = content;
        this.member = member;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addPostTag(PostTag postTag) {
        postTags.add(postTag);
        postTag.setPosts(this);
    }

}
