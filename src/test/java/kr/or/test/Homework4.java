package kr.or.test;

/*
 * 피보나치 수열 구하기
 */
public class Homework4 {

	public static void main(String[] args) {
		
		int a,b,sum;
		a=0;
		b=1;
		
		for(int i=0; i<=14; i++) {
			sum=a+b;
			a=b;
			b=sum;
			System.out.println(sum);
		}
	}

}
