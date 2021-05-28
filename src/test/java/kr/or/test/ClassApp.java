package kr.or.test;

/**
 * 1. 이 클래스는 클래스 상속(extends)에 대한 연습, 2. 오브젝트(객체)생성에 필요한 new 키워드(예약어) 생성자메서드 사용연습
 * 3. 추상클래스(Abstract) 대한 연습
 */

//오브젝트생성에 필요한 new 키워드 생성자메서드 사용한 실습클래스(아래)
class Circle {
	private int r;// 원의 반지름으로 쓸 변수
	// 원을 만드는 메서드(아래)
	// public Circle() {} 만들지 않아도 자동으로 만들어집니다.

	public Circle(int radius) {
		r = radius;// Get|Set중에 셋 저장처리
	}

	// 원의 넓이를 반환(리턴)값의 크기가 더블형(아래)
	public double getCircle() {
		double result = r * r * 3.14;// 원의 넓이를 구하는 공식
		return result;
	}
}

//클래스 상속에 대한 연습(아래)
class Employee { // 고용(사원,취업)
	int mSalary;// m은 멤버변수
	String mDept;// 사원 부서 변수

	public void doJob() {
		System.out.println("환영합니다. 직원 여러분!");
	}
}

class Salesman extends Employee {// extends확장 -> 상속
	public Salesman() { // 클래스명과 똑같은 이름의 메서드가 생성자 함수입니다.
		// 자동으로 생성되는데, 개발자가 커스터마이징할때 생성자함수를 만든다.(아래)
		mDept = "판매 부서";// 부모클래스의 변수를 상속받아서 사용가능.
	}

	public void doJob() {
		System.out.println("환영합니다. " + mDept + " 여러분!!!");
	}
}

class Development extends Employee {
	public Development() {
		mDept = "개발 부서";
	}

	public void doJob() {
		System.out.println("환영합니다. " + mDept + " 여러분!!!");
	}
}

//추상클래스 구현 코딩 실습(아래)
abstract class GraphicObject {
	int x, y;

	abstract void draw();// 추상클래스, 추상메서드는 구현내용이 없고, 메서드명만 존재한다. 추상메서드를 만드는 이유는 메서드가 수십,수백개 이상일때 개발자가 구현할때
							// 어려움이 있다. 위 문제를 해소한 기능이 추상클래스. 다수의 메서드를 이름만 모아서, 유지보수가 편리하게 처리하는 방식.
}

//추상클래스를 사용하는 방법(아래)
//스프링에서는 추상클래스보다는 인터페이스를 더 많이 사용한다. 사용의도 비슷.
class Triangle extends GraphicObject {// 추상클래스를 상속받은 메서드는 구현메서드가 있어야만 오류가 나지 않는다. 현재 Triangle은 부모에게 GraphicObject를 상속받은 상태. 삼각형 그리기

	@Override // 부모클래스(슈퍼클래스)의 메서드를 재정의 : 오버라이드 / 오버로드(메서드명이 같은데, 로드된 파라미터가 다른 메서드를 호출하는 것
				// step1.java확인)
	void draw() {
		// 삼각형을 만듬
		System.out.println("  *  ");
		System.out.println(" * * ");
		System.out.println("*****");
	}

}
class Rectangle extends GraphicObject {
	//사각형 만들기
	@Override //오버라이드 @애노테이션이 있으면, 상속이라는 말.
	void draw() {
		System.out.println("*****");
		System.out.println("*****");
		System.out.println("*****");
		System.out.println("*****");
		
	}
}
public class ClassApp {

	public static void main(String[] args) {
		//추상클래스를 이용해서 오버라이드 메서드를 사용(아래)
		GraphicObject triangleObject = new Triangle();//추상클래스는 new 키워드로 객체를 만들지 못하기 때문에 그래서 상속받은것을 바로 객체로 
		GraphicObject rectangleObject = new Rectangle();
		triangleObject.draw();
		rectangleObject.draw();
		// 입력한 반지름의 원의 넓이를 구하는 오브젝트를 생성(아래)
		Circle circle = new Circle(5);// 반지름이 5인 원의 넓이를 구하는 객체(오브젝트)생성
		System.out.println("원의 넓이는 " + circle.getCircle());
		circle = null;// 오브젝트 메모리 초기화(반환)
		// 클래스 상속과 메서드 실행 실습(아래)
		Employee employee = new Employee();
		Salesman salesman = new Salesman();
		Development development = new Development();
		employee.doJob();
		salesman.doJob();
		development.doJob();
	}

}
