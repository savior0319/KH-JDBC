CREATE TABLE MEMBER (
    MEMBER_ID VARCHAR2(15) PRIMARY KEY,
    MEMBER_PWD VARCHAR2(15) NOT NULL,
    MEMBER_NAME VARCHAR2(20) NOT NULL,
    GENDER CHAR(1) CHECK(GENDER IN('M','F')),
    AGE NUMBER NOT NULL,
    EMAIL VARCHAR2(30),
    PHONE CHAR(11),
    ADDRESS VARCHAR2(500),
    HOBBY VARCHAR2(50),
    ENROLL_DATE DATE DEFAULT sysdate    
);

COMMENT ON COLUMN MEMBER.MEMBER_ID IS 'ȸ�� ���̵�';

COMMENT ON COLUMN MEMBER.MEMBER_PWD IS 'ȸ�� ��й�ȣ';

COMMENT ON COLUMN MEMBER.MEMBER_NAME IS 'ȸ�� �̸�';

COMMENT ON COLUMN MEMBER.GENDER IS '����';

COMMENT ON COLUMN MEMBER.AGE IS '����';

COMMENT ON COLUMN MEMBER.EMAIL IS '�̸���';

COMMENT ON COLUMN MEMBER.PHONE IS '��ȭ��ȣ';

COMMENT ON COLUMN MEMBER.ADDRESS IS '�ּ�';

COMMENT ON COLUMN MEMBER.HOBBY IS '���';

COMMENT ON COLUMN MEMBER.ENROLL_DATE IS '���Գ�¥';

COMMENT ON TABLE MEMBER IS 'ȸ�� ���̺�';


Insert into MEMBER 
(MEMBER_ID,MEMBER_PWD,MEMBER_NAME,GENDER,AGE,EMAIL,PHONE,ADDRESS,HOBBY,ENROLL_DATE) values 
('admin','admin','������','M',30,'admin@iei.or.kr','01012345678','����� ������ ���ﵿ ������� 
7','��Ÿ,����,�',to_date('16/03/15','RR/MM/DD'));
Insert into MEMBER 
(MEMBER_ID,MEMBER_PWD,MEMBER_NAME,GENDER,AGE,EMAIL,PHONE,ADDRESS,HOBBY,ENROLL_DATE) values 
('user11','pass11','ȫ�浿','M',23,'hong77@kh.org','01077778888','��⵵ ������ �ȴޱ� �ȴ޵� 
77','�,���,��Ÿ',to_date('17/09/21','RR/MM/DD'));
Insert into MEMBER 
(MEMBER_ID,MEMBER_PWD,MEMBER_NAME,GENDER,AGE,EMAIL,PHONE,ADDRESS,HOBBY,ENROLL_DATE) values 
('user22','pass22','�Ż��Ӵ�','F',48,'shin50@kh.org','01050005555','������ ������ ������ 5','��
��,�׸�,�丮',to_date('17/05/05','RR/MM/DD'));
Insert into MEMBER 
(MEMBER_ID,MEMBER_PWD,MEMBER_NAME,GENDER,AGE,EMAIL,PHONE,ADDRESS,HOBBY,ENROLL_DATE) values 
('user77','user77','�̼���','M',50,'dltjswnh@naver.com','01021226374','��⵵ 
�����','����',to_date('17/12/08','RR/MM/DD'));
Insert into MEMBER 
(MEMBER_ID,MEMBER_PWD,MEMBER_NAME,GENDER,AGE,EMAIL,PHONE,ADDRESS,HOBBY,ENROLL_DATE) values 
('lsj','lsj','�̼���','F',24,'dltjswnh@naver.com','01021226374','��⵵ �Ȼ��','�,����,��
',to_date('17/08/25','RR/MM/DD'));
Insert into MEMBER 
(MEMBER_ID,MEMBER_PWD,MEMBER_NAME,GENDER,AGE,EMAIL,PHONE,ADDRESS,HOBBY,ENROLL_DATE) values 
('seonn','seonn','�����','F',28,'study11@naver.com','01021226374','��⵵ ������','����,å�б�
',to_date('17/11/08','RR/MM/DD'));

commit;

SELECT * FROM MEMBER;