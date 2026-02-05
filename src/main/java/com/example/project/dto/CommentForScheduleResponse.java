package com.example.project.dto;


import lombok.Getter;

@Getter
public class CommentForScheduleResponse {
    private final long id;
    private final String content;
    private final String username;

    public CommentForScheduleResponse(long id, String content, String username) {
        this.id = id;
        this.content = content;
        this.username = username;
    }
}
