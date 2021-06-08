package com.edu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;

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
	
	@Inject//MemberService서비스를 주입받아서 객체를 사용합니다.(아래)
	private IF_MemberService memberService;
	
	@Test
	public void selectMember() throws Exception {
		//회원관리 테이블에서 더미로 입력한 데이터 100개의 레코드를 출력하는 메서드 테스트 => 회원관리목록이 출력
		//현재는 100명 검색기능, 페이징기능 여기서 구현. 1페이지에 10명씩 나오게 변경
		//현재 몇페이지, 검색어 임시저장공간 필요 -> DB에 페이징조건문, 검색조건문
		//변수를 2,3개 이상은 바로 String 변수로 처리하지 않고 VO만들어서 사용한다.
		//PageVO.java 클래스를 만들어서 여기에 페이징처리변수, 검색어 변수를 선언, GET/SET생성
		//PageVO만들기전 SQL쿼리로 가상으로 페이지를 한번 구현해보면서 필요한 변수를 만들어야한다.
		List<MemberVO> listMember = memberService.selectMember();
		listMember.toString();
	}
	
	
	@Test
	public void oldQueryTest() throws Exception {
		//스프링빈을 사용하지 않을때 예전 방식 : 코딩테스트때 스프링개발환경세팅이 안될수있다.
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","XE2","apmsetup");
		logger.debug("데이터베이스 직접 접속이 성공하였습니다. DB종류는 "+ connection.getMetaData().getDatabaseProductName());
		//직접 쿼리를 날립니다. 날리기전 쿼리문장 객체생성 Statement 
		Statement stmt = connection.createStatement();
		//위 쿼리문장객체를 만드는 이유? 보안(SQL인젝션 공격을 방지)
		//stmt객체가 없으면, 개발자가 SQL인젝션 방지코딩을 일일히 넣어야한다.
		//insert쿼리문장을 만듬(아래)
		//옛날방식으로 더미데이터를 100개 입력합니다.
		/*
		 * for(int cnt=0;cnt<100;cnt++) { //error deptno 자리수가 두자리로 고정이 되어서 100은 입력시 에러
		 stmt.executeQuery("insert into dept02 values("+cnt+", '디자인부','경기도')");
		}
		 */
		 
		//인서트,업데이트,삭제시 sql디벨로퍼에서는 커밋이 필수지만, 외부 java클래스에서 insert할때는 자동커밋된다.
		//테이블에 입력되어있는 레코드셋를 select 쿼리stmt문장으로 가져옴(아래)
		ResultSet rs = stmt.executeQuery("select * from dept order by deptno");//20년전 작업방식
		//위에서 저장된 rs객체를 반복문으로 출력(아래)
		while(rs.next()) {//rs객체의 레코드가 없을때까지 반복
			logger.debug(rs.getString("deptno")+" "+rs.getString("dname")+
					" "+rs.getString("loc"));
		}
		stmt = null;//메모리 반환
		rs = null;//메모리 반환
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