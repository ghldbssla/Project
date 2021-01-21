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
--VALUES('����','����','���','������','����','�󿵰�','�󿵽ð�','��ȭ����');
INSERT INTO TBL_MOVIE_INFO
VALUES('�ҿ�','��Ʈ Ź��','���̹� ����',TO_DATE('2021-01-20','yyyy-mm-dd'),4.5,'����,����,���빮','107��','�ִϸ��̼�');
INSERT INTO TBL_MOVIE_INFO(moviename,director,actor,first_run,theater,theatertime,movieinfo)
VALUES('���̼�����','������','�ٸ��� ��',TO_DATE('2021-01-27','yyyy-mm-dd'),'�Ǵ��Ա�,���빮,��,�̾�','115��','���� �����̾�Ƽ ��¥�糪�� 2 ������!');
INSERT INTO TBL_MOVIE_INFO(moviename,director,actor,first_run,theater,theatertime,movieinfo)
VALUES('Ÿ������ ������ ������','���� �þƸ�','�Ƶ� ����',TO_DATE('2021-01-22','yyyy-mm-dd'),'�б���','121','�簳��~');
INSERT INTO TBL_MOVIE_INFO
VALUES('��ȭ ����','����','���','2021-01-15',5.7,'�󿵰�','�󿵽ð�','��ȭ����');
INSERT INTO TBL_MOVIE_INFO
VALUES('����','����','���','������','����','�󿵰�','�󿵽ð�','��ȭ����');

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

--��������.NEXTVAL : ���� ������ ��
INSERT INTO LIST
VALUES(LIST_SEQ.NEXTVAL,'apple','A01');

INSERT INTO LIST
VALUES(LIST_SEQ.NEXTVAL,'banana','A02');

--��������.CURRVAL : ���� ������ ��
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
VALUES (1,'�Ű��Բ�-�˿� ��','��Ÿ��','12�� ������',TO_DATE('2017-12-20','yyyy-mm-dd'),'8.63','���ȭ','������');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (2,'����ǥ','�ڹ̵�','15�� ������',TO_DATE('21-01-01','yy-mm-dd'),'7.61','�赿��','����ǥ');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (3,'���������','�׼�','15�� ������',TO_DATE('21-01-11','yy-mm-dd'),'6.61','���� �Ľóڸ�','�� ���̽�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (4,'������ ��Ģ: �ΰ����','������','û�ҳ� �����Ұ�',TO_DATE('21-01','yy-mm'),'0','�̼���','�輺��');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (5,'���̺꽺 �ƿ�','�̽��͸�','12�� ������',TO_DATE('19-12-04','yy-mm-dd'),'8.93','���̾� ����','�ٴϿ� ũ���̱�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (6,'�� ��ũ��','������','15�� ������',TO_DATE('21-01-21','yy-mm-dd'),'0','���� �ֵ鷯','���� ���Ľ�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (7,'�ҿ�','�ִϸ��̼�','��ü ������',TO_DATE('21-01-20','yy-mm-dd'),'9.36','��Ʈ ����','���̹� ����');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (8,'���̿� ����','�θǽ�','12�� ������',TO_DATE('21-01-14','yy-mm-dd'),'8.5','���� ��Ƽ��','�˷� ����');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (9,'�ٸ� �ǿ��� ���ϼҼ�','�׼�','15�� ������',TO_DATE('20-08-05','yy-mm-dd'),'8.57','ȫ����','Ȳ����');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (10,'������: �� ��','�θǽ�','15�� ������',TO_DATE('20-10-07','yy-mm-dd'),'8.15','���� �ĺ�','����� ������ Ƽ��');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (11,'����, ȣ���� �׸��� ������','�ִϸ��̼�','�̰���',TO_DATE('21-01','yy-mm'),'0','Ÿ���� ��Ÿ��','�̰���');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (12,'�ƹ��� ������','����','�̰���',TO_DATE('21-01-28','yy-mm-dd'),'0','���� Į����','������ ����');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (13,'���Ϸ���','����','15�� ������',TO_DATE('21-01-28','yy-mm-dd'),'0','�κ� ����Ʈ','���ݶ��� �ڽ���-�е�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (14,'�����: �������','�׼�','12�� ������',TO_DATE('19-04-24','yy-mm-dd'),'9.49','�ȼҴ� ���','�ι�Ʈ �ٿ�� �ִϾ�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (15,'���� �̸�','�θǽ�','12�� ������',TO_DATE('20-10-14','yy-mm-dd'),'7.41','�㵿��','���ҹ�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (16,'������ �͸��� Į��: ���ѿ�����','�ִϸ��̼�','15�� ������',TO_DATE('21-01-27','yy-mm-dd'),'0','������Ű �Ϸ��','�ϳ��� ����Ű');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (17,'���긮Ÿ�� ���� ����','������','15�� ������',TO_DATE('20-10-21','yy-mm-dd'),'5.51','�κ� ����Ŭ','��� ��Ƽ����');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (18,'�Ҹ��� ����','����','15�� ������',TO_DATE('20-10-14','yy-mm-dd'),'7.78','���� Ʈ��ũ','���ϵ�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (19,'���� ��ȥ��','�θǽ�','12�� ������',TO_DATE('18-08-22','yy-mm-dd'),'9.01','�̼���','�ں���');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (20,'���','�׼�','15�� ������',TO_DATE('21-01-28','yy-mm-dd'),'0','�嵿��','�����');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (21,'����','����','û�ҳ� �����Ұ�',TO_DATE('20-10-14','yy-mm-dd'),'7.78','���� Ʈ��ũ','�� �ϵ�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (22,'����� �ð�: ��������','����','û�ҳ� �����Ұ�',TO_DATE('20-06-09','yy-mm-dd'),'6','�Ʒ� �̸��׽�','���̽� ��Ʈ��');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (23,'#����ִ�','���','15�� ������',TO_DATE('20-06-24','yy-mm-dd'),'7.03','������','������');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (24,'������ �� ��: �� ����','���� ��Ȳ','��ü ������',TO_DATE('20-06-10','yy-mm-dd'),'9.39','����','������');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (25,'���츮�Ͻ�','���','15�� ������',TO_DATE('20-06-25','yy-mm-dd'),'6.08','�� ������',' ���� �ݸ���');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (26,'��������','�ڹ̵�','12�� ������',TO_DATE('20-06-18','yy-mm-dd'),'3.71','����â','���α�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (27,'Ŀ��Ʈ','����','15�� ������',TO_DATE('21-01-20','yy-mm-dd'),'7.64','������ ü�̽�','���� �ι�Ʈ��');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (28,'����','����','15�� ������',TO_DATE('21-01-27','yy-mm-dd'),'0','���� ���Ǹ�','�����Ŀ÷� �𷼸�');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (29,'�Ϳ��� ����','�ڹ̵�','15�� ������',TO_DATE('21-01-14','yy-mm-dd'),'7.6','������','�Ź���');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (30,'��Ž�� �ڳ�: ��ȫ�� ���п���','�ִϸ��̼�','12�� ������',TO_DATE('21-01-27','yy-mm-dd'),'0','�߸����� �߽���ġ��','�߸���ġ ı����');
INSERT INTO MOVIE_INFO (M_SEIRAL, M_NAME, M_TYPE, M_RATE, M_DATE, M_GPA, M_DIRECTOR, M_ACTOR)
VALUES (31,'�ʴ� �޹㿡 ������','�θǽ�','12�� ������',TO_DATE('20-06-10','yy-mm-dd'),'8.43','��Űī�� ��','ŰŸ���� Ÿ���');

SELECT * FROM MOVIE_INFO;