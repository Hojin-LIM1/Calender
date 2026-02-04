package com.example.project.repository;

import com.example.project.entity.Comment;
import com.example.project.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    long countByScheduleId(Long scheduleId);
}
