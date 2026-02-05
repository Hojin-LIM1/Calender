package com.example.project.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleAndCommentResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String editor;
    private final List<CommentForScheduleResponse> comments;

    public ScheduleAndCommentResponse(Long id, String title, String content, String editor, List<CommentForScheduleResponse> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.editor = editor;
        this.comments = comments;
    }
    //comment를 별도 리스트로 불러와야함.


}
