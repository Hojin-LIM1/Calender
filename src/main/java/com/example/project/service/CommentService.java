package com.example.project.service;

import com.example.project.dto.*;
import com.example.project.entity.Comment;
import com.example.project.entity.Schedule;
import com.example.project.repository.CommentRepository;
import com.example.project.repository.ScheduleRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    //LV5 댓글 생성
    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request) {
        //일정 존재 여부
        Schedule schedule = scheduleRepository.findById(request.getScheduleId())
                .orElseThrow( () -> new IllegalArgumentException("일정이 존재하지 않습니다."));

        //10개 초과시 제한하기 - count를 통해 댓글 개수 세기(스케줄당)
        long count = commentRepository.countByScheduleId(schedule.getId());
        if (count >=10) {
            throw new IllegalStateException("최대 10개까지 작성할 수 있습니다.");
        }

        Comment comment = new Comment(
                request.getContents(),
                request.getUsername(),
                request.getPassword(),
                schedule
        );

        commentRepository.save(comment);

        return new CreateCommentResponse(
                comment.getId(),
                comment.getContents(),
                comment.getUsername(),
                comment.getCreateDate(),
                comment.getUpdateDate()
        );

    }
}
