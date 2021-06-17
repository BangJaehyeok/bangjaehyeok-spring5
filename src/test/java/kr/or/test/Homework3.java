package kr.or.test;
/*
 * 1000이하의 소수 구하기
 */
public class Homework3 {

	public static void main(String[] args) {
		
		int i,j,namuji;
		for(i=2; i<=1000; i++) {
			namuji=0;
			for(j=2; j<i; j++)
				if(i%j==0) //1과 정수 사이 숫자중 하나라도 나눈 나머지가 0이면 소수가 아니다.
					namuji +=1;
			if(namuji==0) //namuji가 올라갔다면 소수가 아니다.
				System.out.println(i +" 는 소수입니다.");
		}

	}

}
