--DESC:Description 테이블구조를 설명
DESC dept;
--select: 테이블내용 조회, where  조회조건, as(Alias) 별칭으로 필드명이 길때 사용
--concat오라클내장함수는 레포트 작성시
SELECT 
concat(deptno, ' 번') as "부서번호", 
dname as "부서명",
concat(loc,' 시') as "위치"
FROM dept 
WHERE loc = 'NEW YORK';
-- DUAL 가상테이블이름. 테이블이 없는 내용을 select할때
SELECT CONCAT('방', '재혁') from dual;
select 3+5 as "3더하기5는" from dual;
--레코드(row) : 컬럼(필드field)들로 구성
SELECT concat(count(*),'명') as "연봉이 2000인 직원" from emp where sal > 2000;
--큰따옴표(필드명), 작은따옴표(문자처리,비교,결합)
SELECT * from emp WHERE ename != 'KING';
SELECT * FROM emp WHERE hiredate >= '1982/01/01';
--OR은 합집합, AND는 교집합
SELECT * FROM emp WHERE deptno = 10 OR job = 'MANAGER';
SELECT * FROM emp WHERE sal not BETWEEN 2000 and 3000;
SELECT * FROM emp WHERE hiredate 
BETWEEN '1981/01/01' and '1981/12/31';
SELECT * FROM emp WHERE comm not in (300,500,1400);
--Like 조회, 와일드카드=% 조회
--(중요)키워드에 괄호가 있으면 함수upper(),concat(),count()
SELECT * FROM emp WHERE ename LIKE upper ('k%');
SELECT * FROM emp WHERE ename LIKE '%N';
-- null이 중요한 이유 : null값이 있으면 검색이 안됨
-- 그러면 null값이 필드에 있을때, 검색은?
SELECT * FROM emp WHERE comm is not NULL;
-- NVL(Null VaLue) Null값을 대치하는 함수
-- 사원중에 커미션을 0원 받은 사람은?
-- 아래 E는 emp테이블의 알리아스 별칭으로 E.*은 emp.*과 같은내용.
SELECT  nvl2(comm,100,0), E.* FROM emp E WHERE NVL(comm,0) = 0;
--NVL2(필드명, 널이아닐때100, 널일때0), NVL(필드명, 널일때0)
-- 오라클은 표준쿼리X, ANSI쿼리가 표준.
SELECT case when comm is null then 0
when comm = 0 then 100
when comm > 0 then comm 
end AS "CASE출력문"
,DECODE(comm,null,0,100),
NVL2(comm,100,0), 
E.* FROM emp E; --WHERE NVL(comm,0) = 0;
-- 연봉을 기준으로 정렬 sort, 순서 order by 필드명 오름차순(초기값)|내림차순 DESC
-- (중요)서브쿼리 select쿼리가 중복되어있는게 서브쿼리.
SELECT ROWNUM, E.* FROM ( 
SELECT * FROM emp ORDER BY sal desc
) E where rownum = 1;
--위 서브커리의 문장을 해석할때는 안쪽부터 해석
--위 정렬에서 1등만 구할때 limit는 Mysql(마리아DB)의 명령어. 오라클은 없음.
-- Mysqul(마리아DB) 있는 AI(AutoIncrement)자동증가값 명령 오라클은 없음.
-- 중복레코드(row)를 제거하는 명령어 distinct
SELECT deptno AS "부서번호" FROM emp; --사원 수대로 부서번호가 출력된다.
SELECT DISTINCT deptno as "부서번호" from emp; --중복레코드를 제거.
--(중요)문자열을 연결할때 concat함수 외에 ||파이프라인 2개를 합치면된다.
select ename ||' is a '|| job AS "문자열 연결" from emp;
--여기까지 select 마무리 Read
--이후에는 CRUD중에 Insert, Update, Delete입니다.
--함수용어 ABS(Absoulte절대값), Floor(바닥함수,1.5=1 내림)<->Ceil(천정함수,1.4=2올림)
--ROUND(반올림), TRUNC(Truncate소수점자리버리는함수), MOD(나머지구하는함수)
--UPPER(대문자),LOWER(소문자),LENGTH(길이구하는함수)
--Instr(문자의위치를구하는함수), Substr(매개변수로 입력한 숫자위치만큼 문자열을 추출하는함수)
--Lpad(LeftPadding왼쪽여백), Rpad(오른쪽여백), 레포트프로그램에서 출력조정시 사용
--Trim(왼쪽,오른쪽 문자열을 잘라내는 함수)
--날짜 sysdate로 오라클전용 함수로서 게시물입력시간, 회원등록시간
--밀리세컨드까지 지정하려면 systimestamp로 시간을 지정해야한다.
SELECT to_char(systimestamp,'yyyy-mm-dd hh24:mi:ss:ff4') FROM dual;
--위 to_char함수는 날짜를 문자열로 변환하는 형변환함수
SELECT sysdate +1 from dual; --내일날짜
select sysdate -1 from dual; --어제날짜
--6개월간 회원정보 수정이 없는 회원에게 공지서비스를 처리(아래)
select * from tbl_member
where update_date < add_months(sysdate,-6);
