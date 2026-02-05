# Calender
Calender
<API 명세서>
<img width="1443" height="396" alt="API 명세서" src="https://github.com/user-attachments/assets/079b47b9-aca2-4135-85a1-d4b52926b07e" />


<ERD>
<img width="494" height="607" alt="ERD" src="https://github.com/user-attachments/assets/82086326-8b93-4dc2-96e0-5e60d8b837fe" />


<Read Me>
<흐름>
1. Request: 클라이언트가 GET /schedule/{id} 요청을 보냄.
2. Service: ScheduleRepository를 통해 데이터를 찾고, 연결된 Comment 리스트를 스트림으로 변환.
3. DTO Mapping: 보안을 위해 엔티티에서 password를 제외하고 ScheduleAndCommentResponse로 변환.
4. Response: JSON 데이터를 클라이언트에게 응답.

<핵심구현>
1. 비밀번호 보호 (Lv 1-6): 모든 API 응답에서 password 필드를 제외하기 위해 별도의 DTO를 사용하였습니다.
2. 조건부 조회 (Lv 2): @RequestParam(required = false)를 활용해 하나의 엔드포인트에서 전체 조회와 이름 검색을 동시에 처리합니다.
3. JPA Auditing: BaseEntity를 활용하여 생성/수정 시간을 공통 필드로 관리합니다.
4. 검증 로직 (Lv 5): 댓글 작성 시 일정의 댓글 개수가 10개를 초과할 경우 IllegalStateException을 발생시킵니다.
5. 연관 관계 조회 (Lv 6): 지연 로딩(FetchType.LAZY) 상황에서도 @Transactional을 통해 댓글 목록을 안전하게 가져오도록 설계했습니다.
