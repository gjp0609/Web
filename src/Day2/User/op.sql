CREATE TABLE t_user (
  id       VARCHAR2(5) PRIMARY KEY,
  name     VARCHAR2(10) NOT NULL,
  age      NUMBER(3)    NOT NULL,
  sex      VARCHAR2(1)  NOT NULL CHECK (sex IN ('F', 'M')),
  email    VARCHAR2(20),
  address  VARCHAR2(50) NOT NULL,
  password VARCHAR2(50) NOT NULL
);
DROP TABLE t_user;

CREATE SEQUENCE user_seq START WITH 100;
DROP SEQUENCE user_seq;

SELECT
  t.*,
  ROWID
FROM t_user t;

SELECT user_seq.currval
FROM dual;

INSERT INTO t_user VALUES (user_seq.nextval, 'name', 18, 'F', 'email', 'address', 'hello');

DELETE FROM t_user
WHERE id = '100';

UPDATE t_user
SET name = '123', age = 18, sex = 'F', email = '123', address = '123', password = 'hello'
WHERE id = '100';

COMMIT;