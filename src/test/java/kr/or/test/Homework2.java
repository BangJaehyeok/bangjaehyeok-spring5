package kr.or.test;
//짝수와 홀수를 출력해보자.
public class Homework2 {
	int a,b;
	public static void main(String[] args) {
		
		for(int i=1;i<=100;i=i+2) //for반복문으로 100까지 숫자 중 홀수를 구함
		System.out.println(i+" 홀수"); // i값을 출력
		
		for(int i=2;i<=100;i=i+2) //i=2값으로 잡아 짝수를 출력
			System.out.println(i+" 짝수");
		
		for(int i=1;i<=100;i++) {
			if(i%2==0) {//2로 나눈 수의 나머지가 0이라면
				System.out.println(i +" 짝수입니다.");//짝수를 출력
			}else { System.out.println(i +" 홀수입니다.");//나머지가 0이 아니라면 홀수를 출력
			
		}
				
	}
}
}
