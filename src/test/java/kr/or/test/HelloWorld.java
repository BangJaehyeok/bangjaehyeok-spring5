package kr.or.test;

import java.util.Arrays;

public class HelloWorld {

	public static void main(String[] args) {
		// main 자바프로그램의 진입 메서드(자동실행)입니다.
		System.out.println("헬로 자바!!!");
		int[] questions= {1, 5, 4, 3, 2};
		Arrays.sort(questions);		
		System.out.println(Arrays.toString(questions));
		
	}
}
