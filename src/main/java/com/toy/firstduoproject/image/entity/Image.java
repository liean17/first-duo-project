package com.toy.firstduoproject.image.entity;

import com.toy.firstduoproject.posts.entity.Posts;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storeFilename;
//    private Long fileSize;
    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @Builder
    public Image(Long id, String storeFilename, Posts posts) {
        this.id = id;
        this.storeFilename = storeFilename;
        this.posts = posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
        if (!posts.getImages().contains(this)) {
            posts.getImages().add(this);
        }
    }
}
