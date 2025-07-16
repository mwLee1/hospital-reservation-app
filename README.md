
# 🏥 병원 예약 통합 웹서비스

이 프로젝트는 사용자와 병원 관리자를 위한 실시간 병원 예약, 검색, 리뷰 기능을 제공하는 **실제 서비스 수준의 백엔드 시스템**입니다.

## 🔧 주요 기능

### 👤 회원가입 / 로그인
- 이메일 & 전화번호 기반 회원가입
- 비밀번호 암호화 (BCrypt)
- 소셜 로그인 (Google, Naver, Kakao) 지원
- 일반 사용자 vs 병원 관리자 구분

### 🏥 병원 검색
- 건강보험심사평가원 공공 API 연동
- 지역 + 진료과 기반 검색
- 병원명으로 직접 검색
- 현재 진료중 여부 표시

### 📅 예약 기능
- 병원 상세 페이지에서 진료 예약
- 병원별 진료시간/점심시간/휴무일 설정 가능
- 예약 간격 조정 가능 (기본 20분)
- 자동/수동 승인 설정
- 병원측 알림 (예약 발생 시)

### 📝 리뷰 기능
- 진료 완료 후 리뷰 작성 가능
- 별점 + 의견 작성 or 간단 태그형 리뷰 선택 가능
- 리뷰 작성 생략(Skip) 가능
- 병원 리뷰 통계는 확장 기능으로 제공 예정

### ⚠️ 예외 처리
- 공통 예외 처리(GlobalExceptionHandler)
- 에러 응답 형식 통일
- 커스텀 예외 및 코드 관리

## 💾 기술 스택
- Spring Boot 3.x / Gradle
- Spring Security + JWT + OAuth2
- JPA + MySQL + H2
- Lombok, ModelMapper, Validation
- REST API (Postman 테스트)

## 📁 프로젝트 구조
- `auth`, `user`: 로그인 및 유저 관리
- `hospital`, `admin`: 병원 정보 및 관리자 기능
- `reservation`: 예약 및 승인/취소
- `review`: 리뷰 기능
- `region`, `subject`: 지역/진료과 코드 관리
- `exception`, `security`, `jwt`, `config`: 공통 설정

## ✅ 실행 방법
```bash
./gradlew build
java -jar build/libs/hospital-0.0.1-SNAPSHOT.jar
```

## 📄 ERD
[ERD PDF 보기](./hospital_erd.pdf)

---

> 👨‍👩‍👧‍👦 팀원 모두가 이해하기 쉽게 설계된 구조로, 실제 서비스 운영 수준의 완성도를 목표로 합니다.
