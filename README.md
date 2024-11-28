# 플러스주차 프로젝트
#### 팀노션 https://teamsparta.notion.site/05-1382dc3ef51481e894dfe74ab72746b9

## 주요기능 소개
- ### 회원가입, 로그인
  - Jwt토큰을 이용하여 로그인기능구현
  - 유저 등록 메인 게시판에는 상품(뮤지컬,콘서트,스포츠)목록 출력
- ### 상품CRUD 기능 구현
  - 상품생성
  - 상품단건, 다건, 조회
- ### 주문CRUD 기능 구현
  - 주문 가능
  - 주문목록 출력
- ### 배송CRUD 기능 구현
  - 배송 기능 
  - 배송 중 - 배송완료에 대한 목록 출력 
- ### 공연정보조회 기능 구현
  - 데이테베이스에 저장된 유저정보 출력 
- ### 쿠폰생성 및 발급기능 구현

## 와이어프레임
![스크린샷 2024-11-28 오후 8.33.46.png](../../../../var/folders/bm/12ry2z693tv9klg74xyct8_r0000gp/T/TemporaryItems/NSIRD_screencaptureui_wxWeCR/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-11-28%20%EC%98%A4%ED%9B%84%208.33.46.png)
## ERD
![스크린샷 2024-11-28 오후 8.34.41.png](../../../../var/folders/bm/12ry2z693tv9klg74xyct8_r0000gp/T/TemporaryItems/NSIRD_screencaptureui_mAUJNC/%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-11-28%20%EC%98%A4%ED%9B%84%208.34.41.png)
## Branch 전략
Type / {도메인 이름} / {구현 기능} - {이슈 번호}
- Ex. `faet/user/login-21`
- Ex. `feat/cache/viewCount-53`
---
## Commit 전략
git commit -m “[#이슈번호] Type(변경된 클래스) : 커밋내용”
- Ex. `[#13] feat(UserService) : 회원가입 구현`
- Ex  `[#35] feat(CommentService) : 댓글 삭제 구현`
- Ex. `[#12] test(TodoServiceTest) : 테스트 코드 작성`