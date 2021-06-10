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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

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
	public void updateMember() throws Exception {
		//이 메서드는 회원정보를 수정(1개 레코드). jsp에 사용예정.
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail("admin@test.com");
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_ADMIN");
		memberVO.setPoint(100);
		memberVO.setUser_name("최고관리자");
		memberVO.setUser_pw(""); //입력하지않으면 업데이트에서 제외
		//메서드내 적용된 객체변수 생성
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//스프링5시큐리티 암호화 적용로직(아래)
		if((memberVO.getUser_pw()).length() > 0) {
			String userPwEncoder = passwordEncoder.encode(memberVO.getUser_pw());
			memberVO.setUser_pw("userPwEncoder");
		}
		memberVO.setUser_id("admin");//수정조회조건에 사용.
		memberService.updateMember(memberVO);
		// ======여기까지는 jsp에서 1명의 회원만 수정할때 사용하는 로직=========
		// ======이후부터는 모든 회원 중 시큐리티 암호화가 되지않는 사용자만 암호만 업데이트
		//아래 수정 call호출을 회원수만큼 반복해야한다.(아래)
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);//기본값으로 1페이지를 설정.
		pageVO.setPerPageNum(10); //UI하단의 페이지당 개수
		pageVO.setQueryPerPageNum(1000);//쿼리사용 페이지당 개수
		//MemberVO타입을 가진 리스트형 객체 List<MemberVO>
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		//향상된 for반복문(memberOne:listMember) {구현내용}
		for(MemberVO memberOne:listMember ) {//listMember객체 비워질때까지 반복
			//혹시 여러번 실행시켜 중복암호화시킬 수 있으므로 제외조건을 추가(아래)
			String rawPassword = memberOne.getUser_pw();
			if(rawPassword.length() < 10) {//원시암호데이터 길이가 10보다 작을때만 암호화로직 진입
				//memberOne객체(1개의 레코드)의 암호를 뽑아서 시큐리티로 암호화 시킨후 onePWEncoder변수입력
				String onePwEncoder = passwordEncoder.encode(rawPassword);
			memberOne.setUser_pw(onePwEncoder);
			memberService.updateMember(memberOne);//1명(admin만) 수정 -> 모든회원을 업데이트
			
			}
		}
		selectMember();
	}
	@Test
	public void readMember() throws Exception {
		//이 메서드는 회원상세보기(1개 레코드) jsp에 사용될 예정.
		MemberVO memberVO = new MemberVO();
		//100명중 1명을 보려면 고유키(기본키,주키)가 필요=user_id
		//String user_id = "admin";
		memberVO.setUser_id("admin");
		memberVO = memberService.readMember(memberVO.getUser_id());
	}
	
	@Test
	public void deleteMember() throws Exception {
		memberService.deleteMember("user_del");
		selectMember();
	}
	
	@Test
	public void insertMember() throws Exception {
		MemberVO memberVO = new MemberVO();
		//insert쿼리에 저장할 객체
		memberVO.setUser_id("user_del");
		memberVO.setUser_pw("1234");//스프링시큐리티 중 512바이트 암호화로 처리예정
		memberVO.setEmail("user@test.com");
		memberVO.setPoint(10);
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_USER");
		memberVO.setUser_name("삭제할사용자");
		memberService.insertMember(memberVO);
		selectMember();
	}
	
	@Test
	public void selectMember() throws Exception {
		//회원관리 테이블에서 더미로 입력한 데이터 100개의 레코드를 출력하는 메서드 테스트 => 회원관리목록이 출력
		//현재는 100명 검색기능, 페이징기능 여기서 구현. 1페이지에 10명씩 나오게 변경
		//현재 몇페이지, 검색어 임시저장공간 필요 -> DB에 페이징조건문, 검색조건문
		//변수를 2,3개 이상은 바로 String 변수로 처리하지 않고 VO만들어서 사용한다.
		//PageVO.java 클래스를 만들어서 여기에 페이징처리변수, 검색어 변수를 선언, GET/SET생성
		//PageVO만들기전 SQL쿼리로 가상으로 페이지를 한번 구현해보면서 필요한 변수를 만들어야한다.
		//PageVO 객체를 만들어서 가상으로 초기값을 입력합니다.
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);//기본값으로 1페이지를 설정.
		pageVO.setPerPageNum(10); //UI하단의 페이지당 개수
		pageVO.setQueryPerPageNum(1000);//쿼리사용 페이지당 개수
		//pageVO.setTotalCount(memberService.countMember());
		//모든 사용자를 출력하지 않고, 일부 사용자만 출력할때 아래 두줄이 필요
		//pageVO.setSearch_type("user_id"); //검색타입 all, user_id, user_name
		//pageVO.setSearch_keyword("user_del"); //검색어
		//위 setTotalCount 위치가 다른 설정보다 상단이면 에러발생, 왜냐면 calcPage()가 실행되는데, 실행시 위 3가지 변수값이 저장되어있어야지 계산메서드가 정상작동되기때문.
		//위 토탈카운트 변수값은 startPage, endPage계산에 필수입니다.
		//매퍼쿼리_DAO클래스_Service클래스_Junit(나중에 컨트롤러에서 작업) 이제 역순으로 작업진행
		// 더 진행하기 전에 현재 pageVO객체에는 어떤 값이 들어있는지 확인하고 사용.(아래)
		logger.info("디버그:"+pageVO.toString());
		
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		listMember.toString();
	}
	
	
	@Test
	public void oldQueryTest() throws Exception {
		//스프링빈을 사용하지 않을때 예전 방식 : 코딩테스트때 스프링개발환경세팅이 안될수있다.
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","XE","apmsetup");
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