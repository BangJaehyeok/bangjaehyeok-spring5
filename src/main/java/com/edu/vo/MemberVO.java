package com.edu.vo;

import java.util.Date;

/* @author 방재혁
 * 이 클래스는 DB에서 Model클래스로 입출력할때, Model에서 Service클래스로 입출력할때, 
 * Service클래스에서 Controller클래스로 데이터를 입출력할때, 
 * Controller에서 jsp로 데이터를 주고받을때, 사용하는 데이터 클래스입니다.
 */
public class MemberVO {
	//ERD를 보고 멤버변수를 만듭니다.
	private String user_id;
	private String user_pw;
	private String user_name;
	private String email;
	private Integer point;//int가 아니라 integer를 쓰는 이유는 클래스(참조형)데이터형은 Null을 허용하기 때문에 사용.
	private Boolean enabled;//불린형 boolean -> Boolean Null사용가능
	private String levels;
	private Date reg_date;
	private Date update_date;
	//책에서는 롬복lombok.jar을 사용해서 아래 내용을 생략가능합니다.
	//우리는 롬복을 사용하지 않고 Get/Set을 만들어서 사용합니다.
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

}
