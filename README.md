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

### 22.08.02 
- 첫 작성
- Posts Entity, Repository, Controller, Dto 작성
- 배운 점 :
    - @Setter를 사용하지 않는 원칙을 지킬 수 있었다.
        - 데이터에 직접 변조를 방지한다.
        - Dto에서 Entity로 매핑해주는 주는 로직 구현.
        - Entity Class에 Update 메서드를 작성.
	
    - Mapper를 사용하는 대신에 Dto에 Posts 객체 생성자(@Builder)를 만듦으로써 기능과 역할을 분리할 수 있었다. 

### 22.08.06
- Done:
    - [x] Update, Delete 핸들러 메서드 완성
    - [x] Service 클래스 
    - [x] 테스트 
    - [x] 템플릿
    - DB 연동
  
- 소감 : 
  - 정 : 나도 동욱님의 책을 끝내봐야겠다. 앞으로의 진행을 위해서는 선행학습이 반드시 필요하다.
  - 강 : 기본이 중요하다. 앞으로의 진행을 위해서는 복습이 필요하다. 그리고 아이디어 뿐만 아니라 그것을 어떤식으로 적용시킬지 생각해야할것 같다(예를 들어 다대다를 어떻게 구현할 것인가.)

- Todo(22.8.7) : 
  - 회원 엔티티 생성, 글과 매핑 (회원가입 및 DB연동은 추후에)
  - 다대다 연관 관계 매핑할 요소 생각해보기
  - RedirectAttributes 사용 이유
