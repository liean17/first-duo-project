package com.toy.firstduoproject.service.dto;

import com.toy.firstduoproject.domain.entity.PostType;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Getter
public class PostSaveRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private MultipartFile attachFile;

    private PostType postType;

    public PostSaveRequestDto(String title, String content, MultipartFile attachFile, PostType postType) {
        this.title = title;
        this.content = content;
        this.attachFile = attachFile;
        this.postType = Objects.requireNonNullElse(postType, PostType.CHAT);
    }

    //    public Posts toEntity() {
//        return Posts.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }
}
