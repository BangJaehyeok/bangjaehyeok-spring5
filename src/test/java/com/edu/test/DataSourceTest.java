package com.edu.test;

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
	
	@Test
	public void junittest() {
		logger.debug("Junit테스트 시작입니다."); //디버그 상황일때만 나온다. debug를 info로 바꾸면 info상황에 나온다. 상황을 지정해줄수있다.
		//logger의 장점 : 조건에 따라서 출력을 조정할 수 있다.
	}
}
