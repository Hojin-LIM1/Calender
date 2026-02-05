package com.example.project.repository;

import com.example.project.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByEditorOrderByUpdateDateDesc(String editor);

    List<Schedule> findAllByOrderByUpdateDateDesc();

}
