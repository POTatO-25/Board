# DB 삭제, 생성, 선택
DROP DATABASE IF EXISTS board;
CREATE DATABASE board;
use board;

# 외래키 제약조건 비활성화
set foreign_key_checks = 0;

# truncate로 초기화한 데이터는 데이터 추가 시 1번 번호로 시작
truncate question;

# 외래키 제약조건 활성화
set foreign_key_checks = 1;
