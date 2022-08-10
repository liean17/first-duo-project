# first-duo-project
with 강도경

## 목표
### 스프링 웹개발 전반 이해하기 
	- 회원 게시판 CRUD
    - 게시글, 댓글 비밀번호 작성
	- 회원가입 기반
	- 파일 업로드(이미지), 좋아요 등 추가적인 기능을 추가해보기

### 가능하면 포트폴리오 활용
	- 배운 것들을 정리 및 학습
	- 협업 툴을 사용해보자

---

### 22.08.02 
- 첫 작성
- Posts Entity, Repository, Controller, Dto 작성
- 배운 점 :
  - @Setter를 사용하지 않는 원칙을 지킬 수 있었다.
      - 데이터에 직접 변조를 방지한다.
      - Dto에서 Entity로 매핑해주는 주는 로직 구현.
      - Entity Class에 Update 메서드를 작성.
  - Mapper를 사용하는 대신에 Dto에 Posts 객체 생성자(@Builder)를 만듦으로써 기능과 역할을 분리할 수 있었다. 

---

### 22.08.06
- Done:
    - [x] Update, Delete 핸들러 메서드 완성
    - [x] Service 클래스 
    - [x] 테스트 
    - [x] 템플릿
    - DB 연동

---

### 22.08.07
- Done:
  - [x] Member 엔티티 생성, Posts와 매핑
  - [x] DTO Validation 추가


- Todo(22.08.10) :
  - 시큐리티 학습해온 뒤, 시큐리티 기능 구현
    - 글 작성 시 멤버 정보가 들어가는지 확인
  - 예외 처리
  - 다대다 연관 관계 매핑 요소 생각해보기
  - 추후에 추가할 기능 우선순위 매겨서 목록화
  
---

### 22.08.10
- Done
  - 타임리프 로그인 if문 (https://cornarong.tistory.com/73)
  - [x] 시큐리티 기능 구현
  - [x] 예외 처리
  - [x] 다대다 매핑 -> Tag
  - [x] 우선순위 목록화

- Todo(22.08.13) : 
  - 현재 MemberSaveRequestDto의 Password인코딩 과정이 복잡하다. 개선필요
  - 에러메시지 팝업으로 반환하고 리다이렉트 구현
  - 예외처리는 발견할때마다 추가
  - Controller 리팩토링
  - 페이지네이션

- 소감
  - 정 : 뼈대는 완성되었고 살만 붙이면 된다. 앞으로도 열심히 해보자. (웃음점수 0점)
  - 강 : 뼈대는 완성되었는데, 골다공증이 많다. 중간중간에 의문들이 조금씩 있었는데 예를들어서 매핑 방법이라거나, @Bean 과 @Autowire 차이 같은것들. 그래서 기능 구현에 중점을 두는 것은 맞으나 중간중간 드는 의문들을 확실히 기록해서 해소하는 것이 필요하다.
  
---
### 숙제
- 정 : @Bean, @Autowire의 차이(private final)
- 강 : RedirectAttributes 사용 이유
- 각자 함께 : 컨트롤러와 서비스 모범사례 분석(영한,동욱 등)

## 기능 구현 우선순위
| 기능     | 설명            | 우선순위 |
|--------|---------------|------|
| Tag    | 다대다 매핑        | 1순위  |
| 검색기능   | Repository이용  | 2순위  |
| 회원프로필  | 템플릿생성, 사진업로드  | 1순위  |
| 댓글     | 일대다           | 2순위  |
| 파일업로드  | 글에 파일 업로드 가능  | 1순위  |
| 좋아요    | 글에 좋아요(추천) 가능 | 2순위  |
| 카테고리   | 글 종류 분류       | 1순위  |
| 페이지네이션 | 페이지 분석        | 1순위  |