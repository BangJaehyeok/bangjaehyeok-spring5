package kr.or.test;

public class Homework {

	public static void main(String[] args) {
		int sumall = 0; //sum을 숫자형으로 정의
	
		for(int i=0;i<=100;i++) //for반복문으로 1~100까지 출력
			sumall += i; //i를 모두 더해줌
		System.out.println(sumall); //i를 모두 더한 sum값을 출력
		
	}
}
