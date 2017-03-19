CREATE TABLE USERS (
  ID       VARCHAR2(19) PRIMARY KEY,
  NAME     VARCHAR2(20)  NOT NULL,
  PASSWORD VARCHAR2(6)   NOT NULL CHECK (PASSWORD LIKE '______'),
  PHONE    VARCHAR2(11),
  BALANCE  NUMBER(20, 4) NOT NULL
);

--创建序列
CREATE SEQUENCE USERS_SEQ START WITH 6217002490001000001;

--插入数据
INSERT INTO USERS VALUES (USERS_SEQ.nextval, 'Zhang', '123123', '13912341243', 3000.00);

--查询数据
SELECT * FROM USERS;

--删除数据
DELETE FROM USERS WHERE ID = '6217002490001000001';

--更新信息
UPDATE USERS SET PHONE = '15312345678' WHERE ID = '6217002490001000001';
UPDATE USERS SET PASSWORD = '123456' WHERE ID = '6217002490001000001';
UPDATE USERS SET BALANCE = 1000.0 WHERE ID = '6217002490001000001';

--查询序列
SELECT USERS_SEQ.nextval FROM dual;
SELECT USERS_SEQ.currval FROM dual;

--删除表
DROP TABLE USERS;

--删除序列
DROP SEQUENCE USERS_SEQ;

SELECT sysdate FROM DUAL
