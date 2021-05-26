#### 20210526(수) 작업예정
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
