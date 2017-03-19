CREATE TABLE T_ACCOUNT (
  CARD_ID   VARCHAR2(19) PRIMARY KEY,
  PASSWORD  VARCHAR2(20)  NOT NULL,
  NAME      VARCHAR2(20)  NOT NULL,
  PHONE_NUM VARCHAR2(11)  NOT NULL,
  ADDRESS   VARCHAR2(50)  NOT NULL,
  BALANCE   NUMBER(20, 4) NOT NULL
);
DROP TABLE T_ACCOUNT;

CREATE SEQUENCE CARD_ID_SEQ START WITH 622001;
DROP SEQUENCE CARD_ID_SEQ;

INSERT INTO T_ACCOUNT VALUES ('', '', '', '', '', '');

DELETE FROM T_ACCOUNT
WHERE CARD_ID = '';

UPDATE T_ACCOUNT
SET PASSWORD = '', NAME = '', PHONE_NUM = '', ADDRESS = '', BALANCE = 123
WHERE CARD_ID = '';

SELECT *
FROM T_ACCOUNT;

SELECT *
FROM T_ACCOUNT
WHERE CARD_ID = '';

COMMIT;

SELECT CARD_ID_SEQ.nextval
FROM dual;