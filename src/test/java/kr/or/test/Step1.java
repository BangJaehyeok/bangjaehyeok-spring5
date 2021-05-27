package kr.or.test;
/**
 * 내부변수와 배열 사용에 대해서 실습하는 클래스
 * @author 방재혁
 *
 */
public class Step1 {
	public void Step1() { //클래스명과 같은 이름의 메서드를 생성자 메서드. 자동으로 만들어진다.
		//중요한 이유는 다른 클래스에서 Step1을 객체로 만들때 생성자 메서드가 필요.
		//Step1 aaa = new Step1();
		//자바앱에서는 객체를 생성자 메서드로 만들때 필수
		//단, 스프링에선 @Inject으로 객체를 만들어서 사용.
	}
	//멤버변수(전역변수)는 Step1클래스 전체에 영향을 모두 주는 변수
	//필드변수(내부변수)는 main메서드 내부에서만 영향을 주는 변수
	//private String name;//멤버변수 사용예 Step2 클래스에서 사용예정.
	public static void main(String[] args) {
		// name, age, phoneNum 필드변수 사용
		String name;//문자열 이름 입력받는 변수
		int age;	//정수형 나이 입력받는 변수
		String phoneNum;//문자열 폰번호 입력받는 변수
		//변수에 값을 입력(아래)
		name = "방재혁";//문자열은 무조건 큰따옴표, 작은따옴표면 안됨.
		age = 10;//자바스크립트보다는 자료형 사용에 엄격하다.
		phoneNum = "000-0000-0000";//;은 한 문장의 끝을 명시
		printMember(name, age, phoneNum);//프린트멤버 메서드를 호출(아규먼트1, args2,args3))
		name = "성춘향";
		age = 18;
		phoneNum = "111-1111-1111";
		printMember(name, age, phoneNum);
		name = "각시탈";
		age = 28;
		phoneNum = "222-2222-2222";
		printMember(name, age, phoneNum);
		
		//배열을 이용해서 입력을 더 편리하게 변경한다.(아래)
		String[] names = {"홍길동","성춘향","각시탈"};
		int[] ages = {10,19,58};
		String[] phoneNums = {"333-3333-3333","555-5555-5555","111-1111-1111"};
		printMember(names, ages, phoneNums);//장점 : 메서드를 1번만 호출하면 3개가 모두 출력
		//메서드명이 같은데, 로드된 파라미터가 다른 메서드를 호출하는 것.= 오버로드
	}

	private static void printMember(String[] names, int[] ages, String[] phoneNums) {
		// for 반복문으로 3명을 한번에 출력
		int dataLength = names.length;
		for(int i=0;i<dataLength;i++) { //고전적 반복문
			System.out.println("입력하신 회원의 이름은 "+names[i]+"님, 나이는 "+ages[i]+", 폰번호는 "+phoneNums[i]);
		}
	}

	private static void printMember(String name, int age, String phoneNum) {
		// printMember(파라미터1, param1, param2,...)
		System.out.println("입력하신 회원의 이름은 "+name+"님, 나이는 "+age+", 폰번호는 "+phoneNum);
		// println 메서드는 출력 후 줄바꿈 명령실행 ln=new line
		
	}

}
