package com.sbs.qna_service.boundedContext.answer;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository  extends JpaRepository<Answer, Integer> {

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE answer AUTO_INCREMENT = 1", nativeQuery = true)
    void clearAutoIncrement();
}
