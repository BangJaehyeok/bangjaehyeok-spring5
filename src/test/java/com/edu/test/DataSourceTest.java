package com.edu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/*
 * 이 클래스는 오라클과 연동해서 CRUD를 테스트하는 클래스입니다.
 * 과장,팀장 급이 Junit CRUD까지 만들어서 팀원들과 공유한다.
 * @author 방재혁
 *
 */
//RunWith라는 인터페이스는 현재 클래스가 Junit실행 클래스라고 명시 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) //경로에서 별표두개는 모든 폴더를 명시(**), 별한개는 모든 파일명을명시(*), 해당 경로는 spring 폴더 안의 모든 xml문서를 불러오는 경로설정.
@WebAppConfiguration
public class DataSourceTest {
	//디버그용 로그 객체(=변수)생성
	private Logger logger = Logger.getLogger(DataSourceTest.class);
	//dataSource 객체는 데이터베이스객체를 pool로 저장해서 사용할때 DataSource 클래스를 사용(아래)
	@Inject //인젝트는 스프링에서 객체를 만드는 방법, 이전 자바에서는 new 키워드로 객체를 생성했음. 
	DataSource dataSource; //Inject로 객체를 만들면 메모리 관리를 스프링이 대신 해줌
	//Inject 자바8부터 지원, 이전 자바7에서는 @Autowired로 객체를 만들었음.
	
	@Test
	public void oldQueryTest() throws Exception {
		//스프링빈을 사용하지 않을때 예전 방식 : 코딩테스트때 스프링개발환경세팅이 안될수있다.
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","XE","apmsetup");
		logger.debug("데이터베이스 접속이 성공하였습니다. DB종류는 "+ connection.getMetaData().getDatabaseProductName());
		
		connection = null;//메모리 초기화
	}
	
	@Test
	public void dbConnectionTest() {
	//데이터베이스 커넥션테스트 : 설정은 root-context의 빈(스프링 클래스)을 이용
		try {
			Connection connection = dataSource.getConnection();
			logger.debug("데이터베이스 접속이 성공하였습니다. DB종류는 "+ connection.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			logger.debug("데이터베이스 접속이 실패하였습니다.");
			// e.printStackTrace();
		}
	}
	
	@Test
	public void junittest() {
		logger.debug("Junit테스트 시작입니다."); //디버그 상황일때만 나온다. debug를 info로 바꾸면 info상황에 나온다. 상황을 지정해줄수있다.
		//logger의 장점 : 조건에 따라서 출력을 조정할 수 있다.
	}
}