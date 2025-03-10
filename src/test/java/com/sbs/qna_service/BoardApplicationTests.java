package com.sbs.qna_service;

import com.sbs.qna_service.boundedContext.question.Question;
import com.sbs.qna_service.boundedContext.question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BoardApplicationTests {
	
	@Autowired // 필드 주입
	private QuestionRepository questionRepository;

	@Test
	@DisplayName("데이터 저장하기") // 테스트 의도 설명
	void testCase001() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		questionRepository.save(q1); // 첫 번째 질문 저장
		// save() : 내부적으로 INSERT 쿼리 실행

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		questionRepository.save(q2); // 두 번째 질문 저장
	}

	/*
	* findAll() : SELECT * FROM question;
	* */
	@Test
	@DisplayName("findAll")
	void testCase002() {
		List<Question> all = questionRepository.findAll();
		assertEquals(2, all.size());

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	/*
	*  findById() : SELECT * FROM question WHERE id = 1;
	* */
	@Test
	@DisplayName("findById")
	void testCase003() {
		Optional<Question> oq = questionRepository.findById(1);
		if(oq.isPresent()) { // isPresent : 값의 존재를 확인
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}

	/*
	 *  findBySubject() : SELECT * FROM question WHERE subject = 'sbb가 무엇인가요?';
	 * */
	@Test
	@DisplayName("findBySubject")
	void testCase004() {
		Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	/*
	 *  findBySubjectAndContent() : SELECT * FROM question WHERE subject = 'sbb가 무엇인가요?'
	 *  AND content = 'sbb에 대해서 알고 싶습니다.';
	 * */
	@Test
	@DisplayName("findBySubjectAndContent")
	void testCase005() {
		Question q = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q.getId());
	}

	/*
	 *  findBySubjectLike() : SELECT * FROM question WHERE subject like 'sbb%';
	 * */
	@Test
	@DisplayName("findBySubjectLike")
	void testCase006() {
		List<Question> qList = questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}
}
