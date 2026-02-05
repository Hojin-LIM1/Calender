package com.example.project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener; //jpa Auditing 추가
import java.time.LocalDateTime; //자바 8이후로 나온 날짜를 관리하기 위한 기능
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter // 생성날짜를 함부로 수정하지 못하도록 setter 설정
@Entity
@Table(name = "Schedule")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable =false)
    private String title;
    @Column(unique = true, nullable = false)
    private String contents;
    private String editor;
    private Integer password;
    @CreatedDate
    @Column(updatable = false, nullable=false)
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime updateDate;

    public Schedule(String title, String contents, String editor, Integer password) {
        this.title = title;
        this.contents = contents;
        this.editor = editor;
        this.password = password;
        //this.createDate = createDate; jpa auditing으로 미작성
        //this.updateDate = updateDate;
    }

    public void update(String title, String editor) {
        this.title = title;
        //this.contents = contents; 조건에 없음
        this.editor = editor;
        //this.createDate = createDate;
        //this.updateDate = updateDate;
    }


    //매우 중요 1:다 인데  mappedBy = "schedule"로 스케줄이랑 연결해주고
    @OneToMany(mappedBy = "schedule", cascade=CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

}



