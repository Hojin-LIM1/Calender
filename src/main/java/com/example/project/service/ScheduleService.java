package com.example.project.service;


import com.example.project.dto.*;
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
                request.getPassword()
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


    // 수정
    @Transactional
    public UpdateScheduleResponse update(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정은 존재하지 않습니다.")
        );

        //비밀번호 검증 로직 하나 더 추가
        if (schedule.getPassword() !=null && !schedule.getPassword().equals(request.getPassword())){
            throw new IllegalStateException("비밀번호 불일치로 수정할 수 없습니다.");
        }

        schedule.update(
                request.getTitle(),
                request.getEditor()
                //request.getCreateDate() JPA Auditing으로 처리됨 안넣어도됨,.
                //request.getUpdateDate()
        );
        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getEditor(),
                schedule.getCreateDate(), //response에는 넣어야겠지?
                schedule.getUpdateDate()
        );
    }

    @Transactional
    public void delete(Long scheduleId, Integer password) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("해당 일정은 존재하지 않습니다.")
        );

        if(!schedule.getPassword().equals(password)){
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        scheduleRepository.delete(schedule);

    }

}
