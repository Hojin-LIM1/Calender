package com.example.project.service;


import com.example.project.dto.CreateScheduleRequest;
import com.example.project.dto.CreateScheduleResponse;
import com.example.project.dto.GetOneScheduleResponse;
import com.example.project.entity.Schedule;
import com.example.project.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(),
                request.getContents(),
                request.getEditor(),
                request.getCreateDate(),
                request.getUpdateDate()
                );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getEditor(),
                savedSchedule.getCreateDate(),
                savedSchedule.getUpdateDate()
        );
    }

    //단건 조회

    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long scheduleID) {
        Schedule schedule = scheduleRepository.findById(scheduleID).orElseThrow(
                () -> new IllegalStateException("해당 스케줄은 없습니다.")
        );
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getEditor(),
                schedule.getCreateDate(),
                schedule.getUpdateDate()
        );
    }

    // 다건 조회
    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<GetOneScheduleResponse> dtos = new ArrayList<>();
        for(Schedule schedule : schedules) {
            GetOneScheduleResponse dto = new GetOneScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getEditor(),
                    schedule.getCreateDate(),
                    schedule.getUpdateDate()
            );
            dtos.add(dto);
        }
        return dtos;
    }

}
