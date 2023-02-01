----------------------------------------------------
-- SELECT * FROM EMP_TB;
-- SELECT * FROM DEPT_TB;
----------------------------------------------------
DROP TABLE EMP_TB;
DROP TABLE DEPT_TB;
----------------------------------------------------

CREATE TABLE DEPT_TB
(
    DEPT_NO	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    DNAME	VARCHAR CHECK( LENGTH(DNAME) <= 14 ),
    LOC		VARCHAR CHECK( LENGTH(LOC) <= 13 )
);

CREATE TABLE EMP_TB
(
    EMP_NO	 INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    ENAME	 VARCHAR(14) CHECK( LENGTH(ENAME) <= 14 ),
    JOB		 VARCHAR(13) CHECK( LENGTH(JOB) <= 13 ),
    HIREDATE  DATE,
    DEPT_NO INT NOT NULL,
    FOREIGN KEY(DEPT_NO) REFERENCES DEPT_TB(DEPT_NO)
    --Hibernate 做關聯查詢其實可以不用在實體表格設定foreign-key
);


INSERT INTO DEPT_TB(DEPT_NO,DNAME,LOC) VALUES (10,'財務部','臺灣台北');
INSERT INTO DEPT_TB(DEPT_NO,DNAME,LOC) VALUES (20,'研發部','臺灣新竹');
INSERT INTO DEPT_TB(DEPT_NO,DNAME,LOC) VALUES (30,'業務部','美國紐約');
INSERT INTO DEPT_TB(DEPT_NO,DNAME,LOC) VALUES (40,'生管部','中國上海');

INSERT INTO EMP_TB(EMP_NO,ENAME,JOB,HIREDATE,DEPT_NO) VALUES (7001,'King','president','1981-11-17',10);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Blake','manager','1981-05-01',30);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Clark','manager','1981-01-09',10);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Jones','manager','1981-04-02',20);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Martin','salesman','1981-09-28',40);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Allen','salesman','1981-02-2',30);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Turner','salesman','1981-09-28',30);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('James','clerk','1981-12-03',30);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Ward','salesman','1981-02-22',30);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Ford','analyst','1981-12-03',20);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Smith','clerk','1980-12-17',20);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Scott','analyst','1981-12-09',40);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Adams','clerk','1983-01-12',20);
INSERT INTO EMP_TB(ENAME,JOB,HIREDATE,DEPT_NO) VALUES ('Miller','clerk','1982-01-23',10);