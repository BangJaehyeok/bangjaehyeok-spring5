package kr.or.test;

import java.util.function.IntSupplier;

import com.edu.service.IF_MemberService;

/*
 * 이 클래스는 람다식(애로우메서드)을 테스트하는 클래스입니다.
 * 방재혁
 */
public class Lambda {

	//스태틱 메서드 1: 이 클래스를 컴파일하면, 메모리에 자동으로 등록되는 메서드, 클래스를 말한다.
	//스태틱을 지정하지않으면, 런타임(호출)시 메모리에 등록이 됨
	//스태틱 메서드 2 : 이 클래스에서만 메서드로 사용하겠다고 명시하는 것. 상속주거나 받거나X
	public static int plus(int x, int y, String lambda) {
		int result = 0;
		//람다식 적용 전
		if(lambda.equals("nonLambda")) {
			IntSupplier2 intSupplier = new IntSupplier2() {
				@Override
				public int getAsInt() {
					int sum = x + y;
					return sum;
				}

				@Override
				public int getAsInt2(int x, int y) {
					int product = x*y;
					return product;
				}
				
			};
			result = intSupplier.getAsInt();
		} 
		//람다식 적용에는 제약이 있음. 클래스의 메서드가 1개인 객체만 가능.
		//(o) -> o.getAuthority().equals("ROLE_USER")
		//(매개변수) -> ~ 자바일때 형식, 람다식 익명함수에서 매개변수 1개일때는 괄호()생략가능.
		//(무명함수) -> ~ 자바스크립트일때 람다식
		if(lambda.equals("lambda")){
			IntSupplier intSupplier = () -> x+y; //뒤 쪽항부터 해석 x+y를 실행합니다. 
			/*
			 * { int sum = x * y; return sum; };
			 */
			result = intSupplier.getAsInt();//객체의 메서드가 2개 이상일때 여기서 선택
		}
		return result;
	}
	
	public static void main(String[] args) {
		// plus메서드를 호출
		System.out.println("람다식 적용전 메서드 결과: " + plus(3,4, "nonLambda"));
		System.out.println("람다식 적용후 메서드 결과: "+plus(3,4,"lambda"));
	}

}
