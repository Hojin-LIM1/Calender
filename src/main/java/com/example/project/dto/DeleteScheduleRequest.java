package com.example.project.dto;


import lombok.Getter;

@Getter
public class DeleteScheduleRequest {

    private Integer password; //삭제할때 비밀번호만 필요하기에 별도 dto를 작성
}
