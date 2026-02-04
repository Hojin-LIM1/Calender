package com.example.project.dto;


import jakarta.persistence.Column;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleRequest {

    private String title;
    private String editor;
    private Integer password;


}
