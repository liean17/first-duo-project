package com.toy.firstduoproject.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Posts> posts = new ArrayList<>();

    @Builder
    public Member(String username, String password, String nickname, String email,String role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
    }

}
