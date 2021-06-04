--8장 함수(count, upper, lower, to_char, round,...) 그룹함수
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
