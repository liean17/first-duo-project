package com.toy.firstduoproject.domain.entity;

public enum PostType {
    NOTICE("공지"),CHAT("잡담"), INFO("정보"), FILE("자료"), ETC("기타");

    private final String type;
    PostType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
