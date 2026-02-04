package com.example.project.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private Long scheduleId;
    private String contents;
    private String username;
    private Integer password;
}
