package com.example.project.controller;

import com.example.project.dto.CreateCommentRequest;
import com.example.project.dto.CreateCommentResponse;
import com.example.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

        private final CommentService commentService;

        @PostMapping("/schedule/scheduleId/comments")
        public ResponseEntity<CreateCommentResponse> CreateComment (
                @RequestBody CreateCommentRequest request
                ) {
            //코멘트 서비스 불러오기
            CreateCommentResponse response = commentService.save(request);
            // 성공 반환
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }



}
