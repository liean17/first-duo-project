package com.toy.firstduoproject.service.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class PostSaveRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;


    public PostSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

//    public Posts toEntity() {
//        return Posts.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }
}
