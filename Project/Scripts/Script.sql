CREATE TABLE TBL_MOVIE_INFO(
	moviename		VARCHAR2(200) PRIMARY KEY,
	director		VARCHAR2(100),
	actor			VARCHAR2(500),
	first_run		DATE,
	rate			NUMBER(2,1),
	theater			VARCHAR2(200),
	theatertime		VARCHAR2(200),
	movieinfo		VARCHAR2(500)
);
DROP TABLE TBL_MOVIE_INFO;

--INSERT INTO TBL_MOVIE_INFO
--VALUES('제목','감독','배우','개봉일','평점','상영관','상영시간','영화정보');
INSERT INTO TBL_MOVIE_INFO
VALUES('소울','피트 탁터','제이미 폭스',TO_DATE('2021-01-20','yyyy-mm-dd'),4.5,'강변,구로,동대문','107분','애니메이션');
INSERT INTO TBL_MOVIE_INFO(moviename,director,actor,first_run,theater,theatertime,movieinfo)
VALUES('토이솔져스','오윤동','줄리엔 강',TO_DATE('2021-01-27','yyyy-mm-dd'),'건대입구,동대문,명동,미아','115분','리얼 버라이어티 가짜사나이 2 극장판!');
INSERT INTO TBL_MOVIE_INFO(moviename,director,actor,first_run,theater,theatertime,movieinfo)
VALUES('타오르는 여인의 조각상','셀린 시아마','아델 에넬',TO_DATE('2021-01-22','yyyy-mm-dd'),'압구정','121','재개봉~');
INSERT INTO TBL_MOVIE_INFO
VALUES('영화 제목','감독','배우','2021-01-15',5.7,'상영관','상영시간','영화정보');
INSERT INTO TBL_MOVIE_INFO
VALUES('제목','감독','배우','개봉일','평점','상영관','상영시간','영화정보');

SELECT * FROM TBL_MOVIE_INFO;
SELECT SYSDATE FROM DUAL;
SELECT * FROM TBL_MOVIE_INFO
WHERE first_run<=SYSDATE
ORDER BY first_run;

CREATE TABLE MOVIE_TICKET(
	user_id			varchar2(200),
	movienum		NUMBER(3),
	moviename		VARCHAR2(200),
	movieprice		NUMBER(5),
	ticketamount	NUMBER(3)
);
SELECT * FROM MOVIE_TICKET;
SELECT * FROM MOVIE_TICKET WHERE user_id='A';
INSERT INTO MOVIE_TICKET
VALUES('A',1,'123',6000,100);
CREATE TABLE MOVIE_USER(
	userid			VARCHAR2(100),
	userpw			VARCHAR2(500),
	username		VARCHAR2(100),
	useremail		VARCHAR2(500),
	userphone		VARCHAR2(11),
	useraddr		VARCHAR2(300),
	userbday		VARCHAR2(100),
	usercoupon		NUMBER(5),
	usermoney		NUMBER(38),
	CONSTRAINT USERS_PK PRIMARY KEY(USERID)
);
SELECT * FROM MOVIE_USER;





CREATE SEQUENCE	MOVIENUM_SEQ
START WITH 1
MINVALUE 1
MAXVALUE 100000;

--시퀀스명.NEXTVAL : 다음 시퀀스 값
INSERT INTO LIST
VALUES(LIST_SEQ.NEXTVAL,'apple','A01');

INSERT INTO LIST
VALUES(LIST_SEQ.NEXTVAL,'banana','A02');

--시퀀스명.CURRVAL : 현재 시퀀스 값
SELECT LIST_SEQ.CURRVAL FROM DUAL;
-----------------------------------------
CREATE TABLE MOVIE_INFO(
	M_SEIRAL	VARCHAR2(100) PRIMARY KEY,
	M_NAME		VARCHAR2(100),
	M_TYPE		VARCHAR2(100),
	M_RATE		VARCHAR2(100),
	M_DATE		DATE,
	M_GPA		VARCHAR2(100),
	M_DIRECTOR	VARCHAR2(100),
	M_ACTOR		VARCHAR2(100)
);
DROP TABLE MOVIE_INFO;
SELECT * FROM MOVIE_INFO WHERE M_DATE<=SYSDATE ORDER BY TO_number(M_SEIRAL);
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (1,'신과함께-죄와 벌','판타지','12세 관람가',TO_DATE('2017-12-20','yyyy-mm-dd'),'8.63','김용화','하정우');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (2,'차인표','코미디','15세 관람가',TO_DATE('21-01-01','yy-mm-dd'),'7.61','김동규','차인표');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (3,'사라져버린','액션','15세 관람가',TO_DATE('21-01-11','yy-mm-dd'),'6.61','피터 파시넬리','앤 헤이시');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (4,'게임의 법칙: 인간사냥','스릴러','청소년 관람불가',TO_DATE('21-01','yy-mm'),'0','이수성','김성수');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (5,'나이브스 아웃','미스터리','12세 관람가',TO_DATE('19-12-04','yy-mm-dd'),'8.93','라이언 존슨','다니엘 크레이그');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (6,'더 시크릿','스릴러','15세 관람가',TO_DATE('21-01-21','yy-mm-dd'),'0','유발 애들러','누미 라파스');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (7,'소울','애니메이션','전체 관람가',TO_DATE('21-01-20','yy-mm-dd'),'9.36','피트 닥터','제이미 폭스');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (8,'아이엠 히어','로맨스','12세 관람가',TO_DATE('21-01-14','yy-mm-dd'),'8.5','에릭 라티고','알랭 샤바');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (9,'다만 악에서 구하소서','액션','15세 관람가',TO_DATE('20-08-05','yy-mm-dd'),'8.57','홍원찬','황정민');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (10,'애프터: 그 후','로맨스','15세 관람가',TO_DATE('20-10-07','yy-mm-dd'),'8.15','로저 컴블','히어로 파인즈 티핀');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (11,'조제, 호랑이 그리고 물고기들','애니메이션','미개봉',TO_DATE('21-01','yy-mm'),'0','타무라 코타로','미개봉');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (12,'아무도 없었다','공포','미개봉',TO_DATE('21-01-28','yy-mm-dd'),'0','엘르 칼라한','아이작 제이');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (13,'사일런싱','공포','15세 관람가',TO_DATE('21-01-28','yy-mm-dd'),'0','로빈 프론트','니콜라이 코스터-왈도');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (14,'어벤져스: 엔드게임','액션','12세 관람가',TO_DATE('19-04-24','yy-mm-dd'),'9.49','안소니 루소','로버트 다우니 주니어');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (15,'나의 이름','로맨스','12세 관람가',TO_DATE('20-10-14','yy-mm-dd'),'7.41','허동우','전소민');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (16,'극장판 귀멸의 칼날: 무한열차편','애니메이션','15세 관람가',TO_DATE('21-01-27','yy-mm-dd'),'0','소토자키 하루오','하나에 나츠키');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (17,'에브리타임 아이 다이','스릴러','15세 관람가',TO_DATE('20-10-21','yy-mm-dd'),'5.51','로비 마이클','드류 폰티에로');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (18,'소리도 없이','범죄','15세 관람가',TO_DATE('20-10-14','yy-mm-dd'),'7.78','조쉬 트랭크','톰하디');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (19,'너의 결혼식','로맨스','12세 관람가',TO_DATE('18-08-22','yy-mm-dd'),'9.01','이석근','박보영');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (20,'운봉','액션','15세 관람가',TO_DATE('21-01-28','yy-mm-dd'),'0','장동현','신재범');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (21,'폰조','범죄','청소년 관람불가',TO_DATE('20-10-14','yy-mm-dd'),'7.78','조쉬 트랭크','톰 하디');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (22,'사냥의 시간: 데스게임','공포','청소년 관람불가',TO_DATE('20-06-09','yy-mm-dd'),'6','아론 미르테스','레이시 하트젤');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (23,'#살아있다','드라마','15세 관람가',TO_DATE('20-06-24','yy-mm-dd'),'7.03','조일형','유아인');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (24,'아이즈 온 미: 더 무비','공연 실황','전체 관람가',TO_DATE('20-06-10','yy-mm-dd'),'9.39','없음','권은비');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (25,'인헤리턴스','드라마','15세 관람가',TO_DATE('20-06-25','yy-mm-dd'),'6.08','본 스테인',' 릴리 콜린스');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (26,'열혈형사','코미디','12세 관람가',TO_DATE('20-06-18','yy-mm-dd'),'3.71','윤여창','김인권');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (27,'커넥트','공포','15세 관람가',TO_DATE('21-01-20','yy-mm-dd'),'7.64','제이콥 체이스','아지 로버트슨');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (28,'골든맨','범죄','15세 관람가',TO_DATE('21-01-27','yy-mm-dd'),'0','빈센조 알피리','지암파올로 모렐리');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (29,'귀여운 남자','코미디','15세 관람가',TO_DATE('21-01-14','yy-mm-dd'),'7.6','김정욱','신민재');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (30,'명탐정 코난: 진홍의 수학여행','애니메이션','12세 관람가',TO_DATE('21-01-27','yy-mm-dd'),'0','야마모토 야스이치로','야마구치 캇페이');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (31,'너는 달밤에 빛나고','로맨스','12세 관람가',TO_DATE('20-06-10','yy-mm-dd'),'8.43','츠키카와 쇼','키타무라 타쿠미');

SELECT * FROM MOVIE_INFO;