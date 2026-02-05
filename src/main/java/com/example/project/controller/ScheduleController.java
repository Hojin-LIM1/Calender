package com.example.project.controller;


import com.example.project.dto.*;
import com.example.project.entity.Schedule;
import com.example.project.repository.ScheduleRepository;
import com.example.project.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ScheduleRepository scheduleRepository;

    @PostMapping("/schedule")
    public ResponseEntity<CreateScheduleResponse> createSchedule(@RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    //단건조회
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<GetOneScheduleResponse> getOneSchedule(@PathVariable long scheduleId) {
        GetOneScheduleResponse result = scheduleService.getOne(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //다건조회
    @GetMapping("/schedule")
    public ResponseEntity<List<GetOneScheduleResponse>> getAllSchedule(

            @RequestParam(required = false) String editor
    ) {
        List<GetOneScheduleResponse> result = scheduleService.getAll(editor);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> update(
            @PathVariable Long scheduleId,
            @RequestBody UpdateScheduleRequest request
            ){
        UpdateScheduleResponse result = scheduleService.update(scheduleId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<Void> delete(
            @PathVariable long scheduleId,
            @RequestBody DeleteScheduleRequest request
    ) {
        scheduleService.delete(scheduleId, request.getPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    // LV6구현 일정 단건 조회 업그레이드
    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<ScheduleAndCommentResponse> getScheduleAndComments(@PathVariable Long scheduleId) {
        ScheduleAndCommentResponse result = scheduleService.getSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

}
