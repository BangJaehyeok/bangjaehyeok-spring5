--8장 함수(count, upper, lower, to_char, round,...) 그룹함수
--having은 group by 의 조건문을 적습니다. group by 안에서 where와 같은 역할을 한다.
--부서별 평균 연봉이 2000이상인 부서의 번호와 부서별 평균급여
SELECT deptno, round(avg(sal)) from emp
--where avg(sal) >=2000 --검색조건
group by deptno
having avg(sal) >= 2000; --그룹조건


--부서별 연봉의 합계를 구해서 제일 급여가 많이 지출되는 부서(아래)
--자바코딩에서는 소문자로 통일.
select * from (
SELECT deptno, sum(sal) as dept_sal
from emp group by deptno) R order by dept_sal desc; --R의 역할은 Alias 별명이다. 별칭. as R
--서브쿼리에 별칭을 정해서 구분짓기 쉽게 하는 역할도 한다.

select deptno, sum(sal) from emp group by deptno
order by sum(sal) desc; --group by의 특징 : select에 있는 필드명과 group by에 있는 필드명이 일치해야한다.


--라운드함수(반올림) 소수점기준. round(10.05,2) 소수점 두번째자리 반올림
select ename, round(sal, -3) from emp; --레코드가 여러개 출력
select sum(sal) from emp; --한개의 레코드만 나옴. 그룹함수라고 말함.
select round(avg(sal),2) from emp; -- 평균 1개의 레코드로 출력.

-- 사원 중에 평균연봉보다 많이 받는 사람의 수 구하기(아래),
-- 아래 AVG함수를 where 조건에 사용못할때, 서브쿼리를 이용한다. 쿼리안의 쿼리
select count(*) from emp where sal < 
(select avg(sal) from emp);

select max(sal)
, min(sal)
, max(sal)-min(sal) AS "대표와 사원의 연봉차"
from emp;

--DDL문(create; alter;), DCL문(commit; rollback;)
--DML문(Data Manufacture Language) insert, update, delete
--inser문 : 테이블에 새로운 레코드(row)를 추가
CREATE TABLE dept02 AS SELECT * FROM dept where 1=0;
--위 쿼리는 테이블을 복제하는 명령
--위처럼 쿼리를 실행하면 dept테이블과 구조와 내용이 똑같은 테이블이 생성된다.
--where 조건이 붙으면 구조는 같으니 내용은 빈 테이블이 생성된다.
insert INTO dept02 (
--필드명
deptno, dname, loc
) VALUES(
10, '개발부서', '천안'
--바인딩값
);
--DCL명령어 커밋을 반드시 해줘야한다.
COMMIT;--데이터베이스쿼리 직접입력한 결과는 반드시 커밋을 해줘야지만 실제 저장이 된다.
--커밋을 하지 않으면 여기만 보이고 다른 곳에서는 결과값이 보이지 않는다.
select * from dept02
--delete는 레코드1줄을 지우는 명령
DELETE from dept02; --이렇게 사용하면 안된다.
delete from dept02 where deptno >=0; --모두 삭제 where 반드시 포함
--DROP은 테이블자체를 DB에서 물리적으로 없애는 명령
DROP TABLE dept02; --드롭테이블은 커밋없이 바로 적용가능
CREATE TABLE emp01 AS select * from emp; --테이블 복제명령
SELECT * FROM emp01;
--UPDATE 테이블명 SET 필드명 where empno='특정ID' = '바꿀값' : 모든 정보가 다 바뀌어버린다. where조건이 필요하다.
update emp01 set ename = '방재혁' where empno = 7839;
rollback; --DCL문 롤백은 마지막 커밋 바로 전까지 되돌린다.
update emp01 set sal = sal*1.1; --모든직원의 연봉을 10%인상
update emp01 set hiredate = SYSDATE;
--MERGE는 표준쿼리(ANSI)가 아니다.

--9장은 패스(레포트용 함수사용)

--10장 테이블 조인 2개의 테이블을 연결해서 결과를 구하는 예약어
--댓글 개수 구할 때, 
--카티시안 프러덕트 조인(합집합) - 게시물10개, 댓글100개 = 110개~1000개 의미없는 조인.
-- (인너)조인(교집합)을 제일 많이 사용
-- 아래 조인방식이 오라클방식
select * from emp, dept 
where emp.deptno = dept.deptno
and emp.ename = 'SCOTT';
-- 표준쿼리(ANSI) 방식(아래)
select d.dname, e.* from emp e join dept d 
on e.deptno = d.deptno
where e.ename = 'SCOTT';
-- 조인과 그룹을 이용해서 댓글카운터도 출력하는 게시판리스트 만들기
select bod.bno, title, count(*) , writer, bod.reg_date
from tbl_board  BOD
Inner join tbl_reply REP ON bod.bno=rep.bno
group by bod.bno, title, writer, bod.reg_date
order by bod.bno;

SELECT bod.bno ,title||'['||count(*)||']'
,writer,bod.reg_date, view_count
FROM tbl_board BOD
INNER JOIN tbl_reply REP ON bod.bno=rep.bno
GROUP BY bod.bno, title, writer, bod.reg_date, view_count
ORDER BY bod.bno;

--11.서브쿼리
--단일행 서브쿼리 필드값을 비교할때, 비교하는 값인 단일함(필드값)
--다중행서브쿼리 테이블값을 select쿼리로 생성(레코드값)

--12. 테이블 구조 생성(create), 변경(alter), 삭제(drop)
-- ERD 관계형 다이어그램으로 물리테이블을 생성(포워드 엔지니어링)
create table TBL_MEMBER_DEL
(
USER_ID varchar(50) PRIMARY KEY, --고유key
USER_PW varchar(255),
USER_NAME varchar(255),
EMAIL varchar(255),
POINT NUMBER(11,0),
ENABLED NUMBER(1),
LEVELS varchar(255),
REG_DATE timestamp,
UPDATE_DATE timestamp
);
--ALTER 테이블로 필드명 변경(아래)
desc tbl_member_del;
alter table TBL_MEMBER_DEL rename column point to user_point;
--DEPT테이블의 deptno 숫자2자리 때문에 에러 -> 4자리로 크기 변경
desc dept; --단, 작은자리에서 큰자리로 변경은 문제없음.
alter table dept modify (deptno number(4));
drop table tbl_member_del;

--14장 트랜잭션 DB단에서 사용하지 않고, 
--스프링단에서 트랜잭션을 사용 @Transactional
-- commit과 rollback; (DML문 : insert,update,delete)
-- rollback은 제일 마지막 커밋된 상태로 되돌린다.

--15장 PK생성시 자동으로 2가지가 생성 : NOT NULL(빈값방지), UNIQUE(NO중복)
--제약조건(constraint)이 자동생성, index도 자동생성(검색시 중요)
-- ERD로 게시판테이블-댓글과 첨부파일 테이블 둘다 Foreign Key(외래키) 부자관계

--19장 사용자 추가(CreateWorkSpace)시 오라클데스크탑,SQL플러스 둘다 안씀 대신 
--웹프로그램을 사용 http://127.0.0.1:9000/apex/f?p=4950