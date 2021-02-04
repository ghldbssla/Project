CREATE TABLE MOVIE_USER(
   userid         VARCHAR2(100),      -- ���̵� (PK)
   userpw         VARCHAR2(500),      -- ��й�ȣ
   username      VARCHAR2(100),      -- ����
   useremail      VARCHAR2(500),      -- �̸���
   userphone      VARCHAR2(11),      -- �� ��ȣ
   useraddr      VARCHAR2(300),      -- �ּ�
   userbday      VARCHAR2(100),      -- �������
   usercoupon      NUMBER(5),      -- ����
   usermoney      NUMBER(38),      -- ��
   CONSTRAINT USERS_PK PRIMARY KEY(USERID)
);

CREATE TABLE TBL_MOVIE_TICKET(
   userid         VARCHAR2(100),    -- USERID (FK)
   m_name         VARCHAR2(100),    -- MOVIE_LIST (FK)
   T_NAME         VARCHAR2(100),    -- THEATER (FK)
   ticket_price      VARCHAR2(100),
   S_SCHEDULE_TIME    VARCHAR2(100),   -- SCREEN_THEATER(FK)
   CONSTRAINT MOVIE_USER_FK FOREIGN KEY(USERID)
   REFERENCES MOVIE_USER(USERID) ON DELETE CASCADE,   -- MOVIE_USER�� FK
   CONSTRAINT MOVIE_LIST_FK FOREIGN KEY(M_NAME)
   REFERENCES MOVIE_LIST(M_NAME) ON DELETE CASCADE,   -- MOVIE_LIST�� FK
   CONSTRAINT THEATER_FK FOREIGN KEY(T_NAME)
   REFERENCES THEATER(T_NAME) ON DELETE CASCADE,   -- THEATER�� FK
   CONSTRAINT SCREEN_THEATER_FK FOREIGN KEY(S_SCHEDULE_TIME)
   REFERENCES SCREEN_THEATER(S_SCHEDULE_TIME) ON DELETE CASCADE   -- SCREEN_THEATER(FK)
);
SELECT*FROM TBL_MOVIE_TICKET;

CREATE TABLE FAV_USER(
   userid VARCHAR2(100),
   M_TITLE VARCHAR2(100)
);
SELECT * FROM FAV_USER;

CREATE TABLE THEATER(
   T_SERIAL      VARCHAR2(100),   -- ��ȭ�� ���� ��ȣ   EX)123 OR ABC
   T_NAME         VARCHAR2(100),   -- ��ȭ�� �̸�      EX)CGV '����'
   T_CITY         VARCHAR2(100),   -- ��ȭ�� ��ġ      EX)���, ����, ��õ
   CONSTRAINT THEATER_PK PRIMARY KEY(T_NAME)
);
SELECT*FROM THEATER;
INSERT INTO THEATER(T_SERIAL,T_NAME)
 VALUES('a','B');
DELETE FROM THEATER WHERE T_CITY IS NULL;
CREATE TABLE MOVIE_LIST(
   M_NAME      VARCHAR2(100),   -- ��ȭ ����      EX)�ҿ�
   M_DIRECTOR   VARCHAR2(100),   -- ��ȭ ����      EX)��Ʈ ����
   M_ACTOR      VARCHAR2(100),   -- ��ȭ ���      EX)���̹� ����
   M_GENRE      VARCHAR2(100),   -- ��ȭ �帣      EX)�ִϸ��̼�
   M_RATE      VARCHAR2(100),   -- ��ȭ ����      EX)9.37
   CONSTRAINT MOVIE_PK PRIMARY KEY(M_NAME)
);

CREATE TABLE MOVIE_LIST_SOON(
   M_NAME      VARCHAR2(100),   -- ��ȭ ����      EX)�ҿ�
   M_DIRECTOR   VARCHAR2(100),   -- ��ȭ ����      EX)��Ʈ ����
   M_ACTOR      VARCHAR2(100),   -- ��ȭ ���      EX)���̹� ����
   M_GENRE   VARCHAR2(100),   -- ��ȭ �帣      EX)�ִϸ��̼�
   CONSTRAINT MOVIE_SOON_PK PRIMARY KEY(M_NAME)
);

CREATE TABLE SCREEN_THEATER(        
   S_SCHEDULE_TIME  VARCHAR2(100),      -- �� ���� �ð�   EX)6��~11�� (����) | 11��~23�� (�Ϲ�) | 23��~6�� (�߰�)
   S_SEAT_CNT      VARCHAR2(100),   -- �� �¼� ��      EX)   �� �¼� 230��
   m_name         VARCHAR2(100),    -- MOVIE_LIST (FK)  ��ȭ���� 
   T_NAME         VARCHAR2(100)    -- THEATER (FK)
);
SELECT * FROM SCREEN_THEATER;
INSERT INTO SCREEN_THEATER
VALUES('A','B','C','B');