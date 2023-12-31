# 해외축구 단체관람 예약 서비스 (Football Cinema)

---

* 해외축구 경기를 영화관에 상영을 등록하고, 사용자는 관람하고 싶은 경기를 예약 및 결제 후 관람할 수 있는 서비스입니다.

---
# 주요기능

1. 사용자 기능
   * 회원가입 & 로그인
       * 카카오 오픈 API를 사용합니다.
       * 사용자는 카카오톡 아이디를 통해 회원가입, 로그인을 하여 서비스를 이용할 수 있습니다.
       * 매치 결제 및 관람은 회원가입 및 로그인을 진행 한 회원만 가능합니다.
   * 매치 검색
     * 관람하고 싶은 매치를 선택한 후 해당 매치를 상영하는 영화관을 선택합니다.
     * 영화관을 선택한 후 좌석을 선택합니다. 이 때 Redis를 사용하여 중복예약을 방지합니다.
   * 매치 결제
     * 매치를 선택했다면 결제를 진행합니다.
     * I'mport API를 사용하여 매치를 결제합니다.
   * 매치 예약 확인
     * 예약한 매치를 결제까지 성공하였다면 관람 티켓을 발급합니다.
     * 티켓에는 좌석번호, 날짜 및 시간, 영화관 및 상영관 등의 정보가 포함되어있습니다.
     * 발급한 티켓은 카카오 메세지 API를 통해 사용자에게 전송합니다.
   * 매치 예약 취소
     * 예약취소는 다른 사용자의 구매를 위해 경기시작 1시간 전까지만 가능합니다.
     * 예약취소시 예약취소정보를 전송하고 결제한 금액을 환불합니다.
2. 관리자 기능
   * 영화관 정보 등록
     * 영화관, 상영관, 좌석 정보를 등록합니다.
   * 매치 등록
     * 영화관, 상영관을 선정하여 상영할 매치를 등록합니다.
   * 경기정보 저장
     * API-football API를 통해 진행되는 경기정보를 저장합니다.
     * 경기정보는 한주마다 갱신됩니다.

---
# ERD

![erd](https://i.ibb.co/wyPqL0N/football-Cinema.png)

1. 사용자
   * 사용자의 정보를 저장합니다.
2. 관리자
   * 각 영화관 별로 하나의 관리자 계정을 생성할 수 있습니다.
   * 영화관에 매치스케줄을 등록합니다.
3. 영화관
   * 영화관의 이름, 주소등의 정보를 저장합니다.
4. 상영관
   * 이름, 총 좌석 수를 저장합니다.
5. 좌석
   * 좌석의 행, 열 번호를 저장합니다.
6. 매치정보 
   * API를 통해 축구경기 정보를 받아와 저장합니다.
7. 매치
   * 매치정보에 저장된 각각의 매치들 중 상영 할 매치를 컨텐츠화 하여 저장합니다.
8. 예약정보
    * 예매한 좌석, 예매 가격등을 저장합니다.
9. 거래
    * 결제, 환불등의 정보를 저장합니다.

Trouble Shooting
---
[go to the trouble shooting section](TROUBLE_SHOOTING.md)

### Tech Stack

---
<div align=center> 
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
</div>
