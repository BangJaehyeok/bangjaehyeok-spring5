package kr.or.test;

import java.util.Scanner;

/*
 * 피보나치 수열 구하기
 */
public class Homework4 {

	public static void main(String[] args) {
		
		int num1,num2,sum;
		num1=0;
		num2=1;
		
		for(int i=0; i<=14; i++) {
			sum=num1+num2;
			System.out.println(sum);
			num1=num2;
			num2=sum;
		}
	}

}
