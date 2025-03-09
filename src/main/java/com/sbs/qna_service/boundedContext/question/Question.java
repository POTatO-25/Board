package com.sbs.qna_service.boundedContext.question;

import com.sbs.qna_service.boundedContext.answer.Answer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@Entity // 스프링 부트가 Question을 Entity로 봄
public class Question {
    @Id // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private Integer id;

    @Column(length = 200) // VARCHAR(200)
    private String subject;

    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    private LocalDateTime createDate; // DATETIME

    // @OneToMany : 자바의 편의를 위해 필드 생성된 것으로, 실제 DB 테이블에 컬럼 생성되지 X
    // DB는 리스트나 배열을 만들 수 없기 때문
    // CascadeType.REMOVE : 질문이 삭제되면 답변도 같이 삭제
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    public Question() {

    }
}
