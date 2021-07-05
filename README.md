#### 작업예정.
- 1달간(프론트엔드)은 주로 UI 계속진행(VS code개발환경) 하면서,
- 피곤할때, 자바|스프링(이클립스-egov전자정부프레임워크개발환경) 기본언어실습진행.
- 피곤할때, 오라클DB(SQL디벨러퍼개발환경) Ansi-SQL(표준SQL)기본언어실습진행CRUD.
- 2달째부터(백엔드), 주로 스프링으로 실습이 진행(납품용-이력서포트폴리오용).
- VS code에서 만든 UI를 JSP로 변경 한 후 스프링웹프로젝트를 진행합니다.
- 스프링 프로젝트 순서
1. JUnit > 마이바티스(DB핸들링) > AOP(다중게시판기능) > 페이징기능 > 검색기능 > 트랜잭션기능 (게시판삭제시 댓글과 첨부파일까지 삭제처리되게) > 첨부파일기능(파일업로드/다운로드) > 스프링시큐리티(로그인 인증/권한체크)
2. 댓글기능(RestAPI-백엔드,Ajax처리-프런트단) > 네이버아이디로그인(외부API사용) > 헤로쿠클라우드배포
3. 문서작업(화면기획서XLS 제작, 화면설계서PPT 제작)
- 헤로쿠 클라우드에 배포할때, 매퍼폴더의 mysql폴더내의 쿼리에 now()함수를 date_add(now(3)), interval 9 hour) 변경 예정.(이유는 DB서버 타임존이 미국이기 때문) 


#### 20210705(월) 작업
- 헤로쿠 클라우드에서 App 생성
- 헤로쿠 App name = Host name = 호스트네임.herokuapp.com(호스트네임<도메인네임)
- 클라우드 컨테이너 생성시 위와 같은 방식으로 호스트네임 도메인을 부여합니다.
- Deploy 에러 : No web processing running => 현재 프로젝트에 클라우드용 설정파일이 필요 = 헤로쿠에서 procfile 확장자 없는 설정파일이 필요
- 위 Procfile에서 web processes running시키는 라인이 추가되어야함.
- 스프링에서 작업해서 배포한다는 의미 : All or Not All
- 작업한 개별파일 1개씩 수정해서 올리는 방식은 PHP에서 워드프레스, 그누보드 등에서 사용
- 그러나 스프링방식은 작업한 파일이 1개라도 1개만 올리는 것이 아니고, 모든 파일을 컴파일해서 패키징(war파일)한 후 업로드한다.
- hsqldb 외부모듈 pom.xml에 추가(자바기반 DB사용가능)
- 프로젝트에 HsqlDB를 생성 -> Maven에서 Hsql모듈을 업데이트
- 오라클은 로컬에서 개발, HsqlDB는 헤로쿠 클라우드용을 개발할 수 있도록 root-context.xml에서 설정예정.
- 아래 3가지가 root-context에 추가됩니다.
- hsql용 jdbc드라이버를 스프링빈으로 생성하기
- DB생성 스크립트 실행
- DB매니저실행하기

- 오라클은 로컬에서 개발, HsqlDB는 헤로쿠 클라우드용으로 개발할 수 있도록 root-context.xml에서 설정
- 현재까지 작업한 소스를 내 이름 도메인으로 배포예정.
- 관리자단 대시보드 작업예정.
- 사용자단 게시물관리 CRUD작업예정.
- 사용자단 메인페이지(대시보드) 작업예정. 

#### 20210702(금) 작업
- 사용자단 마이페이지 수정, 탈퇴 JSP기능 OK.
- 사용자단 회원가입 작업예정 OK.
- form폼에서 name은 VO/매퍼쿼리 필드명이 같다. id는 선택해서 jsp()단에서 제어(j쿼리)할때 사용.
- 사용자단 에러발생시 화면이쁘게 보이게 처리예정 OK.
- error_spring.jsp 만듭니다. OK
- 위 jsp를 에러발생시(Exception) 무조건 나오게 처리 : AOP중 @ControllerAdvice로 구현.
- 위 어드바이스컨트롤러에서 에러메세지를 캐치해서 jsp에러페이지로 보내서, 에러메세지를 세련되게 나오게하기.
- 404에러는 컨트롤러에서 발생되지 않음. 그래서 별도로 파일을 만들어야함.
- 톰캣서버에서 발생하는 에러코드404이기 때문에 web.xml에서 설정을 추가합니다.
- 404코드가 발생시 error_404.jsp와 바인딩되는 설정. HomeController에서 Get방식으로 /home/error/error_404 경로추가 OK
- ========================================================
- 헤로쿠 클라우드 배포작업 준비.
- 헤로쿠 클라우드는 미국의 회사로서 컨테이너를 제공하는 회사.
- 컨테이너는 리눅스OS>톰캣WAS>자바JVM>스프링기반 환경제공
- 외부서버(DB) Add on이라는 이름으로 사용가능
- 무료: PostgeresDB(조건), 마리아DB(무료긴한데 신용카드등록필수)
- 유료 : Mysql(유료)
- HsqlDB로 연동해서 헤로쿠에 배포예정. http://hsqldb.org/
- 100% Java Database : 임베디드DB, 메모리 DB, 서버를 설치할 필요없이 Hsql이라는 Maven모듈만 있으면 가능
- 관리자단 대시보드 작업예정.
- 사용자단 게시물관리 CRUD작업예정.
- 사용자단 메인페이지(대시보드) 작업예정. 


#### 20210701(목) 작업
- 스프링시큐리티 내장클래스에서 user_pw(admin1234)와 password(해시값)비교함수
passwordEncoder.matches(rawPassword(유저입력비밀번호), encodedPassword(해시값))
- 스프링시큐리티 로그인및 권한체크 설정 후 사용자단 로그인/로그아웃 기능 처리 OK.
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가예정.
- 관리자단 대시보드 작업예정.
- 사용자단 게시물관리 CRUD작업예정.
- 사용자단 메인페이지(대시보드) 작업예정.


#### 20210630(수) 작업.
- 댓글 Delete 구현 후 마무리
- 스프링시큐리티 로그인및 권한체크 설정 후 사용자단 로그인 구현 예정.(관리자단  끝이면서, 사용자단 시작)
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가예정.


#### 20210629(화) 작업.
- 댓글 VO제작 -> 매퍼쿼리제작 -> DAO클래스제작 -> Service클래스 제작-> @RestController클래스 제작/Delete 마무리 OK -> 크롬부메랑테스트 -> JSP제작(1페이지 CRUD처리 Ajax이용)
- jsp에서 1페이지 작업 $.ajax를 이용해서 RestAPI서버 사용.
- 스프링시큐리티 로그인및 권한체크 설정 후 사용자단 로그인 구현 예정.
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가예정.
- 댓글RUD는 모달(팝업)창에서 작업시작.
- $.ajax로 CRUD를 구현할때는 전송값은 json데이터(form태그가 필요없음, submit으로 안보냄)로 보내고,(form태그가 필요없음) : submit은 폼태그가 있을때만 작동되는 브라우저 내장명령입니다.
- RestAPI가 사용되는 곳 : 1페이지로 서비스가 이루어지는 곳에서 주로 RestAPI를 사용
- 데이터를 시각화하는 페이지에서 주로 사용 : 구글맵, 네이버지도 기반의 데이터를 시각화해서 수익창출 서비스.
- RestAPI가 스프링과 노드js 연동 : RestAPI 실시간으로 결과공유할 수 있게 만드는 것.
- 게시물 상세보기 페이지에는
- 게시물 관련내용 : 컨트롤러에서 보낸 model객체에 담긴 변수값을 jsp사용.
- 댓글 관련내용 : Rest컨트롤러에서 보낸 ResponseEntity객체에 담긴 변수값을 jsp사용.
- json데이터(1개 레코드=K:V 무제한 형태)가 자바의 List데이터객체(1개 레코드=K:V제한형)와 대부분 같음.  틀린점은 K:V 형태는 같으나 v값이 무제한, 제한


#### 20210628(월) 작업.
- 댓글 VO제작 -> 매퍼쿼리제작 -> DAO클래스제작 -> Service클래스 제작/여기까지. -> @RestController클래스 제작:일반 컨트롤러와 다르게 반환값이 body로 출력됨. -> 크롬부메랑테스트
-> JSP제작(1페이지 CRUD처리 Ajax이용)
- 스프링시큐리티 로그인및 권한체크 설정 후 사용자단 로그인 구현 예정.
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가예정.

- 네트워크로 자료를 전송하는 방식 : SOAP소프(구버전), REST레스트(최신,HTTP방식으로 헤더에 자료를 담아서 보내는 방식)
- Endpoint : 마이크로서비스는 RestAPI로 구현되고, 요청하는 URL을 엔드포인트라고 합니다. 트렌드이긴 하지만 쉽지않음. 기존 컨트롤러(게이트웨이)를 모두 RestController(Rest게이트웨이)로 변경이 필요하기 때문.
- 엔드포인트(URL주소)에는 데이터를 전송할때, 쿼리스트링으로 보내지 않는 방식이 트렌드
- 옛날방식 : /reply/reply_list?bno=59&page=1 -> @RequestParam 애노테이션으로 받음
- 요즘방식 : /reply/reply_list/{게시물번호값}/{페이지번호값} -> @PathVariable 애노테이션으로 받음
- 결과, /reply/reply_list/59/1 => 구글검색에 쉽게 노출시키기 위해서


#### 20210625(금) 작업.
- 게시물관리 Create 작업 마무리 OK.
- 관리자단 댓글관리 CRUD 처리(6.RestAPI서버구현,JUnit대신에 크롬부메랑으로 테스트)
- 댓글 VO제작 -> 매퍼쿼리제작 -> DAO클래스제작 -> Service클래스 제작 -> @RestController클래스 제작
-> 크롬부메랑테스트(JUnit테스트대신)->JSP제작(1페이지CRUD처리 Ajax이용)
- 고전CRUD와 RestFull(API)방식 차이점 : 고전(화면이 이동), RestAPI가 요즘대세(한개의 화면에서 CRUD 처리)


#### 20210624(목) 작업.
[복습]오늘 작업한 첨부파일 처리도 데이터 변수의 이동상태나 변수값이 제일 중요합니다.
핵심은 아래와 같습니다. Attach테이블에서 select쿼리 결과 테이터 구조는 아래와 같습니다.
List<AttachVO> delFiles = [
{"save_file_name":"abc1.jpg","real_file_name":"한글이미지1.jpg","bno":"bno10"},
{"save_file_name":"abc2.jpg","real_file_name":"한글이미지2.jpg","bno":"bno10"}
]
데이터베이스에서 가져올때, 위와 같이 구조가 발생됩니다. 구조를 정리하면 아래와 같습니다.
대괄호 List[VO배열] 안에 
중괄호 VO{1개레코드 } 안에
콜론으로 "키":"값" 구분 후 콥마, 로 멤버변수들을 구분합니다.

- file.getBytes() 설명 포함 board_update메서드 리뷰 후 수업진행.
- 작업순서: CRUD -> U 작업OK.
- Create작업: 
- update: updateBoard(서비스)참조 -> board_update(컨트롤러)작업+jsp작업
- 업데이트 이후엔 파일업로드 구현 후 /download 컨트롤러 실습OK.

한글파일명일때, IE브라우저에서 400에러발생됩니다. JSTL로 처리하면 해결 됩니다.(아래)
-- 수정전 소스
<a href="/download?save_file_name=${boardVO.save_file_names[idx]}&real_file_name=${boardVO.real_file_names[idx]}">
-- 수정후 소스
<c:url value="/download" var="url"> 
   <c:param name="save_file_name" value="${boardVO.save_file_names[idx]}" />
   <c:param name="real_file_name" value="${boardVO.real_file_names[idx]}" /> 
</c:url>
<a href="${url}">


#### 20210623(수) 작업
- 정방향으로 개발 시작. VO제작->매퍼쿼리제작 -> DAO클래스 제작 -> Service클래스제작 -> Controller+jsp
- 위 내용 중 게시물관리에서 CRUD 컨트롤러 + jsp처리(4.파일업로드구현)
- 작업순서 : RD OK -> C(Create),U(Update) 작업예정 
- 업데이트 이후 파일업로드 구현 후 download 컨트롤러 실습예정

- 시큐어코딩 방지메서드 : 목적은 코딩태그를 특수문자로 변경하는 메서드

- 세션 사용법(=겟(사용),셋(생성),삭제하는 방법)
- 세션 생성법 : session.setAttribute("세션변수명","값"); //로그인시 세션변수 생성.
- 세션 값불러오기 : session.getAttribute("세션변수명");
- 세션 삭제하기 : session.removeAttribute("세션변수명");
- 전체 세션삭제 : session.invalidate(); : 전체 세션변수명을 삭제 = 세션초기화 = 로그아웃시 사용.

- jsp=>컨트롤러로 @RequestParam("bno") 단방향
- 컨트롤러->jsp로 Model model 단방향
- jsp<=>컨트롤러  @ModelAttribute("pageVO") PageVO pageVO 양방향
- 단, redirect는 Model로 담아서 보낼 수 없음. redirect에서는 Model대신에 RedirectAttribute 클래스를 이용해서 jsp값을 보냅니다.

#### 20210622(화) 작업
- 정방향으로 개발 시작. VO제작->매퍼쿼리제작 -> DAO클래스 제작 -> Service클래스제작 -> Controller+jsp
- 위 내용 중 게시물관리에서 CRUD 컨트롤러 + jsp처리(4.파일업로드구현)
- 작업순서 : RUD -> C (R:ReadBoard;상세보기) readBoard(서비스)참조 -> board_view(컨트롤러)작업
- web.xml에 프로그램실행순서가 나타난다. 그런데 AOP를 가장 먼저 실행되는 root-context에 두어서 실행이 안됐다.
- AOP와 연결된 controller가 있는 servlet-context에 놨어야 했다. 그래서 해결됨.
- 에러상황 : IE11이하 계열에서 검색 후 페이지 선택시 400에러발생(크롬,엣지는 이상없음)
- 스프링시큐리티 : 로그인정보가 발생=세션, 즉 로그인정보(세션)가 없으면, 홈페이지로 가도록 처리 예정.

#### 20210621(월) 작업.
- 관리자단 게시물관리 CRUD처리 (4.파일업로드구현, 5.트랜잭션구현 OK).
- root-context와 servelet-context설정파일에 트랜잭션과 파일업로드설정처리 OK
- @Service 클래스 마무리 OK
- @Controller 클래스 추가 & jsp화면처리
- @Service 트랜잭션 기능 추가.
- @Aspect 기능 마무리.
- 관리자단 댓글관리 CRUD 처리(6.RestAPI서버구현,크롬부메랑으로 테스트)
- 트랜잭션 : 여러개의 메서드를 1개의 메서드처럼 처리하게 구현하는 애노테이션(인터페이스)을 사용.
- 트랜잭션 목적 : 데이터 무결성 유지를 위해
- 1단어로 표시하면 All or Not All(모두 실행되던지, 모두 실행되지 않던지)
- AOP기능 중 Aspect기능의 설정은 servlet-context.xml에 위치필수



#### 20210618(금) 작업.
 게시물관리 시작 : 다중게시판이란 1개 페이지에 board_type변수를 이용해서 공지사항, 갤러리 등등 여러개의 게시판을 구별해서 사용.(쿼리스트링이 길어져서 @Aspect로 사용)
- 검색처리는 멤버쿼리에서 작성한 내용 붙여넣고, 다중게시판용 필드조회조건 board_type 추가.
- 정방향으로 개발 시작. VO제작->매퍼쿼리제작 -> DAO클래스 제작 -> Service클래스제작.
- 상황1:두사람이상이 동시에 글을 쓴다 -> 모두 첨부파일을 추가하는 상황
- 실행순서 : 사람1 insertBoard->bno(101)->첨부파일 insertAttach -> bno필요 
- 사람2 insertBoard->bno(102)->사람1이 사람2의 bno를 가져다 사용하는 경우는?
- 해결책1 : @Transantional을 insertBoard매서드를 감싸주면, 간단하게 해결.
- 해결책2 : insertBoard쿼리에 return 값을 bno받아서 insertAttach를 실행하게 처리.
- @Service까지는 DB(테이블) CRUD합니다.
- 그러면, 첨부파일은 @Controller에서 업로드/다운로드 로직 처리. 여기가 코딩이 제일 지저분함.



#### 20210617(목) 작업.
- UI디자인 과제물제출 4교시.
- [복습] : 스프링의 기능 IoC(제어의 역전:객체의 메모리관리 개발자가안함, 스프링이 대신), DI(의존성 주입, @Inject)
- 관리자단 게시판 생성관리 RU 페이지 마무리 예정.
- (문제점) 관리자단 왼쪽메뉴에 게시판종류가 동적(실시간)으로 출력이 되야하는데, 지금은 게시판 생성관리 목록페이지에서만 보임.
- 위 문제를 해결방식으로 AOP기능을 사용.
- 스프링 AOP(Aspect Oriented Programing)구현은 3가지 방식: @Aspect, @ControllerAdvice, intercept(가로채기) 태그사용. 관점지향프로그래밍을 구현.

- AOP용어: 관점지향?-프로그램전체에 영향을 주는 공통의 기능 적용하는 패턴 기법.
- AOP용어: Advice(충고-간섭):프로세스작업 중간 필요한 기능을 끼워넣는 것을 어드바이스 라고 함.
- Advice : 포인트컷(충고-간섭,필요한 기능을 끼워넣는 시점, @Before, @After, @Around실습)
- 게시판종류 출력: @ControllerAdvice로 구현.(게시판생성관리CRUD작업시 실습)
- @ControllerAdvice 실행조건: 컨트롤러 클래스의 메서드에만 Advice(간섭) 가능.
- 검색시 pageVO처럼 board_type을 값을 계속 유지하는 기능: @Aspect로 구현.(게시물관리CRUD작업시 실습)
- @Aspect 실행조건: 컨트롤러 에 더해서 서비스(실습),DAO클래스의 메서드에도 Advice 가능.
- @Aspect 장점 : 특정클래스의 특징메서드실행시 자동실행되는 메서드를 지정이 가능.
- 보안-로그인체크,권한체크시 : intercept(스프링시큐리티)태그를 사용해서 구현.(로그인기능,권한체크기능구현시 실습)
=============================================================================
- intercept태그는 스프링시큐리티에서 관리.
- 관리자단 게시판 생성관리 CD 처리 OK.(3.스프링의 AOP기능구현).
- UI디자인 과제물제출 4교시 예정.
- 관리자단 게시물관리 CRUD 처리(4.파일업로드구현,5.트랜잭션구현).
- 게시물관리 시작 : 다중게시판이란 1개 페이지에 board_type변수를 이용해서 공지사항, 갤러리 등등 여러개의 게시판을 구별해서 사용.
- 정방향으로 개발 시작. VO제작->매퍼쿼리제작



#### 20210616(수) 작업.
- [공지]06-17 목요일(4교시) UI 디자인 시험 있습니다.(화면기획서XLS제작, 화면설계서PPT제출용) 확인 후 수업진행.
- 관리자단 게시판 생성관리 CRUD 처리.(3.AOP기능구현).
- 10,20년 지나도 변하지 않는것은 변수값의 흐름이다. 정방향 개발 시작
- 시작.VO -> 매퍼쿼리xml -> DAO클래스 생성 -> Service클래스 생성 -> 컨트롤러 -> jsp화면처리


#### 20210615(화) 작업.
- 관리자단 회원관리 수정 암호 수정 잘 되는지 확인OK.
- 회원관리 CRUD 화면 JSP구현 update(OK),delete(OK),insert(OK)


#### 20210614(월) 작업
- multipart(첨부파일기능) 라는 폼태그 전송방식을 추가 -> commons.fileupload 외부모듈필수(pom.xml에서 의존성을 추가합니다.)
- 위 외부모듈을 스프링 빈으로 등록합니다.(servlet-context.xml 하단에 추가)
- CRUD에서 multipart를 사용한다면, 리졸브(resolve-해석기) 스프링빈이 필요

```
<beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
   </beans:bean>
```
- 데이터변수를 전송할때 GET(URL쿼리스트링)으로 전송받으면, 타 도메인에서도 GET으로 접근이 가능합니다.
- 쉽게 말하면, 다른 도메인(서버)에서 GET은 검색만 가능, 입력/수정/삭제 불가능
- 단, 데이터변수를 POST(숨김)으로 전송받으면, 타 도메인에서는 접근이 불가능합니다.
- 쉽게 말하면, 같은 도메인(서버)에서만 POST로는 입력/수정/삭제 가 가능
- 웹프로그램은 보안때문에 외부도메인에서 컨트롤러에서 지정한 GET방식의 URL로 접근해서 데이터출력이 가능.
- 단, 입력수정삭제 기능은 자바컨트롤러클래스 GET방식으로 못하고, POST방식으로 지정 -> 다른 도메인(서버)에서 회원가입을 못하도록 서버단에서 에러를 발생시킴.
- GET : Insert(외부사이트에서도 입력 가능) - 쿼리스트링으로 데이터전송 url?key=value&key2=value2
- POST : Insert(외부 사이트에서 입력 불가능, 같은 사이트의 입력폼에서만 가능) form의 입력태그(히든스트링)으로 데이터전송
- 나머지 관리자 회원관리 CRUD 화면 JSP구현 update(OK), delete(OK)


#### 20210611(금) 작업
- 정방향개발(앞으로 이렇게 진행) : 매퍼쿼리 -> DAO -> Service -> JUnit 컨트롤러클래스 -> JSP
- 역방향개발 : 정방향으로 개발한 것을 검증용으로만 하기
- JSTL : Java Standard Tag Library 자바표준태그모델로서 JSP에서 자바를 사용하는 표준
- 관리자단 회원목록 처리 마무리(1.페이징 및 검색기능구현)
- model을 이용해서 결과를 JSP로 구현(2. JSP화면은 표준언어인 JSTL로 구현)
- 나머지 관리자 회원관리 CRUD 화면 JSP처리. view -> update -> insert

- taglib uri(uniform resource identifier-의미가있는 고유값=식별값) > url(링크경로) uri가 더 많은 정보를 포함
- prefix(접두어) : 태그 별칭으로 사용 <c 시작.
- c:out 태그는 해킹방지용이다.


#### 20210610(목) 작업
-자바에서 객체가 공백 또는 비었는지 비교할때, 예를들면, 우리프로젝트에서 첨부파일이 있는지 비교할때 아래처럼 사용하지 않고
- if(save_file_name !=null && "".equals(save_file_name))
- 다음처럼 짧게(널과 공백체크를 한번에) 사용합니다.(아래)
- if(!save_file_name.isEmpty()) 게시판 첨부파일 체크시 사용예정
- GTM시간(그리니치천문대기준-표준시)-KST한국시간과는 9시간 차이.
- DB서버에 타임존설정 Asia/Seoul 되어있으면 그냥 사용.
- 만약 위 GTM+9시간해서 insert, update 한국시간으로 사용.
- 오라클일때 확인 : SELECT TO_CHAR(systimestamp + numtodsinterval( 9, 'HOUR' ), 'YYYY-MM-DD HH24:MI.SS.FF4')  from dual;
- Mysql(마리아dB)확인 : SELECT DATE_ADD(NOW(3), INTERVAL 9 HOUR);
=====================================================================
- 업데이트 실습은 회원암호를 스프링시큐리티5 암호화(1234->해시데이터)로 일괄변경 실습예정.
- 정방향 암호화 가능, 역방향 복호화는 불가능(JAVA용 스프링시큐리티암호화, DB용 MD5 등등)

- 컨트롤러 이용해서 관리자단 회원관리화면 JSP 만들기 진행시작.
- JUnit 마치고 관리자단 회원관리(CRUD) jsp까지는 과장급들이 만들어줌. 아닐수도있음. 이후 앞에 내용을 참조해서 확장해 나가는 작업이 이어진다.


#### 20210609(수) 작업
- sql디벨로퍼 : select now() => 현재시간, 연월일시분초 select now(3) : 1000분의 1초까지
- 프로젝트 진행 : 보유기술 70% + 신기술30%(개발사도 모룸)
- 스타트업은 보유기술이 별로 없고 신기술을 많이 시도해서 실패를 많이 함.
- <![CDATA[쿼리]]> : 태그 안쪽에 부등호를 사용하기 위해서 문자열 변환 태그를 사용.
-쿼리에서 변수와 문자열과의 연결할때는 +는안됨(자바방식), ,(안됨) ||파이프라인으로 변수와 문자열을 연결
- Junit에서 회원관리 나머지 CRUD 테스트 진행. 암호화도 실습예정
- 컨트롤러 이용해서 관리자단 회원관리화면 JSP만들기 진행예정.

#### 20210608(화) 작업
- CRUD 중 조회가 할게 많은데, 이번주는 조회기능 중 검색기능, 페이징처리기능을 실습할 예정.
- 스프링코딩 작업순서 1부터6까지(아래)
- 1. ERD를 기준으로 VO클래스를 생성.
- M-V-C 사이에 모델과 뷰사이, 뷰와 컨트롤사이 서로의 사이사이에 데이터를 입출력하는 임시저장 공간(VO클래스-멤버변수+Get/Set메서드) 생성
- 보통 ValueObject클래스는 DB테이블과 1:1로 매칭이 됩니다.*오늘 MemberVO만듬
- 2.매퍼쿼리(마이바티스사용)를 만듭니다.(VO사용해서쿼리생성)-내일만들예정
- 3.DAO(데이터엑세스오브젝트)클래스를 생성(SqlSession사용쿼리실행).*오늘 Sql세션은 root-context에 빈으로 만들었습니다.
- IF인터페이스는 만드는 목적 : 복잡한 구현클래스 간단하게 구조화 시켜서 개발자가 관리하기 편하게 정리하는 역할, 기사시험 책에는 캡슐화 구현과 관련(알약 캡슐 - 안에 어떤 약이 있는지 모르게 먹게하기) 프로그램에서도 캡슐화는 구현내용을 몰라도, 이름만 보고 사용하게 만드는 것.
- 스프링부트(간단한 프로젝트)에서는 4번 Service클래스가 없이 바로 컨트롤러로 이동합니다.
- DAO가 3개이상이면 Service로 하나로 묶을 수 있다. 그래서 서비스가 필요없어보일 수 있지만 여러개면 하나로 묶을 필요성이 있다.
- 4.Service(서비스)클래스생성(서비스에 쿼리결과를 담아 놓습니다.)
- 게시물을 등록하는 서비스1개(tbl_board-DAO1개, tbl_attach-DAO2개)
- Junit에서 위 작업한 내용을 CRUD테스트(선배작업) -> 대리,사원에게 
- 5.Controller(컨트롤러)클래스생성(서비스결과를 JSP로 보냅니다.)
- 6.JSP(View파일) 생성(컨트롤러의Model객체를 JSTL을 이용해 화면에 뿌려 줍니다.)

- Ctrl+스페이스바 누르면 입력힌트가 나온다.

- 페이징에 사용되는 변수(쿼리변수+VO변수) 아래
- queryStartNo, queryPerPageNum, page, perPageNum, startPage, endPage
- 검색에 사용되는 변수(쿼리변수만) : 검색어(search_keyword), 검색조건(search_type)

```
--SQL쿼리 페이징을 구현해서 변수로 삼을것을 정의
--PageVO의 멤버변수로 사용예정
select TB.* from
(
    select ROWNUM as RNUM, TA.* from
    (
        select * from tbl_member
        order by reg_date desc
        where user_id like '%admin%'
        or user_name like '%사용자8%'
    ) TA where ROWNUM <= (1*5)+5
) TB where TB.rnum > 1*5 
--페이징쿼리에 필요한 변수는 2개
--현재페이지수의 변수 a*b == page * b == queryStartNo
--1페이지당 보여줄개수의 변수 b == queryPerPageNum
--PageVO에서 필요한 추가변수 : page
--UI 하단에 페이지 선택번호 출력할때 사용하는 변수(아래)
-- perPageNum 변수를 받아서 startPage, endPage를 구해서
--하단의 페이지 선택 번호를 출력
```



#### 20210607(월) 작업
- 책 스프링 웹프로젝트는 개발 STS(스프링툴슈트) 툴.
- 우리가 하는 책 스프링 웹프로젝트는 개발 전자정부표준프레임워크 개발 툴. => 기반은 이클립스 기반을 확장
- 오라클 기초이론 마무리 : 시퀀스(스프링에서 사용-AI자동증가기능)
- Junit CRUD실습.
- 오라클의 DB관리 로그인정보 : Application Express웹프로그램 = admin/Apmsetup1234%(암호변경됨, 대문자추가+특문추가)

```
-- SQL디벨로퍼에서 system으로 로그인 후 아래 쿼리를 이용해서 XE2사용자를 완벽하게 지우는명령.(아래)
select * from all_users;
-- all_users는 테이블이 아니다. 시노님(동의어)
delete from all_users where username='XE2';
-- 시노님=테이블명이 사용이 힘들정도로 길거나 오라클은 스프링과 같은 방식
-- 패키지명 안에 함수
--프로시저(오라클프로그램)를 만들 수 있습니다.
--패키지명이 길어서 사용시 개발자에게 부담됨.
--위 상황을 해결하도록 만든것이 시노님(동의어)임.
DROP USER XE2 cascade; --XE2사용자를 지울때,
--XE사용자의 테이블까지 모두 지우는 명령
--CSS(Cascade계층형 Style Sheet)
```

- 현업에서는 오픈소스인 mysql(마리아DB)를 사용할 기회가 더 많다. - 개발자가 많은 편-
- 오라클은 납품시 SW비용이 산정되어서 사용할 기회가 없지만, 개발자가 상대적으로 적은 편.
- 보통 학교, 시청, 기업체 전산실에 부탁을 해서 XE(기타)사용자를 추가해달라고 요청해서, 발급받은 계정으로 개발을 시작
- 시노님(긴 객체를 개발자가 타이핑하기 어려워서 만든 단축 이름) ex) sys.dual -> dual 단축이름으로 사용가능
- 만약 시퀀스(AI)기능이 없다면, 게시물 작성시 첫번째, 두번째 게시물 등등을 구별하는 숫자를 계속 추가하려면, 현재까지 저장된 게시물의 최고번호값(Max)을 구해서 +1을 해야합니다.(개발자가 Insert시)
- DB에서 +1하는 로직을 만들게 됩니다. 이 기능이 시퀀스(AI)입니다. 
- 우리 스프링프로젝트에서는 2개 시퀀스를 만든다.(게시물시퀀스  SEQ_BNO, 댓글 시퀀스 SEQ_RNO)
- 오라클 : 더미데이터 일괄등록 예정. 회원관리(100명), 게시판관리(공지50개, 갤러리50개)
- 위 더미데이터는 프로시저(함수)라는 DB프로그램 방식으로 추가합니다.
- SQL프로시저를 하고난후에 마지막에 꼭 commit;을 집어넣어야한다. 꼭!! 안그러면 데이터가 전송이 안됨.

- 스프링에서는 마이바티스를 이용해서 쿼리를 관리하게된다.
- 이전에는 자바코드(jsp코드)안에 쿼리를 만들어서 프로그램을 제작하였음 -> 문제점(너무 복잡하고 뒤죽박죽임 : 스파게티코딩,짬뽕코딩)
- MVC로 분리 : Model부분의 SQL쿼리를 분리하도록 기능을 추가한 것이 마이바티스 다.
- 마이바티스(mybatis): 형은 아이바티스(ibatis) 지금은 안씀. 현재는 마이바티스만씀
- 마이바티스 나오기전에는 쿼리를 모아서 작업을 프로시저로 만들어서 작업을 했다.
- 지금은 스프링으로 옮기지 못한 프로그램만 제외하고는 대부분 마이바티스로 쿼리를 관리한다.
- 그래서, 마이바티스 메이븐 세팅을 한다.
- 마이바티스 추가 순서 : 
- 1.pom.xml에 의존성 추가
- 2.마이바티스 설정파일 추가(쿼리를 저장할 위치지정, 파일명 지정)


#### 20210604(금) 작업
- junit테스트시 SQL에러를 디버그하는 방법은 jdbc드라이버 -> log4jdbc드라이버 바꾸면, SQL에러가 나오게 됩니다.
- junit에서는 SQL에러가 보입니다. 콘솔창에서는 보이질않음. 콘솔창에서 SQL로그상황이 나오게하는 드라이버를 pom.xml에 추가한다. 
- 오라클일때 : localhost:1521/XE 접속 URL끝의 XE는 서비스ID명이다. 
- 오라클은 사용자명(XE)이 스키마명(DB명)이다. 사용자(XE2)워크스페이스를 추가하면 테이블스페이스(DB)가 새로 만들어집니다. (구버전은 데스크탑프로그램 -> 지금은 웹프로그램 사용자추가/DB(테이블스페이스)추가)
- Mysql(마리아DB) : localhost:3306/XE URL끝의 XE는 스키마명(DB명)


#### 20210603(목) 작업
- Junit테스트 : 오라클 연동한 후 회원관리부분 CRUD 테스트 진행
- admin부터 프로그램 작업 시작예정.
- 스프링에서 오라클 연동방법 2가지
1. jdbc(java database connection) 확장모듈 pom에 추가
2. 오라클 접속 드라이버 확장 모듈을 pme에 추가 X, 직접 jar파일을 추가.
- root-context.xml 파일에 오라클 커넥션 빈(스프링 클래스)을 추가.
- 스프링이 관리하는 클래스를 추가하는 방법2가지 : 1. @Controller, @Repository, @Service, @Component
- 2. context.xml에서 빈(bean태그)을 추가하면, 스프링 클래스가 됨.


#### 20210602(수) 작업.
- views/home/index.jsp 엑박처리도 후 분해(header.jsp, footer.jsp, index.jsp)
- admin 폴더 만든이후 분해함. Junit 실습 후 작업
- 4장 패키지와 예외처리 실습.
- ERD제작 -> html제작 -> jsp제작(현재진행: 관리자단부터 작업, 그다음 사용자단 작업),  관리자단에 10개의 기능이 있다면 사용자단에는 5개 그 이하까지만 보여준다. 그래서 관리자단 먼저한다.

- 일반홈페이지(카페24)-URL직접접근 가능 (보안위험)
- MVC웹프로그램 차이점-URL 직접접근 불가능 (상대적으로 보안위험낮음)-관공서, 대학, 은행 주사용
- MVC프로젝트에도 직접접근이 가능한 resources폴더 직접접근 가능 - static콘텐츠(html, css, js)를 모아놓은 폴더 .views폴더 jsp는 직접접근이 불가능
- views폴더처럼 직접접근이 불가능한 컨텐츠는 Controller(라우터 역할)로 접근하게 된다.
- Controller클래스 + home/index.jsp(화면) 한쌍이다. 그래서 Controller클래스에서 만든 변수를 index.jsp에서 사용가능하다. 
- ex) 안드로이드앱 = 액티비티(java) + 레이아웃(xml)화면
- ex) C#닷넷 = test.aspx.cs(프로그램) + test.aspx(화면) 한쌍
- 패키지는 관련기능을 한곳에 모아서 개발자 관리하기 편하게 하기 위해서 구분한 이름(폴더명)
- 예외처리: 에러발생시 프로그램이 중지되는 것을 방지하고, 계속 프로그램을 사용할 수 있도록 처리하는 방법=Exception
- 스프링앱에서는 예외(에러)정보를 스프링 처리합니다.
- 예외처리: Throwable 오브젝트가 실행시 에러가 발생하면, 예외(내용)를 보낼때(던질때) 사용하는 클래스 Throwable클래스 입니다.
- 쓰레드:thread, 실행되는 프로그램을 명시, 1개 프로그램에는 보통 1개 쓰레드가 실행(우리소스는 여기까지), 특이한 경우(http웹네트워크 프로그램인경우-게시물 다운로드버튼 다운로드 100G 쓰레드가실행 되면서, 리스트버튼을 클릭했을때 목록보기 쓰레드가 동시에 실행-안드로이드앱에서필요) 여러개 쓰레드 실행이 필요한 있습니다.
- 예외처리하는 목적: 에러상황에서도 프로그램이 중단되지 않도록 하는 것이 주목적(에러상황을 세련되게 넘기게 처리)
스프링에서는 개발자가 처리하는 100의코딩에서 2-3개정도(파일업로드,다운로드)이고, 대부분 throws Exception 스프링으로 넘김.(예외의 전파)
- Junit(Java unit Test) : 자바 단위 테스트(서비스프로그램 만들기 전 프로토타입 시제품 제작) Junit CRUD 작업 후 본격작업시작
- Logger의 레벨 : DEBUG > INFO > WARN > ERROR > FATAL
- Logger의 장점 : 조건에 따라서 출력을 조정할 수 있다. 그래서 개발시에는 레벨을 높여 작동이 잘 되는지에 대한 테스트를 다 해보는 것. 납품시에는 레벨을 낮춰 테스트시 사용한 테스트문구들이 안나오게 조정한다.
- 개발할때 : DEBUG로 Logger레벨을 설정
- 납품할때 : WARN으로 Logger레벨을 변경


#### 20210601(화) 작업
- 관리자단 AdminLTE적용-스프링시간|UI 디자인시간 선택 후 아래 작업 진행 예정
- 오늘부터 VS code -> 이클립스에서 작업.
- 회원관리CRUD-jsp, 게시판생성관리CRUD-jsp
- html을 분해(1개의 페이지를 2개로 분해. 1.header.jsp(메뉴공통), 2.footer.jsp(공통))
- 이클립스로 작업한 html 가져가서 -> resources 폴더(admin,home,root파일까지 복붙)로 배치
- 프런트 개발자에게 작업결과를 받아서 배치하는 것이 백엔드개발자의 역할.
- resource폴더에 static콘텐츠(html,css,js,font) 배치합니다.
- 프런트 개발자가 작업한 결과를 백엔드 개발자가 확인하는 과정.
- 일괄바꾸기 1.  "/home -> "/resources/home
- 일괄바꾸기 2.  '/home -> '/resources/home 
- 스프링이 관리하는 클래스 : 빈. 빈의 종류 3가지 (사용법 : 개발자가 만드는 클래스 상단에 입력)
- 1.@Controller 클래스 빈 - 라우터 역할 메서드를 만듭니다.(라우터 역할의 클래스)
- 2.@Service 클래스빈  - 비즈니스로직 메서드를 만듭니다.(개발자위주의 외부클래스 기능)
- 3.@Repository 클래스빈 - Model처리 메서드를 만듭니다.(DB핸들링 외부클래스 사용)
- 위의 3가지 @를 사용하지 않는 클래스는 스프링의 IoC(제어의역전-메모리자동관리), AOP(관점지향프로그래밍), DI(객체생성=의존성주입) 이러한 기능들을 쓰지 못한다.
- 언어를 배우는 순서 : 주석 > 디버그하는방법 > 변수 > 메서드(함수) > 클래스 > MVC모델
- 디버그하는 방법 : 자바(system.out 이용해서 콘솔창에 출력)
- 스프링에서는 logger를 사용한다.
- Controller클래스에서 생성한 변수 사용(자바)model객체전송(스프링) ->home.jsp 출력
- 출력하는 방식 : EL방식출력 ${변수} 오래된방식
- 많이사용하는 방식 : JSTL방식(*표준)-출력,반복,조건 등등 다양한 방식
- 프로젝트의 버전을 올립니다. -> 외부라이브러리 부분의 버전을 올림 - 메이븐 컴파일러 -> pom.xml에서 버전을 관리. 메이븐은 수정 및 추가 -> maven 메뉴에서 maven update 메뉴사용
- 에러 : 404(file not found 경로이상일때), 500(자바프로그램 에러)

#### 20210531(월) 작업
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
