package com.example.project.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetOneScheduleResponse {
    private final long id;
    private final String title;
    private final String contents;
    private final String editor;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;

    public GetOneScheduleResponse(long id, String title, String contents, String editor, LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.editor = editor;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
