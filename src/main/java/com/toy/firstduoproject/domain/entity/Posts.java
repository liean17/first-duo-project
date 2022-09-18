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

//    private String storeFilename;
    @OneToMany(mappedBy = "posts")
    private List<Image> images = new ArrayList<>();

    private PostType postType;

    private LocalDateTime createdAt = LocalDateTime.now();

    //List 타입의 댓글
    @OneToMany(mappedBy = "posts", cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comments> comments = new ArrayList<>();

    @Builder
    public Posts(String title, Member member,String content, PostType postType, List<Comments> comments) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.postType = postType;
        this.comments = comments;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void changePostType(PostType postType) {
        this.postType = postType;
    }

    public void addImage(Image image) {
        this.images.add(image);
        if (image.getPosts() != this) {
            image.setPosts(this);
        }
    }
}
