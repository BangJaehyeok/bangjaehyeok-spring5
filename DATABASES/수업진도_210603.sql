--DESC:Description ���̺����� ����
DESC dept;
--select: ���̺��� ��ȸ, where  ��ȸ����, as(Alias) ��Ī���� �ʵ���� �涧 ���
--concat����Ŭ�����Լ��� ����Ʈ �ۼ���
SELECT 
concat(deptno, ' ��') as "�μ���ȣ", 
dname as "�μ���",
concat(loc,' ��') as "��ġ"
FROM dept 
WHERE loc = 'NEW YORK';
-- DUAL �������̺��̸�. ���̺��� ���� ������ select�Ҷ�
SELECT CONCAT('��', '����') from dual;
select 3+5 as "3���ϱ�5��" from dual;
--���ڵ�(row) : �÷�(�ʵ�field)��� ����
SELECT concat(count(*),'��') as "������ 2000�� ����" from emp where sal > 2000;
--ū����ǥ(�ʵ��), ��������ǥ(����ó��,��,����)
SELECT * from emp WHERE ename != 'KING';
SELECT * FROM emp WHERE hiredate >= '1982/01/01';
--OR�� ������, AND�� ������
SELECT * FROM emp WHERE deptno = 10 OR job = 'MANAGER';
SELECT * FROM emp WHERE sal not BETWEEN 2000 and 3000;
SELECT * FROM emp WHERE hiredate 
BETWEEN '1981/01/01' and '1981/12/31';
SELECT * FROM emp WHERE comm not in (300,500,1400);
--Like ��ȸ, ���ϵ�ī��=% ��ȸ
--(�߿�)
SELECT * FROM emp WHERE ename LIKE upper ('k%');
SELECT * FROM emp WHERE ename LIKE '%N';
-- null�� �߿��� ���� : null���� ������ �˻��� �ȵ�
-- �׷��� null���� �ʵ忡 ������, �˻���?
SELECT * FROM emp WHERE comm is not NULL;
-- NVL(Null VaLue) Null���� ��ġ�ϴ� �Լ�
-- ����߿� Ŀ�̼��� 0�� ���� �����?
-- �Ʒ� E�� emp���̺��� �˸��ƽ� ��Ī���� E.*�� emp.*�� ��������.
SELECT  nvl2(comm,100,0), E.* FROM emp E WHERE NVL(comm,0) = 0;
--NVL2(�ʵ��, ���̾ƴҶ�100, ���϶�0), NVL(�ʵ��, ���϶�0)
-- ����Ŭ�� ǥ������X, ANSI������ ǥ��.
SELECT  DECODE(comm,null,0,100),NVL2(comm,100,0), E.* FROM emp E WHERE NVL(comm,0) = 0;
-- ������ �������� ���� sort, ���� order by �ʵ�� ��������(�ʱⰪ)|�������� DESC
-- (�߿�)�������� select������ �ߺ��Ǿ��ִ°� ��������.
SELECT ROWNUM, E.* FROM ( 
SELECT * FROM emp ORDER BY sal desc
) E where rownum = 1;
--�� ����Ŀ���� ������ �ؼ��Ҷ��� ���ʺ��� �ؼ�
--�� ���Ŀ��� 1� ���Ҷ� limit�� Mysql(������DB)�� ��ɾ�. ����Ŭ�� ����.
-- Mysqul(������DB) �ִ� AI(AutoIncrement)�ڵ������� ��� ����Ŭ�� ����.
-- �ߺ����ڵ�(row)�� �����ϴ� ��ɾ� distinct
SELECT deptno AS "�μ���ȣ" FROM emp; --��� ����� �μ���ȣ�� ��µȴ�.
SELECT DISTINCT deptno as "�μ���ȣ" from emp; --�ߺ����ڵ带 ����.
--(�߿�)���ڿ��� �����Ҷ� concat�Լ� �ܿ� ||���������� 2���� ��ġ��ȴ�.
select ename ||' is a '|| job AS "���ڿ� ����" from emp;
-- ������� select ������ Read
-- ���Ŀ��� CRUD�߿� Insert, Update, Delete
