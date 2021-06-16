package com.edu.vo;
/*
 * 이 클래스는 게시판 생성관리 데이터를 입출력(임시저장)하는 기능의 클래스
 * 이 클래스를 이용해서 AOP(관점지향프로그래밍 실습)
 * @author 방재혁
 */
public class BoardTypeVO {
	//멤버변수 생성
	private String board_type;//PK 고유값, 식별자, 식별값, 식별키, 프라이머리키
	private String board_name;
	private Integer board_sun; //Integer는 null을 허용하기 때문에 int보다 넓은 개념이다.
	//nullPoint 예외처리 방지할수있다.
	//입출력가능한 메서드를 만든다.
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public Integer getBoard_sun() {
		return board_sun;
	}
	public void setBoard_sun(Integer board_sun) {
		this.board_sun = board_sun;
	}
	
}
