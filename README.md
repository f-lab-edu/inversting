# investing

## 개요

- 본 프로젝트는 모의 주식투자 서비스를 제공합니다.
- 코스콤 API를 사용하여, 실제 주식 시세만 가져오며, 주식을 사고 파는 경험을 할 수 있게 해줍니다.

## 기술 스택

- JDK 17
- Spring Boot, Spring Batch, JPA
- Redis
- Kafka
- Mysql
- gradle
- intelliJ

## 예상 아키텍처

<img width="548" alt="image" src="https://github.com/NohYeongHun/zero-gifticon/assets/24979159/6f217f9b-376f-45af-b5b5-ba491154a37e">

## ERD 

https://www.erdcloud.com/d/rQgnTmEjaaxAW6hH2

## Use Case

### 전체적인 룰

주식의 등락폭을 조절하기 위해 몇가지 룰을 추가한다.

- 주식 종목에서 종가 기준 30% 내리거나 오르면, 일일 하루 정지. (상한가, 하한가)
- 20% 오르거나 내려가면, 2분간 하나의 가격으로 살수 있음. (VI)
- 코스피/ 코스닥이 하루에 10% 내려가거나 올라가면 20분간 거래 중지
- 30분안에 20%가 오르면, 에러발생 3일간 30분 단위 거래

- 구매 및 판매는 하루에 이루어 지지 않으면, 자동 취소가 된다.

---

### 메인 화면

#### 일반 회원일떄

1. 코스피, 코스닥 현재 지수를 보여준다.    
   1-1. 현재 코스피, 코스닥 지수를 나란히 보여준다.  
   1-2. 등락폭이 오르고 있으면, 빨간색 내리고 있으면 파란색으로 보여준다.  
2. 코스피, 코스닥 밑에 현재 가지고 있는 현금 자산을 보여준다.  
   2-1. 자산 옆에 ```>``` 버튼이 있어, 버튼 클릭시, 잔액충전 화면으로 이동한다.  
3. 현금 자산 밑에는 현재 가지고 있는 주식을 보여준다. (최대 5개)   
   3-1. 현재 가기고 있는 주식에는 현재 주식 가격이 변동률에 반영하여, 가격도 보여준다. (오르고 있으면, 빨간색 내려간 것이면 파란색 글씨)  
   3-2. 5개 초과시, 더보기 버튼이 생긴다.   
   3-3. 더보기 버튼 클릭시, 주식목록을 전체를 보여준다.  
   3-4. 주식종목을 클릭하게 되면, 상세화면으로 이동한다.  
4. 1시간마다 사용 유저들이 상세보기화면에 많이 들어간 종목 5개를 보여준다.  
   4-1. 주식명과 가격을 보여준다. (오르고 있으면, 빨간색 내려간 것이면 파란색 글씨)  
   4-2. 주식종목을 클릭하게 되면, 상세화면으로 이동한다.
5. 관심목록에 주식과 현재 가격을 보여준다.  
    3-1. 관심목록없을시, ```관심목록을 추가해주세요``` 문구 응답  
    3-2. 관심목록 주식 종목을 클릭시, 상세화면으로 이동한다.
6. 제일 상단 오른쪽 구석에 내정보 화면으로 이동할 수 있다.
7. 오른쪽 상단에 주식을 조회할 수 있는 검색창이 존재한다.  
   7-1. 주식 조회시, 자동완성으로 주식을 최대 3종료 보여준다.

#### 비회원일때

1. 코스피, 코스닥 현재 지수를 보여준다.  
   1-1. 현재 코스피, 코스닥 지수를 나란히 보여준다.   
   1-2. 등락폭이 오르고 있으면, 빨간색 내리고 있으면 파란색으로 보여준다.
2. 메인 화면 이외의 화면에서는 ```로그인 후 진행해주세요``` 메시지창으로 알려준다.  
   1-1. 문구에는 확인버튼이 있어 클릭하면, 팝업이 종료된다.  

---

### 회원가입 - 신규

1. 아이디, 비밀번호, 생년월일, 이름을 입력받도록 한다.  
  1.1 아이디는 이메일형식 (본인인증을 위해)  
  1.2 비밀번호는 8 이상 특수문자 1개 포함  
  1.3 중복되는 아이디가 있을경우에는 ```중복된 아이디가 있습니다.``` 메시지창이 뜬다.    
  1.4 입력값들이 유효성 검사가 문제가 없으면, 축하한다는 메시지와 함께 이메일 인증을 요구하는 메시지가 뜬다.   
  1.5 메시지 창이 닫히면, 로그인 페이지로 이동 한다.  
2. 회원가입 완료시 회원가입에 쓰인 이메일로 인증 메일을 보내고, 인증 후, 로그인이 가능하다.   
  2.1. 이메일 인증을 안하면 로그인이 실패한다.  

---

### 로그인

1. 메인화면에서 로그인 버튼 클릭시, 로그인 창 이동   
   1.1. 휴먼상태(6개월 이상 접속 안한 상태) 일경우, 이메일 인증 후, 사용가능  
2. 로그인 버튼 밑에, 아이디/비밀번호 찾기를 할 수 있다.  
   2.1 아이디찾기 버튼 클릭시, 이메일 입력시, 현재 회원인지 알려준다.  
   2.2 비밀번호찾기 버튼시, 이메일로 임시 비밀번호가 전송된다.

### 내정보

1. 이메일은 읽기 전용으로 회색바탕의 수정 불가능한 상태로 보여준다.
2. 이메일 밑에는 비밀번호와 비밀번호 확인 입력칸이 있고, 그 밑에 비밀번호 변경 버튼이 있다.
   2-1. 비밀번호 변경 버튼 클릭시, 비밀번호가 변경이 된다.
   2-2. 비밀번호와 비밀번호 확인 입력칸이 다르면, 확인해달라는 메시지창이 뜬다.  

---

### 주식 거래

#### 구매

1. 메인 화면에서 주식 검색이나 주식종목 클릭으로 주식 상세화면에 이동이된다.    
2. 주식의 종목 이름, 주식 고유 번호, 등락률을 볼 수 있다.  
3. 분단위, 주단위, 년단위 주식 그래프를 보여준다.
4. 주식 그래프 밑에 구매버튼이 존재한다.
   4-1. 구매 버튼을 클릭하여, 구매할 주와 ```시장가``` 또는 ```원하는 금액```을 입력한다.   
   4-2. VI 상태 일때는, 금액은 하나로만 살수 있음.  
   4-3. 금액 부족시, 잔액 충전후 이동해달라고 응답  
   4-4. 종가 기준 30% 오르거나 내려간 상품이면 결제불가  
5. 현재 가지고 있는 주식목록에 ```구매 대기``` 상태로 올라간다.  
   4-1. 단기과열완화상태일 경우면, 30분마다, 구매대기 상태로 변경 그 전에는 ```과열 상태``` 라고 표시  
6. 구매한 돈은 등급별로 수수료를 매긴다. (VIP: 0%, 일반 고객: 0.015%)  
7. 구매 대기 상태에서 하루동안 구매가 이루어 지지 않을 경우, 취소가 된다.  

#### 판매

1) 구매 대기 상태일 경우   
   1. 현재 가지고 있는 주식목록에 ```구매 대기``` 상태 제품을 클릭한다.  
   2. 구매 취소를 누리면 예약 테이블에서 DELETE 상태 코드가 변경이 된다.  
       2-1. 취소하는 사이에 구매가 되면, 취소는 되지 않는다.
    

2) 현재 가지고 있는 주식일 경우  
   1. 현재 가지고 있는 주식이 예약 테이블로 ```판매 대기```로 들어가게 된다.  
   2. 판매는 구매한 주식이 있어야 판매가 되며, 하루가 지나게 되면, 판매 대기 상태는 다시 소유 상태로 돌아간다.

#### 관심종목 추가

1. 메인화면에서, 주식검색이나 추천 주식종목에서 상세화면에 들어가게 된다.  
2. 주식 상세보기 화면에서, 구매 버튼 옆에 관심종목 추가 버튼 (별표) 클릭시, 관심 종목으로 추가가된다.  
   2-1. 상세보기 화면에서 관심종목 추가 버튼이 제거 버튼으로 변경된다.  (활성화시, 별이 노란색으로 변함)  
   2-2. 제거 버튼 클릭시, 종목에서 제거 된다. (비활성화시 별은 흰색)  

#### 잔액충전

1. 메인화면에서 자산 옆에 ```>``` 버튼 클릭시, 잔액충전 화면으로 이동된다.  
   1-1. 거래중에 잔액이 부족해도 충전 화면으로 이동    
2. 거래창에서 성공으로 응답받으면, 잔액에 충전이 된다.

---

### 관리자 (백오피스)

#### 로그인

1. 로그인 권한을 가진 아이디로 로그인한다.
2. Google OTP 인증후, 로그인이된다.
   1. 최초 admin은 OTP 등록 후 로그인을 재시도한다.
3. 일일 거래량, 거래 금액을 확인할 수 있다.    
   3-1. 일일 거래량은 숫자로 나타내며, 일단위로 보여준다.  
   3-2. 일일거래량 밑에 거래금액이 숫자로 표시된다. (구매 합계)  
4. 화면 제일 위 중앙에 주식 검색이 있다.
5. 주식 검색 기능 옆에 관리자 추가 버튼이 있다.
   5-1. 관리자 추가버튼 클릭시, 관리자를 추가할 수 있는 화면으로 이동된다.

#### 주식 상태 변경

1. 메인 화면에서 주식 검색을 한다.
2. 상세 화면으로 이동후, 거래 상태를 변경을 할 수 있다.
   2-1. 거래정지: 거래가 정지 상태로 변경   
   2-2. 정상:     정상적인 상태  
   2-3. VI 발동:  2분간 하나의 가격으로만 살 수 있음  
   2-4. 단기과열완화상태 : 30분단위로 거래 가능  
3. 관리자는 구매와 판매 버튼이 없다.

#### 관리자 추가

1. 관리자 추가 버튼 클릭 시 관리자 추가 화면으로 이동한다.
2. 일반회원 목록을 보여준다.
    2-1. 일반 회원 아이디 옆에 권한부여 버튼이 있다.  
    2-2. 버튼 클릭시, 일반회원에게 관리자 권한을 줄 수 있다.  
3. 최초 관리자 접속시, OTP 등록 후 로그인

