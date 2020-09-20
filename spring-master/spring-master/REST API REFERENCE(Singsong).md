[TOC]



# REST API REFERENCE



## 1. 사용자 API

| Members                   | Descriptions         |
| :------------------------ | -------------------- |
| POST /user/join           | 회원가입             |
| POST /user/login          | 회원 로그인          |
| POST /user/getinfo        | 회원정보             |
| POST /user/charge         | 회원 포인트 충전     |
| POST /coocon/checkAccount | 회원 계좌 확인       |
| POST /user/payment        | 회원 노래방 결제     |
| POST /transinfo/getList   | 회원 거래 내역       |
| POST /user/updateAccount  | 회원 계좌 등록(수정) |

### 	1.1 회원 가입

`Request parameters`

| Field    | Type   | Description   |
| -------- | ------ | ------------- |
| u_id     | String | 회원 아이디   |
| u_pw     | String | 회원 비밀번호 |


`Response (success)`

| Field  | Type   | Description            |
| ------ | ------ | ---------------------- |
| result | String | 회원가입 성공(success) |

`Response (fail)`

| Field  | Type   | Description         |
| ------ | ------ | ------------------- |
| result | String | 회원가입 실패(fail) |



### 	1.2 회원 로그인

`Request parameters`

| Field    | Type   | Description   |
| -------- | ------ | ------------- |
| u_id     | String | 회원 아이디   |
| u_pw     | String | 회원 비밀번호 |

`Response (success)`

| Field  | Type   | Description          |
| ------ | ------ | -------------------- |
| result | String | 로그인 성공(success) |

`Response (fail)`

| Field  | Type   | Description       |
| ------ | ------ | ----------------- |
| result | String | 로그인 실패(fail) |



### 	1.3 회원 정보 보기



`Request parameters`

| Field | Type   | Description   |
| ----- | ------ | ------------- |
| u_id  | String | 회원 아이디   |

`Response (success)`

| Field         | Type    | Description        |
| ------------- | ------- | ------------------ |
| uid           | Integer | DB에서 관리하는 id |
| u_id          | String  | 회원 아이디        |
| u_pw          | String  | 회원 비밀번호      |
| u_name        | String  | 회원 이름          |
| u_bank        | String  | 회원 은행          |
| u_account     | String  | 회원 계좌번호      |
| u_profile     | String  | 회원 프로필        |
| u_recorde     | String  | 회원 음성녹음      |
| u_chargeMoney | Integer | 회원 잔액          |

`Response (fail)`

| Field  | Type   | Description        |
| ------ | ------ | ------------------ |
| result | String | 회원가입 실패(fai) |



### 	1.4 회원 포인트 충전



`Request parameters`

| Field    | Type   | Description |
| -------- | ------ | ----------- |
| CUST_ID  | String | 회원 아이디 |
| TRAN_AMT | String | 충전 금액   |

`Response (success)`

| Field  | Type   | Description        |
| ------ | ------ | ------------------ |
| result | String | 충전 성공(success) |

`Response (fail)`

| Field  | Type   | Description     |
| ------ | ------ | --------------- |
| result | String | 충전 실패(fail) |



### 	1.5 회원 포인트 확인



`Request parameters`

| Field | Type   | Description   |
| ----- | ------ | ------------- |
| u_id  | String | 회원 아이디   |

`Response (success)`

| Field   | Type    | Description      |
| ------- | ------- | ---------------- |
| BAL_AMT | Integer | 회원 잔여 포인트 |
| REPY_CD | String  | 확인 성공시 0000 |

`Response (fail)`

| Field  | Type   | Description        |
| ------ | ------ | ------------------ |
| result | String | 회원가입 실패(fai) |



### 	1.6 회원 노래방 결제



`Request parameters`

| Field    | Type   | Description |
| -------- | ------ | ----------- |
| CUST_ID  | String | 회원 아이디 |
| OWNER_ID | String | 사장 아이디 |
| TRAN_AMT | String | 결제 금액   |
| ROOM_NUM | String | 방 번호     |

`Response (success)`

| Field  | Type   | Description    |
| ------ | ------ | -------------- |
| result | String | 결제 성공(success) |

`Response (fail)`

| Field  | Type   | Description    |
| ------ | ------ | -------------- |
| result | String | 결제 실패(fail) |



### 	1.7 회원 거래 내역

`Response (success)`

| Field   | Type    | Description                    |
| ------- | ------- | ------------------------------ |
| tid     | Integer | DB에서 관리하는 id             |
| t_u_id  | String  | 회원 아이디                    |
| t_money | Integer | 거래 가격                      |
| t_date  | String  | 거래 일                        |
| t_type  | String  | 거래 유형(0 : 충전 , 1 : 결제) |

`Response (fail)`

| Field  | Type   | Description    |
| ------ | ------ | -------------- |
| result | String | 조회 실패(fail) |

### 	1.8 회원 계좌 등록(수정)

`Request parameters`

| Field     | Type   | Description |
| --------- | ------ | ----------- |
| u_id      | String | 회원 아이디 |
| u_name    | String | 회원 이름   |
| u_bank    | String | 은행        |
| u_account | String | 계좌        |


`Response (success)`

| Field  | Type   | Description    |
| ------ | ------ | -------------- |
| result | String | 등록 성공(success) |

`Response (fail)`

| Field  | Type   | Description    |
| ------ | ------ | -------------- |
| result | String | 등록 실패(fail) |




/user/updateAccount


## 2. 사장 API

| Members              | Descriptions   |
| :------------------- | -------------- |
| POST /owner/allowner | 사장 전체 정보 |
| POST /profit/getList | 사장 거래 내역 |



### 2.1 사정 전체 정보

`Response (success)`

| Field             | Type    | Description        |
| ----------------- | ------- | ------------------ |
| oid               | Integer | DB에서 관리하는 id |
| o_id              | String  | 사장 아이디        |
| o_pw              | String  | 사장 비밀번호      |
| o_name            | String  | 사장 이름          |
| o_bank            | String  | 은행               |
| o_account         | String  | 계좌               |
| o_address         | String  | 노래방 주소        |
| o_lat             | String  | 노래방 위도        |
| o_lon             | String  | 노래방 경도        |
| o_songByMoney     | Integer | 천원당 곡수        |
| o_singingroomname | String  | 노래방 이름        |

`Response (fail)`

| Field  | Type   | Description    |
| ------ | ------ | -------------- |
| result | String | 조회 실패(fai) |



### 2.2 사정 거래 내역

`Response (success)`

| Field   | Type    | Description                    |
| ------- | ------- | ------------------------------ |
| p_id    | Integer | DB에서 관리하는 id             |
| p_o_id  | Integer | 사장 아이디                    |
| p_price | Integer | 거래 가격                      |
| p_date  | String  | 거래 일                        |
| p_type  | String  | 거래 유형(0 : 입금 , 1 : 환전) |


`Response (fail)`

| Field  | Type   | Description    |
| ------ | ------ | -------------- |
| result | String | 조회 실패(fai) |



## 3.녹화파일 api




| Members                   | Descriptions     |
| :------------------------ | ---------------- |
| POST /record/getList           | 녹화파일 리스트 |


### 2.2 녹화파일 리스트

`Response (success)`

| Field   | Type    | Description           |
| ------- | ------- | --------------------- |
| rid     | Integer | DB에서 관리하는 id    |
| r_u_id  | String  | 사용자 아이디         |
| r_url   | String  | 녹화파일 경로         |
| r_likes | Integer | 녹화파일 좋아요       |
| r_users | String  | 녹화파일 좋아요한사람 |
| r_date  | String  | 녹화파일  업로드 일   |

`Response (fail)`	

| Field  | Type   | Description |
| ------ | ------ | ----------- |
| result | String | X           |























