package com.toy.firstduoproject.member.entity;

import com.toy.firstduoproject.comments.entity.Comments;
import com.toy.firstduoproject.posts.entity.Posts;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

    private String email;

    private String role;

    private String storeFilename;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Posts> posts = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comments> comments = new ArrayList<>();

    @Builder
    public Member(String username, String password, String nickname, String email,String role, String storeFilename) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.storeFilename = storeFilename;
    }

    public void update(String nickname, String email, String storeFilename) {
        this.nickname = nickname;
        this.email = email;
        this.storeFilename = storeFilename;
    }
}
