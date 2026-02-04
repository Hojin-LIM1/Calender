package com.example.project.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateScheduleRequest {

    private String title;
    private String contents;
    private String editor;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
