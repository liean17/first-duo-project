package com.toy.firstduoproject.service.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class PostSaveRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private MultipartFile attachFile;

    public PostSaveRequestDto(String title, String content, MultipartFile attachFile) {
        this.title = title;
        this.content = content;
        this.attachFile = attachFile;
    }

    //    public Posts toEntity() {
//        return Posts.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }
}
