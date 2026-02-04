package com.example.project.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String contents;
    private final String username;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public CreateCommentResponse(Long id, String contents, String username, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.contents = contents;
        this.username = username;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
