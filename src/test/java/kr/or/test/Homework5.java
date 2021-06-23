package kr.or.test;
/*
 * 최대공약수, 최소공배수
 */

import java.util.Scanner;

public class Homework5 {
		static int a, b;
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);//scanner객체설정
			System.out.println("a,b의 값을 입력해주세요");
			a = 20;
			b = 55;
			if(b > a) {
				int c = b;
				b = a;
				a = c;
			}
			int result = gcd(a, b);
			System.out.println("최대공약수 = "+ result);
			System.out.println("최소공배수 = "+ a*b / result);
		}
		public static int gcd(int a, int b) {
			if(a%b == 0) {
				return b;
			}
			return gcd(b, a%b);
		}
	}