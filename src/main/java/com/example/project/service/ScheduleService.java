package com.example.project.service;


import com.example.project.dto.CreateScheduleRequest;
import com.example.project.dto.CreateScheduleResponse;
import com.example.project.entity.Schedule;
import com.example.project.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
