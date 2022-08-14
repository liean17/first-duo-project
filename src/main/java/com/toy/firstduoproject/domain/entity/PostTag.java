package com.toy.firstduoproject.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PostTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "tags_id")
    private Tags tags;

    @Builder
    public PostTag(Posts posts, Tags tags) {
        this.posts = posts;
        this.tags = tags;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }
}
