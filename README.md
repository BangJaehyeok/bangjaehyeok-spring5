#### 20210531(월) 작업
- 4장 패키지와 예외처리 실습
- 스프링프로젝트 ERD 제작 후 게시판UI디자인 적용.
- 오라클 : 테이블스페이스(TableSpace) = MySQL: 스키마(Scheme) = MS-SQL:데이터베이스(Database)
- 암호는 apmsetup으로 통일.
- 오라클 웹용 관리프로그램에서 XE라는 테이블스페이스를 XE사용자로 추가했음.
- ERD(Entity Relation Diagram)-객체관계그림 : Entity=테이블
- 데이터 모델 : Model Object를 형상화 시킨것을 모델이라고 함. 데이터베이스를 말함.
- MVC 스프링프로젝트에서 M이 Model. 스프링프로젝트 구성 중에 데이터베이스를 가리킨다.
- 참고로 V는 View, jsp를 말한다. C는 Controller고 클래스를 말한다.
- RDBMS : Relation DataBase Management System(관계형데이터베이스관리시스템)
- 테이블구성 : 필드(컬럼)=테이블의 멤버변수(자바클래스의 멤버변수)
- 필드구성시 PK(Primary Key;기본키, 고유키) = 테이블영역에서 고유한 ID를 말한다. 중복되지 않음.
- PK(개인을 식별할 수 있는 값) 예 : 로그인id, 개인이메일주소, 주민번호 등
- 필드 데이터형 : VARCHAR2(2바이트를 1글자 = 한글), VARCHAR(1바이트가 1글자 - 영문전용)
- 필드 데이터형2 : CLOB(CHAR LONG BYTE) 글내용이 많을때 사용하는 데이터형태
- 필드 데이터형3 : Timestamp - 연월일시분초밀리초까지 표기. Date-년월일까지
- ERD에서 Relation 생성 : 게시판타입테이블(부모)과 게시물관리테이블(자식)의 관계를 생성
- 부자의 관계는 부모의 PK를 기준으로, 자식에게는 FK(Foreign Key)로 관계를 맺습니다.
- 게시판타입 : notice, gallery 2가지가 존재한다면, 게시물은 공지사항,갤러리 게시판 두개만 가능하고 다른 게시판은 등록이 오류가 발생함.
- 외래키 관계 = 데이터 무결성을 유지하는 역할.
- PK를 AI(AUTO INCREMENT 자동증가)로 만들기 : 오라클(시퀸스객체로 기능구현), MySQL(AI라는 필드속성으로 처리)
- E-R 다이어그램은 그림일뿐, 물리DB(테이블)은 ERD 기준으로 생성가능.
- 1:N 관계 = 게시물1개에 댓글 n개 달릴 수 있음.
- 스프링시큐리티 2단계(구현예정) : 1단계 인증 로그인인증(ENABLED) : AUTHENTICATION (로그인X, 글쓰기X, 관리자X)
- 2단계 인증 권한체크(LEVELS) : AUTHORITY (관리자-admin URL 접근가능, 일반유저는 사용자홈페이지에서 글작성, 댓글작성 가능, 공지사항X, 갤러리만 사용가능)
- ERD 만든것을 물리 테이블로 만드는것. forward 엔지니어링
- 데이터 딕셔너리를 모델과 동기화 : 자료사전(데이터의 데이터)을 DB테이블(표)과 동기화
- 데이터 딕셔너리는 메타 데이터와 동일하다. 콘텐츠가 없고, 구조 구성 정보만 존재
- 물리테이블을 ERD로 만드는 것 : reverse 엔지니어링(기존 DB분석시 사용)
- 토드(SQL디벨로퍼와 비슷한 상용프로그램)에서는 버튼으로 포워드 엔지니어링이 처리됨.
- SQL디벨로퍼는 무료라서, 버튼으로 안된다. 그래서 DDL문을 실행해서 포워드 엔지니어링을 처리한다.
- 참고, mysql용 워크벤치는 무료지만 버튼으로 포워드 엔지니어링이 가능하다.
- SQL쿼리를 3가지로 분류한다.
- 1. DDL문 : Data Definition Language. 데이터 정의 언어 - 테이블생성 (초기)
- 2. DCL문 : Data Control Language. 데이터 제어 언어 - commit, roll-back (유지)
- 3. DML문 : Data Manufacture Language. 데이터 조작 언어 - CRUD 쿼리. (개발)



#### 20210528(금) 작업
- 오라클11g ExpressEdition 설치
- SQL디벨러퍼를 다운받고 실행.
- Structured Query Language : 구조화된 질의언어(오라클서버에)
- QueryString : URL에서 전송하는 값(서버에 질의문)을 문장으로 저장한 내용 ?로 시작 
- CLI(Command Line Interface): SQL*Plus 터미널 화면으로 SQL쿼리 실행X
- GUI(Graphic User Interface): SQL디벨러퍼 윈도우화면 에디터에서 SQL쿼리를 실행 O
- SQL Developer 프로그램으로 ERD 다이어그램 그림으로 프로젝트 구조제작예정
- EntityRelationDiagram(ERD): 테이블관계도형, 
- 게시판테이블(부모) <- 댓글테이블(자식), 첨부파일(자식)
==================================================================
- 프로젝트 진행 : 발주(클라이언트) -> 수주(개발사) -> 디자인UI(클라이언트-개발사) -> ERD(직급높은개발자) -> 코딩시작(ERD를 보면서 UI소스에 프로그램입히기)
- ERD에서 SQL쿼리가 생성 -> 물리테이블을 생성.
==================================================================
- 자바앱에서는 객체를 생성 후 사용이 끝나면, 메모리에서 삭제하는 명령이 있어야함.
- 객체를 메모리에서 삭제 : Object = null; Object.close();
- 스프링에서는 내장된 가비지컬렉션(쓰레기수집)이 자동실행해서 사용하지 않는 객체를 자동삭제함.
- 위와 같이 개발자가 하던 메모리관리를 스프링이 대신하는 것 = IoC(Inversion Of Control) 제어의 역전.
- 스프링의 특징 : IoC, AOP, DI
- extends 관계클래스에서 this.(현재클래스), super.(부모클래스)
- 추상클래스 실습은 오늘, 인터페이스 실습은 스프링에서 많이 하게 될 것.
- 다형성: 메서드 @오버라이드(상속), 메서드 오버로드(동일 함수명에 파라미터의 개수 or 종류가 다른 메서드) 구현됩니다.
- 오버라이드 : 상속받아서 재정의 메서드 @오버라이드 = 다형성 구현했다.


#### 20210527(목) 작업
- 인스턴스(클라우드용어) : 실행중인 프로그램(=오브젝트 =객체 =실행중인 클래스)
- 클래스(int, long, String처럼 자료형)는 멤버변수, 멤버메서드, 서브클래스 등등 포함할 수 있습니다.
- 접근제어자 : public(패키지위치에 상관없이 접근-제일 많이 사용)
- public 클래스안에서 멤버변수는 private을 제일 많이 사용.(private 보안성 갖춤)
- public 클래스안에서 멤버메서드는 public을 많이 사용.(외부 다른 클래스의 메서드를 사용) 대신, 변수가 private이기 때문에, 직접 수정이 안됨. 실행만 가능함.
- final 클래스 : 부모가 될 수 없는 클래스(상속해서 재사용이 불가능한 클래스임)
- abstract클래스(추상클래스) : 구현내용이 없이 메스명만 있는 클래스,
- 메서드 명만 있기때문에(=구현내용이 없기 때문에), 객체로 만들 수 없는 클래스. 부모로서 자식에게 상속만해서 메서드이름만 재사용가능하게 된다.
- 객체로 만들 수 없다? => 실행가능한 클래스로 못만듬. 그러나 프로그램을 구조화시킬때 필요.
- Step1 aaa = new Step1();// 이렇게 생성자로 객체를 만들지 못함.
- 스프링에서는 extends 상속보다는 인터페이스 implements를 더 많이 사용한다.
- 이클립스에서 커밋, 푸시가 안될때 커밋 팝업창에서 force어쩌구 항목을 체크하고 푸시할것.

#### 20210526(수) 작업
- 붕어빵 : 붕어빵틀(클래스) -> 구워져나온 붕어빵 : 오브젝트(객체)
- OOP: 자바를 객체지향(클래스를 실행가능하게 하는) 프로그래밍
- 객체=오브젝트=인스턴스=실행가능한 클래스
- 객체(Object) : 클래스형 자료를 실행가능하게 만든 것을 오브젝트(객체)라고함.
- 추상화(Abstract) : 오프라인 업무 -> 대표 업무만 추려낸 클래스 = 추상클래스
- 클래스는 멤버변수 + 멤버메서드(실행) + 서브클래스
- 변하지 않는 변수=상수=변수명을 대문자(원주율) PI=3.141592...
- 보통 상수변수는 클래스의 제일 상단에 위치한다.
- 기본형(정수자료형) : byte<short<int<long, 10L(롱타입변수)
- 기본형(실수자료형-소수점): float<double, 12.34f(실수형 숫자)
- 기본형(문자형-'a' 작은따옴표로 값을 입력해야 에러가 안난다.) : char, 1개 문자만 입력.
- 기본형(불린형-true,false) : boolean, (0|1)
- 문자형에서 유니코드 \u숫자 입니다.
- 참조형(대문자로 시작) : 참조형변수의 대표적인 변수 클래스입니다.
- 클래스 변수(저장된상태) -> 실행가능하게 되었을때, 인스턴스 변수(메모리로드된 상태)
- 인스턴스라는 말보다는 오브젝트라는 말을 더 많이 사용한다.
- String(문자열 클래스변수) : 대문자로 시작
- 상수변수를 명시적으로 만들때 : final int MAX = 100;
- for-each 반복문전까지 실습


#### 20210525(화) 작업
- 자바 기초실습
- 자바 .java클래스파일을 컴파일해서 생성된 .class(object)파일 실행하는 구조.
- 파이썬/자바스크립트는 인터프리터 방식으로 실행.
- 자바스크립트는 함수구조의 프로그래밍 = Function(함수)
- 자바는 객체지향 프로그래밍(Object Oriented Program)
- 오브젝트(객체) = 실행가능한 Class(클래스)
- 아스키, 유니코드(UnicodeTypeFormat-8)
- 아스키코드 IoT에서 데이터 전송시 필수로 확인해야한다. 0,1을 전송하면 IoT에는 48, 49값을 받는다.
- IoT프로그램시 하드웨어 값을 주고 받을때  if(var1 == 48) {구현내용}
- 공유기가 하위 가질 수 있는 사설 IP는 공유기본인IP + 255개(여유분) = 256개 인터넷이 가능.
- 영어인코딩은 아스키코드로 충분히 표현가능. 단, 한글,중국어,일본어 등 언어들은 아스키코드로 다 표현 못함. 단, 한글인코딩 등의 다른 언어들은 인코딩을 잘 못함. 그래서 유니코드 등장 => UTF-8
- Hex(16진수), Dec(10진수), Char(문자) = 127개. 아스키문자의 크기(컴문자-사람문자 1:1 매칭)
- Oct(8진수), Bit(2진수)
- 아스키코드 7비트 코드(127글자) -> ANSI코드 8비트(256글자) -> UniCode(65536 글자): UTF-8
- UTF8mb4(이모지까지 포함 : 이모티콘+이미지)
- 자바언어를 한다는 것은 컴파일로 실행프로그램을 만들게됩니다. => 실습예정
- 자바스크립트(파이썬)는 그냥 실행해서 프로그램이 만들어진다. => 실습예정
- 스프링MVC프로젝트 : ModelViewController의 약자 MVC구조(웹프로그램 구조)
- src/test/java폴더를 생성 : 테스트작업은 이 폴더에서 하라는 약속.
- src/main/java폴더가 진짜 프로그램작업을 하는 폴더
- javac HelloWorld.java -encoding UTF-8 (한글내용도 컴파일됨)
- 위 java컴파일러로 실행한 결과 -> HelloWorld.class 파일이 생성됨.
- 주) 클래스파일은 패키지의 루트(최상위)에서 실행해야함.
- kr.or.test패키지 root폴더는 src/test/java폴더에 실행을 해야함.
- java kr.or.test.HelloWorld 로 클래스를 실행한다.
- 25년 전 javac 컴파일에서 class프로그램을 만들었다.
- 지금은 터미널에서 javac 사용하지 않고, 이클립스에서 바로 실행한다.
- javac : 자바앱컴파일러 -> 클래스 실행파일을 만든다 .class (자바환경JVM에서 실행)
- 메이븐은 무엇인가: Maven 웹프로그램 컴파일러 -> 웹프로그램(앱) 실행파일을 만든다. .war(톰캣에서 실행)
- 메이븐이 컴파일할때, 자바소스만 컴파일하는 것이 아니고, 외부 라이브러리도 가져와서 바인딩(묶어줌)하게 됨 = 패키징이라고 한다. => .war파일로 패키징
- 메이븐이 관리하는 외부 라이브러리파일(lib) 목록을 저장하는 파일 pom.xml이다.
- pom.xml에서 자바버전을 1.8로 바꾼 후 메이븐을 업데이트합니다.
- 외부 라이브러리 파일을 참조하는 방식을 영어로 = dependency라고 이야기함.
