package com.toy.firstduoproject.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tags_id")
    private Long id;

    private String tag;

    @OneToMany(mappedBy = "tags")
    private List<PostTag> taggedPost = new ArrayList<>();

    @Builder
    public Tags(String tag) {
        this.tag = tag;
    }

    public void addTaggedPost(PostTag postTag) {
        taggedPost.add(postTag);
        postTag.setTags(this);
    }
}
